package master.realist.REAlistGUIGenerator.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgentHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;

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

public class AgentPanel extends VerticalPanel{
	
	// Logger
	private final static Logger logger = Logger.getLogger("AgentPanelLogger");
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// Panel for Agent Administration + Agent ArrayList
	private Label agentSelectionIntroductionLabel = new Label("Agent Administration");
	private HorizontalPanel agentTableAndAddEditPanel = new HorizontalPanel();
	private FlexTable agentSelectionFlexTable = new FlexTable();
	private VerticalPanel agentAddEditPanel = new VerticalPanel();
	private Label agentAddEditHeader = new Label("Create or update Agent: ");
	private FlexTable agentAddEditFlexTable = new FlexTable();
	private Label agentIdLabel = new Label("AgentId:");
	private TextBox agentIdTextTextBox = new TextBox();
	private Label agentNameLabel = new Label("Agentname:");
	private CustomTextBox agentNameTextBox = new CustomTextBox();
	private Label agentTypeLabel = new Label("Agenttype:");
	private ListBox agentTypeListBox = new ListBox();
	private Button agentOkButton = new Button("Ok");
	private HorizontalPanel agentAddPanel = new HorizontalPanel();
	private Button agentAddButton = new Button("Add");	
		
	// Arraylist for existing agenttypes
	private ArrayList<AgenttypeDTO> existingAgenttypeDTOs = new ArrayList<AgenttypeDTO>();
		
	// flag that specifies if a user wants to save or update
	private boolean agentSaveActionState = true;
	// DualityStatusDTO object that should be updated
	private AgentDTO agentUpdateObject = new AgentDTO();
	// Updated AgentDTO object
	private AgentDTO updatedAgentObject;
	
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);	
	
	// currently selected AgenttypeDTO in agentTypeListBox + index
	private AgenttypeDTO selectedAgenttypeDTOInListBox;
	private int selectedIndexInListBox;
	
	// Map keeping track of attributeDTOs and corresponding TextBoxes
	private Map<AttributeDTO,CustomTextBox> attributeLabelMap;
	
	
	/**
	 * Constructor
	 */
	public AgentPanel(){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();
		
	}
	
	
	/**
	 * Method populating the Agent panel
	 */
	public void populateAgentPanel(){
		
		// styles for tableAndAddEditPanel
		//agentTableAndAddEditPanel.addStyleName("fullsizePanel");
		
		// get all the existing agenttypes (they are added to the existingAgenttypeDTOs arrayList)
		callGetAgenttypes();
		
		// Populating the horizontalpanel and adding it to the agentSelectionPanel
		// Populating the flex table for the selection of agents
		agentSelectionFlexTable.setText(0, 0, "Id");
		agentSelectionFlexTable.setText(0, 1, "Name");
		agentSelectionFlexTable.setText(0, 2, "Agenttype");
		agentSelectionFlexTable.setText(0, 3, "Edit");
		agentSelectionFlexTable.setText(0, 4, "Remove");
				
		// setting padding of 4 to the cells of the statusSelectionFlexTable
		agentSelectionFlexTable.setCellPadding(4);
				
		// Add styles to elements in the statusSelectionFlexTable
		agentSelectionFlexTable.getRowFormatter().addStyleName(0, "adminFlexTableHeader");
		agentSelectionFlexTable.getCellFormatter().addStyleName(0, 1, "adminFlexTableColumn");
		agentSelectionFlexTable.getCellFormatter().addStyleName(0, 2, "adminFlexTableColumn");
		agentSelectionFlexTable.getCellFormatter().addStyleName(0, 3, "adminFlexTableEditRemoveColumn");
		agentSelectionFlexTable.getCellFormatter().addStyleName(0, 4, "adminFlexTableEditRemoveColumn");
		agentSelectionFlexTable.addStyleName("adminFlexTable");	
		
		// get all the existing agents (they are added to the existingAgentDTOs arrayList)
		populateAgentSelectionFlexTable();
		
		// define style for agentSelectionIntroductionLabel
		agentSelectionIntroductionLabel.addStyleName("introductionLabel");
		
		// Adding the headline label to the agentSelectionPanel
		this.add(agentSelectionIntroductionLabel);
		
		// Adding the agentSelectionFlexTable to the agentTableAndAddEditPanel
		agentTableAndAddEditPanel.add(agentSelectionFlexTable);		
		
		// setting validators for dualityStatusStatusCodeTextBox
		Validator agentNameValidator = new TextValidator(45);
		agentNameTextBox.addValidator(agentNameValidator);
		
		// applying styles to the agentAddEditHeader and adding it to the agentAddEditPanel
		agentAddEditHeader.addStyleName("addEditHeaderLabel");
		agentAddEditPanel.add(agentAddEditHeader);
						
		// Populating the agentAddEditFlexTable
		agentAddEditFlexTable.setWidget(0, 0, agentIdLabel);
		agentAddEditFlexTable.setWidget(0, 1, agentIdTextTextBox);
		agentAddEditFlexTable.setWidget(1, 0, agentNameLabel);
		agentAddEditFlexTable.setWidget(1, 1, agentNameTextBox);
		agentAddEditFlexTable.setWidget(2,0, agentTypeLabel);
		agentAddEditFlexTable.setWidget(2, 1, agentTypeListBox);
		
		agentAddEditPanel.add(agentAddEditFlexTable);
		agentAddEditPanel.setVisible(false);
		agentIdTextTextBox.setReadOnly(true);
		
		// applying style for Ok Button
		agentOkButton.addStyleDependentName("ok");
		agentAddEditPanel.add(agentOkButton);

		// applying style to the agentAddEditPanel
		agentAddEditPanel.addStyleName("adminFlexTable");
		agentAddEditPanel.addStyleName("addEditPanel");
		
		// adding the agentAddEditPanel to the agentTableAndAddEditPanel
		agentTableAndAddEditPanel.add(agentAddEditPanel);
		
		// adding the agentTableAndAddEditPanel to the agentpanel
		this.add(agentTableAndAddEditPanel);
		
		// adding style to agentChooseAddPanel
		agentAddPanel.addStyleName("addPanel");
		agentAddPanel.addStyleName("fullsizePanel");
		
		// adding style to the agentAddButton
		agentAddButton.addStyleDependentName("add");
						
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
		
		// Listen for changes on agentTypeListBox
		agentTypeListBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent event){
				
				// set the current selected selectedAgenttypeDTOInListBox and selectedIndexInListBox
				for(int i = 0; i < existingAgenttypeDTOs.size(); i++){
					if(existingAgenttypeDTOs.get(i).getId().equals(agentTypeListBox.getItemText(agentTypeListBox.getSelectedIndex()))){
						selectedIndexInListBox = i;
						selectedAgenttypeDTOInListBox = existingAgenttypeDTOs.get(i);
						break;
					}
				}
				
				if(agentSaveActionState){
					Window.alert("ListBox Save Change!");
					addAdditionalAttributesForSelectedAgenttype(false);
				}else{
					Window.alert("ListBox Update Change!");
					addAdditionalAttributesForSelectedAgenttype(false);
				}
				
			}
		});
				
		this.add(agentAddPanel);
		
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
				
				boolean firstIsSet = false;
				
				for(AgenttypeDTO atdto : result){
					
					// firstRetrievedAgenttypeDTO is set to the first atdto
					if(!firstIsSet){
						selectedAgenttypeDTOInListBox = atdto;
						firstIsSet = true;
					}
					
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
	private void populateAgentSelectionFlexTable(){
		
		// agents were already loaded on startup 
		// therefore the list in the reaDBEntryContainer is taken
		for(AgentDTO adto : reaDBEntryContainer.getExistingAgentDTOs()){
			
			// final variable needed for the button specifications
			final AgentDTO currentAgentDTO = adto;
			
			// Buttons to edit and delete agents
			Button updateAgentButton = new Button("Update");
			updateAgentButton.addStyleDependentName("removeupdate");

			updateAgentButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					updateAgentAddEditPanel(currentAgentDTO);
				}
			});	
			
			Button deleteAgentButton = new Button("X");
			deleteAgentButton.addStyleDependentName("removeupdate");
			
			deleteAgentButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					
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
			agentSelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
			agentSelectionFlexTable.setText(row, 2, agenttypeDTO.getId());
			agentSelectionFlexTable.setWidget(row, 3, updateAgentButton);
			agentSelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableEditRemoveColumn");
			agentSelectionFlexTable.setWidget(row, 4, deleteAgentButton);
			agentSelectionFlexTable.getCellFormatter().addStyleName(row, 4, "adminFlexTableEditRemoveColumn");
			
		}
	    
	   
	}
	
	
	/**
	 * Updating the AgentAddEditPanel (just setting it to visible)
	 */
	private void updateAgentAddEditPanel(AgentDTO agentDTO){
		
		// reset the styles of the fixed attirbutevalued
		agentNameTextBox.removeStyles();
		
		// Check if the agentDTO object is null
		// If so the textboxes are granted for adding new agents without content
		if(agentDTO == null){
			
			// setting the values of the agents
			agentSaveActionState = true;
			
			if(reaDBEntryContainer.getExistingAgentDTOs().size()>0){
				int lastId = reaDBEntryContainer.getExistingAgentDTOs().get(reaDBEntryContainer.getExistingAgentDTOs().size()-1).getId();
				agentIdTextTextBox.setText(String.valueOf(lastId+1));
				
			}else{	
				agentIdTextTextBox.setText("-");	
			}
			
			agentNameTextBox.setText("");
			agentTypeListBox.setItemSelected(0, true);
			
			// reset selected index and AgentDTO in ListBox
			selectedIndexInListBox = 0;
			selectedAgenttypeDTOInListBox = existingAgenttypeDTOs.get(0);

			// adding the additional attribute labels and textfields for the initially selected agenttype
			addAdditionalAttributesForSelectedAgenttype(false);
			
		} else{
			
			agentSaveActionState = false;
			
			// copy the current state of the agentDTO object
			agentUpdateObject.setId(agentDTO.getId());
			agentUpdateObject.setName(agentDTO.getName());
			agentUpdateObject.setAgenttypes(agentDTO.getAgenttypes());
			agentUpdateObject.setAdditionalAttributeValues(agentDTO.getAdditionalAttributeValues());
						
			// copy the agentDTO object itself
			updatedAgentObject = agentDTO;
			
			// setting the values of saved id, name, and agenttype
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
			
			selectedIndexInListBox = selectedIndex;
			selectedAgenttypeDTOInListBox = existingAgenttypeDTOs.get(selectedIndexInListBox);
			
			// adding the additional attribute labels and textfields for the initially selected agenttype (with saved values)
			addAdditionalAttributesForSelectedAgenttype(true);
			
		}
		
		agentAddEditPanel.setVisible(true);
		agentNameTextBox.setFocus(true);	
	}
	
	
	/**
	 * Method adding the additional Attribute Textboxes and Labels for specific agenttypes
	 */
	private void addAdditionalAttributesForSelectedAgenttype(boolean isUpdate){
		
		// create attributeLabelMap
		attributeLabelMap = new HashMap<AttributeDTO,CustomTextBox>();
					
		// get the additional Attribute DTo set for the selected agentype
		Set<AttributeDTO> agenttypeAttributes = selectedAgenttypeDTOInListBox.getAttributes();
		
		if(!isUpdate){
			
			if(agenttypeAttributes != null){
				
				int row = agentAddEditFlexTable.getRowCount();
				
				// if more than 3 exist. delete all but the first three
				if(row > 3){
					for(int i = 3; i < row; i++){
						agentAddEditFlexTable.removeRow(3);
					}
				}
				
				for(AttributeDTO adto : agenttypeAttributes){
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
					
					row = agentAddEditFlexTable.getRowCount();
					agentAddEditFlexTable.setWidget(row, 0, attributeLabel);
					agentAddEditFlexTable.setWidget(row, 1, attributeTextBox);
							
					// adding entries to eventAttributeMap that stores all additional agenttype attributes
					attributeLabelMap.put(adto,attributeTextBox);
				}

			}
		} else{
			
			if(agenttypeAttributes != null){
							
				// get additional attribute values for agentDToO that should be updated
				Set<AgentHasAdditionalattributevalueDTO> additionalattributevalues = agentUpdateObject.getAdditionalAttributeValues();
							
				int row = agentAddEditFlexTable.getRowCount();
							
				// if more than 3 exist. delete all but the first three
				if(row > 3){
					for(int i = 3; i < row; i++){
						agentAddEditFlexTable.removeRow(3);
					}
				}

				// run through all existing addtitional attribute values
				for(AgentHasAdditionalattributevalueDTO adto : additionalattributevalues){
					
					Label attributeLabel = new Label(adto.getAttribute().getName() + ":");
					CustomTextBox attributeTextBox = new CustomTextBox();
					String textBoxContent = "";
					Validator attributeValidator;
					
					// set textBoxContent and Validators (depending on datatypes)
					if (adto.getAttribute().getDatatype().equals("INT") || adto.getAttribute().getDatatype().equals("DOUBLE")){

						textBoxContent = String.valueOf(adto.getNumericValue());
						attributeValidator = new NumericValidator();
						
			    	} else if (adto.getAttribute().getDatatype().equals("VARCHAR")){
			    		
			    		textBoxContent = String.valueOf(adto.getTextualValue());
			    		attributeValidator = new TextValidator(45);
			    		
			    	} else if (adto.getAttribute().getDatatype().equals("BOOLEAN")){
			    		
			    		textBoxContent = String.valueOf(adto.getBooleanValue());
			    		attributeValidator = new BooleanValidator();

			    	} else {
			    	
			    		textBoxContent = DateTimeFormat.getFormat("yyyy-MM-dd").format(adto.getDatetimeValue());
			    		attributeValidator = new DateValidator();
			    		
			    	}
					
					// set the text for the textbox
					attributeTextBox.setText(textBoxContent);
					
					// adding the validator for the textbox
					attributeTextBox.addValidator(attributeValidator);

					row = agentAddEditFlexTable.getRowCount();
					agentAddEditFlexTable.setWidget(row, 0, attributeLabel);
					agentAddEditFlexTable.setWidget(row, 1, attributeTextBox);
										
					// adding entries to eventAttributeMap that stores all additional agenttype attributes
					attributeLabelMap.put(adto.getAttribute(),attributeTextBox);
				}
				
			}
		}	
	}
	
	
	/**
	 * Adding new Agent
	 * Calling the addAgent method of the READBService
	 * 
	 */
	private void addNewAgent(){

		// Only add if the validation does not fail
		if(!agentTextBoxValidationResult()){
			return;
		}
		
		// check if the actionstate is 'save'
		if(!agentSaveActionState){
			int indexOfUpdateObject = reaDBEntryContainer.getExistingAgentDTOs().indexOf(updatedAgentObject);
			String oldName = agentUpdateObject.getName();
			String updatedName = agentNameTextBox.getText();
			
			// TODO: the REA DB is capable of representing agents with more than one agenttype
			// for this prototype it is assumed that each agent has only one agenttype
			Iterator<AgenttypeDTO> iterator = agentUpdateObject.getAgenttypes().iterator();
			AgenttypeDTO agenttypeDTO = iterator.next();
			String oldagenttype = agenttypeDTO.getName();
			String newagenttype = agentTypeListBox.getItemText(agentTypeListBox.getSelectedIndex());
			
			// flags helping to distinguish between different updates
			boolean agentNameChange = false;
			boolean agentTypeChange = false;
			boolean agentAttributeValueChange = false;
	
		
			// check if agent name has changed. If so, set agentNameChange to true
			if(!oldName.matches(updatedName)){
				agentNameChange = true;
				updatedAgentObject.setName(updatedName);
			}
			
			// only when agenttype stays the same, the additional attributes are compared
			if(oldagenttype.matches(newagenttype)){
				
				if(!additionalAttributeEquality()){
					
					// set agentAttributeValueChange to true 
					agentAttributeValueChange = true;
					Set<AgentHasAdditionalattributevalueDTO> updatedAttributeValues = 
							new LinkedHashSet<AgentHasAdditionalattributevalueDTO>(agentUpdateObject.getAdditionalAttributeValues().size());
					
					// examine all attributeDTOs in the attributeLabelMap
					for(AgentHasAdditionalattributevalueDTO attributeValue : agentUpdateObject.getAdditionalAttributeValues()){
						
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
					
					// setting the new additional attribute value set for the updatedAgentObject
					updatedAgentObject.setAdditionalAttributeValues(updatedAttributeValues);
					
				} 
				
			} else {
				
				// set agentTypeChange to true and set new agentype
				agentTypeChange = true;
				
				// set agentAttributeValueChange to true
				agentAttributeValueChange = true;
				
				Set<AgenttypeDTO> newagenttypes = new LinkedHashSet<AgenttypeDTO>(1);
				for(AgenttypeDTO atDTO : existingAgenttypeDTOs){
			    	if(atDTO.getId().equals(newagenttype)){
			    		newagenttypes.add(atDTO);
			    		break;
			    	}
			    }  
				updatedAgentObject.setAgenttypes(newagenttypes);
				
				// also update the additional attribute values, since they are new
				updatedAgentObject.setAdditionalAttributeValues(createAttributeValueSetForSelectedAgenttype(updatedAgentObject));
				
			}
			
			// If name, type, or attributes have changes --> update agent. If not, show Message
			if(agentNameChange || agentTypeChange || agentAttributeValueChange){
				
				updateAgent(updatedAgentObject,indexOfUpdateObject,agentNameChange,agentTypeChange,agentAttributeValueChange);
				
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
	    AsyncCallback<AgentDTO> callback = new AsyncCallback<AgentDTO>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("saveAgent()");
		    	caught.printStackTrace();
			}

			public void onSuccess(AgentDTO result) {
				
				Window.alert("New Agent '" + result.getName() + "' added to REA DB with Id " + result.getId() );
				
				// adding the added agentDTO to the list of agentDTOs
				reaDBEntryContainer.getExistingAgentDTOs().add(result);
				
				final AgentDTO savedAgentDTO = result;
				
				// Buttons to edit and delete agents
				Button updateAgentButton = new Button("Update");
				updateAgentButton.addStyleDependentName("removeupdate");
				
				updateAgentButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						updateAgentAddEditPanel(savedAgentDTO);
					}
				});	
				
				Button deleteAgentButton = new Button("X");
				deleteAgentButton.addStyleDependentName("removeupdate");
				
				deleteAgentButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						deleteAgent(savedAgentDTO);
						
					}
				});	
				
				// TODO: the REA DB is capable of representing agents with more than one agenttype
				// for this prototype it is assumed that each agent has only one agenttype
				Iterator<AgenttypeDTO> iterator = result.getAgenttypes().iterator();
				AgenttypeDTO atdto = iterator.next();
				
				// adding the new agent to the agentSelectionFlexTable
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
	    
	    // Creating a agentDTO object that will be added to DB and table
	    AgentDTO agentDTO;
		
	    try {
			
			agentDTO = createAgentDTO();
			
			// Make the call
		    reaDBSvc.saveAgent(agentDTO, callback);
		    
		} catch (ParseException e) {
			
			e.printStackTrace();
			logger.info("SimpleDateformate was not converted correctly.");
		}
	    
	    
	    
	}
	
	
	/**
	 * Updating an Agent from the REA DB
	 * 
	 */
	private void updateAgent(AgentDTO updatedAgentDTO, int listUpdateIndex, boolean agentNameChange, boolean agentTypeChange, boolean agentAttributeValueChange){
		
		final int updatedListIndex = listUpdateIndex;
		final boolean changedName = agentNameChange;
		final boolean changedType = agentTypeChange;
		final boolean changedAttributeValue = agentAttributeValueChange;
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }	
	    
	    // Set up the callback object.
	    AsyncCallback<AgentDTO> callback = new AsyncCallback<AgentDTO>() {
	    	
	    	public void onFailure(Throwable caught) {
	    		logREADBRPCFailure("updateAgent()");
		    	caught.printStackTrace();
			}
	    	
	    	public void onSuccess(AgentDTO result) {
	    		
	    		// print update message
	    		String updateAgentStatusMsg = "";
	    		if(changedName){
	    			updateAgentStatusMsg += "Name of Agent (Id " + updatedAgentObject.getId() + ") updated from '"
							+ agentUpdateObject.getName() + "' to '" + updatedAgentObject.getName() + "'. \n";
	    		}
	    		
	    		if(changedType){
	    			updateAgentStatusMsg += "Agenttype of Agent (Id " + updatedAgentObject.getId() + ") updated from '"
	    					+ agentUpdateObject.getAgenttypes().iterator().next().getName() + "' to '"
	    					+ updatedAgentObject.getAgenttypes().iterator().next().getName() + "'. \n";
	    		}
	    		
	    		if(changedAttributeValue){
	    			updateAgentStatusMsg += "Values of additional attributes of Agent (Id " + updatedAgentObject.getId() + ") updated.";
	    		}
	    		
	    		Window.alert(updateAgentStatusMsg);
	    		logger.info(updateAgentStatusMsg);
	    		
	    		// update the dagentDTO arraylist

	    		reaDBEntryContainer.getExistingAgentDTOs().get(updatedListIndex).setName(result.getName());
	    		reaDBEntryContainer.getExistingAgentDTOs().get(updatedListIndex).setAgenttypes(result.getAgenttypes());
	    		
	    		// update entries from flextable
	    		agentSelectionFlexTable.setText(updatedListIndex + 1, 1, result.getName());
	    		agentSelectionFlexTable.setText(updatedListIndex + 1, 2, result.getAgenttypes().iterator().next().getName());
	    		
				updateAgentAddEditPanel(reaDBEntryContainer.getExistingAgentDTOs().get(updatedListIndex));
	    	}
	    };
	    
	    // Make the call
	    reaDBSvc.updateAgent(updatedAgentDTO, callback);
	    
	}
	
	
	/**
	 * Deleting an Agent from the REA DB
	 * 
	 */
	private void deleteAgent(AgentDTO deleteAgentDTO){
		
		final int removedListIndex = reaDBEntryContainer.getExistingAgentDTOs().indexOf(deleteAgentDTO);
		
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
				reaDBEntryContainer.getExistingAgentDTOs().remove(removedListIndex);
				
				// remove entries from flextable
				agentSelectionFlexTable.removeRow(removedListIndex+1);
				
				// update the dualitystatusandeditpanel if it is already visible
				if(agentAddEditPanel.isVisible()){
					updateAgentAddEditPanel(null);
				}
				
				// if no agents existagentaddeditpanel is set to invisible
				// the same happens to the agentSelectionFlexTable
				if(reaDBEntryContainer.getExistingAgentDTOs().size() == 0){
					agentAddEditPanel.setVisible(false);
				}
				
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteAgent(deleteAgentDTO.getId(), callback);
		
	}
	
	
	/**
	 * Creating an AgentDTO object inclusing additional attribute values
	 * @return
	 * @throws ParseException 
	 */
	private AgentDTO createAgentDTO() throws ParseException{
		
		// Create a agenttypeDTO object that represents the type of the agentDTo that should be saved
	    Set<AgenttypeDTO> agenttypes = new LinkedHashSet<AgenttypeDTO>(1);
	    agenttypes.add(selectedAgenttypeDTOInListBox);
	    
	    AgentDTO agentDTO = new AgentDTO();
	    agentDTO.setName(agentNameTextBox.getText());
	    agentDTO.setAgenttypes(agenttypes);
	    
	    // setting additionalattributevalues for agentDTO
	    agentDTO.setAdditionalAttributeValues(createAttributeValueSetForSelectedAgenttype(agentDTO));
	    
		return agentDTO;
	}
	
	
	/**
	 * Method creating an AgentHasAdditionalattributevalueDTO set for the agenttypedto selected in the listbox
	 * @param agentDTO agentDTO the set belongs to
	 * @return attributes
	 */
	private Set<AgentHasAdditionalattributevalueDTO> createAttributeValueSetForSelectedAgenttype(AgentDTO agentDTO){
		
		// creating additional attribute values for agentDTO
	    Set<AgentHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<AgentHasAdditionalattributevalueDTO>();
	    
	    //for(AttributeDTO attribute : agenttypes.iterator().next().getAttributes()){
	    for(AttributeDTO attribute : selectedAgenttypeDTOInListBox.getAttributes()){
	    	
	    	// get the textbox content for the current attribute from the agentAttributeLabelMap
	    	String textBoxContent = attributeLabelMap.get(attribute).getText();
	    	
	    	// create additional attribute value DTO object
	    	AgentHasAdditionalattributevalueDTO attributevalue = new AgentHasAdditionalattributevalueDTO();
	    	attributevalue.setAgent(agentDTO);
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
		for(AgentHasAdditionalattributevalueDTO attributeValue : agentUpdateObject.getAdditionalAttributeValues()){
			
			
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
	 * Method validating all textboxes relevant for an Agent
	 * @return True if all validations are passed. False if one fails
	 */
	private boolean agentTextBoxValidationResult(){
		
		boolean validationResult = true;
		
		// validate the agentname textbox first
		// if this validation fails, validationResult is set to false immediately
		if(!agentNameTextBox.validate()){
			validationResult = false;
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
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
}
