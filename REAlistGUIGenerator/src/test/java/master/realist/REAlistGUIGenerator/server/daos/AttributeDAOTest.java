package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AttributeDAOTest {
	
	private static AttributeDAO attributehandler;
	
	private AttributeDTO attributeDTO;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		
		attributehandler = (AttributeDAO) SpringUtil.context.getBean("attributedao");
	}
	
	@Before
	public void setUpBeforeTest(){
		
		attributeDTO = new AttributeDTO();
		attributeDTO.setId("Test_Attribute");
		attributeDTO.setName("Test_Attribute_Name");
		attributeDTO.setDatatype("INT");		
	}
	
	@Test
	public void testSaveAttribute(){
		
		// get initial number of attributes
		int initialAttributeNumber = attributehandler.getAttributes().size();
		
		// save new test attribute to REA DB
		String savedId = attributehandler.saveAttribute(attributeDTO);
		
		// get new number of existing attributes in REA DB and assert that it increased by 1
		int newAttributeNumber = attributehandler.getAttributes().size();
		assertTrue(newAttributeNumber == initialAttributeNumber + 1);
		
		// delete added attribute from REA DB
		attributehandler.deleteAttribute(savedId);
		
		// get new number of existing attributes in REA DB and assert that it equals initial size
		newAttributeNumber = attributehandler.getAttributes().size();
		assertTrue(newAttributeNumber == initialAttributeNumber);
		
	}
}

