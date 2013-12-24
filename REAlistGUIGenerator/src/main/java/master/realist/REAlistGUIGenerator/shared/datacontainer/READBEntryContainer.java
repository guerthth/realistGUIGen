package master.realist.REAlistGUIGenerator.shared.datacontainer;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;

/**
 * Class administrating all Lists of retrieved entities (DualityStatus, Agent, Resource)
 * Singleton Pattern
 * @author Thomas
 *
 */
public class READBEntryContainer {
	
	// single READBEntryContainer instance
	private static READBEntryContainer instance = null;
	
	// List of existing Agents in REA DB
	private List<AgentDTO> existingAgentDTOs = new ArrayList<AgentDTO>();
	
	/**
	 * Default Constructor
	 */
	private READBEntryContainer(){
		
	}
	
	/**
	 * Static method returning the single instance of the class
	 * @return instance
	 */
	public static synchronized  READBEntryContainer getInstance() {
		
		if(instance == null){
			instance = new READBEntryContainer();
		} 
		
		return instance;
	}

	/**
	 * Getter for list of existing agents in READB
	 * @return existingAgentDTOs ist of existing agents in READB
	 */
	public List<AgentDTO> getExistingAgentDTOs() {
		return existingAgentDTOs;
	}

	/**
	 * Setter for list of existing agents in READB
	 * @param existingAgentDTOs ist of existing agents in READB
	 */
	public void setExistingAgentDTOs(List<AgentDTO> existingAgentDTOs) {
		this.existingAgentDTOs = existingAgentDTOs;
	}
}
