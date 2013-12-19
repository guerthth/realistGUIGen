package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for Agenttype DAO
 * @author Thomas
 *
 */
public class AgenttypeDAOTest {

	private static AgenttypeDAO agenttypehandler;
	
	private static AgenttypeDTO agenttypeDTO;	
	
	@BeforeClass
	public static void setUpBeforeClass(){
		agenttypehandler = (AgenttypeDAO) SpringUtil.context.getBean("agenttypedao");

		agenttypeDTO = new AgenttypeDTO();
		agenttypeDTO.setId("TestAgenttype");
		agenttypeDTO.setName("TestAgenttype");
		agenttypeDTO.setExternal(false);
		agenttypeDTO.setParentAgenttypeId(null);
	}
	
	/**
	 * Testing if the AgentypeDAOs getAgenttypeList method works as intended
	 */
	@Test
	public void testGetAgenttypeList(){
		
		int initialsize = agenttypehandler.getAgenttypeList().size();
		
		String id = agenttypehandler.saveAgenttype(agenttypeDTO);
		
		int actualsize = agenttypehandler.getAgenttypeList().size();
		assertTrue(actualsize == initialsize +1);
		
		agenttypehandler.deleteAgenttype(id);
		actualsize = agenttypehandler.getAgenttypeList().size();
		assertTrue(actualsize == initialsize);
	}
	
	/**
	 * Testing if the functionality of saveAgent works fine
	 */
	@Test
	public void testSaveAgenttype(){
		
		int agenttypeCount = agenttypehandler.getAgenttypeList().size();
		
		String savedId = agenttypehandler.saveAgenttype(agenttypeDTO);
		assertFalse(savedId.equals(""));
		
		int newagenttypeCount = agenttypehandler.getAgenttypeList().size();
		
		assertTrue(newagenttypeCount == agenttypeCount + 1);
		
		agenttypehandler.deleteAgenttype(savedId);
		
	}
	
	/**
	 * Testing the functionality of the AgenttypeDAOs deleteAgent method
	 */
	@Test
	public void testDeleteAgenttype(){
		
		int agenttypeCount = agenttypehandler.getAgenttypeList().size();
		
		String savedId = agenttypehandler.saveAgenttype(agenttypeDTO);
		assertFalse(savedId.equals(""));
		
		int newagenttypeCount = agenttypehandler.getAgenttypeList().size();
		
		assertTrue(newagenttypeCount == agenttypeCount + 1);
		
		agenttypehandler.deleteAgenttype(savedId);
		
		newagenttypeCount = agenttypehandler.getAgenttypeList().size();
		
		assertTrue(newagenttypeCount == agenttypeCount);
	}
	
	
}
