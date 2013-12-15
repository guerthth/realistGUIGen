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

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgentHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.model.AgentHasAdditionalattributevalue;

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
	
	// Panel for Agent Administration + Agent ArrayList
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
	private AgentDTO agentUpdateObject = new AgentDTO();
	// Updated AgentDTO object
	private AgentDTO updatedAgentObject;
	
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);	
	
	// currently selected AgenttypeDTO in agentTypeListBox + index
	private AgenttypeDTO selectedAgenttypeDTOInListBox;
	private int selectedIndexInListBox;
	
	// Map keeping track of attributeDTOs and corresponding TextBoxes
	private Map<AttributeDTO,TextBox> attributeLabelMap;
	
	// Map relating an agentDTO with an agentAttributeLabelMap
	private Map<AgentDTO, Map<AttributeDTO, TextBox>> agentAttributeLabelMap;
	
	/**
	 * Constructor
	 */
	public AgentPanel(){
		populateAgentPanel();
		agentAttributeLabelMap = new HashMap<AgentDTO, Map<AttributeDTO, TextBox>>();
	}
	
	
	/**
	 * Method populating the Agent panel
	 */
	private void populateAgentPanel(){
		
		// get all the existing agenttypes (they are added to the existingAgenttypeDTOs arrayList)
		callGetAgenttypes();
		
		// get all the existing agents (they are added to the existingAgentDTOs arrayList)
		callGetAgents();	
		
		// Adding the headline label to the agentSelectionPanel
		this.add(agentSelectionIntroductionLabel);
						
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
		
		agentAddEditPanel.add(agentAddEditFlexTable);
		agentAddEditPanel.setVisible(false);
		agentIdTextTextBox.setReadOnly(true);
		agentTableAndAddEditPanel.add(agentAddEditPanel);
						
		this.add(agentTableAndAddEditPanel);	
						
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
				agentIdTextTextBox.setText("-");	
			}
			
			agentNameTextBox.setText("");
			agentTypeListBox.setItemSelected(0, true);
			
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
			
			// setting the values of the saved attributes
			Map<AttributeDTO, TextBox> agentDTOAttributeMap = agentAttributeLabelMap.get(agentDTO);	
			
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
		attributeLabelMap = new HashMap<AttributeDTO,TextBox>();
					
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
					Label attributenameLabel = new Label(adto.getName());
					TextBox attributenameTextBox = new TextBox();
					
					if(isUpdate){
						Set<AgentHasAdditionalattributevalueDTO> additionalattributevalues = agentUpdateObject.getAdditionalAttributeValues();
						
						
					}
					row = agentAddEditFlexTable.getRowCount();
					agentAddEditFlexTable.setWidget(row, 0, attributenameLabel);
					agentAddEditFlexTable.setWidget(row, 1, attributenameTextBox);
							
					// adding entries to eventAttributeMap that stores all additional eventtype attributes
					attributeLabelMap.put(adto,attributenameTextBox);
				}
				
				// add the add button at the and of the flextable
				row = agentAddEditFlexTable.getRowCount();
				agentAddEditFlexTable.setWidget(row, 0, agentOkButton);	
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
					
					Label attributenameLabel = new Label(adto.getAttribute().getName());
					TextBox attributenameTextBox = new TextBox();
					String textBoxContent = "";
								
					if (adto.getAttribute().getDatatype().equals("INT") || adto.getAttribute().getDatatype().equals("DOUBLE")){

						textBoxContent = String.valueOf(adto.getNumericValue());
						
			    	} else if (adto.getAttribute().getDatatype().equals("VARCHAR")){
			    		
			    		textBoxContent = String.valueOf(adto.getTextualValue());
			    		
			    	} else if (adto.getAttribute().getDatatype().equals("BOOLEAN")){
			    		
			    		textBoxContent = String.valueOf(adto.getBooleanValue());

			    	} else {
			    		
			    		textBoxContent = adto.getDatetimeValue().toString();
					
			    	}
					
					// set the text for the textbox
					attributenameTextBox.setText(textBoxContent);

					row = agentAddEditFlexTable.getRowCount();
					agentAddEditFlexTable.setWidget(row, 0, attributenameLabel);
					agentAddEditFlexTable.setWidget(row, 1, attributenameTextBox);
										
					// adding entries to eventAttributeMap that stores all additional eventtype attributes
					attributeLabelMap.put(adto.getAttribute(),attributenameTextBox);
				}
							
				// add the add button at the and of the flextable
				row = agentAddEditFlexTable.getRowCount();
				agentAddEditFlexTable.setWidget(row, 0, agentOkButton);	
				
			}
		}	
	}
	
	
	/**
	 * Adding new Agent
	 * Calling the addAgent method of the READBService
	 * 
	 */
	private void addNewAgent(){

		// check if the actionstate is 'save'
		if(!agentSaveActionState){
			int indexOfUpdateObject = existingAgentDTOs.indexOf(updatedAgentObject);
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
			
			if(oldName.matches(updatedName) && oldagenttype.matches(newagenttype) && additionalAttributeEquality()){
				Window.alert("Nothing has been updated");
			}else{
				if(!oldName.matches(updatedName)){
					agentNameChange = true;
					updatedAgentObject.setName(updatedName);
				}
		
				if(!oldagenttype.matches(newagenttype)){
					agentTypeChange = true;
					Set<AgenttypeDTO> newagenttypes = new LinkedHashSet<AgenttypeDTO>(1);
					for(AgenttypeDTO atDTO : existingAgenttypeDTOs){
				    	if(atDTO.getId().equals(newagenttype)){
				    		newagenttypes.add(atDTO);
				    		break;
				    	}
				    }  
					updatedAgentObject.setAgenttypes(newagenttypes);
				}
				
				updateAgent(updatedAgentObject,indexOfUpdateObject,agentNameChange,agentTypeChange);
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
				
				Window.alert("New Agent '" + result.getName() + "' added to REA DB with Id " + result.getId() );
				
				// adding the added agentDTO to the list of agentDTOs
				existingAgentDTOs.add(result);
				
				final AgentDTO savedAgentDTO = result;
				
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
		    
		    // relate the agentDTO object with the 
		    agentAttributeLabelMap.put(agentDTO, attributeLabelMap);
		    System.out.println("ATTRIBUTEMAP: "  + agentAttributeLabelMap.get(agentDTO));
		    
		} catch (ParseException e) {
			
			e.printStackTrace();
			logger.info("SimpleDateformate was not converted correctly.");
		}
	    
	    
	    
	}
	
	
	/**
	 * Updating a Dualitystatus from the REA DB
	 * 
	 */
	private void updateAgent(AgentDTO updatedAgentDTO, int listUpdateIndex, boolean agentNameChange, boolean agentTypeChange){
		
		final int updatedListIndex = listUpdateIndex;
		final boolean changedName = agentNameChange;
		final boolean changedType = agentTypeChange;
		
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
	    					+ updatedAgentObject.getAgenttypes().iterator().next().getName() + "'";
	    		}
	    		
	    		Window.alert(updateAgentStatusMsg);
	    		logger.info(updateAgentStatusMsg);
	    		
	    		// update the dualitySatusDTO arraylist

	    		existingAgentDTOs.get(updatedListIndex).setName(result.getName());
	    		existingAgentDTOs.get(updatedListIndex).setAgenttypes(result.getAgenttypes());
	    		
	    		// update entries from flextable
	    		agentSelectionFlexTable.setText(updatedListIndex + 1, 1, result.getName());
	    		agentSelectionFlexTable.setText(updatedListIndex + 1, 2, result.getAgenttypes().iterator().next().getName());
	    		
				updateAgentAddEditPanel(existingAgentDTOs.get(updatedListIndex));
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
				
				// update the dualitystatusandeditpanel if it is already visible
				if(agentAddEditPanel.isVisible()){
					updateAgentAddEditPanel(null);
				}
				
				// if not dualitystatus exist dualitystatusaddeditpanel is set to invisible
				if(existingAgentDTOs.size() == 0){
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
	    
	    // creating additional attribute values for agentDTO
	    Set<AgentHasAdditionalattributevalueDTO> attributes = new LinkedHashSet<AgentHasAdditionalattributevalueDTO>();
	    
	    for(AttributeDTO attribute : agenttypes.iterator().next().getAttributes()){
	    	
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
	    
	    // setting additionalattributevalues for agentDTO
	    agentDTO.setAdditionalAttributeValues(attributes);
	    
		return agentDTO;
	}
	
	
	/**
	 * Method checking if the additional attributes have changed or not (for update)
	 * @return attributeEquality
	 */
	private boolean additionalAttributeEquality(){
		
		boolean attributeEquality = true;
				
		return attributeEquality;
	}
	
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
}
