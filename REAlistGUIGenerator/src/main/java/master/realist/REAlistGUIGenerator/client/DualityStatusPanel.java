package master.realist.REAlistGUIGenerator.client;

import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.TextValidator;
import master.realist.REAlistGUIGenerator.shared.Validator;
import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DualityStatusPanel extends VerticalPanel{

	// Logger
	private final static Logger logger = Logger.getLogger("DualityStatusPanelLogger");
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// Panel for DualityStatus Administration + Resource ArrayList
	private Label statusSelectionIntroductionLabel = new Label("DualityStatus Administration");
	private HorizontalPanel tableAndAddEditPanel = new HorizontalPanel();
	private FlexTable statusSelectionFlexTable = new FlexTable();
	private VerticalPanel dualityStatusAddEditPanel = new VerticalPanel();
	private Label dualityStatusAddEditHeader = new Label("Create or update DualityStatus: ");
	private FlexTable dualityStatusAddEditFlexTable = new FlexTable();
	private Label dualityStatusIdLabel = new Label("StatusId:");
	private TextBox dualityStatusIdTextTextBox = new TextBox();
	private Label dualityStatusStatusCodeLabel = new Label("StatusCode:");
	private CustomTextBox dualityStatusStatusCodeTextBox = new CustomTextBox();
	private Button dualityStatusOkButton = new Button("Ok");
	private HorizontalPanel dualityStatusChooseAddPanel = new HorizontalPanel();
	private Button dualityStatusAddButton = new Button("Add");	
	//private ArrayList<DualityStatusDTO> existingDualityStatusDTOs = new ArrayList<DualityStatusDTO>();
	
	// flag that specifies if a user wants to save or update
	private boolean saveActionState = true;
	// DualityStatusDTO object that should be updated
	private DualityStatusDTO updateObject = new DualityStatusDTO();
	
	// Updated DualityStatusDTO object
	private DualityStatusDTO updatedObject;
		
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
		
	
	/**
	 * Constructor
	 */
	public DualityStatusPanel(){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();	
		
	}
	

	/**
	 * Method populating the DualityStatus panel
	 */
	public void populateDualityStatusPanel(){
		
		// Populating the horizontalpanel and adding it to the panelstatusSelectionPanel
		// Populating the flex table for the selection of status
		statusSelectionFlexTable.setText(0, 0, "Id");
		statusSelectionFlexTable.setText(0, 1, "Statuscode");
		statusSelectionFlexTable.setText(0, 2, "Edit");
		statusSelectionFlexTable.setText(0, 3, "Remove");
				
		// setting padding of 4 to the cells of the statusSelectionFlexTable
		statusSelectionFlexTable.setCellPadding(4);
		
		// Add styles to elements in the statusSelectionFlexTable
		statusSelectionFlexTable.getRowFormatter().addStyleName(0, "adminFlexTableHeader");
		statusSelectionFlexTable.getCellFormatter().addStyleName(0, 1, "adminFlexTableColumn");
		statusSelectionFlexTable.getCellFormatter().addStyleName(0, 2, "adminFlexTableEditRemoveColumn");
		statusSelectionFlexTable.getCellFormatter().addStyleName(0, 3, "adminFlexTableEditRemoveColumn");
		statusSelectionFlexTable.addStyleName("adminFlexTable");
		
		// populate the statusSelectionFlextable with the values from the DB
		populateStatusSelectionFlextable();
		
		// define style for agentSelectionIntroductionLabel
		statusSelectionIntroductionLabel.addStyleName("introductionLabel");
				
		// Adding the headline label to the statusSelectionPanel
		this.add(statusSelectionIntroductionLabel);
		
		// Adding the statusSelectionFlexTable to the statusSelectionEmptyMessageFlexTablePanel
		tableAndAddEditPanel.add(statusSelectionFlexTable);				
		
		// setting validators for dualityStatusStatusCodeTextBox
		Validator statuscodeValidator = new TextValidator(45);
		
		dualityStatusStatusCodeTextBox.addValidator(statuscodeValidator);
		
		// applying styles to the dualityStatusAddEditHeader and adding it to the dualityStatusAddEditPanel
		dualityStatusAddEditHeader.addStyleName("addEditHeaderLabel");
		dualityStatusAddEditPanel.add(dualityStatusAddEditHeader);
		
		// Populating the dualityStatusAddEditPanel
		dualityStatusAddEditFlexTable.setWidget(0, 0, dualityStatusIdLabel);
		dualityStatusAddEditFlexTable.setWidget(0, 1, dualityStatusIdTextTextBox);
		dualityStatusAddEditFlexTable.setWidget(1, 0, dualityStatusStatusCodeLabel);
		dualityStatusAddEditFlexTable.setWidget(1, 1, dualityStatusStatusCodeTextBox);
		
		// adding the dualityStatusAddEditFlexTable to the dualityStatusAddEditPanel
		dualityStatusAddEditPanel.add(dualityStatusAddEditFlexTable);
		dualityStatusAddEditPanel.setVisible(false);
		
		// set dualityStatusIdTextTextBox to read only
		dualityStatusIdTextTextBox.setReadOnly(true);
		
		// applying style for Ok Button 
		dualityStatusOkButton.addStyleDependentName("ok");
		dualityStatusAddEditPanel.add(dualityStatusOkButton);
		
		// applying style for dualityStatusAddEditPanel
		dualityStatusAddEditPanel.addStyleName("adminFlexTable");
		dualityStatusAddEditPanel.addStyleName("addEditPanel");

		// adding the dualityStatusAddEditPanel to the tableAndAddEditPanel
		tableAndAddEditPanel.add(dualityStatusAddEditPanel);
		
		// Adding the statusSelectionEmptyMessageFlexTablePanel to the dualitystatusPanel
		this.add(tableAndAddEditPanel);
		
		// adding stype to dualityStatusChooseAddPanel
		dualityStatusChooseAddPanel.addStyleName("addPanel");
		dualityStatusChooseAddPanel.addStyleName("fullsizePanel");
		
		// adding style to the dualityStatusAddButton
		dualityStatusAddButton.addStyleDependentName("add");
		
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
						
		this.add(dualityStatusChooseAddPanel);
	}
	
	
	/**
	 * Calling the getDualityStatus method of the READBService
	 */
	private void populateStatusSelectionFlextable(){

		// dualitystatuses were already loaded on startup 
		// therefore the list in the reaDBEntryCOntainer is taken
		for(DualityStatusDTO dsdto : reaDBEntryContainer.getExistingDualityStatusDTOs()){
			
			// final variable needed for the button specifications
			final DualityStatusDTO currentDualityStatusDTO = dsdto;
			
			// Buttons to edit and delete dualitystatus
			Button updateDualityStatusButton = new Button("Update");
			updateDualityStatusButton.addStyleDependentName("removeupdate");
			
			updateDualityStatusButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					updateDualityStatusAddEditPanel(currentDualityStatusDTO);
				}
			});	
			
			Button deleteDualityStatusButton = new Button("X");
			deleteDualityStatusButton.addStyleDependentName("removeupdate");
			
			deleteDualityStatusButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					
					deleteDualityStatus(currentDualityStatusDTO);
				}
			});
			
			// adding rows for each dualitystatus that exists in the REA DB and apply styles
			int row = statusSelectionFlexTable.getRowCount();
			
			statusSelectionFlexTable.setText(row, 0, String.valueOf(dsdto.getId()));
			statusSelectionFlexTable.setText(row, 1, dsdto.getStatus());
			statusSelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
			statusSelectionFlexTable.setWidget(row, 2, updateDualityStatusButton);
			statusSelectionFlexTable.getCellFormatter().addStyleName(row, 2, "adminFlexTableEditRemoveColumn");
			statusSelectionFlexTable.setWidget(row, 3, deleteDualityStatusButton);
			statusSelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableEditRemoveColumn");
			
		}
	    
	}	
	
	
	/**
	 * Updating the DualityStatusAddEditPanel (just setting it to visible)
	 */
	private void updateDualityStatusAddEditPanel(DualityStatusDTO dualityStatusDTO){
		
		// reset the styles of the fixed attirbutevalued
		dualityStatusStatusCodeTextBox.removeStyles();
		
		// Check if the dualityStatusDTO object is null
		// If so the textboxes are granted for adding new dualitystatus
		if(dualityStatusDTO == null){
			
			// setting the value of the dualitystatus id
			saveActionState = true;
			
			if(reaDBEntryContainer.getExistingDualityStatusDTOs().size()>0){
				int lastId = reaDBEntryContainer.getExistingDualityStatusDTOs().get(reaDBEntryContainer.getExistingDualityStatusDTOs().size()-1).getId();
				dualityStatusIdTextTextBox.setText(String.valueOf(lastId+1));
				
			}else{	
				dualityStatusIdTextTextBox.setText("-");	
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
		
		// Only add if the validation does not fail
		if(!dualityStatusStatusCodeTextBox.validate()){
			return;
		}
		
		// check if the actionstate is 'save'
		if(!saveActionState){
			int indexOfUpdateObbject = reaDBEntryContainer.getExistingDualityStatusDTOs().indexOf(updatedObject);
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
				
				// if the Id is 0 return
				if(result.getId() == 0){
					Window.alert("New Dualitystatus '" + result.getStatus() + "' could not be saved to REA DB.");
					return;
				}
				
				String addDualityStatusMsg = "New Dualitystatus '" + result.getStatus() + "' added to REA DB with Id " + result.getId();
				Window.alert(addDualityStatusMsg);
				logger.info(addDualityStatusMsg);
				
				// adding the added dualitystatusDTO to the list of dualitydtos
				reaDBEntryContainer.getExistingDualityStatusDTOs().add(result);
				
				final DualityStatusDTO savedDualityStatusDTO = result;
				
				
				// Buttons to edit and delte dualitystatus
				Button updateDualityStatusButton = new Button("Update");
				updateDualityStatusButton.addStyleDependentName("removeupdate");
				
				updateDualityStatusButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						updateDualityStatusAddEditPanel(savedDualityStatusDTO);
					}
				});	
				
				Button deleteDualityStatusButton = new Button("X");
				deleteDualityStatusButton.addStyleDependentName("removeupdate");
				
				deleteDualityStatusButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
						deleteDualityStatus(savedDualityStatusDTO);
						
					}
				});	
				
				// adding the new dualitystatus to the statusSelectionFlexTable
				int row = statusSelectionFlexTable.getRowCount();
				
				statusSelectionFlexTable.setText(row, 0, String.valueOf(result.getId()));
				statusSelectionFlexTable.setText(row, 1, result.getStatus());
				statusSelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
				statusSelectionFlexTable.setWidget(row, 2, updateDualityStatusButton);
				statusSelectionFlexTable.getCellFormatter().addStyleName(row, 2, "adminFlexTableEditRemoveColumn");
				statusSelectionFlexTable.setWidget(row, 3, deleteDualityStatusButton);
				statusSelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableEditRemoveColumn");

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
	 * Updating a Dualitystatus from the REA DB
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
	    		reaDBEntryContainer.getExistingDualityStatusDTOs().get(updatedListIndex).setStatus(result.getStatus());
	    		// update entries from flextable
				statusSelectionFlexTable.setText(updatedListIndex + 1, 1, result.getStatus());
	    		
				updateDualityStatusAddEditPanel(reaDBEntryContainer.getExistingDualityStatusDTOs().get(updatedListIndex));
	    	}
	    };
	    
	    // Make the call
	    reaDBSvc.updateDualityStatus(updatedDualityStatusDTO, callback);
	    
	}
	
	
	
	/**
	 * Deleting a Dualitystatus from the REA DB
	 * 
	 */
	private void deleteDualityStatus(DualityStatusDTO deleteDualityStatusDTO){
		
		final int removedListIndex = reaDBEntryContainer.getExistingDualityStatusDTOs().indexOf(deleteDualityStatusDTO);
		
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
				reaDBEntryContainer.getExistingDualityStatusDTOs().remove(removedListIndex);
				// remove entries from flextable
				statusSelectionFlexTable.removeRow(removedListIndex+1);
				
				// update the dualitystatusaddeditpanel if it is already visible
				if(dualityStatusAddEditPanel.isVisible()){
					updateDualityStatusAddEditPanel(null);
				}
				
				// if not dualitystatus exist dualitystatusaddeditpanel is set to invisible
				if(reaDBEntryContainer.getExistingDualityStatusDTOs().size() == 0){
					dualityStatusAddEditPanel.setVisible(false);
				}
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteDualityStatus(deleteDualityStatusDTO.getId(), callback);
	    
	}
	
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
}
	
	

