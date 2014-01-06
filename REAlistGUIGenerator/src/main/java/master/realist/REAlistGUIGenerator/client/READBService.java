package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;

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
	 * @param dualityStatusDTO updated version of the object that should be saved to the REA DB
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
	
	/**
	 * Updating an existing agent object in the REA DB
	 * @param agentDTO updated version of the object that should be saved to the REA DB
	 * @return updated agent object
	 */
	public AgentDTO updateAgent(AgentDTO agentDTO);
	
	/**
	 * Retrieving all existing resourcetypes in the REA DB
	 * @return list of the existing resourcetypes in the REA DB
	 */
	public List<ResourcetypeDTO> getResourcetypes();
	
	/**
	 * Retrieving all existing resources in the REA DB
	 * @return list of existing ResourceDTOs
	 */
	public List<ResourceDTO> getResources();
	
	/**
	 * saving a resourceDTO object as resource object in the REA DB
	 * @param resourceDTO object that should be saved
	 * @return saved resourcedto object
	 */
	public ResourceDTO saveResource(ResourceDTO resourceDTO);
	
	/**
	 * deleting an resourceDTO object from the REA DB
	 * @param resourceId Id of the resource object that should be deleted from the REA DB
	 * @return Id of the deleted object
	 */
	public Integer deleteResource(Integer resourceId);
	
	/**
	 * Updating an existing resource object in the REA DB
	 * @param resourceDTO updated version of the object that should be saved to the REA DB
	 * @return updated resource object
	 */
	public ResourceDTO updateResource(ResourceDTO resourceDTO);
	
	/**
	 * getting all existing EventtypeParticipationHasAdditionalAttributes in the REA DB
	 * @param participation
	 * @return all existing EventtypeParticipationHasAdditionalAttributes in the REA DB
	 */
	public List<EventtypeParticipationHasAdditionalAttributeDTO> getEventtypeParticipationsHasAdditionalAttribtes(EventtypeParticipationDTO participation);
	
	/**
	 * getting all existing EventtypeStockflowHasAdditionalAttributes in the REA DB
	 * @param stockflow
	 * @return all existing EventtypeStockflowHasAdditionalAttributes in the REA DB
	 */
	public List<EventtypeStockflowHasAdditionalAttributeDTO> getEventtypeStockflowHasAdditionalAttributes(EventtypeStockflowDTO stockflow);
	
	/**
	 * Retrieving all existing dualities in the REA DB
	 * @return list of existing DualityDTOs
	 */
	public List<DualityDTO> getDualities();
	
	/**
	 * saving a dualityDTO object as duality object in the REA DB
	 * @param dualityDTO object that should be saved
	 * @return saved dualityDTO object
	 */
	public DualityDTO saveDuality(DualityDTO dualityDTO);

	/**
	 * deleting an dualityDTO object from the REA DB
	 * @param dualityId Id of the duality object that should be deleted from the REA DB
	 * @return Id of the deleted object
	 */
	public Integer deleteDuality(Integer dualityId);
	
	public EventDTO saveEvent(EventDTO eventDTO);
	
}
