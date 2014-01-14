package master.realist.REAlistGUIGenerator.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.BooleanValidator;
import master.realist.REAlistGUIGenerator.shared.DateValidator;
import master.realist.REAlistGUIGenerator.shared.NumericValidator;
import master.realist.REAlistGUIGenerator.shared.TextValidator;
import master.realist.REAlistGUIGenerator.shared.Validator;
import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResourcePanel extends VerticalPanel{
	
	// Logger
	private final static Logger logger = Logger.getLogger("ResourcePanelLogger");

	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// Panel for Resource Administration + Resource ArrayList
	private Label resourceSelectionIntroductionLabel = new Label("Resource Administration");
	private HorizontalPanel resourceTableAndAddEditPanel = new HorizontalPanel();
	private FlexTable resourceSelectionFlexTable = new FlexTable();
	private VerticalPanel resourceAddEditPanel = new VerticalPanel();
	private Label resourceAddEditHeader = new Label("Create or update Resource: ");
	private FlexTable resourceAddEditFlexTable = new FlexTable();
	private Label resourceIdLabel = new Label("ResourceId:");
	private TextBox resourceIdTextTextBox = new TextBox();
	private Label resourceNameLabel = new Label("Resourcename:");
	private CustomTextBox resourceNameTextBox = new CustomTextBox();
	private Label resourceTypeLabel = new Label("Resourcetype:");
	private ListBox resourceTypeListBox = new ListBox();
	private Label resourceIsBulkLabel = new Label("Bulkresource:");
	private CustomTextBox resourceIsBulkTextBox = new CustomTextBox();
	private Label resourceIsIdentifiableLabel = new Label("Identifiable:");
	private CustomTextBox resourceIsIdentifiableTextBox = new CustomTextBox();
	private Label resourceQoHLabel = new Label("QoH:");
	private CustomTextBox resourceQoHTextBox = new CustomTextBox();
	private Label resourceIsComposedLabel = new Label("Composed:");
	private CustomTextBox resourceIsComposedTextBox = new CustomTextBox();
	private Button resourceOkButton = new Button("Ok");
	private HorizontalPanel resourceAddPanel = new HorizontalPanel();
	private Button resourceAddButton = new Button("Add");	

	// Arraylist for existing resourcetypes
	private ArrayList<ResourcetypeDTO> existingResourcetypeDTOs = new ArrayList<ResourcetypeDTO>();
		
	// flag that specifies if a user wants to save or update
	private boolean resourceSaveActionState = true;
	// DualityStatusDTO object that should be updated
	private ResourceDTO resourceUpdateObject = new ResourceDTO();
	// Updated ResourceDTO object
	private ResourceDTO updatedResourceObject;
		
	// Async READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);	
		
	// currently selected ResourcetypeDTO in resourceTypeListBox + index
	private ResourcetypeDTO selectedResourcetypeDTOInListBox;
	private int selectedIndexInListBox;
		
	// Map keeping track of attributeDTOs and corresponding TextBoxes
	private Map<AttributeDTO,CustomTextBox> attributeLabelMap;
	
	
	/**
	 * Constructor
	 */
	public ResourcePanel(){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();
		
	}
	
	
	/**
	 * Method populating the Resource Panel
	 */
	public void populateResourcePanel(){
		
		// get all the existing resourcetypes (they are added to the existingResourcetypeDTOs arrayList)
		callGetResourcetypes();
		
		// Populating the flex table for the selection of resources 
		resourceSelectionFlexTable.setText(0, 0, "Id");
		resourceSelectionFlexTable.setText(0, 1, "Name");
		resourceSelectionFlexTable.setText(0, 2, "Resourcetype");
		resourceSelectionFlexTable.setText(0, 3, "IsBulk");
		resourceSelectionFlexTable.setText(0, 4, "IsIdentifiable");
		resourceSelectionFlexTable.setText(0, 5, "IsComposed");
		resourceSelectionFlexTable.setText(0, 6, "QoH");
		resourceSelectionFlexTable.setText(0, 7, "Edit");
		resourceSelectionFlexTable.setText(0, 8, "Remove");
				
		// setting padding of 4 to the cells of the resourceSelectionFlexTable
		resourceSelectionFlexTable.setCellPadding(4);
		
		// Add styles to elements in the resourceSelectionFlexTable
		resourceSelectionFlexTable.getRowFormatter().addStyleName(0, "adminFlexTableHeader");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 1, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 2, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 3, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 4, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 5, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 6, "adminFlexTableColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 7, "adminFlexTableEditRemoveColumn");
		resourceSelectionFlexTable.getCellFormatter().addStyleName(0, 8, "adminFlexTableEditRemoveColumn");
		resourceSelectionFlexTable.addStyleName("adminFlexTable");			
		
		// populate resourceSelectionFlexTable
		populateResourceSelectionFlexTable();
		
		// define style for resourceSelectionIntroductionLabel
		resourceSelectionIntroductionLabel.addStyleName("introductionLabel");
	
		// Adding the headline label to the resourceSelectionPanel
		this.add(resourceSelectionIntroductionLabel);
			
		// Adding the resourceSelectionFlexTable to the resourceTableAndAddEditPanel
		resourceTableAndAddEditPanel.add(resourceSelectionFlexTable);
		
		// setting validators for resource fixed textboxes
		Validator resourceNameValidator = new TextValidator(45);
		Validator resourceBooleanValidtor = new BooleanValidator();
		Validator resourceDoubleValidator = new NumericValidator();
		
		resourceNameTextBox.addValidator(resourceNameValidator);
		resourceIsBulkTextBox.addValidator(resourceBooleanValidtor);
		resourceIsIdentifiableTextBox.addValidator(resourceBooleanValidtor);
		resourceQoHTextBox.addValidator(resourceDoubleValidator);
		resourceIsComposedTextBox.addValidator(resourceBooleanValidtor);
		
		// applying styles to the resourceAddEditHeader and adding it to the resourceAddEditPanel
		resourceAddEditHeader.addStyleName("addEditHeaderLabel");
		resourceAddEditPanel.add(resourceAddEditHeader);
		
		// resourceIsBulkTextBox and resourceIsIdentifiableTextBox can not be edited
		// the values are set depending on the chosen resourcetype
		resourceIsBulkTextBox.setReadOnly(true);
		resourceIsIdentifiableTextBox.setReadOnly(true);
		
		// Populating the resourceAddEditFlexTable
		resourceAddEditFlexTable.setWidget(0, 0, resourceIdLabel);
		resourceAddEditFlexTable.setWidget(0, 1, resourceIdTextTextBox);
		resourceAddEditFlexTable.setWidget(1, 0, resourceNameLabel);
		resourceAddEditFlexTable.setWidget(1, 1, resourceNameTextBox);
		resourceAddEditFlexTable.setWidget(2,0, resourceTypeLabel);
		resourceAddEditFlexTable.setWidget(2, 1, resourceTypeListBox);
		resourceAddEditFlexTable.setWidget(3,0, resourceIsBulkLabel);
		resourceAddEditFlexTable.setWidget(3, 1, resourceIsBulkTextBox);
		resourceAddEditFlexTable.setWidget(4,0, resourceIsIdentifiableLabel);
		resourceAddEditFlexTable.setWidget(4, 1, resourceIsIdentifiableTextBox);
		resourceAddEditFlexTable.setWidget(5,0, resourceIsComposedLabel);
		resourceAddEditFlexTable.setWidget(5, 1, resourceIsComposedTextBox);
		
		// adding the resourceAddEditFlexTable to the resourceAddEditPanel
		resourceAddEditPanel.add(resourceAddEditFlexTable);
		resourceAddEditPanel.setVisible(false);
		
		// set resourceIdTextTextBox to read only
		resourceIdTextTextBox.setReadOnly(true);
		
		// applying style for Ok Button
		resourceOkButton.addStyleDependentName("ok");
		resourceAddEditPanel.add(resourceOkButton);
		
		// applying style to the resourceAddEditPanel
		resourceAddEditPanel.addStyleName("adminFlexTable");
		resourceAddEditPanel.addStyleName("addEditPanel");
		
		// adding the resourceAddEditPanel to the resourceTableAndAddEditPanel
		resourceTableAndAddEditPanel.add(resourceAddEditPanel);
		
		// Adding the resourceSelectionEmptyMessageFlexTablePanel to the resourcePanel
		this.add(resourceTableAndAddEditPanel);	
		
		// adding style to resourceChooseAddPanel
		resourceAddPanel.addStyleName("addPanel");
		resourceAddPanel.addStyleName("fullsizePanel");
		
		// adding style to the resourceAddButton
		resourceAddButton.addStyleDependentName("add");
			
		// Populating the resourceAddPanel (horizontal) and adding it to the resourceSelectionPanel
		resourceAddPanel.add(resourceAddButton);
		
		// listen for mouse events on the resourceAddButton
		resourceAddButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				updateResourceAddEditPanel(null);
						
			}
		});
								
		// listen for mouse events in the resourceOkButton
		resourceOkButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addNewResource();
			}
		});
		
		// Listen for changes on resourceTypeListBox
		resourceTypeListBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event){
						
				// set the current selected selectedResourcetypeDTOInListBox and selectedIndexInListBox
				for(int i = 0; i < existingResourcetypeDTOs.size(); i++){
					if(existingResourcetypeDTOs.get(i).getId().equals(resourceTypeListBox.getItemText(resourceTypeListBox.getSelectedIndex()))){
						selectedIndexInListBox = i;
						selectedResourcetypeDTOInListBox = existingResourcetypeDTOs.get(i);
						break;
					}
				}
						
				if(resourceSaveActionState){

					resetBulkAndIdentifiableTextBox();
					addAdditionalAttributesForSelectedResourcetype(false);
				}else{

					resetBulkAndIdentifiableTextBox();
					addAdditionalAttributesForSelectedResourcetype(false);
				}
						
			}
		});
		
		// adding the resourceAddPanel to the resource panel
		this.add(resourceAddPanel);
		
	}
	
	/**
	 * Calling the getResourcetypes method of the READBService
	 */
	private void callGetResourcetypes(){
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<List<ResourcetypeDTO>> callback = new AsyncCallback<List<ResourcetypeDTO>>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("getResourcetypes()");
		    	caught.printStackTrace();
			}

			public void onSuccess(List<ResourcetypeDTO> result) {
				
				existingResourcetypeDTOs.clear();
				
				boolean firstIsSet = false;
				
				for(ResourcetypeDTO rtdto : result){
					
					// firstRetrievedResourcetypeDTO is set to the first atdto
					if(!firstIsSet){
						
						selectedResourcetypeDTOInListBox = rtdto;
						
						// reset the values in the IsBulk and IsIdentifiable Textbox
						resetBulkAndIdentifiableTextBox();
						
						firstIsSet = true;
					}
					
					// adding the ResourcetypeDTOs to the existingResourcetypeDTOs arrayList
					existingResourcetypeDTOs.add(rtdto);
					
					// populate the resourceTypeListBox with the retrieved resourcetypes
					resourceTypeListBox.addItem(rtdto.getId());
					
				}
				
			}
	    	
	    };
	    
	 // Make the call
	    reaDBSvc.getResourcetypes(callback);
	}
	
	
	/**
	 * Calling the getResources method of the READBService
	 */
	private void populateResourceSelectionFlexTable(){
	    
		// resources were already loaded on startup
		// therefore the list in the reaDBEntryContainer is taken
		for(ResourceDTO rdto : reaDBEntryContainer.getExistingResourceDTOs()){
			
			// final variable needed for the button specifications
			final ResourceDTO currentResourceDTO = rdto;
			
			// Buttons to edit and delete resources
			Button updateResourceButton = new Button("Update");
			updateResourceButton.addStyleDependentName("removeupdate");

			updateResourceButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					updateResourceAddEditPanel(currentResourceDTO);
				}
			});	
			
			Button deleteResourceButton = new Button("X");
			deleteResourceButton.addStyleDependentName("removeupdate");
			
			deleteResourceButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					
					deleteResource(currentResourceDTO);
				}
			});
			
			ResourcetypeDTO resourcetypeDTO = rdto.getResourcetype();
			
			int row = resourceSelectionFlexTable.getRowCount();
			resourceSelectionFlexTable.setText(row, 0, String.valueOf(rdto.getId()));
			resourceSelectionFlexTable.setText(row, 1, rdto.getName());
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
			resourceSelectionFlexTable.setText(row, 2, resourcetypeDTO.getId());
			resourceSelectionFlexTable.setText(row, 3, String.valueOf(rdto.isBulk()));
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableColumn");
			resourceSelectionFlexTable.setText(row, 4, String.valueOf(rdto.isIdentifiable()));
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 4, "adminFlexTableColumn");
			resourceSelectionFlexTable.setText(row, 5, String.valueOf(rdto.getIsComposed()));
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 5, "adminFlexTableColumn");
			
			if(rdto.getQoH() % 1 != 0){
				resourceSelectionFlexTable.setText(row, 6, String.valueOf(rdto.getQoH()));
			} else {
				resourceSelectionFlexTable.setText(row, 6, String.valueOf(rdto.getQoH()).substring(0, String.valueOf(rdto.getQoH()).indexOf(".")));
			}
			
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 6, "adminFlexTableColumn");
			resourceSelectionFlexTable.setWidget(row, 7, updateResourceButton);
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 7, "adminFlexTableEditRemoveColumn");
			resourceSelectionFlexTable.setWidget(row, 8, deleteResourceButton);
			resourceSelectionFlexTable.getCellFormatter().addStyleName(row, 8, "adminFlexTableEditRemoveColumn");
			
		}
	   
	}
	
	
	/**
	 * Updating the RresourceAddEditPanel
	 */
	private void updateResourceAddEditPanel(ResourceDTO resourceDTO){
		
		// reset styles of fixed attribute textboxes
		resourceNameTextBox.removeStyles();
		resourceIsBulkTextBox.removeStyles();
		resourceIsIdentifiableTextBox.removeStyles();
		resourceQoHTextBox.removeStyles();
		resourceIsComposedTextBox.removeStyles();
		
		// Check if the resourceDTO object is null
		// If so the textboxes are granted for adding new resources without content
		if(resourceDTO == null){
			
			// setting the values of the resources
			resourceSaveActionState = true;
			
			if(reaDBEntryContainer.getExistingResourceDTOs().size()>0){
				int lastId = reaDBEntryContainer.getExistingResourceDTOs().get(reaDBEntryContainer.getExistingResourceDTOs().size()-1).getId();
				resourceIdTextTextBox.setText(String.valueOf(lastId+1));
				
			}else{	
				resourceIdTextTextBox.setText("-");	
			}
			
			resourceNameTextBox.setText("");
			resourceTypeListBox.setItemSelected(0, true);
			
			// reset selected index and ResourceDTO in ListBox
			selectedIndexInListBox = 0;
			selectedResourcetypeDTOInListBox = existingResourcetypeDTOs.get(0);
			
			resourceQoHTextBox.setText("");
			resourceIsComposedTextBox.setText("");
			
			// update the values of the isBulk and IsIdentifiable textbox
			resetBulkAndIdentifiableTextBox();
			
			// adding the additional attribute labels and textfields for the initially selected resourcetype
			addAdditionalAttributesForSelectedResourcetype(false);
			
		} else{
			
			resourceSaveActionState = false;
			
			// copy the current state of the resourceDTO object
			resourceUpdateObject.setId(resourceDTO.getId());
			resourceUpdateObject.setName(resourceDTO.getName());
			resourceUpdateObject.setResourcetype(resourceDTO.getResourcetype());
			resourceUpdateObject.setBulk(resourceDTO.isBulk());
			resourceUpdateObject.setIdentifiable(resourceDTO.isIdentifiable());
			resourceUpdateObject.setQoH(resourceDTO.getQoH());
			resourceUpdateObject.setIsComposed(resourceDTO.getIsComposed());
			resourceUpdateObject.setAdditionalAttributeValues(resourceDTO.getAdditionalAttributeValues());
						
			// copy the resourceDTO object itself
			updatedResourceObject = resourceDTO;
			
			// setting the values of saved id, name, and resourcetype and so on
			resourceIdTextTextBox.setText(String.valueOf(resourceDTO.getId()));
			resourceNameTextBox.setText(resourceDTO.getName());
			ResourcetypeDTO resourcetypeDTO = resourceDTO.getResourcetype();
			
			int selectedIndex = 0;
			
			for(int i = 0; i < existingResourcetypeDTOs.size(); i++){
				if(existingResourcetypeDTOs.get(i).getId().equals(resourcetypeDTO.getId())){
					selectedIndex = i;
					break;
				}
			}
			
			resourceTypeListBox.setItemSelected(selectedIndex, true);
			
			resourceIsBulkTextBox.setText(String.valueOf(resourceDTO.isBulk()));
			resourceIsIdentifiableTextBox.setText(String.valueOf(resourceDTO.isIdentifiable()));
			resourceQoHTextBox.setText(String.valueOf(resourceDTO.getQoH()));
			resourceIsComposedTextBox.setText(String.valueOf(resourceDTO.getIsComposed()));
			
			selectedIndexInListBox = selectedIndex;
			selectedResourcetypeDTOInListBox = existingResourcetypeDTOs.get(selectedIndexInListBox);
			
			// adding the additional attribute labels and textfields for the initially selected resourcetype (with saved values)
			addAdditionalAttributesForSelectedResourcetype(true);
			
		}
		
		resourceAddEditPanel.setVisible(true);
		resourceNameTextBox.setFocus(true);	
	}
	
	
	/**
	 * Method adding the additional Attribute Textboxes and Labels for specific resourcetypes
	 */
	private void addAdditionalAttributesForSelectedResourcetype(boolean isUpdate){
		
		// create attributeLabelMap
		attributeLabelMap = new HashMap<AttributeDTO,CustomTextBox>();
					
		// get the additional Attribute DTo set for the selected resourcetype
		Set<AttributeDTO> resourcetypeAttributes = selectedResourcetypeDTOInListBox.getAttributes();
		
		if(!isUpdate){
			
			if(resourcetypeAttributes != null){
				
				int row = resourceAddEditFlexTable.getRowCount();
				
				// the treashold vor fixed attributes either is 7 or 6 depending on the fact if a resource is bulk or not
				int treshold = 7;
				if (!selectedResourcetypeDTOInListBox.isBulk()){treshold = 6;};
				
				// if more than 7 exist. delete all but the first three
				if(row > treshold){
					for(int i = treshold; i < row; i++){
						resourceAddEditFlexTable.removeRow(treshold);
					}
				}
				
				for(AttributeDTO adto : resourcetypeAttributes){
					Label attributeLabel = new Label(adto.getName() + ":");
					CustomTextBox attributeTextBox = new CustomTextBox();
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
					
					row = resourceAddEditFlexTable.getRowCount();
					resourceAddEditFlexTable.setWidget(row, 0, attributeLabel);
					resourceAddEditFlexTable.setWidget(row, 1, attributeTextBox);
							
					// adding entries to eventAttributeMap that stores all additional resourcetype attributes
					attributeLabelMap.put(adto,attributeTextBox);
				}

			}
		} else{
			
			if(resourcetypeAttributes != null){
				
				// get additional attribute values for resourceDTO that should be updated
				Set<ResourceHasAdditionalattributevalueDTO> additionalattributevalues = resourceUpdateObject.getAdditionalAttributeValues();
							
				int row = resourceAddEditFlexTable.getRowCount();
					
				// the treashold vor fixed attributes either is 7 or 6 depending on the fact if a resource is bulk or not
				int treshold = 7;
				if (!selectedResourcetypeDTOInListBox.isBulk()){treshold = 6;};
				
				
				// if more than 7 exist. delete all but the first three
				if(row > treshold){
					for(int i = treshold; i < row; i++){
						resourceAddEditFlexTable.removeRow(treshold);
					}
				}

				// run through all existing additional attribute values
				for(ResourceHasAdditionalattributevalueDTO rdto : additionalattributevalues){
					
					Label attributeLabel = new Label(rdto.getAttribute().getName() + ":");
					CustomTextBox attributeTextBox = new CustomTextBox();
					String textBoxContent = "";
					Validator attributeValidator;
					
					// set textBoxContent and Validators (depending on datatypes)
					if (rdto.getAttribute().getDatatype().equals("INT") || rdto.getAttribute().getDatatype().equals("DOUBLE")){
						
						//textBoxContent= String.valueOf(rdto.getNumericValue());
						textBoxContent = String.valueOf(rdto.getNumericValue());
						attributeValidator = new NumericValidator();
						
			    	} else if (rdto.getAttribute().getDatatype().equals("VARCHAR")){
			    		
			    		textBoxContent = String.valueOf(rdto.getTextualValue());
			    		attributeValidator = new TextValidator(45);
			    		
			    	} else if (rdto.getAttribute().getDatatype().equals("BOOLEAN")){
			    		
			    		textBoxContent = String.valueOf(rdto.getBooleanValue());
			    		attributeValidator = new BooleanValidator();

			    	} else {
			    	
			    		textBoxContent = DateTimeFormat.getFormat("yyyy-MM-dd").format(rdto.getDatetimeValue());
			    		attributeValidator = new DateValidator();
			    		
			    	}
					
					// set the text for the textbox
					attributeTextBox.setText(textBoxContent);
					
					// adding the validator for the textbox
					attributeTextBox.addValidator(attributeValidator);

					row = resourceAddEditFlexTable.getRowCount();
					resourceAddEditFlexTable.setWidget(row, 0, attributeLabel);
					resourceAddEditFlexTable.setWidget(row, 1, attributeTextBox);
										
					// adding entries to eventAttributeMap that stores all additional resourcetype attributes
					attributeLabelMap.put(rdto.getAttribute(),attributeTextBox);
				}
				
			}
		}	
	}
	
	
	/**
	 * Adding new Resource
	 * Calling the addResource method of the READBService
	 * 
	 */
	private void addNewResource(){
		
		// Only add if the validation does not fail
		if(!resourceTextBoxValidationResult()){
			return;
		}
		
		// check if the actionstate is 'save'
		if(!resourceSaveActionState){

			
			int indexOfUpdateObject = reaDBEntryContainer.getExistingResourceDTOs().indexOf(updatedResourceObject);
			String oldName = resourceUpdateObject.getName();
			String updatedName = resourceNameTextBox.getText();
			boolean oldIsBulk = resourceUpdateObject.isBulk();
			boolean updatedIsBulk = Boolean.valueOf(resourceIsBulkTextBox.getText());
			boolean oldIsIdentifiable = resourceUpdateObject.isIdentifiable();
			boolean updatedIsIdentifiable = Boolean.valueOf(resourceIsIdentifiableTextBox.getText());
			double oldQoH = resourceUpdateObject.getQoH();
			double updatedQoH = Double.parseDouble(resourceQoHTextBox.getText());
			boolean oldIsComposed = resourceUpdateObject.getIsComposed();
			boolean updatedIsComposed = Boolean.valueOf(resourceIsComposedTextBox.getText());

			ResourcetypeDTO resourcetypeDTO = resourceUpdateObject.getResourcetype();
			String oldresourcetype = resourcetypeDTO.getName();
			String newresourcetype = resourceTypeListBox.getItemText(resourceTypeListBox.getSelectedIndex());
			
			// flags helping to distinguish between different updates
			boolean resourceNameChange = false;
			boolean resourceTypeChange = false;
			boolean resourceIsBulkChange = false;
			boolean resourceIsIdentifiableChange = false;
			boolean resourceQoHChange = false;
			boolean resourceIsComposedChange = false;
			boolean resourceAttributeValueChange = false;
	
		
			// check if resource name has changed. If so, set resourceNameChange to true
			if(!oldName.matches(updatedName)){
				resourceNameChange = true;
				updatedResourceObject.setName(updatedName);
			}
			
			// check if resource isBulk attribute has changed. If so, set resourceIsBulkChange to true
			if(!(oldIsBulk == updatedIsBulk)){
				resourceIsBulkChange = true;
				updatedResourceObject.setBulk(updatedIsBulk);
			}
			
			// check if resource IsIdentifiable attribute has changed. If so, set resourceIsIdentifiableChange to true
			if(!(oldIsIdentifiable == updatedIsIdentifiable)){
				resourceIsIdentifiableChange = true;
				updatedResourceObject.setIdentifiable(updatedIsIdentifiable);
			}
			
			// check if resource QoH attribute has changed. If so, set resourceQoHChange to true
			if(!(oldQoH == updatedQoH)){
				resourceQoHChange = true;
				updatedResourceObject.setQoH(updatedQoH);
			}		
			
			// check if resource isComposed attribute has changed. If so, set resourceIsComposedChange to true
			if(!(oldIsComposed == updatedIsComposed)){
				resourceIsComposedChange = true;
				updatedResourceObject.setIsComposed(updatedIsComposed);
			}
			
			// only when resourcetype stays the same, the additional attributes are compared
			if(oldresourcetype.matches(newresourcetype)){
				
				if(!additionalAttributeEquality()){
					
					// set resourceAttributeValueChange to true 
					resourceAttributeValueChange = true;
					Set<ResourceHasAdditionalattributevalueDTO> updatedAttributeValues = 
							new LinkedHashSet<ResourceHasAdditionalattributevalueDTO>(resourceUpdateObject.getAdditionalAttributeValues().size());
					
					// examine all attributeDTOs in the attributeLabelMap
					for(ResourceHasAdditionalattributevalueDTO attributeValue : resourceUpdateObject.getAdditionalAttributeValues()){
						
						// setting the new attributes
						String textBoxContent = attributeLabelMap.get(attributeValue.getAttribute()).getText();
					
						// setting the value depending on the datatype
				    	if (attributeValue.getAttribute().getDatatype().equals("INT") || attributeValue.getAttribute().getDatatype().equals("DOUBLE")){
				    		attributeValue.setNumericValue(Double.parseDouble(textBoxContent));
				    	} else if (attributeValue.getAttribute().getDatatype().equals("VARCHAR")){
				    		attributeValue.setTextualValue(textBoxContent);
				    	} else if (attributeValue.getAttribute().getDatatype().equals("BOOLEAN")){
				    		attributeValue.setBooleanValue(Boolean.valueOf(textBoxContent));
				    	} else {		
				    		Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(textBoxContent);
				    		attributeValue.setDatetimeValue(date);
				    	}
				    	
				    	// adding the updated attribute values to the updatedAttributeValues set
				    	updatedAttributeValues.add(attributeValue);
						
					} 
					
					// setting the new additional attribute value set for the updatedResourceObject
					updatedResourceObject.setAdditionalAttributeValues(updatedAttributeValues);
					
				} 
				
			} else {
				
				// set resourceTypeChange to true and set new resourcetype
				resourceTypeChange = true;
				
				// set resourceAttributeValueChange to true
				resourceAttributeValueChange = true;
			
				for(ResourcetypeDTO rtDTO : existingResourcetypeDTOs){
			    	if(rtDTO.getId().equals(newresourcetype)){
			    		updatedResourceObject.setResourcetype(rtDTO);
			    		break;
			    	}
			    }  
				
				// also update the additional attribute values, since they are new
				updatedResourceObject.setAdditionalAttributeValues(createAttributeValueSetForSelectedResourcetype(updatedResourceObject));
				
			}
			
			// If name, type, or attributes have changes --> update resource. If not, show Message
			if(resourceNameChange || resourceTypeChange || resourceIsBulkChange || resourceIsIdentifiableChange
					|| resourceQoHChange || resourceIsComposedChange || resourceAttributeValueChange){
				
				Window.alert("Call Update");
				updateResource(updatedResourceObject,indexOfUpdateObject,resourceNameChange,resourceTypeChange,
						resourceIsBulkChange, resourceIsIdentifiableChange, resourceQoHChange, resourceIsComposedChange,resourceAttributeValueChange);
				
			} else {
				Window.alert("Nothing has been updated");
			}
			
			return;
		}
		
		// SAVING
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<ResourceDTO> callback = new AsyncCallback<ResourceDTO>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("saveResource()");
		    	caught.printStackTrace();
			}

			public void onSuccess(ResourceDTO result) {
				
				Window.alert("New Resource '" + result.getName() + "' added to REA DB with Id " + result.getId() );
				
				// adding the added resourceDTO to the list of resourceDTOs
				reaDBEntryContainer.getExistingResourceDTOs().add(result);
				
				final ResourceDTO savedResourceDTO = result;
				
				// Buttons to edit and delete resource
				Button updateResourceButton = new Button("Update");
				updateResourceButton.addStyleDependentName("removeupdate");
				
				updateResourceButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						updateResourceAddEditPanel(savedResourceDTO);
					}
				});	
				
				Button deleteResourceButton = new Button("X");
				deleteResourceButton.addStyleDependentName("removeupdate");
				
				deleteResourceButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						deleteResource(savedResourceDTO);
						
					}
				});	
				
				// get resourcetypeDTO
				ResourcetypeDTO rtdto = result.getResourcetype();
				
				// adding the new resource to the resourceSelectionFlexTable
				int row = resourceSelectionFlexTable.getRowCount();
				
				resourceSelectionFlexTable.setText(row, 0, String.valueOf(result.getId()));
				resourceSelectionFlexTable.setText(row, 1, result.getName());
				resourceSelectionFlexTable.setText(row, 2, rtdto.getId());
				resourceSelectionFlexTable.setText(row, 3, String.valueOf(result.isBulk()));
				resourceSelectionFlexTable.setText(row, 4, String.valueOf(result.isIdentifiable()));
				resourceSelectionFlexTable.setText(row, 5, String.valueOf(result.getIsComposed()));
				
				if(result.getQoH() % 1 != 0){

					resourceSelectionFlexTable.setText(row, 6, String.valueOf(result.getQoH()));
				} else {

					resourceSelectionFlexTable.setText(row, 6, String.valueOf(result.getQoH()).substring(0, String.valueOf(result.getQoH()).indexOf(".")));
				}
				
				// resourceSelectionFlexTable.setText(row, 6, String.valueOf(result.getQoH()));
				resourceSelectionFlexTable.setWidget(row, 7, updateResourceButton);
				resourceSelectionFlexTable.setWidget(row, 8, deleteResourceButton);
				
				// updating the updateResourceAddEditPanel
				updateResourceAddEditPanel(null);
			}
	    	
	    };
	
	    // Creating a resourceDTO object that will be added to DB and table
	    ResourceDTO resourceDTO;
		
	    try {
			
	    	resourceDTO = createResourceDTO();
			
			// Make the call
		    reaDBSvc.saveResource(resourceDTO, callback);
		    
		} catch (ParseException e) {
			
			e.printStackTrace();
			logger.info("SimpleDateformate was not converted correctly.");
		}
	}
	
	
	/**
	 * Updating a Resource from the REA DB
	 * 
	 */
	private void updateResource(ResourceDTO updatedResourceDTO, int listUpdateIndex, boolean resourceNameChange, boolean resourceTypeChange, 
			boolean resourceIsBulkChange, boolean resourceIsIdentifiableChange, boolean resourceQoHChange, boolean resourceIsComposedChange, boolean agentAttributeValueChange){
		
		final int updatedListIndex = listUpdateIndex;
		final boolean changedName = resourceNameChange;
		final boolean changedType = resourceTypeChange;
		final boolean changedIsBulk = resourceIsBulkChange;
		final boolean changedIsIdentifiable = resourceIsIdentifiableChange;
		final boolean changedQoH = resourceQoHChange;
		final boolean changedIsComposed = resourceIsComposedChange;
		final boolean changedAttributeValue = agentAttributeValueChange;
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }	
	    
	    // Set up the callback object.
	    AsyncCallback<ResourceDTO> callback = new AsyncCallback<ResourceDTO>() {
	    	
	    	public void onFailure(Throwable caught) {
	    		logREADBRPCFailure("updateResource()");
		    	caught.printStackTrace();
			}
	    	
	    	public void onSuccess(ResourceDTO result) {
	    		
	    		// print update message
	    		String updateResourceStatusMsg = "";
	    		if(changedName){
	    			updateResourceStatusMsg += "Name of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
							+ resourceUpdateObject.getName() + "' to '" + updatedResourceObject.getName() + "'. \n";
	    		}
	    		
	    		if(changedType){
	    			updateResourceStatusMsg += "Resourcetype of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
	    					+ resourceUpdateObject.getResourcetype().getName() + "' to '"
	    					+ updatedResourceObject.getResourcetype().getName() + "'. \n";
	    		}
	    		
	    		if(changedIsBulk){
	    			updateResourceStatusMsg += "IsBulk attribute of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
							+ resourceUpdateObject.isBulk() + "' to '" + updatedResourceObject.isBulk() + "'. \n";
	    		}
	    		
	    		if(changedIsIdentifiable){
	    			updateResourceStatusMsg += "IsIdentifiable attribute of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
							+ resourceUpdateObject.isIdentifiable() + "' to '" + updatedResourceObject.isIdentifiable()  + "'. \n";
	    		}
	    		
	    		if(changedQoH){
	    			updateResourceStatusMsg += "QoH of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
							+ resourceUpdateObject.getQoH() + "' to '" + updatedResourceObject.getQoH()  + "'. \n";
	    		}
	    		
	    		if(changedIsComposed){
	    			updateResourceStatusMsg += "IsComposed attribute of Resource (Id " + updatedResourceObject.getId() + ") updated from '"
							+ resourceUpdateObject.getIsComposed() + "' to '" + updatedResourceObject.getIsComposed()  + "'. \n";
	    		}
	    		
	    		if(changedAttributeValue){
	    			updateResourceStatusMsg += "Values of additional attributes of Resource (Id " + updatedResourceObject.getId() + ") updated.";
	    		}
	    		
	    		Window.alert(updateResourceStatusMsg);
	    		logger.info(updateResourceStatusMsg);
	    		
	    		// update the resourceDTO arraylist
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setName(result.getName());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setResourcetype(result.getResourcetype());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setBulk(result.isBulk());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setIdentifiable(result.isIdentifiable());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setQoH(result.getQoH());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setIsComposed(result.getIsComposed());
	    		reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex).setAdditionalAttributeValues(result.getAdditionalAttributeValues());
	    		
	    		// update entries from flextable
	    		resourceSelectionFlexTable.setText(updatedListIndex + 1, 1, result.getName());
	    		resourceSelectionFlexTable.setText(updatedListIndex + 1, 2, result.getResourcetype().getName());
	    		resourceSelectionFlexTable.setText(updatedListIndex + 1, 3, String.valueOf(result.isBulk()));
	    		resourceSelectionFlexTable.setText(updatedListIndex + 1, 4, String.valueOf(result.isIdentifiable()));
	    		resourceSelectionFlexTable.setText(updatedListIndex + 1, 5, String.valueOf(result.getIsComposed()));
	    		
	    		if(result.getQoH() % 1 != 0){

					resourceSelectionFlexTable.setText(updatedListIndex + 1, 6, String.valueOf(result.getQoH()));
				} else {

					resourceSelectionFlexTable.setText(updatedListIndex + 1, 6, String.valueOf(result.getQoH()).substring(0, String.valueOf(result.getQoH()).indexOf(".")));
				}
	    		
				updateResourceAddEditPanel(reaDBEntryContainer.getExistingResourceDTOs().get(updatedListIndex));
	    	}
	    };
	    
	    // Make the call
	    reaDBSvc.updateResource(updatedResourceDTO, callback);
	    
	}
	
	
	/**
	 * Deleting a Resource from the REA DB
	 * 
	 */
	private void deleteResource(ResourceDTO deleteResourceDTO){
		
		final int removedListIndex = reaDBEntryContainer.getExistingResourceDTOs().indexOf(deleteResourceDTO);
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }		
	    
	    // Set up the callback object.
	    AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("deleteResource()");
		    	caught.printStackTrace();
			}
		
			public void onSuccess(Integer result) {
				
				Window.alert("Resource with Id " + result + " was deleted from the REA DB");
				
				// remove entries from arrayList
				reaDBEntryContainer.getExistingResourceDTOs().remove(removedListIndex);
				// remove entries from flextable
				resourceSelectionFlexTable.removeRow(removedListIndex+1);
				
				// update the dualitystatusandeditpanel if it is already visible
				if(resourceAddEditPanel.isVisible()){
					updateResourceAddEditPanel(null);
				}
				
				// if no resource exists resourceaddeditpanel is set to invisible
				// the same happens to the rsourceSelectionFlexTable
				if(reaDBEntryContainer.getExistingResourceDTOs().size() == 0){
					resourceAddEditPanel.setVisible(false);
				}
				
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteResource(deleteResourceDTO.getId(), callback);
		
	}
	    
	
	/**
	 * Creating an ResourceDTO object including additional attribute values
	 * @return resourceDTO
	 * @throws ParseException 
	 */
	private ResourceDTO createResourceDTO() throws ParseException{
		
	    ResourceDTO resourceDTO = new ResourceDTO();
	    resourceDTO.setName(resourceNameTextBox.getText());
	    resourceDTO.setResourcetype(selectedResourcetypeDTOInListBox);
	    resourceDTO.setBulk(Boolean.valueOf(resourceIsBulkTextBox.getText()));
	    resourceDTO.setIdentifiable(Boolean.valueOf(resourceIsIdentifiableTextBox.getText()));
	    resourceDTO.setIsComposed(Boolean.valueOf(resourceIsComposedTextBox.getText()));
	    if(resourceQoHTextBox.getText().equals("")){
	    	resourceDTO.setQoH(1);
	    } else {
	    	resourceDTO.setQoH(Double.parseDouble(resourceQoHTextBox.getText()));
	    }
	    
	    
	    // setting additionalattributevalues for resourceDTO
	    resourceDTO.setAdditionalAttributeValues(createAttributeValueSetForSelectedResourcetype(resourceDTO));
	    
		return resourceDTO;
	}
	
	
	/**
	 * Method creating an ResourceHasAdditionalattributevalueDTO set for the resourcetypedto selected in the listbox
	 * @param resourceDTO resourceDTO the set belongs to
	 * @return attributes
	 */
	private Set<ResourceHasAdditionalattributevalueDTO> createAttributeValueSetForSelectedResourcetype(ResourceDTO resourceDTO){
		
		// creating additional attribute values for resourceDTO
	    Set<ResourceHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<ResourceHasAdditionalattributevalueDTO>();
	    
	    for(AttributeDTO attribute : selectedResourcetypeDTOInListBox.getAttributes()){
	    	
	    	// get the textbox content for the current attribute from the resourceAttributeLabelMap
	    	String textBoxContent = attributeLabelMap.get(attribute).getText();
	    	
	    	// create additional attribute value DTO object
	    	ResourceHasAdditionalattributevalueDTO attributevalue = new ResourceHasAdditionalattributevalueDTO();
	    	attributevalue.setResource(resourceDTO);
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
	    	
	    	// adding the attributevalue to the attributes Set
	    	attributes.add(attributevalue);
	    	
	    }
	    
	    // return the attributes set
	    return attributes;
	}
	
	
	/**
	 * Method checking if the additional attributes have changed or not (for update)
	 * @return attributeEquality
	 */
	private boolean additionalAttributeEquality(){
		
		boolean attributeEquality = true;
		
		// examine all attributeDTOs in the attributeLabelMap
		for(ResourceHasAdditionalattributevalueDTO attributeValue : resourceUpdateObject.getAdditionalAttributeValues()){
			
			
			if(attributeValue.getBooleanValue() != null){
				
				// if value is boolean
				String booleanValue = String.valueOf(attributeValue.getBooleanValue());
				if(!booleanValue.equals(attributeLabelMap.get(attributeValue.getAttribute()).getText())){
					attributeEquality = false;
					break;
				}
				
			} else if (attributeValue.getDatetimeValue() != null){
				
				// if value is datetime
				String dateValue = DateTimeFormat.getFormat("yyyy-MM-dd").format(attributeValue.getDatetimeValue());
				if(!dateValue.equals(attributeLabelMap.get(attributeValue.getAttribute()).getText())){
					attributeEquality = false;
					break;
				}
				
			} else if(attributeValue.getNumericValue() != null){
				
				// if value is numeric
				String numericValue = String.valueOf(attributeValue.getNumericValue());
				if(!numericValue.equals(attributeLabelMap.get(attributeValue.getAttribute()).getText())){
					attributeEquality = false;
					break;
				}
				
			} else {
				
				// if value is String
				String textualValue = attributeValue.getTextualValue();
				if(!textualValue.equals(attributeLabelMap.get(attributeValue.getAttribute()).getText())){
					attributeEquality = false;
					break;
				}
				
			}		
			
		}
		
		return attributeEquality;
	}
	
	
	/**
	 * Method validating all textboxes relevant for an Resource
	 * @return True if all validations are passed. False if one fails
	 */
	private boolean resourceTextBoxValidationResult(){
		
		boolean validationResult = true;
		
		// validate the resourcename, is bulk, isidentifiable, QoH, isComposed textboxs first
		// if this validation fails, validationResult is set to false immediately
		if(!resourceNameTextBox.validate() || !resourceIsBulkTextBox.validate() || !resourceIsIdentifiableTextBox.validate() 
				|| !resourceIsComposedTextBox.validate()){
			validationResult = false;
		}
		
		// If the selected resource is a bulk resource, the QoH is also validated
		if(selectedResourcetypeDTOInListBox.isBulk() && validationResult == false){
			if(!resourceQoHTextBox.validate()){
				
				validationResult = false;
			}
		}
			
		// run through all entries of the attributeLabelMap and validate the textboxes
		for(AttributeDTO adto : attributeLabelMap.keySet()){
				
			// if a textbox validation returns false, set validationResult to false
			if(!attributeLabelMap.get(adto).validate()){
				validationResult = false;
			}
		}
		
		return validationResult;
	}
	
	
	/**
	 * Method resting the values in the
	 */
	private void resetBulkAndIdentifiableTextBox(){
		
		// set the values in the resourceIsBulkTextBox and resourceIsIdentifiableTextBox
		if(selectedResourcetypeDTOInListBox.isBulk()){
			
			resourceIsIdentifiableTextBox.setText("false");
			resourceIsBulkTextBox.setText("true");
			
			// QoH label and textbox are only visible if the resourcetype is bulk
			resourceAddEditFlexTable.setWidget(6,0, resourceQoHLabel);
			resourceAddEditFlexTable.setWidget(6, 1, resourceQoHTextBox);
			
		} else if (selectedResourcetypeDTOInListBox.isIdentifiable()){
			
			resourceIsIdentifiableTextBox.setText("true");
			resourceIsBulkTextBox.setText("false");
		}
	}
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
	
}
