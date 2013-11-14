package master.realist.REAlistGUIGenerator.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import master.realist.REAlistGUIGenerator.client.READBService;
import master.realist.REAlistGUIGenerator.server.daos.DualityDAO;
import master.realist.REAlistGUIGenerator.server.daos.DualitytypeDAO;
import master.realist.REAlistGUIGenerator.server.daos.EventDAO;
import master.realist.REAlistGUIGenerator.server.daos.EventHasAdditionalattributevalueDAO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
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
	 * Service method saving an dualityDTO as duality object to the DB
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
