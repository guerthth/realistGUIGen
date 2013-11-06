package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class REAlistGUIGenerator implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();  
	private FlexTable stocksFlexTable = new FlexTable(); 
	private HorizontalPanel addPanel = new HorizontalPanel();  
	private TextBox newSymbolTextBox = new TextBox();  
	private Button addStockButton = new Button("Add");  
	private Label lastUpdatedLabel = new Label();
	
	// Dualitytype panel + ArrayList
	private HorizontalPanel dualitytypePanel = new HorizontalPanel();
	private Label dualitytypeLabel = new Label("Choose Dualitytype: ");
	private ListBox dualitytypeListBox = new ListBox();
	private List<DualitytypeDTO> existingDualitytypeDTOs = new ArrayList<DualitytypeDTO>();
	
	// fun chrystal
	private Button funButton = new Button("Drücke mich Chrystal!");  
	
	private READBServiceAsync reaDBSvc = GWT.create(READBService.class);
	
	public void onModuleLoad() {

		// Create table for stock data.  
		stocksFlexTable.setText(0, 0, "Symbol");  
		stocksFlexTable.setText(0, 1, "Price");  
		stocksFlexTable.setText(0, 2, "Change");  
		stocksFlexTable.setText(0, 3, "Remove");
		
		// Assemble Add Stock panel.
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		
		// Assemble Dualitytype panel
		dualitytypePanel.add(dualitytypeLabel);
		dualitytypePanel.add(dualitytypeListBox);

		// Assemble Main panel.
		//mainPanel.add(stocksFlexTable);
		//mainPanel.add(addPanel);
		//mainPanel.add(lastUpdatedLabel);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("readb").add(mainPanel);

		// Move cursor focus to the input box.
		newSymbolTextBox.setFocus(true);
		
		//load the dualitytype selection
		loadDualityTypes();
	}
	
	/**
	 * Method loading the existing dualitytypes in the GUI
	 */
	private void loadDualityTypes(){
		
		 // Initialize the service proxy.
	    if (reaDBSvc == null) {
	    	reaDBSvc = GWT.create(READBService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<List<DualitytypeDTO>> callback = new AsyncCallback<List<DualitytypeDTO>>() {
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	    	  System.err.println("Error when making READB Service RPC");
	    	  caught.printStackTrace();
	      }

	      public void onSuccess(List<DualitytypeDTO> result) {
	        
	    	  for(DualitytypeDTO ddto : result){
	    		  dualitytypeListBox.addItem(ddto.getName());
	    		  existingDualitytypeDTOs.add(ddto);
	    	  }
	    	  
	    	  dualitytypeListBox.setVisibleItemCount(1);
	    	  
	    	  // assemble Main panel
	    	  mainPanel.add(dualitytypePanel);
	    	  chrystallove();
	    	  
	      }
	    };

	    // Make the call to the stock price service.
	    reaDBSvc.getDualitytypes(callback);
	}
	
	private void chrystallove(){
		 mainPanel.add(funButton);
		 funButton.addClickHandler(new ClickHandler(){
			 public void onClick(ClickEvent event){
				 Label chrystallove = new Label("Hallo du hübsches Ding! Ich liebe dich!");
				 mainPanel.add(chrystallove);
			 }
		 });
	}
  
}
