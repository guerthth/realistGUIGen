package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.HashMap;
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
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel that represents definitions for a participation of a specific eventtype
 * (as Tab in the ParticipationPanel)
 * @author Thomas
 *
 */
public class ParticipationContentPanel extends VerticalPanel{

	// Logger
	private final static Logger logger = Logger.getLogger("ParticipationContentPanelLogger");
		
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// participationDTO the Panel is created for
	private EventtypeParticipationDTO participation;
	
	// Flextable for participating agents and additional attributes
	private FlexTable participationAttributeFlexTable = new FlexTable();
	private Label participatingAgentLabel = new Label("Participating Agent:");
	private ListBox participatingAgentListBox = new ListBox();
	
	private VerticalPanel participationAttributePanel = new VerticalPanel();
	
	private HorizontalPanel participationAttributeSeriesPanel = new HorizontalPanel();
	private Button participationAttributeSeriesAddButton = new Button("Add Participation");
	
	// Arraylist of possible agents that can participate in the eventtype
	ArrayList<AgentDTO> possibleAgents = new ArrayList<AgentDTO>();
	
	// Map keeping track of attributeIDs and corresponding TextBoxes
	private Map<AttributeDTO,CustomTextBox> participationAttributeLabelMap = new HashMap<AttributeDTO,CustomTextBox>();

	// EventtypeDTO object the participation belongs to
	private EventtypeDTO eventtypeDTO;
	
	// EventDTO object the participation belongs to
	private EventDTO eventDTO;
	
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
	
	private List<EventtypeParticipationHasAdditionalAttributeDTO> existingEventtypeParticipationHasAdditionalAttributeDTOs = new ArrayList<EventtypeParticipationHasAdditionalAttributeDTO>();
		
	// Tabpanel object the ParticipationContentPanel is added to
	private TabPanel participationTabPanel = null;
	
	// map keeping track of eventtypes and their corresponding attributes + textboxes
	private Map<EventDTO,Map<ParticipationDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeParticipationAttributeLabelMap; 
	//private Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeParticipationAttributeLabelMap; 
	
	// participationMap
	private Map <ParticipationDTO, Map<AttributeDTO, CustomTextBox>> participationMap;
	
	// participationDTO object for current participation
	private ParticipationDTO participationdto;
	
	
	/**
	 * Default Constructor
	 * @param participation
	 */
	public ParticipationContentPanel(EventtypeParticipationDTO participation, EventDTO eventdto , TabPanel participationTabPanel, 
										Map<EventDTO,Map<ParticipationDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeParticipationAttributeLabelMap, 
										Map<ParticipationDTO, Map<AttributeDTO, CustomTextBox>> participationMap, EventtypeDTO eventtypeDTO){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();		
				
		// set participation
		this.participation = participation;
		
		// set eventtypeDTO
		this.eventtypeDTO = eventtypeDTO;
		
		// set tabpanel
		this.participationTabPanel = participationTabPanel;
		
		// set eventtypeAttributeLabelMap
		this.eventtypeParticipationAttributeLabelMap = eventtypeParticipationAttributeLabelMap;
		
		// set eventdto
		this.eventDTO = eventdto;
		
		// set participationMap
		this.participationMap = participationMap;

		// set participationdto and add it to the list of current eventdto
		this.participationdto = new ParticipationDTO();
		this.eventDTO.getParticipations().add(participationdto);
		
		// populate ParticipationContentPanel
		populateParticipationContenPanel();
	}
	
	
	/**
	 * Method populating ParticipationContentPanel
	 */
	private void populateParticipationContenPanel(){
		
		// apply styles for the ParticipationContentPanel
		this.addStyleName("fullsizePanel");
		
		// apply styles for participationAttributePanel
		participationAttributePanel.addStyleName("fullsizePanel");
		
		// apply styles for participationAttributeFlexTable
		participationAttributeFlexTable.addStyleName("fullsizePanel");
		participationAttributeFlexTable.getColumnFormatter().addStyleName(0, "quartersizePanel");
		participationAttributeFlexTable.getColumnFormatter().addStyleName(1, "threequartersizePanel");
		participationAttributeFlexTable.setCellPadding(2);
		
		// apply style for participatingAgentListBox
		participatingAgentListBox.addStyleName("attributeTableTextBox");
		
		// ChangeHandler for participatingAgentListBox
		participatingAgentListBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event){
				
				// TODO: 
				Window.alert("Participating agent changed to : " + possibleAgents.get(participatingAgentListBox.getSelectedIndex()).getName());
				// if change occured, reset agent
				participationdto.setAgent(possibleAgents.get(participatingAgentListBox.getSelectedIndex()));
			}
		});
		
		// adding participating agent labels to the flextable
		participationAttributeFlexTable.setWidget(0, 0, participatingAgentLabel);
		participationAttributeFlexTable.setWidget(0, 1, participatingAgentListBox);
		
		
		// populate participatingAgentListBox
		populateParticipatingAgentListBox();
		
		// populate participationAttributeFlexTable
		populateParticipationAttributeFlexTable();
		
		// add the participationAttributeFlexTable to the participationAttributePanel
		participationAttributePanel.add(participationAttributeFlexTable);
		
		// define add series button functionality
		// listen for mouse events on the agentAddButton
		participationAttributeSeriesAddButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				Window.alert("add new participation");
				participationTabPanel.add(new ParticipationContentPanel(participation, eventDTO, participationTabPanel, eventtypeParticipationAttributeLabelMap, participationMap, eventtypeDTO), participation.getAgenttypeId());		
			}
		});
		
		// populate participationAttributeSeriesPanel and add styles
		participationAttributeSeriesPanel.add(participationAttributeSeriesAddButton);
		participationAttributeSeriesPanel.setVisible(false);
		participationAttributeSeriesPanel.addStyleName("introductionLabel");
		
		// add participationAttributeSeriesPanel to participationAttributePanel
		participationAttributePanel.add(participationAttributeSeriesPanel);
		// set the panel visible if participation is a series
		if(participation.getIsSeries()){
			participationAttributeSeriesPanel.setVisible(true);
		}
		
		// add the participationAttributePanel to the ParticipationContentPanel
		this.add(participationAttributePanel);
		
	}
	
	
	/**
	 * Method populating the participatingAgentListBox
	 */
	private void populateParticipatingAgentListBox(){
		
		// if no agenty exist in the REA DB, the values of the provideagent and
		// receiveagent listboxes are set to "No Agents found"
		if(reaDBEntryContainer.getExistingAgentDTOs().size() == 0){
			String noAgentsString = "No agents found";
			participatingAgentListBox.addItem(noAgentsString);
			return;
		}
				
		// list of possible Provide Agents
		possibleAgents .clear();
		
		for(AgentDTO adto : reaDBEntryContainer.getExistingAgentDTOs()){
			
			// currently agenttypes can only have one agenttye
			AgenttypeDTO agenttype = adto.getAgenttypes().iterator().next();
			
			// if agenttype in agentlist equals agenttype for current participation
			// add the agentDTO to the possibleAgents list and add the Id as Item in the participatingAgentListBox
			if(agenttypeFits(agenttype)){

				possibleAgents.add(adto);
				participatingAgentListBox.addItem(adto.getName());
			}
			
		}
		
		// if no agents exist for selection
		if (participatingAgentListBox.getItemCount() == 0){
			
			String noAgentsString = "No matches found";
			participatingAgentListBox.addItem(noAgentsString);
			participationdto.setAgent(null);
			
		} else {
			participationdto.setAgent(possibleAgents.get(participatingAgentListBox.getSelectedIndex()));
		}
			
	}
	
	
	/**
	 * Method populating the populateParticipationAttributeFlexTable with the additional attributes
	 */
	private void populateParticipationAttributeFlexTable(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<EventtypeParticipationHasAdditionalAttributeDTO>> callback = new AsyncCallback<List<EventtypeParticipationHasAdditionalAttributeDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getEventtypeParticipationsHasAdditionalAttribtes()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<EventtypeParticipationHasAdditionalAttributeDTO> result) {
				
				existingEventtypeParticipationHasAdditionalAttributeDTOs.clear();
				
				for(EventtypeParticipationHasAdditionalAttributeDTO etphaa : result){
					
					// only participation and policy attributes
					if(!etphaa.isReserveProperty()){
						// adding the EventtypeParticipationHasAdditionalAttributeDTO to the existingEventtypeParticipationHasAdditionalAttributeDTOs arrayList
						existingEventtypeParticipationHasAdditionalAttributeDTOs.add(etphaa);
						
						// attribute Label and CustomTextBox
						Label attributeLabel = new Label(etphaa.getAttribute().getName() + ":");
						CustomTextBox attributeTextBox = new CustomTextBox();
						
						// Validator for the CustomTextBox
						Validator attributeValidator;
						
						// setting Validations depending on the datatype
					 	if (etphaa.getAttribute().getDatatype().equals("INT") || etphaa.getAttribute().getDatatype().equals("DOUBLE")){
					 		
					  		attributeValidator = new NumericValidator();
					  	
					   	} else if (etphaa.getAttribute().getDatatype().equals("VARCHAR")){
							    		
					   		attributeValidator = new TextValidator(45);
							    	
					   	} else if (etphaa.getAttribute().getDatatype().equals("BOOLEAN")){
							    	
					   		attributeValidator = new BooleanValidator();
							    	
					   	} else {		
							    	
					   		attributeValidator = new DateValidator();
							    	
					   	}	
							    	
					   	// adding validator to attributeTextBox
					   	attributeTextBox.addValidator(attributeValidator);
									
						int row = participationAttributeFlexTable.getRowCount();

						participationAttributeFlexTable.setWidget(row, 0, attributeLabel);
						participationAttributeFlexTable.setWidget(row, 1, attributeTextBox);
												
						// adding entries to participationAttributeMap that stores all additional participation attributes
						participationAttributeLabelMap.put(etphaa.getAttribute(),attributeTextBox);
					}
					
					
				}
				
				// add ParticipationDTO with CustomTextBoxesto participationMap
				participationMap.put(participationdto, participationAttributeLabelMap);
				
				// add ParticipationMap to eventtypeParticipationAttributeLabelMap
				eventtypeParticipationAttributeLabelMap.put(eventDTO, participationMap);
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getEventtypeParticipationsHasAdditionalAttribtes(participation, callback);
		
	}
	
	
	/**
	 * Method checking if an agenttype (or one of its parent agenttypes) fits the type needed in the participation.
	 * @param agenttype
	 * @return agenttypefirst
	 */
	private boolean agenttypeFits(AgenttypeDTO agenttype){
		
		boolean agenttypefirst = false;
		
		AgenttypeDTO currentagenttype = agenttype;
		
		if(participation.getAgenttypeId().equals(currentagenttype.getId())){
			agenttypefirst = true;
		} else if(agenttype.getParentAgenttypeId() != null){
			// check agenttypes of parentagents
			currentagenttype = agenttype.getParentAgenttypeId();
			agenttypefirst = agenttypeFits(currentagenttype);
		} else {
			agenttypefirst = false;
		}
		
		return agenttypefirst;
	}
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
		
	}
}
