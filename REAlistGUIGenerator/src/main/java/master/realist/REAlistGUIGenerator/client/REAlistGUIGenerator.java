package master.realist.REAlistGUIGenerator.client;



import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import master.realist.REAlistGUIGenerator.shared.datacontainer.READBEntryContainer;
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


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
	
	// administration panels
	private DualityStatusPanel dualityStatusPanel = new DualityStatusPanel();
	private AgentPanel agentPanel = new AgentPanel();
	private ResourcePanel resourcePanel = new ResourcePanel();
	
	// tab panel containing all administration panels as tabs
	private TabPanel administrationTabPanel = new TabPanel();
	
	// Dualitytype panel 
	private DualityTypePanel dualitytypePanel = new DualityTypePanel();
	
	// Async READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);	
	
	// READBEntryContainer
	private READBEntryContainer reaDBEntryContainer;

	/**
	 * On module load method
	 */
	public void onModuleLoad() {
		
		// initialize READBEntryContainer
		reaDBEntryContainer = READBEntryContainer.getInstance();
		
		// setup the lists in the READBEntryContainer
		callGetAgents();
		callGetDualityStatus();
		
		// mainpanel stype
		mainPanel.addStyleName("fullsizePanel");
		
		// initializing logger to INFO level
		logger.setLevel(Level.INFO);

		// create Administration Panels and apply styles to set their widhts to 100%
		
		
		// Assemble administration tabpanel
		administrationTabPanel.add(dualityStatusPanel,"Dualitystatus");
		administrationTabPanel.add(agentPanel,"Agents");
		administrationTabPanel.add(resourcePanel,"Resources");
		administrationTabPanel.selectTab(0);
		administrationTabPanel.addStyleName("fullsizePanel");
		
		// Assemble the stack panel
		stackPanel.add(administrationTabPanel, "Entity Administration");
		stackPanel.add(dualitytypePanel,"Business Case Creation");
		stackPanel.showStack(0);
		stackPanel.addStyleName("fullsizePanel");
		
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("readb").add(mainPanel);	
		
		// assemble Main panel
		mainPanel.add(stackPanel);
		
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
				
				reaDBEntryContainer.getExistingAgentDTOs().clear();
				
				reaDBEntryContainer.setExistingAgentDTOs(result);
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getAgents(callback);
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
				
				reaDBEntryContainer.getExistingDualityStatusDTOs().clear();
				
				reaDBEntryContainer.setExistingDualityStatusDTOs(result);
				
			}
	    	
	    };
	    
	    // Make the call
	    reaDBSvc.getDualityStatus(callback);
	    
	}	
	   

	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
  
}
