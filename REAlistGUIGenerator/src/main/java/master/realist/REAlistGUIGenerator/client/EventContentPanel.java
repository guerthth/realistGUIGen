package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;

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
	
	// panels and flextables
	private HorizontalPanel horizontalDataPanel;
	private FlexTable eventtypeMainFlexTable;
	private VerticalPanel eventtypeAttributePanel;
	private Label eventDescriptionLabel;
	private FlexTable eventtypeAttributeFlexTable;
	
	// Eventname label
	private Label eventtypeNameLabel = new Label("Eventname:");
	private TextBox eventtypeActualNametextBox = new TextBox();
	
	// eventtypeDTO object the eventContentPanel is created for
	private EventtypeDTO eventtypeDTO;
	
	// type of eventtypeDTO (Increment or Decrement)
	private String eventsort;
	
	// Trimmed name of eventtypeDTO
	private String trimmedEventTypeName;
	
	// Map keeping track of attributeIDs and corresponding TextBoxes
	private Map<String,TextBox> attributeLabelMap = new HashMap<String,TextBox>();
	
	// Map setting the eventtypeDTO in relation to the attributeLabelMap
	private Map<String, Map<String,TextBox>> eventAttributeMap = new HashMap<String, Map<String,TextBox>>();
		
	// Eventtype startdate panel
	VerticalPanel eventtypeStartdatePanel;
	Label eventtypeStartdateLabel;
	DatePicker eventtypeStartdatePicker;
	
	// Eventtype enddate panel
	VerticalPanel eventtypeEnddatePanel;	
 	Label eventtypeEnddateLabel;
 	DatePicker eventtypeEnddatePicker;
	
	// startdate and enddate of the event
	private Date starteventdate;
	private Date endeventdate;
	
	// List of eventDTO objects that should be saved to the DB
	private List<EventDTO> saveEventDTOList = new ArrayList<EventDTO>();
	
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
		
	
	/**
	 * Constructor
	 */
	public EventContentPanel(EventtypeDTO eventtype){		
		
		// specify the trimmed name of the event
		trimmedEventTypeName = eventtype.getName().substring(eventtype.getName().indexOf("_")+1);
		
		// Defining if the event is an increment or decrement event
		if(eventtype.getIsIncrement()){
			eventsort = "Increment";
		}else{
			eventsort = "Decrement";
		}
		
		// set the eventtypeDTO object the eventContentPanel is created for
		eventtypeDTO = eventtype; 
		
		populateEventContentPanel();
	}
	
	/**
	 * Method populating the EventContentPanel
	 */
	private void populateEventContentPanel(){
		
		// set spacing to 10
		this.setSpacing(10);
		
		// Horizontal panel for attribute flextable and date flextable
		horizontalDataPanel = new HorizontalPanel();
		
		// Flextable for the eventtype main panel
		eventtypeMainFlexTable = new FlexTable();
		
		// Eventtype attribute panel
		eventtypeAttributePanel = new VerticalPanel();
		
		// Label for general Eventtypedefinitiontitle
		eventDescriptionLabel = new Label(eventsort + " event: " + trimmedEventTypeName);
		
		// Flextable for eventtypename and additional attributes
		eventtypeAttributeFlexTable = new FlexTable();
		
		// Definitions for eventtypeActualNametextBox
		eventtypeActualNametextBox.setText(eventtypeDTO.getName());
		eventtypeActualNametextBox.setReadOnly(true);
		eventtypeActualNametextBox.setVisibleLength(eventtypeDTO.getName().length());
		
		// adding event name labels to the flextable
		eventtypeAttributeFlexTable.setWidget(0, 0, eventtypeNameLabel);
		eventtypeAttributeFlexTable.setWidget(0, 1, eventtypeActualNametextBox);
		
		// Generation of labels and textboxes for additional attributes of events
		populateEventtypeAttributeFlexTable();
		
		//  Add entry to the eventAttributeMap to set the eventtypeDTOs in relation to the attributeLabelMap
		eventAttributeMap.put(eventtypeDTO.getId(), attributeLabelMap);
		
		// adding the flextable to the eventtypeAttributePanel
		eventtypeAttributePanel.add(eventtypeAttributeFlexTable);
		
		// creating the datepanels
		createDatePanels();
		
		// Event save button
		Button eventtypeSaveButton = new Button("Save");
								
		eventtypeSaveButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				// TODO:
				//saveDuality(currentDualityType);
			}
		});	
		
		// adding the eventtypeAttributePanel and eventtypeMainFlexTable to the horizontalDataPanel
		horizontalDataPanel.add(eventtypeAttributePanel);
		horizontalDataPanel.add(eventtypeMainFlexTable);
			
		// adding the eventDescriptionLabel, horizontalDataPanel, and eventtypeSaveButton to the EventContentPanel
		this.add(eventDescriptionLabel);
		this.add(horizontalDataPanel);
		this.add(eventtypeSaveButton);		
	}
	
	
	/**
	 * Method populating the eventtypeAttributeFlexTable
	 * and generating a Map to keep track of attribute Ids and corresponding TextBoxes
	 */
	private void populateEventtypeAttributeFlexTable(){
		
		for(AttributeDTO adto : eventtypeDTO.getAttributes()){
			Label attributenameLabel = new Label(adto.getName());
			TextBox attributenameTextBox = new TextBox();
			int row = eventtypeAttributeFlexTable.getRowCount();
			eventtypeAttributeFlexTable.setWidget(row, 0, attributenameLabel);
			eventtypeAttributeFlexTable.setWidget(row, 1, attributenameTextBox);
						
			// adding entries to eventAttributeMap that stores all additional eventtype attributes
			attributeLabelMap.put(adto.getId(),attributenameTextBox);
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
			    
		eventtypeStartdatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat().format(date);
				// TODO:
				Window.alert("Startdate: " + dateString);
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
			 			
		eventtypeEnddatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat().format(date);
				// TODO:
				Window.alert("Enddate: " + dateString);
				endeventdate = date;
			}
		});
			 	
		// Set the default value for the dualitytype datepicker
		eventtypeEnddatePicker.setValue(new Date(), true);	
		eventtypeEnddatePanel.add(eventtypeEnddateLabel);
		eventtypeEnddatePanel.add(eventtypeEnddatePicker);	
		
		// adding the datepanels to the eventtypeMainFlexTable
		eventtypeMainFlexTable.setWidget(0, 0, eventtypeStartdatePanel);
		eventtypeMainFlexTable.setWidget(0, 1, eventtypeEnddatePanel);
		
		// create an eventDTO object that will be saved to the DB
		EventDTO eventdto = new EventDTO();
		eventdto.setEventtype(eventtypeDTO);
		eventdto.setDateEnd(endeventdate);
		eventdto.setDateStart(starteventdate);
				
		// add the eventDTO to the list of eventdtos that should be saved
		saveEventDTOList.add(eventdto);
		
	}
	
	/**
	 * Saving the specified duality to the database
	 */
	private void saveDuality(DualitytypeDTO currentDualityType){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<DualityDTO> callback = new AsyncCallback<DualityDTO>() {
	      public void onFailure(Throwable caught) {
	    	  logREADBRPCFailure("saveDuality()");
	    	  caught.printStackTrace();
	      }

	      public void onSuccess(DualityDTO result) {
	    	  
	    	  for(EventDTO eventdto : saveEventDTOList){
	    		  	eventdto.setDuality(result);
	  	    		saveEvent(eventdto);
	  	      }
	    	  
	      }
	    };

	    // create the DualityDTO object that should be saved in the database
	    DualityDTO savedDuality = new DualityDTO();
	    savedDuality.setDate(new Date());
	    savedDuality.setDualitytype(currentDualityType);
	    
	    // Make the call to the stock price service.
	    reaDBSvc.saveDuality(savedDuality,callback);

	}
	
	
	/**
	 * Saving the included events to the database
	 */
	private void saveEvent(EventDTO savedEventdto){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<EventDTO> callback = new AsyncCallback<EventDTO>() {
	      public void onFailure(Throwable caught) {
	    	  logREADBRPCFailure("saveEvent()");
	    	  caught.printStackTrace();
	      }

	      public void onSuccess(EventDTO result) {
	    	  
	    	  Window.alert("Event " + result.getEventtype().getId() + " has been saved to the DB with id " + result.getId());
	    	  
	    	  // TODO: Hier mit dem EventDTO object (samt ID aus DB entry) weiterarbeiten 
	    	  //und event_hasadditionalattributevalues entries erzeugen
	    	  // Es wird eine Hashmap folgender Form benötigt Map<String, Map<String,TextBox>>
	    	  /**
	    	  Map<String,TextBox> retrievedMap = eventAttributeMap.get(result.getEventtype().getId());
	    	  for(AttributeDTO adto : result.getEventtype().getAttributes()){
	    		  
	    		  String attributeLabelText = retrievedMap.get(adto.getId()).getText();
	    		  EventHasAdditionalattributevalueDTO attrvalue = new EventHasAdditionalattributevalueDTO();
	    		  attrvalue.setAttribute(adto);
	    		  attrvalue.setEvent(result);
	    		  attrvalue.setId(new EventHasAdditionalattributevalueIdDTO(result.getId(),adto.getId()));
	    		  
	    		  // depending on the datatype the labltext is set 
	    		  if(adto.getDatatype().matches("INT") || adto.getDatatype().matches("DOUBLE")){
	    			  attrvalue.setTextual(attributeLabelText);
	    			  attrvalue.setBoolean_(false);
	    			  attrvalue.setDatetime(new Date());
	    			  attrvalue.setNumeric(1.);
	    			  //attrvalue.setNumeric(Double.parseDouble(attributeLabelText));
	    		  
	    		  }else if(adto.getDatatype().matches("BOOLEAN")){
	    			  attrvalue.setTextual(attributeLabelText);
	    			  attrvalue.setBoolean_(false);
	    			  attrvalue.setDatetime(new Date());
	    			  attrvalue.setNumeric(1.);
	    			  //attrvalue.setBoolean_(Boolean.parseBoolean(attributeLabelText));
	    		  
	    		  }else if(adto.getDatatype().matches("VARCHAR")){
	    			  
	    			  attrvalue.setTextual(attributeLabelText);
	    			  attrvalue.setBoolean_(false);
	    			  attrvalue.setDatetime(new Date());
	    			  attrvalue.setNumeric(1.);
	    			  
	    		  }else{
	    			  attrvalue.setTextual(attributeLabelText);
	    			  attrvalue.setBoolean_(false);
	    			  attrvalue.setDatetime(new Date());
	    			  attrvalue.setNumeric(1.);
	    			 
	    			  try {
						attrvalue.setDatetime(formatter.parse(attributeLabelText));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			  
	    		  }
	      
	    		  
	    		  
	    		  //saveAdditionalEventAttributes(attrvalue);
	    	  }**/
	      }
	    };
	    
	    // Make the call to the stock price service.
	    reaDBSvc.saveEvent(savedEventdto, callback);
	}
	
	
	/**
	 * Method sacvind the additional attributes of the event
	 * @param attrvalue
	 */
	private void saveAdditionalEventAttributes(EventHasAdditionalattributevalueDTO attrvalue){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<EventHasAdditionalattributevalueDTO> callback = new AsyncCallback<EventHasAdditionalattributevalueDTO>() {
	      public void onFailure(Throwable caught) {
	    	  logREADBRPCFailure("saveEventHasAdditionalattributevalue()");
	    	  caught.printStackTrace();
	      }

	      public void onSuccess(EventHasAdditionalattributevalueDTO result) {
	    	  
	    	  Window.alert("Additional attributes for " + result.getId().getEventId() + " saved");
	    	  
	      }
	    };
	    
	    // Make the call to the stock price service.
	    reaDBSvc.saveEventHasAdditionalattributevalue(attrvalue, callback);
	}
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
	
}
