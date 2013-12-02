package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class AgentDAOTest {
	
	private static AgentDAO agenthandler;
	
	private static Set<AgenttypeDTO> agenttypes;
	private static AgenttypeDTO agenttypeDTO;
	private static AgentDTO agentDTO;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		agenthandler = (AgentDAO) SpringUtil.context.getBean("agentdao");
		
		agenttypes = new HashSet<AgenttypeDTO>();
		
		agenttypeDTO = new AgenttypeDTO();
		agenttypeDTO.setId("Customer");
		agenttypeDTO.setName("Customer");
		agenttypeDTO.setParentAgenttypeId(null);
		agenttypeDTO.setExternal(true);
		
		agenttypes.add(agenttypeDTO);
		
		agentDTO = new AgentDTO();
		agentDTO.setName("testAgent");
		agentDTO.setAgenttypes(agenttypes);
	}
	
	/**
	 * Testing if the getAgentList() method works as intended
	 * 
	 */
	@Test
	public void testGetAgentList(){
		
		int initialsize = agenthandler.getAgentList().size();
		
		int id = agenthandler.saveAgent(agentDTO);
		int currentsize = agenthandler.getAgentList().size();
		assertTrue(currentsize == initialsize + 1);
		
		agenthandler.deleteAgent(id);
		currentsize = agenthandler.getAgentList().size();
		assertTrue(currentsize == initialsize);
	}
	
	/**
	 * Testing if the saveAgent method works as intended
	 */
	@Test
	public void testSaveAgent(){
		
		int initialListSize = agenthandler.getAgentList().size();
		
		int id = agenthandler.saveAgent(agentDTO);
		
		int newListSize = agenthandler.getAgentList().size();
		
		assertTrue(newListSize == initialListSize + 1);
		
		agenthandler.deleteAgent(id);
		
		newListSize = agenthandler.getAgentList().size();
		
		assertTrue(newListSize == initialListSize);
	}
	
	/**
	 * Testing if the deleteAgent method works as intended
	 */
	@Test
	public void testDeleteAgent(){
		
		int id = agenthandler.saveAgent(agentDTO);
		agentDTO.setId(id);
		
		int listSize = agenthandler.getAgentList().size();
		
		agenthandler.deleteAgent(agentDTO.getId());
		int newListSize = agenthandler.getAgentList().size();
		
		assertTrue(newListSize == listSize - 1);
	}
	
}
