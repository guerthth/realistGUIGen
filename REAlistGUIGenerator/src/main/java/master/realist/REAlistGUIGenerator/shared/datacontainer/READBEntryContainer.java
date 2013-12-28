package master.realist.REAlistGUIGenerator.shared.datacontainer;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;

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
	
	// List of existing DualityStatus in REA DB
	private List<DualityStatusDTO> existingDualityStatusDTOs = new ArrayList<DualityStatusDTO>();

	
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
	 * @return existingAgentDTOs list of existing agents in READB
	 */
	public List<AgentDTO> getExistingAgentDTOs() {
		return existingAgentDTOs;
	}

	/**
	 * Setter for list of existing agents in READB
	 * @param existingAgentDTOs list of existing agents in READB
	 */
	public void setExistingAgentDTOs(List<AgentDTO> existingAgentDTOs) {
		this.existingAgentDTOs = existingAgentDTOs;
	}
	
	/**
	 * Getter for list of existing dualitystatus in REA DB
	 * @return existingDualityStatusDTOs list of existing dualitystatus in REA DB
	 */
	public List<DualityStatusDTO> getExistingDualityStatusDTOs() {
		return existingDualityStatusDTOs;
	}

	/**
	 * Setter for list of existing dualitystatus in REA DB
	 * @param existingDualityStatusDTOs list of existing dualitystatus in REA DB
	 */
	public void setExistingDualityStatusDTOs(
			List<DualityStatusDTO> existingDualityStatusDTOs) {
		this.existingDualityStatusDTOs = existingDualityStatusDTOs;
	}
}
