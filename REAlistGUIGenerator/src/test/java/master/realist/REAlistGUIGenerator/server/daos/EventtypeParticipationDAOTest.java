package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for the EventtypeParticipationDAO
 * @author Thomas
 *
 */
public class EventtypeParticipationDAOTest {

	private static EventtypeParticipationDAO eventtypeParticipationHandler;
	private static AgenttypeDAO agenttypehandler;
	private static EventtypeDAO eventtypehandler;
	
	private static AgenttypeDTO agenttypeDTO;
	private static EventtypeDTO eventtypeDTO;
	private static EventtypeParticipationDTO eventtypeparticipationDTO;
	
	private static String testeventtypeId;
	private static String testagenttypeId;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		
		eventtypeParticipationHandler = (EventtypeParticipationDAO) SpringUtil.context.getBean("eventtypeparticipationdao");
		agenttypehandler = (AgenttypeDAO) SpringUtil.context.getBean("agenttypedao");
		eventtypehandler = (EventtypeDAO) SpringUtil.context.getBean("eventtypedao");
		
		agenttypeDTO = new AgenttypeDTO();
		agenttypeDTO.setId("Test_Agenttype");
		agenttypeDTO.setName("Test_Agenttype_Name");
		agenttypeDTO.setParentAgenttypeId(null);
		agenttypeDTO.setExternal(true);
		
		eventtypeDTO = new EventtypeDTO();
		eventtypeDTO.setId("Test_Eventtype");
		eventtypeDTO.setName("Test_Eventtype_Name");
		eventtypeDTO.setExceptionEvent(false);
		eventtypeDTO.setIsIncrement(false);
		eventtypeDTO.setResourceUsed(false);
		eventtypeDTO.setSeries(false);
		
		eventtypeparticipationDTO = new EventtypeParticipationDTO();
		eventtypeparticipationDTO.setAgenttypeId(agenttypeDTO.getId());
		eventtypeparticipationDTO.setEventtypeId(eventtypeDTO.getId());
		eventtypeparticipationDTO.setIsIdentifiable(false);
		eventtypeparticipationDTO.setIsSeries(false);
		
		// save the testentries
		testeventtypeId = eventtypehandler.saveEventtype(eventtypeDTO);
		testagenttypeId = agenttypehandler.saveAgenttype(agenttypeDTO);
		
	}
	
	/**
	 * check if the getEventtypeParticiations method of the EventtypeParticipationDAO works
	 */
	@Test
	public void getEventtypeParticipationAttributesTest(){
		
		List<EventtypeParticipationDTO> list = eventtypeParticipationHandler.getEventtypeParticiations();
		
		if(list.size() != 0){
			
			for(EventtypeParticipationDTO participation : list){
				
				assertTrue(participation.getEventtypeParticipationAttributes() == null);
				assertTrue(participation.getAgenttypeId() != null);
				assertTrue(participation.getEventtypeId() != null);
			
			}
			
		}
	}
	
	@Test
	public void testSaveAndDeleteEventtypeparticipationDTO(){
		
		List<EventtypeParticipationDTO> list = eventtypeParticipationHandler.getEventtypeParticiations();
		
		int initialsize = list.size();
		
		// save new 
		eventtypeParticipationHandler.saveEventtypeparticipation(eventtypeparticipationDTO);
		
		// get new number of eventtypeparticipations
		int newSize = eventtypeParticipationHandler.getEventtypeParticiations().size();

		// assert that number increased by 1
		assertTrue(newSize == initialsize +1);
		
		// delete new
		eventtypeParticipationHandler.deleteEventtypeparticipation(testeventtypeId, testagenttypeId);
	}
	
	/**
	 * Tasks carried out after all tests
	 */
	@AfterClass
	public static void doAfterClass(){
		
		// delete testdata in REA DB
		agenttypehandler.deleteAgenttype(testagenttypeId);
		eventtypehandler.deleteEventtype(testeventtypeId);
	}
	
}
