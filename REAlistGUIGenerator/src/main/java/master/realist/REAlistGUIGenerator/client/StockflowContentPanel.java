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
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowDTO;

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

public class StockflowContentPanel extends VerticalPanel{

	// Logger
	private final static Logger logger = Logger.getLogger("StockflowContentPanelLogger");
			
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// stockflowDTO the Panel is created for
	private EventtypeStockflowDTO stockflow;
	
	// validator for double values (quantity and price per unit)
	private Validator doubleValidator = new NumericValidator();
	
	// Flextable for affected resources and additional attributes
	private FlexTable stockflowAttributeFlexTable = new FlexTable();
	private Label affectedResourceLabel = new Label("Affected Resource:");
	private ListBox affectedResourceListBox = new ListBox();
	private Label quantityLabel = new Label("Quantity");
	private CustomTextBox quantityTextBox = new CustomTextBox();
	private Label pricePerUnitLabel = new Label("Price per unit");
	private CustomTextBox pricePerUnitTextBox = new CustomTextBox();
	private VerticalPanel stockflowAttributePanel = new VerticalPanel();
		
	private HorizontalPanel stockflowAttributeSeriesPanel = new HorizontalPanel();
	private Button stockflowAttributeSeriesAddButton = new Button("Add Stockflow");
		
	// Arraylist of possible resources that can be affected in the eventtype
	ArrayList<ResourceDTO> possibleResources = new ArrayList<ResourceDTO>();
		
	// Map keeping track of attributeIDs and corresponding TextBoxes
	private Map<AttributeDTO,CustomTextBox> stockflowAttributeLabelMap = new HashMap<AttributeDTO,CustomTextBox>();

	// EventtypeDTO object the participation belongs to
	private EventtypeDTO eventtypeDTO;
		
	// EventDTO object the participation belongs to
	private EventDTO eventDTO;
		
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
		
	private List<EventtypeStockflowHasAdditionalAttributeDTO> existingEventtypeStockflowHasAdditionalAttributeDTOs = new ArrayList<EventtypeStockflowHasAdditionalAttributeDTO>();
			
	// Tabpanel object the ParticipationContentPanel is added to
	private TabPanel stockflowTabPanel = null;
		
	// map keeping track of eventtypes and their corresponding attributes + textboxes
	private Map<EventDTO,Map<StockflowDTO, Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap; 
		
	// stockflowdto object for current participation
	private StockflowDTO stockflowdto;
	
	// Map keeping track of the fixed stockflow attribute textboxes
	private Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap;
	
	// stockflowmap
	private Map<StockflowDTO, Map<AttributeDTO, CustomTextBox>> stockflowMap = new HashMap<StockflowDTO, Map<AttributeDTO, CustomTextBox>>();
	
	// stockflowfixedvaluemap
	private Map<StockflowDTO, ArrayList<CustomTextBox>> stockflowfixedvaluemap = new HashMap<StockflowDTO, ArrayList<CustomTextBox>>();
		
	
	/**
	 * Constructor
	 * @param stockflow
	 * @param eventdto
	 * @param stockflowTabPanel
	 * @param eventtypeStockflowAttributeLabelMap
	 * @param eventtypeDTO
	 */
	public StockflowContentPanel(EventtypeStockflowDTO stockflow, EventDTO eventdto , TabPanel stockflowTabPanel, 
			Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap, 
			EventtypeDTO eventtypeDTO, Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap, Map<StockflowDTO, Map<AttributeDTO, 
			CustomTextBox>> stockflowMap, Map<StockflowDTO, ArrayList<CustomTextBox>> stockflowfixedvaluemap){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();		
						
		// set stockflow
		this.stockflow = stockflow;
				
		// set eventtypeDTO
		this.eventtypeDTO = eventtypeDTO;
				
		// set tabpanel
		this.stockflowTabPanel = stockflowTabPanel;
				
		// set eventtypeAttributeLabelMap
		this.eventtypeStockflowAttributeLabelMap = eventtypeStockflowAttributeLabelMap;
				
		// set eventdto
		this.eventDTO = eventdto;

		// set stockflowdto and add it to the list of current eventdto
		this.stockflowdto = new StockflowDTO();
		this.eventDTO.getStockflows().add(stockflowdto);
				
		// set eventtypeStockflowFixedAttributeMap
		this.eventtypeStockflowFixedAttributeMap = eventtypeStockflowFixedAttributeMap;
		
		// set stockflowMap
		this.stockflowMap = stockflowMap;
		
		// set stockflowfixedvaluemap
		this.stockflowfixedvaluemap = stockflowfixedvaluemap;
		
		// populate StockflowContentPanel
		populateStockflowContentPanel();
		
	}
	
	/**
	 * Method populating the StockflowContentPanel
	 */
	private void populateStockflowContentPanel(){
		
		// apply styles for the StockflowContentPanel
		this.addStyleName("fullsizePanel");
		
		// apply styles for participationAttributePanel
		stockflowAttributePanel.addStyleName("fullsizePanel");
		
		// apply styles for participationAttributeFlexTable
		stockflowAttributeFlexTable.addStyleName("fullsizePanel");
		stockflowAttributeFlexTable.getColumnFormatter().addStyleName(0, "quartersizePanel");
		stockflowAttributeFlexTable.getColumnFormatter().addStyleName(1, "threequartersizePanel");
		stockflowAttributeFlexTable.setCellPadding(2);
				
		// apply style for  stockflowAgentListBox
		affectedResourceListBox.addStyleName("attributeTableTextBox");
				
		// ChangeHandler for affectedResourceListBox
		affectedResourceListBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event){
						
				// TODO: 
				Window.alert("Affected resource changed to : " + possibleResources.get(affectedResourceListBox.getSelectedIndex()).getName());
				// if change occured, reset resource
				stockflowdto.setResource(possibleResources.get(affectedResourceListBox.getSelectedIndex()));
					}
				});
				
				// adding participating agent labels to the flextable
				stockflowAttributeFlexTable.setWidget(0, 0, affectedResourceLabel);
				stockflowAttributeFlexTable.setWidget(0, 1, affectedResourceListBox);
				stockflowAttributeFlexTable.setWidget(1, 0, pricePerUnitLabel);
				stockflowAttributeFlexTable.setWidget(1, 1, pricePerUnitTextBox);

				// TODO: Only add when resource is bulk?
				stockflowAttributeFlexTable.setWidget(2, 0, quantityLabel);
				stockflowAttributeFlexTable.setWidget(2, 1, quantityTextBox);

				// adding validator (quantity + pericePerUnit)
				quantityTextBox.addValidator(doubleValidator);
				pricePerUnitTextBox.addValidator(doubleValidator);
				
				// add to the list of fixed stockflow textboxes for this event
				// only create new, if not already existing
				ArrayList<CustomTextBox> fixedAttributeBoxes = new ArrayList<CustomTextBox>();
				fixedAttributeBoxes.add(pricePerUnitTextBox);
				fixedAttributeBoxes.add(quantityTextBox);
				
				if(eventtypeStockflowFixedAttributeMap.get(eventDTO) == null){
					
					stockflowfixedvaluemap.put(stockflowdto, fixedAttributeBoxes);
					eventtypeStockflowFixedAttributeMap.put(eventDTO, stockflowfixedvaluemap);
					
				} else {
					
					eventtypeStockflowFixedAttributeMap.get(eventDTO).put(stockflowdto, fixedAttributeBoxes);
				
				}
				
				
				// populate affectedResourceListBox
				populateAffectedResourceListBox();
				
				// populate stockflowAttributeFlexTable
				populateStockflowAttributeFlexTable();
				
				// add the stockflowAttributeFlexTable to the stockflowAttributePanel
				stockflowAttributePanel.add(stockflowAttributeFlexTable);
				
				// define add series button functionality
				// listen for mouse events on the resourceAddButton
				stockflowAttributeSeriesAddButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						Window.alert("add new participation");
						stockflowTabPanel.add(new StockflowContentPanel(stockflow, eventDTO, stockflowTabPanel, 
											eventtypeStockflowAttributeLabelMap, eventtypeDTO, eventtypeStockflowFixedAttributeMap, stockflowMap, stockflowfixedvaluemap), stockflow.getResourcetypeId());		
					}
				});
				
				// populate stockflowAttributeSeriesPanel and add styles
				stockflowAttributeSeriesPanel.add(stockflowAttributeSeriesAddButton);
				stockflowAttributeSeriesPanel.setVisible(false);
				stockflowAttributeSeriesPanel.addStyleName("introductionLabel");
				
				// add stockflowAttributeSeriesPanel to stockflowAttributePanel
				stockflowAttributePanel.add(stockflowAttributeSeriesPanel);
				// set the panel visible if participation is a series
				if(stockflow.getIsSeries()){
					stockflowAttributeSeriesPanel.setVisible(true);
				}
				
				// add the stockflowAttributePanel to the StockflowContentPanel
				this.add(stockflowAttributePanel);
	}
	
	
	/**
	 * Method populating the affectedResourceListBox
	 */
	private void populateAffectedResourceListBox(){
		
		// if no resourcetype exist in the REA DB, the values of the provideagent and
		// receiveagent listboxes are set to "No Agents found"
		if(reaDBEntryContainer.getExistingAgentDTOs().size() == 0){
			String noResourceString = "No resources found";
			affectedResourceListBox.addItem(noResourceString);
			return;
		}
				
		// list of possible Provide Agents
		possibleResources.clear();
		
		for(ResourceDTO rdto : reaDBEntryContainer.getExistingResourceDTOs()){
			
			// currently agenttypes can only have one agenttye
			ResourcetypeDTO resourcetype = rdto.getResourcetype();
			
			// if resourcetype in resourcelist equals resourcetype for current stockflow
			// add the resourceDTO to the possibleResources list and add the Id as Item in the affectedResourceListBox
			if(resourcetypeFits(resourcetype)){

				possibleResources.add(rdto);
				affectedResourceListBox.addItem(rdto.getName());
			}
			
		}
		
		// if no resource exist for selection
		if (affectedResourceListBox.getItemCount() == 0){
			
			String noResourcesString = "No matches found";
			affectedResourceListBox.addItem(noResourcesString);
			stockflowdto.setResource(null);
			
		} else {
			stockflowdto.setResource(possibleResources.get(affectedResourceListBox.getSelectedIndex()));
		}
			
	}
	
	
	/**
	 * Method populating the StockflowAttributeFlexTable with the additional attributes
	 */
	private void populateStockflowAttributeFlexTable(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<EventtypeStockflowHasAdditionalAttributeDTO>> callback = new AsyncCallback<List<EventtypeStockflowHasAdditionalAttributeDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getEventtypeParticipationsHasAdditionalAttribtes()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<EventtypeStockflowHasAdditionalAttributeDTO> result) {
				
				existingEventtypeStockflowHasAdditionalAttributeDTOs.clear();
				
				for(EventtypeStockflowHasAdditionalAttributeDTO etsfhaa : result){
					
					// adding the EventtypeStockflowHasAdditionalAttributeDTO to the existingEventtypeStockflowHasAdditionalAttributeDTOs arrayList
					existingEventtypeStockflowHasAdditionalAttributeDTOs.add(etsfhaa);
					
					// attribute Label and CustomTextBox
					Label attributeLabel = new Label(etsfhaa.getAttribute().getName() + ":");
					CustomTextBox attributeTextBox = new CustomTextBox();
					
					// Validator for the CustomTextBox
					Validator attributeValidator;
					
					// setting Validations depending on the datatype
				 	if (etsfhaa.getAttribute().getDatatype().equals("INT") || etsfhaa.getAttribute().getDatatype().equals("DOUBLE")){
				 		
				  		attributeValidator = new NumericValidator();
				  	
				   	} else if (etsfhaa.getAttribute().getDatatype().equals("VARCHAR")){
						    		
				   		attributeValidator = new TextValidator(45);
						    	
				   	} else if (etsfhaa.getAttribute().getDatatype().equals("BOOLEAN")){
						    	
				   		attributeValidator = new BooleanValidator();
						    	
				   	} else {		
						    	
				   		attributeValidator = new DateValidator();
						    	
				   	}	
						    	
				   	// adding validator to attributeTextBox
				   	attributeTextBox.addValidator(attributeValidator);
								
					int row = stockflowAttributeFlexTable.getRowCount();

					stockflowAttributeFlexTable.setWidget(row, 0, attributeLabel);
					stockflowAttributeFlexTable.setWidget(row, 1, attributeTextBox);
											
					// adding entries to stockflowAttributeLabelMap that stores all additional stockflow attributes
					stockflowAttributeLabelMap.put(etsfhaa.getAttribute(),attributeTextBox);
				}
				
				stockflowMap.put(stockflowdto, stockflowAttributeLabelMap);
				eventtypeStockflowAttributeLabelMap.put(eventDTO, stockflowMap);
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getEventtypeStockflowHasAdditionalAttributes(stockflow, callback);
		
	}
	
	
	/**
	 * Method checking if an  resourcetype (or one of its parent resourcetypes) fits the type needed in the stockflow.
	 * @param resourcetype
	 * @return agenttypefits
	 */
	private boolean resourcetypeFits(ResourcetypeDTO resourcetype){
		
		boolean resourcetypefits = false;
		
		ResourcetypeDTO currentresourcetype = resourcetype;
		
		if(stockflow.getResourcetypeId().equals(currentresourcetype.getId())){
			resourcetypefits = true;
		} else if(resourcetype.getParentResourcetype() != null){
			// check resourcetypes of parentresources
			currentresourcetype = resourcetype.getParentResourcetype();
			resourcetypefits = resourcetypeFits(currentresourcetype);
		} else {
			resourcetypefits = false;
		}
		
		return resourcetypefits;
	}
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
		
	}
}
