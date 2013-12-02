package master.realist.REAlistGUIGenerator.client;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class REAlistGUIGenerator implements EntryPoint {

	// Logger
	private final static Logger logger = Logger.getLogger("REAlistGUIGeneratorLogger");
	
	// main panel
	private VerticalPanel mainPanel = new VerticalPanel();  

	// stack panel containing sections for entity administration and business case creation
	private StackPanel stackPanel = new StackPanel();
	private TabPanel administrationTabPanel = new TabPanel();
	
	// Dualitytype panel + ArrayList
	private VerticalPanel dualitytypePanel = new VerticalPanel();
	private FlexTable dualitytypeFlexTable = new FlexTable();
	private Label dualitytypeLabel = new Label("Choose Dualitytype: ");
	private ListBox dualitytypeListBox = new ListBox();
	private List<DualitytypeDTO> existingDualitytypeDTOs = new ArrayList<DualitytypeDTO>();
	private Button dualitytypeSelectionButton = new Button("Select");
	
	// Panel for Dualitystatus Administations + Dualitystatus ArrayList
	private VerticalPanel statusSelectionPanel = new VerticalPanel();
	private Label statusSelectionIntroductionLabel = new Label("Add new status or update existing one");
	private HorizontalPanel tableAndAddEditPanel = new HorizontalPanel();
	private FlexTable statusSelectionFlexTable = new FlexTable();
	private VerticalPanel dualityStatusAddEditPanel = new VerticalPanel();
	private Label dualityStatusAddEditHeaderLabel = new Label("Add or edit dualitystatus");
	private FlexTable dualityStatusAddEditFlexTable = new FlexTable();
	private Label dualityStatusIdLabel = new Label("StatusId:");
	private TextBox dualityStatusIdTextTextBox = new TextBox();
	private Label dualityStatusStatusCodeLabel = new Label("StatusCode:");
	private TextBox dualityStatusStatusCodeTextBox = new TextBox();
	private Button dualityStatusOkButton = new Button("Ok");
	private HorizontalPanel dualityStatusChooseAddPanel = new HorizontalPanel();
	private Button dualityStatusAddButton = new Button("Add");	
	private ArrayList<DualityStatusDTO> existingDualityStatusDTOs = new ArrayList<DualityStatusDTO>();
	
	// flag that specifies if a user wants to save or update
	private boolean saveActionState = true;
	// DualityStatusDTO object that should be updated
	private DualityStatusDTO updateObject = new DualityStatusDTO();
	// Updated DualityStatusDTO object
	private DualityStatusDTO updatedObject;
	
	// Panel for Agent Administration + Agent ArrayList
	private VerticalPanel agentSelectionPanel = new VerticalPanel();
	private Label agentSelectionIntroductionLabel = new Label("Add new agent or update existing one");
	private HorizontalPanel agentTableAndAddEditPanel = new HorizontalPanel();
	private FlexTable agentSelectionFlexTable = new FlexTable();
	private VerticalPanel agentAddEditPanel = new VerticalPanel();
	private Label agentAddEditHeaderLabel = new Label("Add or edit agent");
	private FlexTable agentAddEditFlexTable = new FlexTable();
	private Label agentIdLabel = new Label("AgentId:");
	private TextBox agentIdTextTextBox = new TextBox();
	private Label agentNameLabel = new Label("Agentname:");
	private TextBox agentNameTextBox = new TextBox();
	private Label agentTypeLabel = new Label("Agenttype:");
	private ListBox agentTypeListBox = new ListBox();
	private Button agentOkButton = new Button("Ok");
	private HorizontalPanel agentAddPanel = new HorizontalPanel();
	private Button agentAddButton = new Button("Add");	
	private ArrayList<AgentDTO> existingAgentDTOs = new ArrayList<AgentDTO>();
	
	// Arraylist for existing agenttypes
	private ArrayList<AgenttypeDTO> existingAgenttypeDTOs = new ArrayList<AgenttypeDTO>();
	
	// flag that specifies if a user wants to save or update
	private boolean agentSaveActionState = true;
	// DualityStatusDTO object that should be updated
	private AgentDTO agentUpdateObject;
	
	// Eventtype Tab Panel
	private TabPanel eventypeSetTabPanel;

	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
	
	// Current Dualitytype_Id
	private DualitytypeDTO currentDualityType;
	
	// startdate and enddate of the event
	private Date starteventdate = null;
	private Date endeventdate = null;
	
	// List of eventDTO objects that should be saved to the DB
	private List<EventDTO> saveEventDTOList = new ArrayList<EventDTO>();
	
	// Map of the additional event attribute textboxes with corresponding attribute_id strings
	private Map<String, Map<String,TextBox>> eventAttributeMap = new HashMap<String, Map<String,TextBox>>();
	
	// DateFormatter
	//private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * On module load method
	 */
	public void onModuleLoad() {
		
		// initializing logger to INFO level
		logger.setLevel(Level.INFO);
		
		// Assemble Dualitytype panel
		dualitytypeFlexTable.setWidget(0, 0, dualitytypeLabel);
		dualitytypeFlexTable.setWidget(0, 1, dualitytypeListBox);
		dualitytypeFlexTable.setWidget(0, 2, dualitytypeSelectionButton);
		dualitytypeFlexTable.setCellSpacing(5);

		// listen for mouse events on the dualitytypeSelectionButton
		dualitytypeSelectionButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
			   loadDualitytypeContent(dualitytypeListBox.getSelectedIndex());
			}
		});
		
		// Assembel administration tabpanel
		administrationTabPanel.add(loadDualityStatusSection(),"Dualitystatus");
		administrationTabPanel.add(loadAgentSection(),"Agents");
		administrationTabPanel.selectTab(0);
		administrationTabPanel.setTitle("Administrate dualitystatus, resources, or agents");
		
		// Assemble the stack panel
		stackPanel.add(administrationTabPanel, "Entity Administration");
		stackPanel.add(loadDualityTypes(),"Business Case Creation");
		stackPanel.showStack(0);
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("readb").add(mainPanel);	
		
		// assemble Main panel
		mainPanel.add(stackPanel);
		
	}
	
	
	/**
	 * Method loading the existing dualitytypes in the GUI
	 */
	private Panel loadDualityTypes(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<List<DualitytypeDTO>> callback = new AsyncCallback<List<DualitytypeDTO>>() {
	      public void onFailure(Throwable caught) {
	    	  logREADBRPCFailure("getDualitytypes()");
	    	  caught.printStackTrace();
	      }

	      public void onSuccess(List<DualitytypeDTO> result) {
	        
	    	  // populate dualitytypeListBox
	    	  String dualitytypeDisplayName;
	    	  for(DualitytypeDTO ddto : result){
	    		  // not the autogenerated name, but the simplyfied form should be displayed
	    		  int trimIndex = ddto.getName().indexOf("_Duality");
	    		  dualitytypeDisplayName = ddto.getName().substring(0,trimIndex);
	    		  dualitytypeListBox.addItem(dualitytypeDisplayName);
	    		  existingDualitytypeDTOs.add(ddto);
	    	  }
	    	  
	    	  dualitytypeListBox.setVisibleItemCount(1);
	    	  
	    	  // assemble dualitytypePanel
	    	  dualitytypePanel.add(dualitytypeFlexTable);  
	    	  
	      }
	    };

	    // Make the call to the stock price service.
	    reaDBSvc.getDualitytypes(callback);
	    
	    return dualitytypePanel;
	}
	
	
	/**
	 * Loading Resource, Event, Agentsections
	 */
	private void loadDualitytypeContent(int selectedIndex){
		
		// since a new Dualitytype content is loaded the arraylist for
		// eventdtos to save has to be refreshed
		saveEventDTOList.clear();
		// the same happens for additional attributes
		eventAttributeMap.clear();
		
		
		//retrieve selected DualitytypeDTO object
		DualitytypeDTO selectedDualityType = existingDualitytypeDTOs.get(selectedIndex);
		currentDualityType = selectedDualityType;
		//Window.alert("Content of Dualitytype " + selectedDualityType.getName() + " is now loading");
		
		// removing the tabpanel from the main panel if it already exists to avoid adding it several times
		if(eventypeSetTabPanel != null){
			mainPanel.remove(eventypeSetTabPanel);
		}
		
		
		// Tab Panel for Eventtypes of a specific Duality
		eventypeSetTabPanel = new TabPanel();
		
		// retrieve all eventtypes that occur in the specified duality and save them to
		// arraylists for increments and decrements
		ArrayList<EventtypeDTO> incrementEventtypes = new ArrayList<EventtypeDTO>();
		ArrayList<EventtypeDTO> decrementEventtypes = new ArrayList<EventtypeDTO>();
		
		for(EventtypeDTO etdto : selectedDualityType.getEventtypes()){
			
			if(etdto.getIsIncrement()){
				incrementEventtypes.add(etdto);
			}else{
				decrementEventtypes.add(etdto);
			}
		}
		
		// adding tabs for increment and decrement event sets
		eventypeSetTabPanel.add(createAndFormatEventsetPanel(incrementEventtypes),"Increment");
		eventypeSetTabPanel.add(createAndFormatEventsetPanel(decrementEventtypes),"Decrement");	
		
		// preselecting the first tab of the panel
		eventypeSetTabPanel.selectTab(0);
		
		// add the tabpanel to the main panel
		mainPanel.add(eventypeSetTabPanel);

	}
	
	
	/**
	 * Creating and formating Panel for the increment and decrement eventsets
	 * @return
	 */
	private Panel createAndFormatEventsetPanel(ArrayList<EventtypeDTO> eventtypes){
		
		Panel eventSetPanel = new VerticalPanel();
		
		// if more than one event is included, an additional tabpanel is added
		// of only one event is in the list, no tabpanel is needed
		if(eventtypes.size()>1){
			
			TabPanel eventtypeTabPanel = new TabPanel();
			
			for(EventtypeDTO etdto : eventtypes){
				
				String trimmedEventtypeName = etdto.getName().substring(etdto.getName().indexOf("_")+1);
				eventtypeTabPanel.add(createAndFormatEventtypeContentPanel(etdto),trimmedEventtypeName);
			}
			
			eventSetPanel.add(eventtypeTabPanel);
			
		} else{
			
			eventSetPanel = createAndFormatEventtypeContentPanel(eventtypes.get(0));
		}
		
		return eventSetPanel;
	}
	
	
	/**
	 * Creating vertical Panel for a specific tab of the eventypeTabPanel
	 * @param etdto
	 * @return created eventtypePanel
	 */
	private Panel createAndFormatEventtypeContentPanel(EventtypeDTO etdto){
		
		// Eventtype main panel
		VerticalPanel eventtypeMainPanel = new VerticalPanel();
		eventtypeMainPanel.setSpacing(10);
		
		// Horizontal panel for attribute flextable and date flextable
		HorizontalPanel horizontalDataPanel = new HorizontalPanel();

		// Flextable for the eventtype main panel
		FlexTable eventtypeMainFlexTable = new FlexTable();
		
		// Eventtype attribute panel
		VerticalPanel eventtypeAttributePanel = new VerticalPanel();

			// Defining wheter the event is an increment or decrement event
			String eventsort = "Decrement";
			String trimmedEventTypeName = etdto.getName().substring(etdto.getName().indexOf("_")+1);
			if(etdto.getIsIncrement()){
				eventsort = "Increment";
			}
			
			// Label for general Eventtypedefinitiontitle
			Label eventDescriptionLabel = new Label(eventsort + " event: " + trimmedEventTypeName);

			// Flextable for eventtypename and additional attributes
			FlexTable eventtypeAttributeFlexTable = new FlexTable();
			
			// Eventname label
			Label eventtypeNameLabel = new Label("Eventname:");
			TextBox eventtypeActualNametextBox = new TextBox();
			eventtypeActualNametextBox.setText(etdto.getName());
			eventtypeActualNametextBox.setReadOnly(true);
			eventtypeActualNametextBox.setVisibleLength(etdto.getName().length());
			
			// adding event name labels to the flextable
			eventtypeAttributeFlexTable.setWidget(0, 0, eventtypeNameLabel);
			eventtypeAttributeFlexTable.setWidget(0, 1, eventtypeActualNametextBox);
			
			// Generation of labels and textboxes for additional attributes of events
			Map<String,TextBox> attributeLabelMap = new HashMap<String,TextBox>();
			
			for(AttributeDTO adto : etdto.getAttributes()){
				Label attributenameLabel = new Label(adto.getName());
				TextBox attributenameTextBox = new TextBox();
				int row = eventtypeAttributeFlexTable.getRowCount();
				eventtypeAttributeFlexTable.setWidget(row, 0, attributenameLabel);
				eventtypeAttributeFlexTable.setWidget(row, 1, attributenameTextBox);
				
				// adding entries to eventAttributeMap that stores all additional eventtype attributes
				attributeLabelMap.put(adto.getId(),attributenameTextBox);
			}
			
			eventAttributeMap.put(etdto.getId(), attributeLabelMap);
			
			// adding the flextable to the eventtypeAttributePanel
			eventtypeAttributePanel.add(eventtypeAttributeFlexTable);
		
		// Eventtype startdate panel
		VerticalPanel eventtypeStartdatePanel = new VerticalPanel();
		
			Label eventtypeStartdateLabel = new Label("Startdate:");
			
		    DatePicker eventtypeStartdatePicker = new DatePicker();
		    
		    eventtypeStartdatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			    public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = event.getValue();
			        //String dateString = DateTimeFormat.getMediumDateFormat().format(date);
			        starteventdate = date;
			    }
			});
	
			// Set the default value for the dualitytype datepicker
			eventtypeStartdatePicker.setValue(new Date(), true);
			
			eventtypeStartdatePanel.add(eventtypeStartdateLabel);
			eventtypeStartdatePanel.add(eventtypeStartdatePicker);
		
		// Eventtype enddate panel
		VerticalPanel eventtypeEnddatePanel = new VerticalPanel();
		
			Label eventtypeEnddateLabel = new Label("Enddate:");
			
			DatePicker eventtypeEnddatePicker = new DatePicker();
			
			eventtypeEnddatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			    public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = event.getValue();
			        //String dateString = DateTimeFormat.getMediumDateFormat().format(date);
			        endeventdate = date;
			    }
			});
	
			// Set the default value for the dualitytype datepicker
			eventtypeEnddatePicker.setValue(new Date(), true);
			
			eventtypeEnddatePanel.add(eventtypeEnddateLabel);
			eventtypeEnddatePanel.add(eventtypeEnddatePicker);

		// Event save button
		Button eventtypeSaveButton = new Button("Save");
						
		eventtypeSaveButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				//Window.alert("Saving eventtype " + currentEventtype.getName() );
				saveDuality(currentDualityType);
			}
		});	
			
		// adding the eventtypedefinition label to the mainpanel
		eventtypeMainPanel.add(eventDescriptionLabel);

		eventtypeMainFlexTable.setWidget(0, 0, eventtypeStartdatePanel);
		eventtypeMainFlexTable.setWidget(0, 1, eventtypeEnddatePanel);
		
		horizontalDataPanel.add(eventtypeAttributePanel);
		horizontalDataPanel.add(eventtypeMainFlexTable);
		
		eventtypeMainPanel.add(horizontalDataPanel);
		eventtypeMainPanel.add(eventtypeSaveButton);
		
		// create an eventDTO object that will be saved to the DB
		EventDTO eventdto = new EventDTO();
		eventdto.setEventtype(etdto);
		eventdto.setDateEnd(endeventdate);
		eventdto.setDateStart(starteventdate);
		
		// add the eventDTO to the list of eventdtos that should be saved
		saveEventDTOList.add(eventdto);
		
		return eventtypeMainPanel;
			
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
	 * Configuring the dualitystatussectionPanel and returning it
	 * @return statusSelectionPanel
	 */
	private Panel loadDualityStatusSection(){
		
		// get all the existing dualitystatus (they are added to the existingDualityStatusDTOs arrayList)
		callGetDualityStatus();
		
		// Adding the headline label to the statusSelectionPanel
		statusSelectionPanel.add(statusSelectionIntroductionLabel);
		
		// Populating the horizontalpanel and adding it to the panelstatusSelectionPanel
		// Populating the flex table for the selection of status
		statusSelectionFlexTable.setText(0, 0, "Id");
		statusSelectionFlexTable.setText(0, 1, "Statuscode");
		statusSelectionFlexTable.setText(0, 2, "Edit");
		statusSelectionFlexTable.setText(0, 3, "Remove");
		statusSelectionFlexTable.setTitle("Satusselection");
		
		tableAndAddEditPanel.add(statusSelectionFlexTable);
		
		// Populating the dualityStatusAddEditPanel
		dualityStatusAddEditPanel.add(dualityStatusAddEditHeaderLabel);
		dualityStatusAddEditFlexTable.setWidget(0, 0, dualityStatusIdLabel);
		dualityStatusAddEditFlexTable.setWidget(0, 1, dualityStatusIdTextTextBox);
		dualityStatusAddEditFlexTable.setWidget(1, 0, dualityStatusStatusCodeLabel);
		dualityStatusAddEditFlexTable.setWidget(1, 1, dualityStatusStatusCodeTextBox);
		dualityStatusAddEditFlexTable.setWidget(2, 0, dualityStatusOkButton);
		dualityStatusAddEditPanel.add(dualityStatusAddEditFlexTable);
		dualityStatusAddEditPanel.setVisible(false);
		dualityStatusIdTextTextBox.setReadOnly(true);
		tableAndAddEditPanel.add(dualityStatusAddEditPanel);
		
		statusSelectionPanel.add(tableAndAddEditPanel);	
		
		// Populating the dualityStatusChooseAddPanel (horizontal) and adding it to the panelstatusSelectionPanel
		dualityStatusChooseAddPanel.add(dualityStatusAddButton);
		
		// listen for mouse events on the dualitytypeSelectionButton
		dualityStatusAddButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				updateDualityStatusAddEditPanel(null);
			}
		});
		
		// listen for mouse events in the dualityStatusOkButton
		dualityStatusOkButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addNewDualityStatus();
			}
		});
		
		statusSelectionPanel.add(dualityStatusChooseAddPanel);
		
		return statusSelectionPanel;
	}

	
	/**
	 * Calling the getDualityStatus method of the READBService
	 */
	private void callGetDualityStatus(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<DualityStatusDTO>> callback = new AsyncCallback<List<DualityStatusDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getDualityStatus()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<DualityStatusDTO> result) {
				
				existingDualityStatusDTOs.clear();
				
				for(DualityStatusDTO dsdto : result){
					
					// adding the DualityStatusDTOs to the existingDualityStatusDTOs arrayList
					existingDualityStatusDTOs.add(dsdto);
					
					// final variable needed for the button specifications
					final DualityStatusDTO currentDualityStatusDTO = dsdto;
					final int lastIndex = existingDualityStatusDTOs.indexOf(currentDualityStatusDTO);
					
					// Buttons to edit and delete dualitystatus
					Button updateDualityStatusButton = new Button("Update");

					updateDualityStatusButton.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event){
							updateDualityStatusAddEditPanel(currentDualityStatusDTO);
						}
					});	
					
					Button deleteDualityStatusButton = new Button("X");
					deleteDualityStatusButton.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event){
							
							deleteDualityStatus(currentDualityStatusDTO);
						}
					});
					
					int row = statusSelectionFlexTable.getRowCount();
					statusSelectionFlexTable.setText(row, 0, String.valueOf(dsdto.getId()));
					statusSelectionFlexTable.setText(row, 1, dsdto.getStatus());
					statusSelectionFlexTable.setWidget(row, 2, updateDualityStatusButton);
					statusSelectionFlexTable.setWidget(row, 3, deleteDualityStatusButton);
				}
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getDualityStatus(callback);
	    
	}
	
	
	/**
	 * Updating the DualityStatusAddEditPanel (just setting it to visible)
	 */
	private void updateDualityStatusAddEditPanel(DualityStatusDTO dualityStatusDTO){
		
		// Check if the dualityStatusDTO object is null
		// If so the textboxes are granted for adding new dualitystatus
		if(dualityStatusDTO == null){
			
			// setting the value of the dualitystatus id
			saveActionState = true;
			
			if(existingDualityStatusDTOs.size()>0){
				int lastId = existingDualityStatusDTOs.get(existingDualityStatusDTOs.size()-1).getId();
				dualityStatusIdTextTextBox.setText(String.valueOf(lastId+1));
				
			}else{	
				dualityStatusIdTextTextBox.setText("1");	
			}
			
			dualityStatusStatusCodeTextBox.setText("");
			
		} else{
			saveActionState = false;
			
			// copy the current state of the dualitystatusDTO object
			updateObject.setId(dualityStatusDTO.getId());
			updateObject.setStatus(dualityStatusDTO.getStatus());
			
			// copy the dualityStatusDTO itself
			updatedObject = dualityStatusDTO;
			
			dualityStatusIdTextTextBox.setText(String.valueOf(dualityStatusDTO.getId()));
			dualityStatusStatusCodeTextBox.setText(dualityStatusDTO.getStatus());
		}
		
		dualityStatusAddEditPanel.setVisible(true);
		dualityStatusStatusCodeTextBox.setFocus(true);	
	}
	
	
	/**
	 * Adding new Dualitystatus
	 * Calling the addDualityStatus method of the READBService
	 * 
	 */
	private void addNewDualityStatus(){
		
		// check if the actionstate is 'save'
		if(!saveActionState){
			int indexOfUpdateObbject = existingDualityStatusDTOs.indexOf(updatedObject);
			String oldStatusCode = updateObject.getStatus();
			String updatedStatus = dualityStatusStatusCodeTextBox.getText();
			if(oldStatusCode.matches(updatedStatus)){
				Window.alert("Nothing has been updated");
			}else{
				updatedObject.setStatus(updatedStatus);
				updateDualityStatus(updatedObject,indexOfUpdateObbject);
			}		
			
			return;
		}
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<DualityStatusDTO> callback = new AsyncCallback<DualityStatusDTO>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("saveDualityStatus()");
		    	caught.printStackTrace();
			}

			public void onSuccess(DualityStatusDTO result) {
				
				String addDualityStatusMsg = "New Dualitystatus '" + result.getStatus() + "' added to REA DB with Id " + result.getId();
				Window.alert(addDualityStatusMsg);
				logger.info(addDualityStatusMsg);
				
				// adding the added dualitystatusDTO to the list of dualitydtos
				existingDualityStatusDTOs.add(result);
				
				final DualityStatusDTO savedDualityStatusDTO = result;
				
				
				// Buttons to edit and delte dualitystatus
				Button updateDualityStatusButton = new Button("Update");
				updateDualityStatusButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						updateDualityStatusAddEditPanel(savedDualityStatusDTO);
					}
				});	
				
				Button deleteDualityStatusButton = new Button("X");
				deleteDualityStatusButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						deleteDualityStatus(savedDualityStatusDTO);
						
					}
				});	
				
				// adding the new dualitystatus to the statusSelectionFlexTable
				int row = statusSelectionFlexTable.getRowCount();
				statusSelectionFlexTable.setText(row, 0, String.valueOf(result.getId()));
				statusSelectionFlexTable.setText(row, 1, result.getStatus());
				statusSelectionFlexTable.setWidget(row, 2, updateDualityStatusButton);
				statusSelectionFlexTable.setWidget(row, 3, deleteDualityStatusButton);
				
				// updating the dualityStatusAddEditPanel
				updateDualityStatusAddEditPanel(null);
			}
	    	
	    };
	    
	    // Creating a dualitystatusDTO object that will be added to DB and table
	    DualityStatusDTO dualityStatusDTO = new DualityStatusDTO();
	    dualityStatusDTO.setStatus(dualityStatusStatusCodeTextBox.getText());
	    
	    // Make the call
	    reaDBSvc.saveDualityStatus(dualityStatusDTO,callback);
	}
	
	
	/**
	 * Deleting a Dualitystatus from the REA DB
	 * 
	 */
	private void deleteDualityStatus(DualityStatusDTO deleteDualityStatusDTO){
		
		final int removedListIndex = existingDualityStatusDTOs.indexOf(deleteDualityStatusDTO);
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }		
	    
	    // Set up the callback object.
	    AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("deleteDualityStatus()");
		    	caught.printStackTrace();
			}
		
			public void onSuccess(Integer result) {
				
				String deleteDualityStatusMsg = "Dualitystatus with Id '" + result + "' was deleted from the REA DB";
				Window.alert(deleteDualityStatusMsg);
				logger.info(deleteDualityStatusMsg);
				
				// remove entries from arrayList
				existingDualityStatusDTOs.remove(removedListIndex);
				// remove entries from flextable
				statusSelectionFlexTable.removeRow(removedListIndex+1);
				
				// update the dualitystatusaddeditpanel if it is already visible
				if(dualityStatusAddEditPanel.isVisible()){
					updateDualityStatusAddEditPanel(null);
				}
				
				// if not dualitystatus exist dualitystatusaddeditpanel is set to invisible
				if(existingDualityStatusDTOs.size() == 0){
					dualityStatusAddEditPanel.setVisible(false);
				}
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteDualityStatus(deleteDualityStatusDTO.getId(), callback);
	    
	}
	
	
	/**
	 * Deleting a Dualitystatus from the REA DB
	 * 
	 */
	private void updateDualityStatus(DualityStatusDTO updatedDualityStatusDTO, int listUpdateIndex){
		
		final int updatedListIndex = listUpdateIndex;
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }	
	    
	    // Set up the callback object.
	    AsyncCallback<DualityStatusDTO> callback = new AsyncCallback<DualityStatusDTO>() {
	    	
	    	public void onFailure(Throwable caught) {
	    		logREADBRPCFailure("updateDualityStatus()");
		    	caught.printStackTrace();
			}
	    	
	    	public void onSuccess(DualityStatusDTO result) {
	    		
	    		// print update message
	    		String updateDualityStatusMsg = "Status of Dualitystatus (Id " + updatedObject.getId() + ") updated from '" 
	    											+ updateObject.getStatus() + "' to '" + updatedObject.getStatus() + "'";
	    		Window.alert(updateDualityStatusMsg);
	    		logger.info(updateDualityStatusMsg);
	    		
	    		// update the dualitySatusDTO arraylist
	    		//existingDualityStatusDTOs.set(updatedListIndex, result);
	    		existingDualityStatusDTOs.get(updatedListIndex).setStatus(result.getStatus());
	    		// update entries from flextable
				statusSelectionFlexTable.setText(updatedListIndex + 1, 1, result.getStatus());
	    		
				updateDualityStatusAddEditPanel(existingDualityStatusDTOs.get(updatedListIndex));
	    	}
	    };
	    
	    // Make the call
	    reaDBSvc.updateDualityStatus(updatedDualityStatusDTO, callback);
	    
	}
	
	
	/**
	 * Configuring the agentsectionPanel and returning it
	 * @return statusSelectionPanel
	 */
	private Panel loadAgentSection(){
		
		// get all the existing agenttypes (they are added to the existingAgenttypeDTOs arrayList)
		callGetAgenttypes();
		
		// get all the existing agents (they are added to the existingAgentDTOs arrayList)
		callGetAgents();
				
		// Adding the headline label to the agentSelectionPanel
		agentSelectionPanel.add(agentSelectionIntroductionLabel);
				
		// Populating the horizontalpanel and adding it to the agentSelectionPanel
		// Populating the flex table for the selection of agents
		agentSelectionFlexTable.setText(0, 0, "Id");
		agentSelectionFlexTable.setText(0, 1, "Name");
		agentSelectionFlexTable.setText(0, 2, "Agenttype");
		agentSelectionFlexTable.setText(0, 3, "Edit");
		agentSelectionFlexTable.setText(0, 4, "Remove");
		agentSelectionFlexTable.setTitle("Agentselection");
				
		agentTableAndAddEditPanel.add(agentSelectionFlexTable);
				
		// Populating the agentAddEditPanel
		agentAddEditPanel.add(agentAddEditHeaderLabel);
		agentAddEditFlexTable.setWidget(0, 0, agentIdLabel);
		agentAddEditFlexTable.setWidget(0, 1, agentIdTextTextBox);
		agentAddEditFlexTable.setWidget(1, 0, agentNameLabel);
		agentAddEditFlexTable.setWidget(1, 1, agentNameTextBox);
		agentAddEditFlexTable.setWidget(2,0, agentTypeLabel);
		agentAddEditFlexTable.setWidget(2, 1, agentTypeListBox);
		agentAddEditFlexTable.setWidget(3, 0, agentOkButton);
		agentAddEditPanel.add(agentAddEditFlexTable);
		agentAddEditPanel.setVisible(false);
		agentIdTextTextBox.setReadOnly(true);
		agentTableAndAddEditPanel.add(agentAddEditPanel);
				
		agentSelectionPanel.add(agentTableAndAddEditPanel);	
				
		// Populating the agentAddPanel (horizontal) and adding it to the agentSelectionPanel
		agentAddPanel.add(agentAddButton);
				
		// listen for mouse events on the agentAddButton
		agentAddButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				updateAgentAddEditPanel(null);
				
			}
		});
				
		// listen for mouse events in the agentOkButton
		agentOkButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addNewAgent();
			}
		});
				
		agentSelectionPanel.add(agentAddPanel);
				
		return agentSelectionPanel;
	}
	
	
	/**
	 * Calling the getAgenttypes method of the READBService
	 */
	private void callGetAgenttypes(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<AgenttypeDTO>> callback = new AsyncCallback<List<AgenttypeDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getAgenttypes()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<AgenttypeDTO> result) {
				
				existingAgenttypeDTOs.clear();
				
				for(AgenttypeDTO atdto : result){
					
					// adding the AgenttypeDTOs to the existingAgenttypeDTOs arrayList
					existingAgenttypeDTOs.add(atdto);
					
					// populate the agentTypeListBox with the retrieved agenttypes
					agentTypeListBox.addItem(atdto.getId());
				}
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getAgenttypes(callback);
	}
	
	
	/**
	 * Calling the getAgents method of the READBService
	 */
	private void callGetAgents(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<AgentDTO>> callback = new AsyncCallback<List<AgentDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getAgents()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<AgentDTO> result) {
				
				existingAgentDTOs.clear();
				
				for(AgentDTO adto : result){
					
					// adding the AgentDTOs to the existingAgentDTOs arrayList
					existingAgentDTOs.add(adto);
					
					// final variable needed for the button specifications
					final AgentDTO currentAgentDTO = adto;
					
					// Buttons to edit and delete agents
					Button updateAgentButton = new Button("Update");

					updateAgentButton.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event){
							updateAgentAddEditPanel(currentAgentDTO);
						}
					});	
					
					Button deleteAgentButton = new Button("X");
					deleteAgentButton.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event){
							
							//deleteAgent(agentSelectionFlexTable.getText(lastIndex+1, 0),lastIndex);
							deleteAgent(currentAgentDTO);
						}
					});
					
					// TODO: the REA DB is capable of representing agents with more than one agenttype
					// for this prototype it is assumed that each agent has only one agenttype
					Iterator<AgenttypeDTO> iterator = adto.getAgenttypes().iterator();
					AgenttypeDTO agenttypeDTO = iterator.next();
					
					int row = agentSelectionFlexTable.getRowCount();
					agentSelectionFlexTable.setText(row, 0, String.valueOf(adto.getId()));
					agentSelectionFlexTable.setText(row, 1, adto.getName());
					agentSelectionFlexTable.setText(row, 2, agenttypeDTO.getId());
					agentSelectionFlexTable.setWidget(row, 3, updateAgentButton);
					agentSelectionFlexTable.setWidget(row, 4, deleteAgentButton);
				}
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getAgents(callback);
	    
	}
	
	
	/**
	 * Updating the AgentAddEditPanel (just setting it to visible)
	 */
	private void updateAgentAddEditPanel(AgentDTO agentDTO){
		
		// Check if the agentDTO object is null
		// If so the textboxes are granted for adding new dualitystatus
		if(agentDTO == null){
			
			// setting the values of the agents
			agentSaveActionState = true;
			
			if(existingAgentDTOs.size()>0){
				int lastId = existingAgentDTOs.get(existingAgentDTOs.size()-1).getId();
				agentIdTextTextBox.setText(String.valueOf(lastId+1));
				
			}else{	
				agentIdTextTextBox.setText("1");	
			}
			
			agentNameTextBox.setText("");
			agentTypeListBox.setItemSelected(0, true);
			
		} else{
			agentSaveActionState = false;
			agentUpdateObject = agentDTO;
			agentIdTextTextBox.setText(String.valueOf(agentDTO.getId()));
			agentNameTextBox.setText(agentDTO.getName());
			
			// TODO: the REA DB is capable of representing agents with more than one agenttype
			// for this prototype it is assumed that each agent has only one agenttype
			Iterator<AgenttypeDTO> iterator = agentDTO.getAgenttypes().iterator();
			AgenttypeDTO agenttypeDTO = iterator.next();
			
			int selectedIndex = 0;
			
			for(int i = 0; i < existingAgenttypeDTOs.size(); i++){
				if(existingAgenttypeDTOs.get(i).getId().equals(agenttypeDTO.getId())){
					selectedIndex = i;
					break;
				}
			}
			
			agentTypeListBox.setItemSelected(selectedIndex, true);
		}
		
		agentAddEditPanel.setVisible(true);
		agentNameTextBox.setFocus(true);	
	}
	
	
	/**
	 * Adding new Agent
	 * Calling the addAgent method of the READBService
	 * 
	 */
	private void addNewAgent(){

		// check if the actionstate is 'save'
		if(!agentSaveActionState){
			int indexOfUpdateObject = existingAgentDTOs.indexOf(agentUpdateObject);
			String oldName = agentUpdateObject.getName();
			String updatedName = agentNameTextBox.getText();
			if(oldName.matches(updatedName)){
				Window.alert("Nothing has been updated");
			}else{
				agentUpdateObject.setName(updatedName);
				// TODO: implement update agent functionality
				//updateAgent(agentUpdateObject,indexOfUpdateObject);
			}		
			
			return;
		}
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<AgentDTO> callback = new AsyncCallback<AgentDTO>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("saveAgent()");
		    	caught.printStackTrace();
			}

			public void onSuccess(AgentDTO result) {
				
				Window.alert("New Agents " + result.getName() + " added to REA DB with Id " + result.getId() );
				
				// adding the added agentDTO to the list of agentDTOs
				existingAgentDTOs.add(result);
				
				final AgentDTO savedAgentDTO = result;
				final int lastAddedIndex = existingAgentDTOs.indexOf(savedAgentDTO);
				
				
				// Buttons to edit and delte dualitystatus
				Button updateAgentButton = new Button("Update");
				updateAgentButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						updateAgentAddEditPanel(savedAgentDTO);
					}
				});	
				
				Button deleteAgentButton = new Button("X");
				deleteAgentButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						deleteAgent(savedAgentDTO);
						
					}
				});	
				
				// TODO: the REA DB is capable of representing agents with more than one agenttype
				// for this prototype it is assumed that each agent has only one agenttype
				Iterator<AgenttypeDTO> iterator = result.getAgenttypes().iterator();
				AgenttypeDTO atdto = iterator.next();
				
				// adding the new dualitystatus to the statusSelectionFlexTable
				int row = agentSelectionFlexTable.getRowCount();
				
				agentSelectionFlexTable.setText(row, 0, String.valueOf(result.getId()));
				agentSelectionFlexTable.setText(row, 1, result.getName());
				agentSelectionFlexTable.setText(row, 2, atdto.getId());
				agentSelectionFlexTable.setWidget(row, 3, updateAgentButton);
				agentSelectionFlexTable.setWidget(row, 4, deleteAgentButton);
				
				// updating the updateAgentAddEditPanel
				updateAgentAddEditPanel(null);
			}
	    	
	    };
	    
	    // Create a agenttypeDTO object that represents the type of the agentDTo that should be saved
	    Set<AgenttypeDTO> agenttypes = new HashSet<AgenttypeDTO>(1);
	    for(AgenttypeDTO atDTO : existingAgenttypeDTOs){
	    	if(atDTO.getId().equals(agentTypeListBox.getItemText(agentTypeListBox.getSelectedIndex()))){
	    		agenttypes.add(atDTO);
	    	}
	    }    
	    
	    // Creating a agentDTO object that will be added to DB and table
	    AgentDTO agentDTO = new AgentDTO();
	    agentDTO.setName(agentNameTextBox.getText());
	    agentDTO.setAgenttypes(agenttypes);
	    
	    agentDTO.setAgenttypes(agenttypes);
	    
	    // Make the call
	    reaDBSvc.saveAgent(agentDTO, callback);
	    
	}
	
	
	/**
	 * Deleting an Agent from the REA DB
	 * 
	 */
	private void deleteAgent(AgentDTO deleteAgentDTO){
		
		final int removedListIndex = existingAgentDTOs.indexOf(deleteAgentDTO);
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }		
	    
	    // Set up the callback object.
	    AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("deleteAgent()");
		    	caught.printStackTrace();
			}
		
			public void onSuccess(Integer result) {
				
				Window.alert("Agent with Id " + result + " was deleted from the REA DB");
				
				// remove entries from arrayList
				existingAgentDTOs.remove(removedListIndex);
				// remove entries from flextable
				agentSelectionFlexTable.removeRow(removedListIndex+1);
				// update the dualitystatusandeditpanel
				updateAgentAddEditPanel(null);
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteAgent(deleteAgentDTO.getId(), callback);
		
	}
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	private void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}

  
}
