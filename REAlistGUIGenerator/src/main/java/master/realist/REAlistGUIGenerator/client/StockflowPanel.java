package master.realist.REAlistGUIGenerator.client;

import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;

import com.google.gwt.user.client.ui.TabPanel;

/**
 * Panel for all occurring Stockflows per event
 * @author Thomas
 *
 */
public class StockflowPanel extends TabPanel{

	// eventtypeDTO object, the participatin is created for
	private EventtypeDTO eventtypeDTO;
	
	/**
	 * Default Constructor
	 * @param eventtypeDTO
	 */
	public StockflowPanel(EventtypeDTO eventtypeDTO){
		
		this.eventtypeDTO = eventtypeDTO;
		
		// populating the StockflowPanel
		populateStockflowPanel();
	}
	
	/**
	 * Method populating the StockflowPanel for Events
	 */
	private void populateStockflowPanel(){
		this.addStyleName("participationStockflowPanel");
	}
}
