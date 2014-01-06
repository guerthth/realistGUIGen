package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowDTO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel for all occurring Stockflows per event
 * @author Thomas
 *
 */
public class StockflowPanel extends VerticalPanel{

	// eventtypeDTO object, the participatin is created for
	private EventtypeDTO eventtypeDTO;
	
	// eventDTO object, the participation is created for
	private EventDTO eventdto;
		
	// Label for Paricipation Specification
	private Label stockflowSpecificationLabel = new Label("Affected resources:");
		
	// TabPanel for all existing (or new created, if series) participations
	private TabPanel stockflowTabPanel = new TabPanel();
	
	// map keeping track of eventtypes and their corresponding attributes + textboxes
	private Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap;
	
	// stockflowmap
	private Map<StockflowDTO, Map<AttributeDTO, CustomTextBox>> stockflowMap = new HashMap<StockflowDTO, Map<AttributeDTO, CustomTextBox>>();
	
	// Map keeping track of the fixed stockflow attribute textboxes
	private Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap;
	
	// stockflowfixedvaluemap
	private Map<StockflowDTO, ArrayList<CustomTextBox>> stockflowfixedvaluemap = new HashMap<StockflowDTO, ArrayList<CustomTextBox>>();
	
	/**
	 * Default Constructor
	 * @param eventtypeDTO
	 */
	public StockflowPanel(EventtypeDTO eventtypeDTO, EventDTO eventdto, 
			Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap, Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap){
		
		// setting eventtypeDTO
		this.eventtypeDTO = eventtypeDTO;
		
		// setting eventDTO
		this.eventdto = eventdto;
		
		// setting eventtypeAttributeLabelMap
		this.eventtypeStockflowAttributeLabelMap = eventtypeStockflowAttributeLabelMap;
				
		// set eventtypeStockflowFixedAttributeMap
		this.eventtypeStockflowFixedAttributeMap = eventtypeStockflowFixedAttributeMap;
		
		// populating the StockflowPanel
		populateStockflowPanel();
	}
	
	/**
	 * Method populating the StockflowPanel for Events
	 */
	private void populateStockflowPanel(){
		this.addStyleName("stockflowPanel");
		
		// apply styles for the participationSpecificationLabel
		stockflowSpecificationLabel.addStyleName("participationSpecificationLabel");
		stockflowSpecificationLabel.addStyleName("fullsizePanel");
		
		// add the participationSpecificationLabel to the ParticipationPanel
		this.add(stockflowSpecificationLabel);
		
		// apply styles for participationTabPanel
		stockflowTabPanel.addStyleName("fullsizePanel");
				
		// add a tab for each participation of the current eventtype
		for(EventtypeStockflowDTO etsf : eventtypeDTO.getStockflows()){
					
			stockflowTabPanel.add(new StockflowContentPanel(etsf, eventdto, stockflowTabPanel, 
							eventtypeStockflowAttributeLabelMap, eventtypeDTO, eventtypeStockflowFixedAttributeMap, stockflowMap, stockflowfixedvaluemap),etsf.getResourcetypeId());
				
		}
		
		// set selected tab to the first
		stockflowTabPanel.selectTab(0);
				
		// add the stockflowTabPanel to the StockflowPanel
		this.add(stockflowTabPanel);
	}
}
