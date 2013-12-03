package master.realist.REAlistGUIGenerator.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import master.realist.REAlistGUIGenerator.client.READBService;
import master.realist.REAlistGUIGenerator.server.daos.AgentDAO;
import master.realist.REAlistGUIGenerator.server.daos.AgenttypeDAO;
import master.realist.REAlistGUIGenerator.server.daos.DualityDAO;
import master.realist.REAlistGUIGenerator.server.daos.DualityStatusDAO;
import master.realist.REAlistGUIGenerator.server.daos.DualitytypeDAO;
import master.realist.REAlistGUIGenerator.server.daos.EventDAO;
import master.realist.REAlistGUIGenerator.server.daos.EventHasAdditionalattributevalueDAO;
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

public class READBServiceImpl extends RemoteServiceServlet implements READBService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123094723186655175L;
	
	/**
	 * Service method returning the existing Dualitytypes in the REA DB
	 */
	public List<DualitytypeDTO> getDualitytypes() {

		DualitytypeDAO dualitytypehandler = (DualitytypeDAO) SpringUtil.context.getBean("dualitytypedao");
		return dualitytypehandler.getDualitytypeList();
	}
	
	/**
	 * Service method returning the existing DualityStatis in the REA DB
	 */
	public List<DualityStatusDTO> getDualityStatus(){
		
		DualityStatusDAO dualityStatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");
		return dualityStatushandler.getDualityStatusList();
	}
	
	/**
	 * Service method saving a dualitystatusDTO as dualitystatus object to the REA DB
	 * @param dualityStatusDTO
	 * @return
	 */
	public DualityStatusDTO saveDualityStatus(DualityStatusDTO dualityStatusDTO){
		
		DualityStatusDAO dualityStatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");
		int saveId =  dualityStatushandler.saveDualityStatus(dualityStatusDTO);
		dualityStatusDTO.setId(saveId);
		
		return dualityStatusDTO;
	}
	
	/**
	 * Service method deleting a dualitystatus from the REA DB
	 * @param deleteId
	 */
	public Integer deleteDualityStatus(Integer deleteId){
		
		DualityStatusDAO dualityStatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");
		dualityStatushandler.deleteDualityStatus(deleteId);
		
		return deleteId;
	}
	
	/**
	 * Updating an existing dualityobject in the REA DB
	 * @param dualityStatusDTO updated vesion of the object that should be saved to the REA DB
	 * @return updated dualitystatusDTO object
	 */
	public DualityStatusDTO updateDualityStatus(DualityStatusDTO dualityStatusDTO){
		
		DualityStatusDAO dualityStatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");
		return dualityStatushandler.updateDualityStatus(dualityStatusDTO);

	}
	
	/**
	 * Retrieving all exsiting dagenttypes in the REA DB
	 * @return list of the existing agenttypes in the REA DB
	 */
	public List<AgenttypeDTO> getAgenttypes(){
		AgenttypeDAO agenttypehandler = (AgenttypeDAO) SpringUtil.context.getBean("agenttypedao");
		return agenttypehandler.getAgenttypeList();
	}
	
	/**
	 * Retrieving all existing agents in the REA DB
	 * @return list of existing AgentDTOs
	 */
	public List<AgentDTO> getAgents() {
		AgentDAO agenthandler = (AgentDAO) SpringUtil.context.getBean("agentdao");
		return agenthandler.getAgentList();
	}
	
	/**
	 * saving a agentDTO object as agent object in the REA DB
	 * @param agentDTO object that should be saved
	 * @return saved agentdto object
	 */
	public AgentDTO saveAgent(AgentDTO agentDTO) {
		AgentDAO agenthandler = (AgentDAO) SpringUtil.context.getBean("agentdao");
		int saveId = agenthandler.saveAgent(agentDTO);
		agentDTO.setId(saveId);
		return agentDTO;
	}
	
	/**
	 * deleting an agentDTO object from the REA DB
	 * @param agentId Id of the agent object that should be deleted from the REA DB
	 * @return Id of the deleted object
	 */
	public Integer deleteAgent(Integer deleteId) {
		AgentDAO agenthandler = (AgentDAO) SpringUtil.context.getBean("agentdao");
		agenthandler.deleteAgent(deleteId);
		
		return deleteId;
	}
	
	/**
	 * Updating an existing agent object in the REA DB
	 * @param agentDTO updated version of the object that should be saved to the REA DB
	 * @return updated agent object
	 */
	public AgentDTO updateAgent(AgentDTO agentDTO){
		return null;
	}
	
	/**
	 * Service method saving a dualityDTO as duality object to the REA DB
	 * @param dualityDTO
	 * @return dualityDTO with stored ID of dbentry
	 */
	public DualityDTO saveDuality(DualityDTO dualityDTO) {
		
		DualityDAO dualityhandler = (DualityDAO) SpringUtil.context.getBean("dualitydao");
		int savedId = dualityhandler.saveDuality(dualityDTO);
		dualityDTO.setId(savedId);
		
		return dualityDTO;
		
	}
	
	/**
	 * Service method saving an eventDTO as event object to the DB
	 * @param eventDTO
	 * @return eventDTO with stored ID of dbentry
	 */
	public EventDTO saveEvent(EventDTO eventDTO){
		
		EventDAO eventhandler = (EventDAO) SpringUtil.context.getBean("eventdao");
		int savedId = eventhandler.saveEvent(eventDTO);
		eventDTO.setId(savedId);
		
		return eventDTO;
	}

	/**
	 * Service method saving an EventHasAdditionalattributevalueDTO as EventHasAdditionalattributevalue object to the DB
	 * @param addattrvalDTO
	 * @return addattrvalDTO with stored ID of dbentry
	 */
	public EventHasAdditionalattributevalueDTO saveEventHasAdditionalattributevalue(
			EventHasAdditionalattributevalueDTO addattrvalDTO) {
		// TODO Auto-generated method stub
		System.out.println(addattrvalDTO.getId());
		System.out.println(addattrvalDTO.getId().getAttributeId());
		System.out.println(addattrvalDTO.getId().getEventId());
		
		EventHasAdditionalattributevalueDAO eventHasAdditionalattributevaluehandler = 
				(EventHasAdditionalattributevalueDAO) SpringUtil.context.getBean("eventHasAdditionalattributevalueDAO");
		
		return eventHasAdditionalattributevaluehandler.saveEventHasAdditionalattributevalue(addattrvalDTO);

	}



}
