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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface READBServiceAsync {
	
	// retrieving all existing dualitytypes in REA DB
	void getDualitytypes(AsyncCallback<List<DualitytypeDTO>> callback);
	
	// retriecving all existing dualitystatus in the REA DB
	void getDualityStatus(AsyncCallback<List<DualityStatusDTO>> callback);
	
	// saving a dualitystatusDTO object as dualitystatus onject in the REA DB
	void saveDualityStatus (DualityStatusDTO dualityStatusDTO,AsyncCallback<DualityStatusDTO> callback);
	
	/**
	 * deleting a dualitystatusDTO object from the REA DB
	 * @param dualityId Id of the dualitystatus object that should be deleted from the REA DB
	 * @param callback 
	 */
	void deleteDualityStatus(Integer dualityId, AsyncCallback<Integer> callback);
	
	/**
	 * Updating an existing dualityobject in the REA DB
	 * @param updatedDualitystatusDTO updated vesion of the object that should be saved to the REA DB
	 * @param callback 
	 */
	void updateDualityStatus(DualityStatusDTO updatedDualitystatusDTO, AsyncCallback<DualityStatusDTO> callback);
	
	/**
	 * Retrieving all exsiting dagenttypes in the REA DB
	 * @param callback
	 */
	void getAgenttypes(AsyncCallback<List<AgenttypeDTO>> callback);
	
	/**
	 * Retrieving all existing agents in the REA DB
	 * @return list of existing AgentDTOs
	 */
	void getAgents(AsyncCallback<List<AgentDTO>> callback);
	
	/**
	 * saving a agentDTO object as agent object in the REA DB
	 * @param agentDTO object that should be saved in the REA DB
	 * @param callback
	 */
	void saveAgent(AgentDTO agentDTO, AsyncCallback<AgentDTO> callback);
	
	/**
	 * deleting an agentDTO object from the REA DB
	 * @param agentId Id of the agent object that should be deleted from the REA DB
	 * @param callback
	 */
	void deleteAgent(Integer agentId, AsyncCallback<Integer> callback);
	
	/**
	 * updating an existing agent object in the REA DB
	 * @param agentDTO updated object version that should be saved
	 * @param callback
	 */
	void updateAgent(AgentDTO agentDTO, AsyncCallback<AgentDTO> callback);

	/**
	 * Retrieving all existing resourcetypes in the REA DB
	 * @return list of the existing resourcetypes in the REA DB
	 */
	void getResourcetypes(AsyncCallback<List<ResourcetypeDTO>> callback);
	
	/**
	 * Retrieving all existing resources in the REA DB
	 * @return list of existing ResourceDTOs
	 */
	void getResources(AsyncCallback<List<ResourceDTO>> callback);
	
	/**
	 * saving a resourceDTO object as resource object in the REA DB
	 * @param resourceDTO object that should be saved in the REA DB
	 * @param callback
	 */
	void saveResource(ResourceDTO resourceDTO, AsyncCallback<ResourceDTO> callback);
	
	/**
	 * deleting an resourceDTO object from the REA DB
	 * @param resourceId Id of the resource object that should be deleted from the REA DB
	 * @param callback
	 */
	void deleteResource(Integer resourceId, AsyncCallback<Integer> callback);
	
	/**
	 * updating an existing resource object in the REA DB
	 * @param resourceDTO updated object version that should be saved
	 * @param callback
	 */
	void updateResource(ResourceDTO resourceDTO, AsyncCallback<ResourceDTO> callback);
	
	/**
	 * getting all existing EventtypeParticipationHasAdditionalAttributes in the REA DB
	 * @param callback
	 */
	void getEventtypeParticipationsHasAdditionalAttribtes(EventtypeParticipationDTO participation , AsyncCallback<List<EventtypeParticipationHasAdditionalAttributeDTO>> callback);

	/**
	 * getting all existing EventtypeStockflowHasAdditionalAttributes in the REA DB
	 * @param stockflow
	 * @param callback
	 */
	void getEventtypeStockflowHasAdditionalAttributes(EventtypeStockflowDTO stockflow, AsyncCallback<List<EventtypeStockflowHasAdditionalAttributeDTO>> callback);
	
	/**
	 * Retrieving all existing dualities in the REA DB
	 * @param callback
	 */
	void getDualities(AsyncCallback<List<DualityDTO>> callback);
	
	/**
	 * saving a dualityDTO object as duality object in the REA DB
	 * @param dualityDTO object that should be saved
	 * @return saved dualityDTO object
	 */
	void saveDuality(DualityDTO dualityDTO, AsyncCallback<DualityDTO> callback);
	
	/**
	 * deleting a dualityDTI object from the REA DB
	 * @param dualityId Id of the duality object that should be deleted from the REA DB
	 * @param callback
	 */
	void deleteDuality(Integer dualityId, AsyncCallback<Integer> callback);
	
	void saveEvent(EventDTO eventDTO, AsyncCallback<EventDTO> callback);
	
	//void saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO, AsyncCallback<EventHasAdditionalattributevalueDTO> callback);
}
