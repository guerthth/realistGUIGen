package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowHasAdditionalattributevalueDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DualityTypePanel extends VerticalPanel {

	// Logger
	private final static Logger logger = Logger.getLogger("DualityTypePanelLogger");
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// DualitytypeSelection panel + ArrayList
	private Label agentSelectionIntroductionLabel = new Label("Business Case Administration");
	private HorizontalPanel dualitytypeSelectionPanel = new HorizontalPanel();
	private FlexTable dualitytypeFlexTable = new FlexTable();
	private Label dualitytypeLabel = new Label("Choose Dualitytype: ");
	private ListBox dualitytypeListBox = new ListBox();
	private List<DualitytypeDTO> existingDualitytypeDTOs = new ArrayList<DualitytypeDTO>();
	private Button dualitytypeSelectionButton = new Button("Create new");
	
	// dualitystatusAndDualityAddPanel
	private HorizontalPanel dualitystatusAndDualityAddPanel = new HorizontalPanel();
	private FlexTable dualitystatusAndDualityAddFlexTable = new FlexTable();
	private Label dualitystatusLabel = new Label("Choose Status: ");
	private ListBox dualitystatusListBox = new ListBox();
	private Button dualityAddButton = new Button("Add Business Case");
	
	// list of existing dualitystatus in REA DB
	private List<DualityStatusDTO> dualitystatusList = new ArrayList<DualityStatusDTO>();
	
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);	
	
	// Current Dualitytype_Id
	private DualitytypeDTO currentDualityType;
	
	// Eventtype Tab Panel
	private TabPanel eventypeSetTabPanel;
	
	// DualityDTO object that will be saved as Duality object in the REA DB
	private DualityDTO saveDualityDTO;
	
	// Lists keeping track of events of the duality and the corresponding attributes + textboxes for validation
	private Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeAttributeLabelMap = new HashMap<EventDTO,Map<AttributeDTO,CustomTextBox>>();

	// Lists keeping track of events of the duality and the corresponding attributes + textboxes for validation (participations)
	private Map<EventDTO,Map<ParticipationDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeParticipationAttributeLabelMap = new HashMap<EventDTO,Map<ParticipationDTO,Map<AttributeDTO,CustomTextBox>>>();

	// Lists keeping track of events of the duality and the corresponding attributes + textboxes for validation (stockflows)
	private Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap = new HashMap<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>>();
	
	// Map keeping track of the fixed stockflow attribute textboxes
	private Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap = new HashMap<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>>();
	
	// list of eventdtos that should be saved to the REA DB
	private List<EventDTO> saveEventDTOList = new ArrayList<EventDTO>();
	
	/**
	 * Constructor
	 */
	public DualityTypePanel(){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();
		
		// populate the DualityTypePanel
		populateDualityTypePanel();
	}
	
	/**
	 * Method populating the DualityTypePanel
	 */
	private void populateDualityTypePanel(){
		
		// define style for agentSelectionIntroductionLabel
		agentSelectionIntroductionLabel.addStyleName("introductionLabel");
		
		// adding the agentSelectionIntroductionLabel to the DualitytypePanel
		this.add(agentSelectionIntroductionLabel);
		
		// apply styles for dualitytylePanel
		this.addStyleName("fullsizePanel");
		
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
		
		// add duality button functionality
		dualityAddButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				
				saveDuality();
			}
		});
		
		// get existing dualityType and populate dualitytypeListBox
		getExistingDualityTypes();
		
		// apply style for dualitytypeSelectionPanel
		dualitytypeSelectionPanel.addStyleName("dualitytypeSelection");
		dualitytypeSelectionPanel.addStyleName("fullsizePanel");
		
		// add the dualitytypeFlexTable to the dualitytypeSelectionPanel
		dualitytypeSelectionPanel.add(dualitytypeFlexTable);
		
  		 // add dualitytypeSelectionPanel to the DualitytypePanel
		this.add(dualitytypeSelectionPanel);  
		
	}
	
	
	/**
	 * Method retrieving the dualitytypes in the REA DB 
	 * and returning them as List
	 * @return 
	 */
	private void getExistingDualityTypes(){
		
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
	    	  
	      }
	    };

	    // Make the call 
	    reaDBSvc.getDualitytypes(callback);
	}
	
	
	/**
	 * Loading Resource, Event, Agentsections
	 */
	private void loadDualitytypeContent(int selectedIndex){
		
		// clear maps
		eventtypeAttributeLabelMap.clear();
		eventtypeParticipationAttributeLabelMap.clear();
		eventtypeStockflowAttributeLabelMap.clear();
		eventtypeStockflowFixedAttributeMap.clear();
		
		// clear saveEventDTOList
		saveEventDTOList.clear();
		
		//retrieve selected DualitytypeDTO object
		DualitytypeDTO selectedDualityType = existingDualitytypeDTOs.get(selectedIndex);
		currentDualityType = selectedDualityType;
		
		// DualityDTO object that will be saved as Duality to the REA DB
		saveDualityDTO = new DualityDTO();
		saveDualityDTO.setDualitytype(currentDualityType);
		
		// removing the tabpanel from theDualityTypePanel if it already exists to avoid adding it several times
		if(eventypeSetTabPanel != null){
			this.remove(eventypeSetTabPanel);
		}
		
		// removing dualitystatusAndDualityAddPanel from the main DualityTypePanel if it already exists
		if(dualitystatusAndDualityAddPanel != null){
			this.remove(dualitystatusAndDualityAddPanel);
			//also refresh dualitystatuslistbox content
			dualitystatusListBox.clear();
		}
		
		// Tab Panel for Eventtypes of a specific Duality
		eventypeSetTabPanel = new EventPanel(saveDualityDTO, saveEventDTOList, eventtypeAttributeLabelMap, 
									eventtypeParticipationAttributeLabelMap, eventtypeStockflowAttributeLabelMap, eventtypeStockflowFixedAttributeMap);
		
		// preselecting the first tab of the panel
		eventypeSetTabPanel.selectTab(0);
		
		// add the tabpanel to the main panel
		this.add(eventypeSetTabPanel);
		
		// format dualitystatusAndDualityAddPanel
		dualitystatusAndDualityAddFlexTable.setWidget(0, 0, dualitystatusLabel);
		dualitystatusAndDualityAddFlexTable.setWidget(0, 1, dualitystatusListBox);
		dualitystatusAndDualityAddFlexTable.setWidget(0, 2, dualityAddButton);
		dualitystatusAndDualityAddFlexTable.setCellSpacing(5);
		
		// populate dualitystatusListBox
		populateDualityStatusListBox();
		
		dualitystatusAndDualityAddPanel.add(dualitystatusAndDualityAddFlexTable);
		
		// styles
		dualitystatusAndDualityAddPanel.addStyleName("fullsizePanel");
		dualitystatusAndDualityAddPanel.addStyleName("dualityAdd");
		
		// add panel for dualitystatus selection and adding the dualitytype to the REA DB
		this.add(dualitystatusAndDualityAddPanel);

	}
	
	/**
	 * Method populating the dualityStatusListBox with the existing dualitystatus in the REA DB
	 */
	private void populateDualityStatusListBox(){
		
		// list of all existing dualitystatusDTOs
		dualitystatusList = reaDBEntryContainer.getExistingDualityStatusDTOs();
		
		if(dualitystatusList.size() == 0){
			
			dualitystatusListBox.addItem("No dualitystatus exists");
			return;
		}
		
		// adding all existing dualitystatus in REA DB to the listbox
		for(DualityStatusDTO status : dualitystatusList){
			
			dualitystatusListBox.addItem(status.getStatus());
		}
	}
	
	
	/**
	 * Saving the specified duality to the database
	 */
	private void saveDuality(){
		
		if(!dualitytypeTextBoxValidationResult()){
			return;
		} 
				
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<DualityDTO> callback = new AsyncCallback<DualityDTO>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getAgents()");
		    	caught.printStackTrace();
			}

			public void onSuccess(DualityDTO result) {
				
				Window.alert("Save new Duality with Id '" + result.getId() + "'");
				
				for(EventDTO eventdto : saveEventDTOList){
	    		  	eventdto.setDuality(result);
	    		  	
	    		  	// generate additional attributes for eventdto
	    		  	eventdto.setAdditionalAttributeValues(createAttributeValueSetForSelectedEventDTO(eventdto));
	    		  	
	    		  	// generate additional attribute for participationdtos of event
	    		  	for(ParticipationDTO participationdto : eventtypeParticipationAttributeLabelMap.get(eventdto).keySet()){
	    		  		
	    		  		participationdto.setAdditionalAttributeValues(createAttributeValuesForparticipationDTO(participationdto, eventdto));
	    		  		
	    		  	}
	    		  	
	    		  	// generate additional attribute values for stockflowdtos of event
	    		  	for(StockflowDTO stockflowdto : eventtypeStockflowAttributeLabelMap.get(eventdto).keySet()){
	    		  		
	    		  		stockflowdto.setAdditionalAttributeValues(createAttributeValuesForstockflowDTO(stockflowdto, eventdto));
	    		  	}
	    		  	
	    		  	// fixed valued for stock flows
	    			for(EventDTO edto : eventtypeStockflowFixedAttributeMap.keySet()){
	    				
	    				for(StockflowDTO sfdto : eventtypeStockflowFixedAttributeMap.get(edto).keySet()){
	    					
	    					sfdto.setPricePerUnit(Double.parseDouble(eventtypeStockflowFixedAttributeMap.get(edto).get(sfdto).get(0).getText()));
	    					if(eventtypeStockflowFixedAttributeMap.get(edto).get(sfdto).size() > 1){
	    						sfdto.setQuantity(Double.parseDouble(eventtypeStockflowFixedAttributeMap.get(edto).get(sfdto).get(1).getText()));
	    					}

	    				}

	    			}
	    		  	
	  	    		saveEvent(eventdto);
	  	      	}
				
			}
	    	
	    };
	    
	    // selected dualitystatus
	    DualityStatusDTO dualitystatusDTO = dualitystatusList.get(dualitystatusListBox.getSelectedIndex());
	    
	    // dualitytypeDTO
	    DualitytypeDTO dualitytypeDTO = new DualitytypeDTO();
	 	dualitytypeDTO.setId(currentDualityType.getId());
	 	dualitytypeDTO.setName(currentDualityType.getName());
	 	dualitytypeDTO.setConversion(currentDualityType.isConversion());
	    
	    // create DualityDTO object that will be saved in the database
	    //DualityDTO dualityDTO = createDualityDTO();
	    saveDualityDTO.setDualitystatus(dualitystatusDTO);
	    saveDualityDTO.setDate(new Date());
	    saveDualityDTO.setDualitytype(dualitytypeDTO);
	    
	    // Make the call
	    reaDBSvc.saveDuality(saveDualityDTO, callback);
	    //reaDBSvc.saveDuality(dualityDTO, callback);
	}
	
	
	/**
	 * Method creating a Set of additional attribute values for a specific eventdto
	 * @param eventdto
	 * @return attributes
	 */
	private Set<EventHasAdditionalattributevalueDTO> createAttributeValueSetForSelectedEventDTO(EventDTO eventdto){
		
		// creating additional attribute values for eventDTO
	    Set<EventHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<EventHasAdditionalattributevalueDTO>();
	    
	    // get the attributeLabelMap for the current event
	    Map<AttributeDTO,CustomTextBox> attributeLabelMap = eventtypeAttributeLabelMap.get(eventdto);
	    
	    // run through the elements in the labelmap 
	    for(AttributeDTO attribute : attributeLabelMap.keySet()){
	    	
	    	String textBoxContent = attributeLabelMap.get(attribute).getText();
	    	
	    	// create additional attribute value DTO object
	    	EventHasAdditionalattributevalueDTO attributevalue = new EventHasAdditionalattributevalueDTO();
	    	attributevalue.setEvent(eventdto);
	    	attributevalue.setAttribute(attribute);
	    	
	    	// setting the value depending on the datatype
	    	if (attribute.getDatatype().equals("INT") || attribute.getDatatype().equals("DOUBLE")){
	    		attributevalue.setNumericValue(Double.parseDouble(textBoxContent));
	    	} else if (attribute.getDatatype().equals("VARCHAR")){
	    		attributevalue.setTextualValue(textBoxContent);
	    	} else if (attribute.getDatatype().equals("BOOLEAN")){
	    		attributevalue.setBooleanValue(Boolean.valueOf(textBoxContent));
	    	} else {		
	    		Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(textBoxContent);
	    		attributevalue.setDatetimeValue(date);
	    	}
	    	
	    	// adding the attributevalue to the attribues set
	    	attributes.add(attributevalue);
	    	
	    }
	    
	    return attributes;
	    
	}
	
	/**
	 * Method creating a Set of additional attribute values for a specific participationdto
	 * @param participation
	 * @param eventdto
	 * @return
	 */
	private Set<ParticipationHasAdditionalattributevalueDTO> createAttributeValuesForparticipationDTO(ParticipationDTO participation, EventDTO eventdto){
		
		// creating additional attribute values for participationDTO
	    Set<ParticipationHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<ParticipationHasAdditionalattributevalueDTO>();
	    
	    // get the attributeLabelMap for the current participation
	    Map<AttributeDTO,CustomTextBox> attributeLabelMap = eventtypeParticipationAttributeLabelMap.get(eventdto).get(participation);
		
	    // run through the elements in the labelmap 
	    for(AttributeDTO attribute : attributeLabelMap.keySet()){
	    	
	    	String textBoxContent = attributeLabelMap.get(attribute).getText();
	    	
	    	// create additional attribute value DTO object
	    	ParticipationHasAdditionalattributevalueDTO attributevalue = new ParticipationHasAdditionalattributevalueDTO();
	    	attributevalue.setParticipation(participation);
	    	attributevalue.setAttribute(attribute);
	    	
	    	// setting the value depending on the datatype
	    	if (attribute.getDatatype().equals("INT") || attribute.getDatatype().equals("DOUBLE")){
	    		attributevalue.setNumericValue(Double.parseDouble(textBoxContent));
	    	} else if (attribute.getDatatype().equals("VARCHAR")){
	    		attributevalue.setTextualValue(textBoxContent);
	    	} else if (attribute.getDatatype().equals("BOOLEAN")){
	    		attributevalue.setBooleanValue(Boolean.valueOf(textBoxContent));
	    	} else {		
	    		Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(textBoxContent);
	    		attributevalue.setDatetimeValue(date);
	    	}
	    	
	    	// adding the attributevalue to the attribues set
	    	attributes.add(attributevalue);
	    	
	    }
	    
	    return attributes;
	}
	
	
	/**
	 * Method creating a Set of additional attribute values for a specific stockflowdto
	 * @param stockflow
	 * @param eventdto
	 * @return
	 */
	private Set<StockflowHasAdditionalattributevalueDTO> createAttributeValuesForstockflowDTO(StockflowDTO stockflow, EventDTO eventdto){
		
		// creating additional attribute values for stockflowDTO
	    Set<StockflowHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<StockflowHasAdditionalattributevalueDTO>();
	    
	    // get the attributeLabelMap for the current stockflow
	    Map<AttributeDTO,CustomTextBox> attributeLabelMap = eventtypeStockflowAttributeLabelMap.get(eventdto).get(stockflow);
		
	    // run through the elements in the labelmap 
	    for(AttributeDTO attribute : attributeLabelMap.keySet()){
	    	
	    	String textBoxContent = attributeLabelMap.get(attribute).getText();
	    	
	    	// create additional attribute value DTO object
	    	StockflowHasAdditionalattributevalueDTO attributevalue = new StockflowHasAdditionalattributevalueDTO();
	    	attributevalue.setStockflow(stockflow);
	    	attributevalue.setAttribute(attribute);
	    	
	    	// setting the value depending on the datatype
	    	if (attribute.getDatatype().equals("INT") || attribute.getDatatype().equals("DOUBLE")){
	    		attributevalue.setNumericValue(Double.parseDouble(textBoxContent));
	    	} else if (attribute.getDatatype().equals("VARCHAR")){
	    		attributevalue.setTextualValue(textBoxContent);
	    	} else if (attribute.getDatatype().equals("BOOLEAN")){
	    		attributevalue.setBooleanValue(Boolean.valueOf(textBoxContent));
	    	} else {		
	    		Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(textBoxContent);
	    		attributevalue.setDatetimeValue(date);
	    	}
	    	
	    	// adding the attributevalue to the attribues set
	    	attributes.add(attributevalue);
	    	
	    }
	    
	    return attributes;
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
	    	  
	      }
	    };
	    
	    // Make the call to the stock price service.
	    reaDBSvc.saveEvent(savedEventdto, callback);
	    
	}
	
	
	/**
	 * Method validating all textboxes relevant for a Dualitytype
	 * @return True if all validations are passed. False if one fails
	 */
	private boolean dualitytypeTextBoxValidationResult(){
		
		boolean validationResult = true;
		// TODO: maybe change to 1 loop with 3 loops inside!
		// Eventtype Attributes
		for(EventDTO edto : eventtypeAttributeLabelMap.keySet()){
			for(AttributeDTO adto : eventtypeAttributeLabelMap.get(edto).keySet()){
				
				if(!eventtypeAttributeLabelMap.get(edto).get(adto).validate()){
					validationResult = false;
				}
				
			}
		}
		
		// fixed stockflow attributes
		for(EventDTO edto : eventtypeStockflowFixedAttributeMap.keySet()){
			
			for(StockflowDTO sfdto : eventtypeStockflowFixedAttributeMap.get(edto).keySet()){
				for(CustomTextBox ct : eventtypeStockflowFixedAttributeMap.get(edto).get(sfdto)){
					if(!ct.validate()){
						validationResult = false;
					}
				}
			}

		}
		
		// Stockflow Attributes
		for(EventDTO edto : eventtypeStockflowAttributeLabelMap.keySet()){
			for(StockflowDTO sfdto : eventtypeStockflowAttributeLabelMap.get(edto).keySet()){
				for(AttributeDTO adto : eventtypeStockflowAttributeLabelMap.get(edto).get(sfdto).keySet()){
					
					if(!eventtypeStockflowAttributeLabelMap.get(edto).get(sfdto).get(adto).validate()){
						validationResult = false;
					}
				}
			}
			
		}
		
		// Partcipation Attributes
		for(EventDTO edto : eventtypeParticipationAttributeLabelMap.keySet()){
			for(ParticipationDTO pdto : eventtypeParticipationAttributeLabelMap.get(edto).keySet()){
				
				for(AttributeDTO adto : eventtypeParticipationAttributeLabelMap.get(edto).get(pdto).keySet()){
					
					if(!eventtypeParticipationAttributeLabelMap.get(edto).get(pdto).get(adto).validate()){
						validationResult = false;
					}
				}
			}
			
		}
		
		return validationResult;
	}

	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
	
}
