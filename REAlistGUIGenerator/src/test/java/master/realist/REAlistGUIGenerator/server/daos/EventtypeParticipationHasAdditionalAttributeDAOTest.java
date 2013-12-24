package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for the EventtypeParticipationHasAdditionalAttributeDAO
 * @author Thomas
 *
 */
public class EventtypeParticipationHasAdditionalAttributeDAOTest {

	private static EventtypeParticipationHasAdditionalAttributeDAO eventParticipationhasAdditionalAttributeHandler;
	private static EventtypeParticipationDAO eventtypeParticipationHandler;
	private static AgenttypeDAO agenttypehandler;
	private static EventtypeDAO eventtypehandler;
	private static AttributeDAO attributehandler;
	
	private static AgenttypeDTO agenttypeDTO;
	private static EventtypeDTO eventtypeDTO;
	private static AttributeDTO attributeDTO;
	private static EventtypeParticipationDTO eventtypeparticipationDTO;
	private static EventtypeParticipationHasAdditionalAttributeDTO eventtypeparticipationhasadditionalattributeDTO;
	
	private static String testeventtypeId;
	private static String testagenttypeId;
	private static String testattributeId;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		
		eventParticipationhasAdditionalAttributeHandler = (EventtypeParticipationHasAdditionalAttributeDAO) SpringUtil.context.getBean("eventtypeparticipationhasadditionalattributedao");
		eventtypeParticipationHandler = (EventtypeParticipationDAO) SpringUtil.context.getBean("eventtypeparticipationdao");
		agenttypehandler = (AgenttypeDAO) SpringUtil.context.getBean("agenttypedao");
		eventtypehandler = (EventtypeDAO) SpringUtil.context.getBean("eventtypedao");
		attributehandler = (AttributeDAO) SpringUtil.context.getBean("attributedao");
		
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
		
		attributeDTO = new AttributeDTO();
		attributeDTO.setId("Test_Attribute1");
		attributeDTO.setName("Test_Attribute1_Name");
		attributeDTO.setDatatype("VARCHAR");
		
		eventtypeparticipationDTO = new EventtypeParticipationDTO();
		eventtypeparticipationDTO.setAgenttypeId(agenttypeDTO.getId());
		eventtypeparticipationDTO.setEventtypeId(eventtypeDTO.getId());
		eventtypeparticipationDTO.setIsIdentifiable(false);
		eventtypeparticipationDTO.setIsSeries(false);
		
		eventtypeparticipationhasadditionalattributeDTO = new EventtypeParticipationHasAdditionalAttributeDTO();
		eventtypeparticipationhasadditionalattributeDTO.setAttribute(attributeDTO);
		eventtypeparticipationhasadditionalattributeDTO.setAgenttypeId(agenttypeDTO.getId());
		eventtypeparticipationhasadditionalattributeDTO.setEventtypeId(eventtypeDTO.getId());
		eventtypeparticipationhasadditionalattributeDTO.setOptional(true);
		eventtypeparticipationhasadditionalattributeDTO.setPolicyProperty(false);
		eventtypeparticipationhasadditionalattributeDTO.setReserveProperty(true);
		
		// save the testentries
		testeventtypeId = eventtypehandler.saveEventtype(eventtypeDTO);
		testagenttypeId = agenttypehandler.saveAgenttype(agenttypeDTO);
		testattributeId = attributehandler.saveAttribute(attributeDTO);
		eventtypeParticipationHandler.saveEventtypeparticipation(eventtypeparticipationDTO);
		
	}
	
	/**
	 * check if the getList method of the EventtypeParticipationHasAdditionalAttributeDAO works
	 */
	@Test
	public void getEventtypeParticipationAttributesTest(){
		
		List<EventtypeParticipationHasAdditionalAttributeDTO> list = eventParticipationhasAdditionalAttributeHandler.getList(null,null);
		
		if(list.size() != 0){
			
			for(EventtypeParticipationHasAdditionalAttributeDTO attr : list){
				//System.out.println("Eventtype: " + attr.getEventtypeparticipation().getEventtype().getId());
				//System.out.println("Agenttype: " + attr.getEventtypeparticipation().getAgenttype().getId());
				//System.out.println("AttributeId: " + attr.getAttribute().getId());
				assertTrue(attr.getEventtypeId() != null);
				assertTrue(attr.getAgenttypeId() != null);
				assertTrue(attr.getAttribute() != null);
			}
			
		}
	}
	
	/**
	 * check if the getList method of the EventtypeParticipationHasAdditionalAttributeDAO works
	 * when searching for specific participations (by agenttype and eventtypeID)
	 */
	@Test
	public void getEventtypeSpecificParticipationAttributesTest(){
		
		List<EventtypeParticipationHasAdditionalAttributeDTO> list = eventParticipationhasAdditionalAttributeHandler.getList(agenttypeDTO.getId(), eventtypeDTO.getId());
		
		int initialsize = list.size();
		
		// save new
		eventParticipationhasAdditionalAttributeHandler.saveEventtypeparticipation(eventtypeparticipationhasadditionalattributeDTO);
		
		int newsize = eventParticipationhasAdditionalAttributeHandler.getList(agenttypeDTO.getId(), eventtypeDTO.getId()).size();
		
		assertTrue(initialsize == newsize -1);
		
		if(newsize != 0){

			for(EventtypeParticipationHasAdditionalAttributeDTO attr : eventParticipationhasAdditionalAttributeHandler.getList(agenttypeDTO.getId(), eventtypeDTO.getId())){

				assertTrue(attr.getEventtypeId() != null);
				assertTrue(attr.getAgenttypeId() != null);
				assertTrue(attr.getAttribute() != null);
			}
			
		}
		
		// delete new
		eventParticipationhasAdditionalAttributeHandler.deleteEventtypeparticipation(testeventtypeId, testagenttypeId, testattributeId);
	
		newsize = eventParticipationhasAdditionalAttributeHandler.getList(agenttypeDTO.getId(), eventtypeDTO.getId()).size();
	
		assertTrue(initialsize == newsize);
	}
	
	
	/**
	 * Tasks carried out after all tests
	 */
	@AfterClass
	public static void doAfterClass(){
		
		// delete testdata in REA DB
		eventtypeParticipationHandler.deleteEventtypeparticipation(testeventtypeId, testagenttypeId);
		agenttypehandler.deleteAgenttype(testagenttypeId);
		eventtypehandler.deleteEventtype(testeventtypeId);
		attributehandler.deleteAttribute(testattributeId);
		
	}
	
	
}
