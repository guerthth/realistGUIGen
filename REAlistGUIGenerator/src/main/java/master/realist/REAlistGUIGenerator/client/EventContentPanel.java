package master.realist.REAlistGUIGenerator.client;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.BooleanValidator;
import master.realist.REAlistGUIGenerator.shared.DateValidator;
import master.realist.REAlistGUIGenerator.shared.NumericValidator;
import master.realist.REAlistGUIGenerator.shared.TextValidator;
import master.realist.REAlistGUIGenerator.shared.Validator;
import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


/**
 * Class representing the Panel for specific increment or decrement Events
 * @author Thomas
 *
 */
public class EventContentPanel extends VerticalPanel{
	
	// Logger
	private final static Logger logger = Logger.getLogger("EventContentPanelLogger");
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// eventAttributeDatePanel containing eventtypeAttributePanel and eventtypeDatePanel
	private HorizontalPanel eventAttributeDatePanel = new HorizontalPanel();
	private VerticalPanel eventtypeAttributePanel = new VerticalPanel();
	private HorizontalPanel eventtypeDatePanel = new HorizontalPanel();
	
	// Flextable for eventtypename and additional attributes
	private FlexTable eventtypeAttributeFlexTable = new FlexTable();
	
	// Eventname label and textbox
	private Label eventtypeNameLabel = new Label("Eventname:");
	private TextBox eventtypeActualNametextBox = new TextBox();
	
	// Provide and Receive Agent labels and listboxes
	private Label eventtypeProvideAgentLabel = new Label("ProvideAgent:");
	private ListBox eventtypeProvideAgentListBox = new ListBox();
	private Label eventtypeReceiveAgentLabel = new Label("ReceiveAgent:");
	private ListBox eventtypeReceiveAgentListBox = new ListBox();
	
	// Map keeping track of attributeIDs and corresponding TextBoxes
	private Map<String,TextBox> attributeLabelMap = new HashMap<String,TextBox>();
	
	// Eventtype startdate panel
	private VerticalPanel eventtypeStartdatePanel;
	private Label eventtypeStartdateLabel;
	private DatePicker eventtypeStartdatePicker;
		
	// Eventtype enddate panel
	private VerticalPanel eventtypeEnddatePanel;	
	private Label eventtypeEnddateLabel;
	private DatePicker eventtypeEnddatePicker;
		
	// startdate and enddate of the event
	private Date starteventdate;
	private Date endeventdate;
	
	// participationAndStockflowPanel
	private HorizontalPanel evenettypeParticipationAndStockflowPanel;
	private ParticipationPanel eventtypeParticipationPanel;
	private StockflowPanel eventtypeStockFlowPanel;
	
	// trimmed name of the eventtype of the event 
	private String trimmedEventTypeName;
	// sort of the event (decrement or increment)
	private String eventsort;
	// eventtypeDTO object the EventContentPanel is created for
	EventtypeDTO eventtypeDTO;
		
	
	/**
	 * Constructor
	 */
	public EventContentPanel(EventtypeDTO selectedEventDTO){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();		
		
		// specify the trimmed name of the event
		trimmedEventTypeName = selectedEventDTO.getName().substring(selectedEventDTO.getName().indexOf("_")+1);

		// Defining if the event is an increment or decrement event
		if(selectedEventDTO.getIsIncrement()){
			eventsort = "Increment";
		}else{
			eventsort = "Decrement";
		}
		

		// set the eventtypeDTO object the eventContentPanel is created for
		eventtypeDTO = selectedEventDTO; 
		
		populateEventContentPanel();
	}
	
	
	/**
	 * Method populating the EventContetnPanel
	 */
	private void populateEventContentPanel(){
		
		// apply style for the EventContentPanel
		this.addStyleName("fullsizePanel");
		
		// set spacing to 10
		this.setSpacing(10);
		
		// Definitions for eventtypeActualNametextBox
		eventtypeActualNametextBox.setText(eventtypeDTO.getName());
		eventtypeActualNametextBox.setReadOnly(true);
		eventtypeActualNametextBox.setVisibleLength(eventtypeDTO.getName().length());
			
		// Definitions for 
				// TODO:
		
		// adding event name labels to the flextable
		eventtypeAttributeFlexTable.setWidget(0, 0, eventtypeNameLabel);
		eventtypeAttributeFlexTable.setWidget(0, 1, eventtypeActualNametextBox);

		// adding Provide and Receive Agent labels and listboxes
		eventtypeAttributeFlexTable.setWidget(1, 0, eventtypeProvideAgentLabel);
		eventtypeAttributeFlexTable.setWidget(1, 1, eventtypeProvideAgentListBox);
		eventtypeAttributeFlexTable.setWidget(2, 0, eventtypeReceiveAgentLabel);
		eventtypeAttributeFlexTable.setWidget(2, 1, eventtypeReceiveAgentListBox);
		
		// get selection options for provide and receive agent listboxes
		populateProvideReceiveAgentListBoxes();
		
		// Generation of labels and textboxes for additional attributes of events
		populateEventtypeAttributeFlexTable();	
		
		// apply styles for the eventAttributeDatePanel
		eventAttributeDatePanel.addStyleName("fullsizePanel");
		
		// adding the flextable to the eventtypeAttributePanel
		eventtypeAttributePanel.add(eventtypeAttributeFlexTable);
		
		// creating the datepanels
		createDatePanels();
		
		// adding the eventtypeAttributePanel and the eventtypeDatePanel to the eventAttributeDatePanel
		eventAttributeDatePanel.add(eventtypeAttributePanel);
		eventAttributeDatePanel.add(eventtypeDatePanel);
		
		// adding the eventAttributeDatePanel to the EventContentPanel
		this.add(eventAttributeDatePanel);
		
		// creating evenettypeParticipationAndStockflowPanel panel
		createParticipationAndStockflowPanel();
		
		// adding the evenettypeParticipationAndStockflowPanel to the EventContentPanel
		this.add(evenettypeParticipationAndStockflowPanel);

	}
	
	
	/**
	 * Method that gets possible agents from the REA DB and populates the Provide and
	 * Receive agentlistboxes with it
	 */
	private void populateProvideReceiveAgentListBoxes(){	
		
		// if no agenty exist in the REA DB, the values of the provideagent and
		// receiveagent listboxes are set to "No Agents exist"
		if(reaDBEntryContainer.getExistingAgentDTOs().size() == 0){
			String noAgentsString = "No Agents exist";
			eventtypeReceiveAgentListBox.addItem(noAgentsString);
			eventtypeProvideAgentListBox.addItem(noAgentsString);
			return;
		}
		
		// if no participations exist for eventtypeDTO, set provideagent and
		// receiveagent listboxes to "No Participations exist"
		if(eventtypeDTO.getParticipations() == null){
			String noAgentsString = "No Participations exist";
			eventtypeReceiveAgentListBox.addItem(noAgentsString);
			eventtypeProvideAgentListBox.addItem(noAgentsString);
			return;
		}
		
		// list of possible Provide Agents
		ArrayList<AgentDTO> possibleProvideAgents = new ArrayList<AgentDTO>();
		
		// list of possible Receive Agents
		ArrayList<AgentDTO> possibleReceiveAgents = new ArrayList<AgentDTO>();
		
		// retrieve all agents from the READBEntryContainers existingAgentDTOs list where agenttype matches 
		// the agenttypes of the eventtypeDTO participations
		for(AgentDTO adto : reaDBEntryContainer.getExistingAgentDTOs()){
			
			// currently agenttypes can only have one agenttye
			AgenttypeDTO agenttype = adto.getAgenttypes().iterator().next();
			
			for(EventtypeParticipationDTO etpdto : eventtypeDTO.getParticipations()){
				
				// if agenttypeIds match
				if(etpdto.getAgenttypeId().equals(agenttype.getId())){
					
					
					if(agenttype.isExternal()){
						
						// for increment eventtypes, external agents are providing
						// for decrement eventtypes, external agents are receiving
						if(eventtypeDTO.getIsIncrement()){
							possibleProvideAgents.add(adto);
							// populate eventtypeProvideAgentListBox
							eventtypeProvideAgentListBox.addItem(adto.getName());
						} else {
							possibleReceiveAgents.add(adto);
							// populate eventtypeReceiveAgentListBox
							eventtypeReceiveAgentListBox.addItem(adto.getName());
						}
						
					} else{
						
						// for increment eventtypes, internal agents are receiving
						// for decrement eventtypes, internal agents are providing
						if(eventtypeDTO.getIsIncrement()){
							possibleReceiveAgents.add(adto);
							// populate eventtypeReceiveAgentListBox
							eventtypeReceiveAgentListBox.addItem(adto.getName());	
						} else {
							possibleProvideAgents.add(adto);
							// populate eventtypeProvideAgentListBox
							eventtypeProvideAgentListBox.addItem(adto.getName());
						}
						
					}
				}
			}
		}
		
		// if the listBoxes are still empty, set their text to "No matching agents found"
		if(eventtypeProvideAgentListBox.getItemCount() == 0){
			eventtypeProvideAgentListBox.addItem("No matching agents found");
		} 
		
		if(eventtypeReceiveAgentListBox.getItemCount() == 0){
			eventtypeReceiveAgentListBox.addItem("No matching agents found");
		}
		
		// set selected indexis
		eventtypeProvideAgentListBox.setSelectedIndex(0);
		eventtypeReceiveAgentListBox.setSelectedIndex(0);
		
	}
	
	
	/**
	 * Method populating the eventtypeAttributeFlexTable
	 * and generating a Map to keep track of attribute Ids and corresponding TextBoxes
	 */
	private void populateEventtypeAttributeFlexTable(){
		
		for(AttributeDTO adto : eventtypeDTO.getAttributes()){
			
			
			
			// attribute Label and CustomTextBox
			Label attributeLabel = new Label(adto.getName());
			CustomTextBox attributeTextBox = new CustomTextBox();
			
			// Validator for the CustomTextBox
			Validator attributeValidator;
			
			// setting Validations depending on the datatype
	    	if (adto.getDatatype().equals("INT") || adto.getDatatype().equals("DOUBLE")){
	    		
	    		attributeValidator = new NumericValidator();
	    	
	    	} else if (adto.getDatatype().equals("VARCHAR")){
	    		
	    		attributeValidator = new TextValidator(45);
	    	
	    	} else if (adto.getDatatype().equals("BOOLEAN")){
	    	
	    		attributeValidator = new BooleanValidator();
	    	
	    	} else {		
	    	
	    		attributeValidator = new DateValidator();
	    	
	    	}	
	    	
	    	// adding validator to attributeTextBox
	    	attributeTextBox.addValidator(attributeValidator);
			
			int row = eventtypeAttributeFlexTable.getRowCount();
			eventtypeAttributeFlexTable.setWidget(row, 0, attributeLabel);
			eventtypeAttributeFlexTable.setWidget(row, 1, attributeTextBox);
						
			// adding entries to eventAttributeMap that stores all additional eventtype attributes
			attributeLabelMap.put(adto.getId(),attributeTextBox);
		}
	}
	
	
	/**
	 * Method adding the datepanels to the EventContentPanel
	 */
	private void createDatePanels(){
		
		// Eventtype startdate panel
		eventtypeStartdatePanel = new VerticalPanel();
		eventtypeStartdateLabel = new Label("Startdate:");
		eventtypeStartdatePicker = new DatePicker();
		// add style to startdate panel
		eventtypeStartdatePanel.addStyleName("datePickerPanel");
			    
		eventtypeStartdatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				// TODO: 
				Window.alert("new start date: " + dateString);
				starteventdate = date;
			}
		});
			    
		// Set the default value for the dualitytype datepicker
		eventtypeStartdatePicker.setValue(new Date(), true);
			 			
		// adding startdatelabel and startdatepicker to eventtypeStartdatePanel
		eventtypeStartdatePanel.add(eventtypeStartdateLabel);
		eventtypeStartdatePanel.add(eventtypeStartdatePicker);
			 	
		// Eventtype enddate panel
		eventtypeEnddatePanel = new VerticalPanel();	
		eventtypeEnddateLabel = new Label("Enddate:");		
		eventtypeEnddatePicker = new DatePicker();
		// add style to enddate panel
		eventtypeEnddatePanel.addStyleName("datePickerPanel");
			 			
		eventtypeEnddatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				// TODO: 
				Window.alert("new end date: " + dateString);
				endeventdate = date;
			}
		});
			 	
		// Set the default value for the dualitytype datepicker
		eventtypeEnddatePicker.setValue(new Date(), true);	
		eventtypeEnddatePanel.add(eventtypeEnddateLabel);
		eventtypeEnddatePanel.add(eventtypeEnddatePicker);	
		
		// adding the datepanels to the eventtypeDatePanel
		eventtypeDatePanel.add(eventtypeStartdatePanel);
		eventtypeDatePanel.add(eventtypeEnddatePanel);

		
		// TODO:
		/**
		// create an eventDTO object that will be saved to the DB
		EventDTO eventdto = new EventDTO();
		eventdto.setEventtype(eventtypeDTO);
		eventdto.setDateEnd(endeventdate);
		eventdto.setDateStart(starteventdate);
				
		// add the eventDTO to the list of eventdtos that should be saved
		saveEventDTOList.add(eventdto);
		**/
	}
	
	
	/**
	 * Method creating the ParticipationAndStockflowPanel
	 */
	private void createParticipationAndStockflowPanel(){
		
		// create evenettypeParticipationAndStockflowPanel + apply styles
		evenettypeParticipationAndStockflowPanel = new HorizontalPanel();
		evenettypeParticipationAndStockflowPanel.addStyleName("fullsizePanel");
		
		// creating the 2 Panels
		eventtypeParticipationPanel = new ParticipationPanel(eventtypeDTO);
		eventtypeStockFlowPanel = new StockflowPanel(eventtypeDTO);
		
		// adding the Panels to the evenettypeParticipationAndStockflowPanel
		evenettypeParticipationAndStockflowPanel.add(eventtypeParticipationPanel);
		evenettypeParticipationAndStockflowPanel.add(eventtypeStockFlowPanel);
	}
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
		
	}
	
}
