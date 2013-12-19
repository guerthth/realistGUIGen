package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgentHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for AgentDAO
 * @author Thomas
 *
 */
public class AgentDAOTest {
	
	private static AgentDAO agenthandler;
	private static AgenttypeDAO agenttypehandler;
	private static AttributeDAO attributehandler;
	
	private static String testAgenttypeId1;
	private static String testAgenttypeId2;
	private static String attributeId1;
	private static String attributeId2;
	
	private static Set<AgenttypeDTO> agenttypes;
	private static AgenttypeDTO agenttypeDTO;
	private static AgenttypeDTO newagenttypeDTO;
	private static AgentDTO agentDTO;
	
	private static Set<AgentHasAdditionalattributevalueDTO> attributes;
	private static AttributeDTO attribute1;
	private static AttributeDTO attribute2;
	
	private static AgentHasAdditionalattributevalueDTO attrvalue1;
	private static AgentHasAdditionalattributevalueDTO attrvalue2;
	
	/**
	 * Setting up agenttype and agent handler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		
		agenthandler = (AgentDAO) SpringUtil.context.getBean("agentdao");
		agenttypehandler = (AgenttypeDAO) SpringUtil.context.getBean("agenttypedao");
		attributehandler = (AttributeDAO) SpringUtil.context.getBean("attributedao");
				
		agenttypeDTO = new AgenttypeDTO();
		agenttypeDTO.setId("Test_Agenttype");
		agenttypeDTO.setName("Test_Agenttype_Name");
		agenttypeDTO.setParentAgenttypeId(null);
		agenttypeDTO.setExternal(true);
				
		newagenttypeDTO = new AgenttypeDTO();
		newagenttypeDTO.setId("Test_Agenttype2");
		newagenttypeDTO.setName("Test_Agenttype2_Name");
		newagenttypeDTO.setParentAgenttypeId(null);
		newagenttypeDTO.setExternal(true);
			
		// agenttypes
		agenttypes = new LinkedHashSet<AgenttypeDTO>();
		agenttypes.add(agenttypeDTO);		
		
		// agents
		agentDTO = new AgentDTO();
		agentDTO.setName("Test_Agent_Name");
		agentDTO.setAgenttypes(agenttypes);	
		
		// additional attributes
		// first attribute)
		attribute1 = new AttributeDTO();
		attribute1.setId("Test_Attribute1");
		attribute1.setName("Test_Attribute1_Name");
		attribute1.setDatatype("VARCHAR");
					
		// second attribute
		attribute2 = new AttributeDTO();
		attribute2.setId("Test_Attribute2");
		attribute2.setName("Test_Attribute2_Name");
		attribute2.setDatatype("INT");
		
		// create testdata in REA DB
		testAgenttypeId1 = agenttypehandler.saveAgenttype(agenttypeDTO);
		testAgenttypeId2 = agenttypehandler.saveAgenttype(newagenttypeDTO);
		attributeId1 = attributehandler.saveAttribute(attribute1);
		attributeId2 = attributehandler.saveAttribute(attribute2);
		
	}
	
	/**
	 * Setting up an test agentDTO object
	 */
	@Before
	public void setUpBeforeTest(){
		
		// attribute values
		attributes = new LinkedHashSet<AgentHasAdditionalattributevalueDTO>();
								
		attrvalue1 = new AgentHasAdditionalattributevalueDTO();
		attrvalue1.setAgent(agentDTO);
		attrvalue1.setAttribute(attribute1);
		attrvalue1.setTextualValue("Mercedes");
		attrvalue2 = new AgentHasAdditionalattributevalueDTO();
		attrvalue2.setAgent(agentDTO);
		attrvalue2.setAttribute(attribute2);
		attrvalue2.setNumericValue(1.1);
								
		attributes.add(attrvalue1);
		attributes.add(attrvalue2);

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
	
	/**
	 * Testing if the updateAgent method works as intended
	 */
	@Test
	public void testUpdateAgent(){
		
		int agentCount = agenthandler.getAgentList().size();
		
		int addedId = agenthandler.saveAgent(agentDTO);
		
		agentDTO.setId(addedId);
		agentDTO.setName("Updated_Test_Agent_Name");
		
		agenttypes.clear();
		agenttypes.add(newagenttypeDTO);
		agentDTO.setAgenttypes(agenttypes);
		
		agenthandler.updateAgent(agentDTO);
		
		List<AgentDTO> agentList = agenthandler.getAgentList();
		int newagentCount = agentList.size();
		
		assertTrue(agentCount + 1 == newagentCount);
		
		assertTrue(agentList.get(agentList.size()-1).getId() == addedId);
		assertTrue(agentList.get(agentList.size()-1).getName().equals("Updated_Test_Agent_Name"));
		assertTrue(agentList.get(agentList.size()-1).getAgenttypes().iterator().next().getName().equals(newagenttypeDTO.getName()));
		
		agenthandler.deleteAgent(addedId);
		agentList = agenthandler.getAgentList();
		
		assertTrue(agentList.size() == agentCount);
		
		agenttypes.clear();
		agenttypes.add(agenttypeDTO);
		
	}
	

	/**
	 * Test if adding additional attributes is done right
	 */
	@Test
	public void testAddAddtionalAttributes(){
		
		// get initial number of existing agents
		int initialAgentCount = agenthandler.getAgentList().size();
							
		agentDTO.setAdditionalAttributeValues(attributes);
		
		// save agent with additional attributes
		int savedId = agenthandler.saveAgent(agentDTO);
		agentDTO.setId(savedId);

		// get number of existing agents after inserting new agent
		int newAgentCount = agenthandler.getAgentList().size();
		
		// assert that count increased by one
		assertTrue(newAgentCount == initialAgentCount +1);
		
		// assert that newest agent has additional attributes
		Set<AgentHasAdditionalattributevalueDTO> additionalAttributes = agenthandler.getAgentList().get(agenthandler.getAgentList().size()-1).getAdditionalAttributeValues();
		assertTrue(additionalAttributes.size() != 0);
		// and that the first attributes id is 'Test_Attribute1'
		AgentHasAdditionalattributevalueDTO firstAttribute = additionalAttributes.iterator().next();
		assertTrue(firstAttribute.getAttribute().getId().equals("Test_Attribute1"));
		
		// delete that added agent
		agenthandler.deleteAgent(agentDTO.getId());
		
		// assert that newCount equals the initial count
		newAgentCount = agenthandler.getAgentList().size();
		assertTrue(newAgentCount == initialAgentCount);
	}
	
	
	/**
	 * Test if updating additional attributes is done right
	 */
	@Test
	public void testUpdateAddtionalAttributes(){

		// get initial number of existing agents
		int initialAgentCount = agenthandler.getAgentList().size();
							
		agentDTO.setAdditionalAttributeValues(attributes);
		
		// save agent with additional attributes
		int savedId = agenthandler.saveAgent(agentDTO);
		agentDTO.setId(savedId);

		// get number of existing agents after inserting new agent
		int newAgentCount = agenthandler.getAgentList().size();
		
		// assert that count increased by one
		assertTrue(newAgentCount == initialAgentCount +1);
		
		// update textual value of first attribute of testagent
		agentDTO.getAdditionalAttributeValues().iterator().next().setTextualValue("New_Mercedes");
		agenthandler.updateAgent(agentDTO);
		
		// retrieve list of agents, assure that size has not changed
		List<AgentDTO> agentList = agenthandler.getAgentList();
		assertTrue(agentList.size() == newAgentCount);
		
		// assure that textualvalue of last added agents first attribute is 'New Mercedes' and attribute id is 'Car_Brand'
		Iterator<AgentHasAdditionalattributevalueDTO> iter = agentList.get(newAgentCount-1).getAdditionalAttributeValues().iterator();
		AgentHasAdditionalattributevalueDTO firstAdditionalAttributeValue = iter.next();
		assertTrue(firstAdditionalAttributeValue.getAttribute().getId().equals("Test_Attribute1"));
		assertTrue(firstAdditionalAttributeValue.getTextualValue().equals("New_Mercedes"));

		// delete that added agent
		agenthandler.deleteAgent(agentDTO.getId());
		
		// assert that newCount equals the initial count
		newAgentCount = agenthandler.getAgentList().size();
		assertTrue(newAgentCount == initialAgentCount);
		
	}	
	
	/**
	 * Tasks carried out after all tests
	 */
	@AfterClass
	public static void doAfterClass(){
		
		// delete testdata in REA DB
		agenttypehandler.deleteAgenttype(testAgenttypeId1);
		agenttypehandler.deleteAgenttype(testAgenttypeId2);
		attributehandler.deleteAttribute(attributeId1);
		attributehandler.deleteAttribute(attributeId2);	
	}

}
