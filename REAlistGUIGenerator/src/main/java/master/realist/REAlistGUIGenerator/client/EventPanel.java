package master.realist.REAlistGUIGenerator.client;
import java.util.ArrayList;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;

import com.google.gwt.user.client.ui.TabPanel;

/**
 * Class representing the Panel for Events
 * @author Thomas
 *
 */
public class EventPanel extends TabPanel{
	
	private DualitytypeDTO selectedDualityType;
	
	/**
	 * Constructor
	 * @param selectedDualityType
	 */
	public EventPanel(DualitytypeDTO selectedDualityType){
		
		this.selectedDualityType = selectedDualityType;
		populateEventPanel();
	}
	
	/**
	 * Method populating the EventPanel
	 */
	private void populateEventPanel(){
		
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
			this.add(new EventContentPanel(increment), trimmedEventTypeName);
		}
		
		// adding tabls for decrement event sets
		for(EventtypeDTO decrement : decrementEventtypes){
			trimmedEventTypeName = decrement.getName().substring(decrement.getName().indexOf("_")+1);
			this.add(new EventContentPanel(decrement), trimmedEventTypeName);
		}
		
		
	}
}
