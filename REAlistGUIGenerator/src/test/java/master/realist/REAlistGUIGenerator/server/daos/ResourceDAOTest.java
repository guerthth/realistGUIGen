package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testclass for ResourceDAO
 * @author Thomas
 *
 */
public class ResourceDAOTest {
	
	private static ResourceDAO resourcehandler;
	private static ResourcetypeDAO resourcetypehandler;
	private static AttributeDAO attributehandler;
	
	private static String testResourcetypeId1;
	private static String testResourcetypeId2;
	private static String attributeId1;
	private static String attributeId2;
	
	private static ResourcetypeDTO resourcetypeDTO;
	private static ResourcetypeDTO newresourcetypeDTO;
	private static ResourceDTO resourceDTO;
	
	private static Set<ResourceHasAdditionalattributevalueDTO> attributes;
	private static AttributeDTO attribute1;
	private static AttributeDTO attribute2;
	
	private static ResourceHasAdditionalattributevalueDTO attrvalue1;
	private static ResourceHasAdditionalattributevalueDTO attrvalue2;
	
	/**
	 * Setting up resourcetype and resource handler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		
		resourcehandler = (ResourceDAO) SpringUtil.context.getBean("resourcedao");
		resourcetypehandler = (ResourcetypeDAO) SpringUtil.context.getBean("resourcetypedao");
		attributehandler = (AttributeDAO) SpringUtil.context.getBean("attributedao");
				
		resourcetypeDTO = new ResourcetypeDTO();
		resourcetypeDTO.setId("Test_Resourcetype");
		resourcetypeDTO.setName("Test_Resourcetype_Name");
		resourcetypeDTO.setParentResourcetype(null);
		resourcetypeDTO.setBulk(true);
		resourcetypeDTO.setIdentifiable(false);
		resourcetypeDTO.setUnitOfMeasure("pounds");
				
		newresourcetypeDTO = new ResourcetypeDTO();
		newresourcetypeDTO.setId("Test_Resourcetype2");
		newresourcetypeDTO.setName("Test_Resourcetype2_Name");
		newresourcetypeDTO.setParentResourcetype(null);
		newresourcetypeDTO.setBulk(false);
		newresourcetypeDTO.setIdentifiable(true);
		newresourcetypeDTO.setUnitOfMeasure("euro");	
		
		// resource
		// TODO: Overhead attributes in resources since they could be inherited from their resourcetypes
		resourceDTO = new ResourceDTO();
		resourceDTO.setName("Test_Resource_Name");
		resourceDTO.setResourcetype(resourcetypeDTO);
		resourceDTO.setBulk(resourcetypeDTO.isBulk());
		resourceDTO.setIdentifiable(resourcetypeDTO.isIdentifiable());
		resourceDTO.setIsComposed(false);
		resourceDTO.setQoH(20);
		
		// additional attributes
		// first attribute
		attribute1 = new AttributeDTO();
		attribute1.setId("Test_Attribute1");
		attribute1.setName("Test_Attribute1_Name");
		attribute1.setDatatype("VARCHAR");
					
		// second attribute 
		attribute2 = new AttributeDTO();
		attribute2.setId("Test_Attribute2");
		attribute2.setName("Test_Attribute2_Name");
		attribute2.setDatatype("DATE");
		
		// create testdata in REA DB
		testResourcetypeId1 = resourcetypehandler.saveResourcetype(resourcetypeDTO);
		testResourcetypeId2 = resourcetypehandler.saveResourcetype(newresourcetypeDTO);
		attributeId1 = attributehandler.saveAttribute(attribute1);
		attributeId2 = attributehandler.saveAttribute(attribute2);
		
	}
	
	/**
	 * Setting up an test resourceDTO object
	 */
	@Before
	public void setUpBeforeTest(){
		
		// attribute values
		attributes = new LinkedHashSet<ResourceHasAdditionalattributevalueDTO>();
								
		attrvalue1 = new ResourceHasAdditionalattributevalueDTO();
		attrvalue1.setResource(resourceDTO);
		attrvalue1.setAttribute(attribute1);
		attrvalue1.setTextualValue("Mercedes");
		attrvalue2 = new ResourceHasAdditionalattributevalueDTO();
		attrvalue2.setResource(resourceDTO);
		attrvalue2.setAttribute(attribute2);
		attrvalue2.setDatetimeValue(new Date());
								
		attributes.add(attrvalue1);
		attributes.add(attrvalue2);

	}
	
	
	/**
	 * Testing if the getResourceList() method works as intended
	 * 
	 */
	@Test
	public void testGetResourcetList(){
		
		int initialsize = resourcehandler.getResourceList().size();
		
		int id = resourcehandler.saveResource(resourceDTO);
		int currentsize = resourcehandler.getResourceList().size();
		assertTrue(currentsize == initialsize + 1);
		
		resourcehandler.deleteResource(id);
		currentsize = resourcehandler.getResourceList().size();
		assertTrue(currentsize == initialsize);
	}
	
	/**
	 * Testing if the saveResource method works as intended
	 */
	@Test
	public void testSaveResource(){
		
		int initialListSize = resourcehandler.getResourceList().size();
		
		int id = resourcehandler.saveResource(resourceDTO);
		
		int newListSize = resourcehandler.getResourceList().size();
		
		assertTrue(newListSize == initialListSize + 1);
		
		resourcehandler.deleteResource(id);
		
		newListSize = resourcehandler.getResourceList().size();
		
		assertTrue(newListSize == initialListSize);
	}
	
	
	/**
	 * Testing if the deleteResourcet method works as intended
	 */
	@Test
	public void testDeleteResource(){
		
		int id = resourcehandler.saveResource(resourceDTO);
		resourceDTO.setId(id);
		
		int listSize = resourcehandler.getResourceList().size();
		
		resourcehandler.deleteResource(resourceDTO.getId());
		int newListSize = resourcehandler.getResourceList().size();
		
		assertTrue(newListSize == listSize - 1);
	}
	
	
	/**
	 * Testing if the updateResource method works as intended
	 */
	@Test
	public void testUpdateResource(){
		
		int resourceCount = resourcehandler.getResourceList().size();
		
		int addedId = resourcehandler.saveResource(resourceDTO);
		
		resourceDTO.setId(addedId);
		resourceDTO.setName("Updated_Test_Resource_Name");
		resourceDTO.setResourcetype(newresourcetypeDTO);
		resourceDTO.setQoH(50);
		
		resourcehandler.updateResource(resourceDTO);
		
		List<ResourceDTO> resourceList = resourcehandler.getResourceList();
		int newresourceCount = resourceList.size();
		
		assertTrue(resourceCount + 1 == newresourceCount);
		
		assertTrue(resourceList.get(resourceList.size()-1).getId() == addedId);
		assertTrue(resourceList.get(resourceList.size()-1).getName().equals("Updated_Test_Resource_Name"));
		assertTrue(resourceList.get(resourceList.size()-1).getResourcetype().getName().equals(newresourcetypeDTO.getName()));
		assertTrue(resourceList.get(resourceList.size()-1).getQoH() == 50);
		
		resourcehandler.deleteResource(addedId);
		resourceList = resourcehandler.getResourceList();
		
		assertTrue(resourceList.size() == resourceCount);
			
	}
	
	
	/**
	 * Test if adding additional attributes is done right
	 */
	@Test
	public void testAddAddtionalAttributes(){
		
		// get initial number of existing resources
		int initialResourceCount = resourcehandler.getResourceList().size();
							
		resourceDTO.setAdditionalAttributeValues(attributes);
		
		// save resource with additional attributes
		int savedId = resourcehandler.saveResource(resourceDTO);
		resourceDTO.setId(savedId);

		// get number of existing resources after inserting new resource
		int newResourceCount = resourcehandler.getResourceList().size();
		
		// assert that count increased by one
		assertTrue(newResourceCount == initialResourceCount +1);
		
		// assert that newest resource has additional attributes
		Set<ResourceHasAdditionalattributevalueDTO> additionalAttributes = resourcehandler.getResourceList().get(resourcehandler.getResourceList().size()-1).getAdditionalAttributeValues();
		assertTrue(additionalAttributes.size() != 0);
		// and that the first attributes id is 'Test_Attribute1'
		ResourceHasAdditionalattributevalueDTO firstAttribute = additionalAttributes.iterator().next();
		assertTrue(firstAttribute.getAttribute().getId().equals("Test_Attribute1"));
		assertTrue(firstAttribute.getTextualValue().equals("Mercedes"));
		
		// delete that added resource
		resourcehandler.deleteResource(resourceDTO.getId());
		
		// assert that newCount equals the initial count
		newResourceCount = resourcehandler.getResourceList().size();
		assertTrue(newResourceCount == initialResourceCount);
	}
	
	
	/**
	 * Test if updating additional attributes is done right
	 */
	@Test
	public void testUpdateAddtionalAttributes(){

		// get initial number of existing resources
		int initialResourceCount = resourcehandler.getResourceList().size();
							
		resourceDTO.setAdditionalAttributeValues(attributes);
		
		// save resource with additional attributes
		int savedId = resourcehandler.saveResource(resourceDTO);
		resourceDTO.setId(savedId);

		// get number of existing resources after inserting new resource
		int newResourceCount = resourcehandler.getResourceList().size();
		
		// assert that count increased by one
		assertTrue(newResourceCount == initialResourceCount +1);
		
		// update textual value of second attribute of testresource
		Iterator<ResourceHasAdditionalattributevalueDTO> iter = resourceDTO.getAdditionalAttributeValues().iterator();
		iter.next();
		iter.next().setDatetimeValue(new Date());;
		resourcehandler.updateResource(resourceDTO);
		
		// retrieve list of resources, assure that size has not changed
		List<ResourceDTO> resourceList = resourcehandler.getResourceList();
		assertTrue(resourceList.size() == newResourceCount);
		
		// assure that textualvalue of last added resources second attribute has changed
		Iterator<ResourceHasAdditionalattributevalueDTO> iter2 = resourceList.get(newResourceCount-1).getAdditionalAttributeValues().iterator();
		iter2.next();
		ResourceHasAdditionalattributevalueDTO secondAdditionalAttributeValue = iter2.next();
		assertTrue(secondAdditionalAttributeValue.getAttribute().getId().equals("Test_Attribute2"));
		assertFalse(secondAdditionalAttributeValue.getDatetimeValue() == attrvalue2.getDatetimeValue());

		// delete that added resource
		resourcehandler.deleteResource(resourceDTO.getId());
		
		// assert that newCount equals the initial count
		newResourceCount = resourcehandler.getResourceList().size();
		assertTrue(newResourceCount == initialResourceCount);
		
	}	
	
	
	
	/**
	 * Tasks carried out after all tests
	 */
	@AfterClass
	public static void doAfterClass(){
		
		// delete testdata in REA DB
		resourcetypehandler.deleteResourcetype(testResourcetypeId1);
		resourcetypehandler.deleteResourcetype(testResourcetypeId2);
		attributehandler.deleteAttribute(attributeId1);
		attributehandler.deleteAttribute(attributeId2);	
	}
}
