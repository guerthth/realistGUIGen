package master.realist.REAlistGUIGenerator.shared.datacontainer;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;

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
	
	// List of existing Resources in REA DB
	private List<ResourceDTO> existingResourceDTOs = new ArrayList<ResourceDTO>();

	
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

	/**
	 * Getter for list of existing resources in REA DB
	 * @return list of existing resources in REA DB
	 */
	public List<ResourceDTO> getExistingResourceDTOs() {
		return existingResourceDTOs;
	}

	/**
	 * Setter for list of existing resources in REA DB
	 * @param existingResourceDTOs list of existing resources in REA DB
	 */
	public void setExistingResourceDTOs(List<ResourceDTO> existingResourceDTOs) {
		this.existingResourceDTOs = existingResourceDTOs;
	}
	
	
}
