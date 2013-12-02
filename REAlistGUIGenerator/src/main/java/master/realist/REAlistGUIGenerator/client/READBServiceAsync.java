package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;

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

	void saveDuality(DualityDTO dualityDTO,AsyncCallback<DualityDTO> callback);

	void saveEvent(EventDTO eventDTO, AsyncCallback<EventDTO> callback);
	
	void saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO, AsyncCallback<EventHasAdditionalattributevalueDTO> callback);
}
