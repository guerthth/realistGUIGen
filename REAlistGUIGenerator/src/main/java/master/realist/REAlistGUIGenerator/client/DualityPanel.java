package master.realist.REAlistGUIGenerator.client;

import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DualityPanel extends VerticalPanel {

	// Logger
	private final static Logger logger = Logger.getLogger("DualityPanelLogger");
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;
	
	// dualitySelectionFlexTable
	private FlexTable dualitySelectionFlexTable = new FlexTable();
	
	// Async READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
	
	/**
	 * Default Constructor
	 */
	public DualityPanel(){
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();
		
		// populate panel
		populateDualityPanel();
	}
	
	/**
	 * Method populating the DualityPanel
	 */
	private void populateDualityPanel(){
		
		// trimmed Dualitytypename
		String trimmedDualityType = "";
		
		// Populating the horizontalpanel and adding it to the agentSelectionPanel
		// Populating the flex table for the selection of agents
		dualitySelectionFlexTable.setText(0, 0, "Id");
		dualitySelectionFlexTable.setText(0, 1, "Dualitytype");
		dualitySelectionFlexTable.setText(0, 2, "Date");
		dualitySelectionFlexTable.setText(0, 3, "Status");
		dualitySelectionFlexTable.setText(0, 4, "Remove");
		
		// setting padding of 4 to the cells of the dualitySelectionFlextable
		dualitySelectionFlexTable.setCellPadding(4);
						
		// Add styles to elements in the dualitySelectionFlextable
		dualitySelectionFlexTable.getRowFormatter().addStyleName(0, "adminFlexTableHeader");
		dualitySelectionFlexTable.getCellFormatter().addStyleName(0, 1, "adminFlexTableColumn");
		dualitySelectionFlexTable.getCellFormatter().addStyleName(0, 2, "adminFlexTableColumn");
		dualitySelectionFlexTable.getCellFormatter().addStyleName(0, 3, "adminFlexTableColumn");
		dualitySelectionFlexTable.getCellFormatter().addStyleName(0, 4, "adminFlexTableEditRemoveColumn");
		dualitySelectionFlexTable.addStyleName("adminFlexTable");	
		
		// add the flextable to the dualitypanel
		this.add(dualitySelectionFlexTable);
		
		// only populate if entries in REA DB exist
		if(reaDBEntryContainer.getExistingDualityDTOs().size() != 0){
			
			// dualities were already loaded on startup 
			// therefore the list in the reaDBEntryContainer is taken
			for(DualityDTO ddto : reaDBEntryContainer.getExistingDualityDTOs()){
				
				// trim the name of the current duality			
				int indexOfConversion = ddto.getDualitytype().getName().indexOf("Conversion");
				int indexOfExchange = ddto.getDualitytype().getName().indexOf("Exchange");

				if(indexOfConversion != -1){
					trimmedDualityType = ddto.getDualitytype().getName().substring(0, indexOfConversion);
				} else {
					trimmedDualityType = ddto.getDualitytype().getName().substring(0, indexOfExchange);
				}
				
				// final variable needed for the button specifications
				final DualityDTO currentDualityDTO = ddto;
				
				// Button to delete existing dualities					
				Button deleteDualityButton = new Button("X");
				deleteDualityButton.addStyleDependentName("removeupdate");
						
				deleteDualityButton.addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event){
								
						deleteDuality(currentDualityDTO);
					}
				});

						
				int row = dualitySelectionFlexTable.getRowCount();

				dualitySelectionFlexTable.setText(row, 0, String.valueOf(ddto.getId()));
				dualitySelectionFlexTable.setText(row, 1, trimmedDualityType);
				dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
				dualitySelectionFlexTable.setText(row, 2, DateTimeFormat.getFormat("yyyy-MM-dd").format(ddto.getDate()));
				dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 2, "adminFlexTableColumn");
				dualitySelectionFlexTable.setText(row, 3, ddto.getDualitystatus().getStatus());
				dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableColumn");
				dualitySelectionFlexTable.setWidget(row, 4, deleteDualityButton);
				dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 4, "adminFlexTableEditRemoveColumn");

			}
			
			this.setVisible(true);
			
		} else{

			this.setVisible(false);
			
		}
	}
	
	
	/**
	 * Deleting a duality from the REA DB
	 * @param dualitydto
	 */
	private void deleteDuality(DualityDTO deleteDualityDTO){
		
		final int removedListIndex = reaDBEntryContainer.getExistingDualityDTOs().indexOf(deleteDualityDTO);
		
		// Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }		
	    
	    // Set up the callback object.
	    AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				logREADBRPCFailure("deleteDuality()");
		    	caught.printStackTrace();
			}
		
			public void onSuccess(Integer result) {
				
				Window.alert("Duality with Id " + result + " was deleted from the REA DB");
				
				// remove entries from arrayList
				reaDBEntryContainer.getExistingDualityDTOs().remove(removedListIndex);
				
				// updateDualitySelectionFlexTable 
				removeEntryFromDualitySelectionFlexTable(removedListIndex);
				
			}
			
	    };
	    
	    // Make the call
	    reaDBSvc.deleteDuality(deleteDualityDTO.getId(), callback);
	}
	
	
	/**
	 * Method removing an entry of the dualitySelectionFlextable by a specific index
	 * @param rowIndex
	 */
	private void removeEntryFromDualitySelectionFlexTable(int rowIndex){
		
		// remove entries from flextable
		dualitySelectionFlexTable.removeRow(rowIndex+1);
		
		// if all dalities are deleted set existingDualitiesPanel invisible
		if(reaDBEntryContainer.getExistingDualityDTOs().size()==0){
			this.setVisible(false);
		}
	}
	
	/**
	 * Method adding a new entry to the dualitySelectionFlextable
	 * @param duality
	 */
	public void addEntryToDualitySelectionFlexTable(DualityDTO duality){
		
		// trimmed Dualitytype
		String trimmedDualityType = "";
		
		// trim the name of the current duality			
		int indexOfConversion = duality.getDualitytype().getName().indexOf("Conversion");
		int indexOfExchange = duality.getDualitytype().getName().indexOf("Exchange");

		if(indexOfConversion != -1){
			trimmedDualityType = duality.getDualitytype().getName().substring(0, indexOfConversion);
		} else {
			trimmedDualityType = duality.getDualitytype().getName().substring(0, indexOfExchange);
		}
		
		// final variable needed for the button specifications
		final DualityDTO currentDualityDTO = duality;
		
		// Button to delete existing dualities					
		Button deleteDualityButton = new Button("X");
		deleteDualityButton.addStyleDependentName("removeupdate");
				
		deleteDualityButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
						
				deleteDuality(currentDualityDTO);
			}
		});

				
		int row = dualitySelectionFlexTable.getRowCount();

		dualitySelectionFlexTable.setText(row, 0, String.valueOf(duality.getId()));
		dualitySelectionFlexTable.setText(row, 1, trimmedDualityType);
		dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 1, "adminFlexTableColumn");
		dualitySelectionFlexTable.setText(row, 2, DateTimeFormat.getFormat("yyyy-MM-dd").format(duality.getDate()));
		dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 2, "adminFlexTableColumn");
		dualitySelectionFlexTable.setText(row, 3, duality.getDualitystatus().getStatus());
		dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 3, "adminFlexTableColumn");
		dualitySelectionFlexTable.setWidget(row, 4, deleteDualityButton);
		dualitySelectionFlexTable.getCellFormatter().addStyleName(row, 4, "adminFlexTableEditRemoveColumn");
		
		// add the insereted duality also to the list of existing dualityDTOs
		reaDBEntryContainer.getExistingDualityDTOs().add(duality);
		
		// if the duality panel is not already visible, make it visible
		if(!this.isVisible()){
			this.setVisible(true);
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
