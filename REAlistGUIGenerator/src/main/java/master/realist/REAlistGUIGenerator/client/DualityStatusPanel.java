package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
		
	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
		
	
	/**
	 * Constructor
	 */
	public DualityStatusPanel(){
		populateDualityStatusPanel();
	}
	

	/**
	 * Method populating the DualityStatus panel
	 */
	private void populateDualityStatusPanel(){
		
		// get all the existing dualitystatus (they are added to the existingDualityStatusDTOs arrayList)
		callGetDualityStatus();
				
		// Adding the headline label to the statusSelectionPanel
		this.add(statusSelectionIntroductionLabel);
				
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
				
		this.add(tableAndAddEditPanel);
				
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
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
}
	
	

