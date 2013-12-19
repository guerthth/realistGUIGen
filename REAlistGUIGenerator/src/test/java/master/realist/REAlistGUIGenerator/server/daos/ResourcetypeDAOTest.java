package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for Resourcetype DAO
 * @author Thomas
 *
 */
public class ResourcetypeDAOTest {
	
	private static ResourcetypeDAO resourcetypehandler;
	
	private static ResourcetypeDTO resourcetypeDTO;	
	
	@BeforeClass
	public static void setUpBeforeClass(){
		resourcetypehandler = (ResourcetypeDAO) SpringUtil.context.getBean("resourcetypedao");

		resourcetypeDTO = new ResourcetypeDTO();
		resourcetypeDTO.setId("TestResourcetype");
		resourcetypeDTO.setName("TestResourcetype");
		resourcetypeDTO.setBulk(true);
		resourcetypeDTO.setIdentifiable(true);
		resourcetypeDTO.setUnitOfMeasure("pounds");
		resourcetypeDTO.setParentResourcetype(null);
	}
	
	/**
	 * Testing if the ResourceypeDAOs getResourcetypeList method works as intended
	 */
	@Test
	public void testGetResourcetypeList(){
		
		int initialsize = resourcetypehandler.getResourcetypeList().size();
		
		String id = resourcetypehandler.saveResourcetype(resourcetypeDTO);
		
		int actualsize = resourcetypehandler.getResourcetypeList().size();
		assertTrue(actualsize == initialsize +1);
		
		resourcetypehandler.deleteResourcetype(id);
		actualsize = resourcetypehandler.getResourcetypeList().size();
		assertTrue(actualsize == initialsize);
	}
	
	/**
	 * Testing if the functionality of saveResource works fine
	 */
	@Test
	public void testSaveResourcetype(){
		
		int resourcetypeCount = resourcetypehandler.getResourcetypeList().size();
		
		String savedId = resourcetypehandler.saveResourcetype(resourcetypeDTO);
		assertFalse(savedId.equals(""));
		
		int newresourcetypeCount = resourcetypehandler.getResourcetypeList().size();
		
		assertTrue(newresourcetypeCount == resourcetypeCount + 1);
		
		resourcetypehandler.deleteResourcetype(savedId);
		
	}
	
	/**
	 * Testing the functionality of the ResourcetypeDAOs deleteResource method
	 */
	@Test
	public void testDeleteResourcetype(){
		
		int resourcetypeCount = resourcetypehandler.getResourcetypeList().size();
		
		String savedId = resourcetypehandler.saveResourcetype(resourcetypeDTO);
		assertFalse(savedId.equals(""));
		
		int newresourcetypeCount = resourcetypehandler.getResourcetypeList().size();
		
		assertTrue(newresourcetypeCount == resourcetypeCount + 1);
		
		resourcetypehandler.deleteResourcetype(savedId);
		
		newresourcetypeCount = resourcetypehandler.getResourcetypeList().size();
		
		assertTrue(newresourcetypeCount == resourcetypeCount);
	}
}
