package master.realist.REAlistGUIGenerator.client;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowDTO;

import com.google.gwt.user.client.ui.TabPanel;

/**
 * Class representing the Panel for Events
 * @author Thomas
 *
 */
public class EventPanel extends TabPanel{
	
	private DualitytypeDTO selectedDualityType;
	private DualityDTO saveDualityDTO;
	private Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeAttributeLabelMap;
	private Map<EventDTO,Map<ParticipationDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeParticipationAttributeLabelMap;
	private Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap;
	private List<EventDTO> saveEventDTOList;
	private Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap;
	
	/**
	 * Constructor
	 * @param dualityDTO
	 */
	public EventPanel(DualityDTO dualityDTO, List<EventDTO> saveEventDTOList, Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeAttributeLabelMap,
						Map<EventDTO,Map<ParticipationDTO, Map<AttributeDTO,CustomTextBox>>> eventtypeParticipationAttributeLabelMap, Map<EventDTO,Map<StockflowDTO,Map<AttributeDTO,CustomTextBox>>> eventtypeStockflowAttributeLabelMap,
						Map<EventDTO, Map<StockflowDTO, ArrayList<CustomTextBox>>> eventtypeStockflowFixedAttributeMap){
		
		this.saveDualityDTO = dualityDTO;
		this.selectedDualityType = dualityDTO.getDualitytype();
		this.eventtypeAttributeLabelMap = eventtypeAttributeLabelMap;
		this.eventtypeParticipationAttributeLabelMap = eventtypeParticipationAttributeLabelMap;
		this.eventtypeStockflowAttributeLabelMap = eventtypeStockflowAttributeLabelMap;
		this.saveEventDTOList = saveEventDTOList;
		this.eventtypeStockflowFixedAttributeMap = eventtypeStockflowFixedAttributeMap;
		populateEventPanel();
	}
	
	/**
	 * Method populating the EventPanel
	 */
	private void populateEventPanel(){
		
		// apply style for the EventPanel
		this.addStyleName("fullsizePanel");
		
		// retrieve all eventtypes that occur in the specified duality and save them to
		// arraylists for increments and decrements
		ArrayList<EventtypeDTO> incrementEventtypes = new ArrayList<EventtypeDTO>();
		ArrayList<EventtypeDTO> decrementEventtypes = new ArrayList<EventtypeDTO>();
				
		for(EventtypeDTO etdto : selectedDualityType.getEventtypes()){
			
			if(etdto.getIsIncrement()){
				incrementEventtypes.add(etdto);
			}else{
				decrementEventtypes.add(etdto);
			}
		}
		
		// trimmed name of an eventtype
		String trimmedEventTypeName;
		
		// adding tabs for increment event sets
		for(EventtypeDTO increment : incrementEventtypes){

			trimmedEventTypeName = increment.getName().substring(increment.getName().indexOf("_")+1);
			this.add(new EventContentPanel(increment, saveDualityDTO, saveEventDTOList, eventtypeAttributeLabelMap, 
								eventtypeParticipationAttributeLabelMap, eventtypeStockflowAttributeLabelMap, eventtypeStockflowFixedAttributeMap), trimmedEventTypeName);
		}
		
		// adding tabls for decrement event sets
		for(EventtypeDTO decrement : decrementEventtypes){

			trimmedEventTypeName = decrement.getName().substring(decrement.getName().indexOf("_")+1);
			this.add(new EventContentPanel(decrement, saveDualityDTO, saveEventDTOList, eventtypeAttributeLabelMap,
							eventtypeParticipationAttributeLabelMap, eventtypeStockflowAttributeLabelMap, eventtypeStockflowFixedAttributeMap), trimmedEventTypeName);
		}
		
		
	}
}
