package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reaDB")
public interface READBService extends RemoteService{
	
	// retrieving all existing dualitytypes in REA DB
	public List<DualitytypeDTO> getDualitytypes();
	
	// retrieving all existing dualitystatus in the REA DB
	public List<DualityStatusDTO> getDualityStatus();
	
	// saving a dualitystatusDTO object as dualitystatus onject in the REA DB
	public DualityStatusDTO saveDualityStatus(DualityStatusDTO dualityStatusDTO);

	/**
	 * deleting a dualitystatusDTO object from the REA DB
	 * @param deleteId Id of the dualitystatus object that should be deleted from the REA DB
	 * @return Id of the deleted object
	 */
	public Integer deleteDualityStatus(Integer deleteId);
	
	/**
	 * Updating an existing dualityobject in the REA DB
	 * @param dualityStatusDTO updated vesion of the object that should be saved to the REA DB
	 * @return updated dualitystatusDTO object
	 */
	public DualityStatusDTO updateDualityStatus(DualityStatusDTO dualityStatusDTO);
	
	/**
	 * Retrieving all exsiting dagenttypes in the REA DB
	 * @return list of the existing agenttypes in the REA DB
	 */
	public List<AgenttypeDTO> getAgenttypes();
	
	/**
	 * Retrieving all existing agents in the REA DB
	 * @return list of existing AgentDTOs
	 */
	public List<AgentDTO> getAgents();
	
	/**
	 * saving a agentDTO object as agent object in the REA DB
	 * @param agentDTO object that should be saved
	 * @return saved agentdto object
	 */
	public AgentDTO saveAgent(AgentDTO agentDTO);
	
	/**
	 * deleting an agentDTO object from the REA DB
	 * @param agentId Id of the agent object that should be deleted from the REA DB
	 * @return Id of the deleted object
	 */
	public Integer deleteAgent(Integer agentId);
	
	public DualityDTO saveDuality(DualityDTO dualityDTO);
	
	public EventDTO saveEvent(EventDTO eventDTO);
	
	public EventHasAdditionalattributevalueDTO saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO);
	
}
