package master.realist.REAlistGUIGenerator.client;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


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
	private TabPanel administrationTabPanel = new TabPanel();

	// Eventtype Tab Panel
	private TabPanel eventypeSetTabPanel;

	// Asyn READB Service
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
	
	// Current Dualitytype_Id
	private DualitytypeDTO currentDualityType;
	

	/**
	 * On module load method
	 */
	public void onModuleLoad() {
		
		// initializing logger to INFO level
		logger.setLevel(Level.INFO);

		// Assemble administration tabpanel
		administrationTabPanel.add(new DualityStatusPanel(),"Dualitystatus");
		administrationTabPanel.add(new AgentPanel(),"Agents");
		administrationTabPanel.selectTab(0);
		administrationTabPanel.setTitle("Administrate dualitystatus, resources, or agents");
		
		// Assemble the stack panel
		stackPanel.add(administrationTabPanel, "Entity Administration");
		stackPanel.add(new DualityTypePanel(),"Business Case Creation");
		stackPanel.showStack(0);
		
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
