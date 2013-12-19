package master.realist.REAlistGUIGenerator.client;



import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;

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
	

	/**
	 * On module load method
	 */
	public void onModuleLoad() {
		
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
		stackPanel.add(new DualityTypePanel(),"Business Case Creation");
		stackPanel.showStack(0);
		stackPanel.addStyleName("fullsizePanel");
		
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("readb").add(mainPanel);	
		
		// assemble Main panel
		mainPanel.add(stackPanel);
		
	}

	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	public void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
  
}
