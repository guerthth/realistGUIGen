package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourceHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;
import master.realist.REAlistGUIGenerator.shared.model.Resource;
import master.realist.REAlistGUIGenerator.shared.model.ResourceHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.Resourcetype;

/**
 * DAO for Resource table of the REA DB
 * @author Thomas
 *
 */
public class ResourceDAO {

	private HibernateUtil hibernateUtil;
	
	
	/**
	 * Getting all existing resource entries in the REA DB
	 * @return resourceDTOlist list of all existing resources in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceDTO> getResourceList(){
		
		Session session = null;
		List<Resource> existingResources = null;
		List<ResourceDTO> resourceDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingResources = new ArrayList<Resource>(session.createQuery("from Resource").list());
			
			if(existingResources != null){
				resourceDTOlist = new ArrayList<ResourceDTO>();
				
				for(Resource resource : existingResources){
					
					Hibernate.initialize(resource.getResourcetype());
					Hibernate.initialize(resource.getResourceHasAdditionalattributevalues());
					
					// adding the created resourceDTOs to the List that is returned
					resourceDTOlist.add(createResourceDTO(resource));
				}
			}
			
			session.getTransaction().commit();
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}	
		}
		
		return resourceDTOlist;
	}
	
	
	/**
	 * Saving an resourceDTO object as resourceobject to the REA DB
	 * @param resourceDTO object that should be stored in REA DB
	 * @return resourceId of inserted object
	 */
	public int saveResource(ResourceDTO resourceDTO){
		
		Session session = null;
		Resource resource = new Resource(resourceDTO);
		int resourceId = 0;
		
		Set<ResourceHasAdditionalattributevalueDTO> additionalAttributeValues = resourceDTO.getAdditionalAttributeValues();
		
		Attribute additionalAttribute;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			// if resource has additional attributes
			if(additionalAttributeValues != null){
																	
				for(ResourceHasAdditionalattributevalueDTO dto : additionalAttributeValues){
																							
					// Retrieve the already stored attribute object
					additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());					
																				
					// add the additional attribute values to the resource objects list
					resource.getResourceHasAdditionalattributevalues().add(createAdditionalAttributeValue(resource,additionalAttribute,dto));
											
				}
			}
			
			// save the resource
			session.save(resource);	
				
			resourceId = resource.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return resourceId;
	}
	
	
	/**
	 * Deleting an resource object from the REA DB 
	 * @param id of the resource object that should be deleted
	 */
	public void deleteResource(int id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Resource deleteResource = (Resource) session.get(Resource.class, id);
			session.delete(deleteResource);
			session.getTransaction().commit();
			
		} catch(Exception e){
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally {
			if(session != null){
				session.close();
			}
		}
	}
	
	/**
	 * Updating an existing ResourceDTO object
	 * @param updatedResourceDTO updated object that should be stored in the REA DB
	 * @return updated resourceDTO
	 */
	public ResourceDTO updateResource(ResourceDTO resourceDTO){
		
		Session session = null;
		ResourceDTO updatedResourceDTO = null;
		
		Set<ResourceHasAdditionalattributevalueDTO> additionalAttributeValues = resourceDTO.getAdditionalAttributeValues();

		Attribute additionalAttribute;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Resource updateResource = (Resource) session.get(Resource.class, resourceDTO.getId());
			updateResource.setName(resourceDTO.getName());
			updateResource.setIsBulk(resourceDTO.isBulk());
			updateResource.setIsIdentifiable(resourceDTO.isIdentifiable());
			updateResource.setQoH(resourceDTO.getQoH());
			updateResource.setIsComposed(resourceDTO.getIsComposed());
			updateResource.setResourcetype(new Resourcetype(resourceDTO.getResourcetype()));	
			
			// if resource has additional attributes
			if(additionalAttributeValues != null){
				
				Set<ResourceHasAdditionalattributevalue> updatedAdditionalAttributeValues = 
						new LinkedHashSet<ResourceHasAdditionalattributevalue>(additionalAttributeValues.size());
																			
				for(ResourceHasAdditionalattributevalueDTO dto : additionalAttributeValues){
																				
					// Retrieve the already stored attribute object
					additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());					
					
					// add the updated additional attribute values to the resource objects list
					updatedAdditionalAttributeValues.add(createAdditionalAttributeValue(updateResource,additionalAttribute,dto));
								
				}
				
				// set the updated additional attribute values set for the updateResource object
				updateResource.setResourceHasAdditionalattributevalues(updatedAdditionalAttributeValues);
			}
			
			session.update(updateResource);
			session.getTransaction().commit();
			updatedResourceDTO = resourceDTO;
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null){ session.getTransaction().rollback();}
		} finally {
			if(session != null){ session.close();}
		}
		
		return updatedResourceDTO;
	}
	
	/**
	 * Method transforming a resource object retrieved from the REA DB 
	 * to a resourceDTO object
	 * @param resource
	 * @return resourceDTO object
	 */
	private ResourceDTO createResourceDTO(Resource resource){
		
		Set<ResourceHasAdditionalattributevalue> additionalAttributes = 
				new LinkedHashSet<ResourceHasAdditionalattributevalue>(resource.getResourceHasAdditionalattributevalues().size());
		additionalAttributes = resource.getResourceHasAdditionalattributevalues();	
		Set<ResourceHasAdditionalattributevalueDTO> additionalAttributeDTOs = 
				new LinkedHashSet<ResourceHasAdditionalattributevalueDTO>(additionalAttributes != null ? additionalAttributes.size() : 0);
		
		// create ResourceDTO object
		ResourceDTO createdResourceDTO = new ResourceDTO(resource);
		
		// add additional attribute DTOs to resourceDTO
		for(ResourceHasAdditionalattributevalue additionalAttribute : additionalAttributes){
			
			ResourceHasAdditionalattributevalueDTO additionalAttributeDTO = new ResourceHasAdditionalattributevalueDTO(additionalAttribute);
			additionalAttributeDTO.setResource(createdResourceDTO);

			additionalAttributeDTOs.add(additionalAttributeDTO);
		}
		
		createdResourceDTO.setAdditionalAttributeValues(additionalAttributeDTOs);
		
		return createdResourceDTO;
		
	}	
	
	/**
	 * Method creating an ResourceHasAdditionalattributevalue for a specific ResourceHasAdditionalattributevalueDTO, 
	 * resource, and attribute
	 * @param resource Resource object
	 * @param additionalAttribute Attribute object
	 * @param dto ResourceHasAdditionalattributevalueDTO containing textual, numeric, date, or boolean values
	 * @return additionalResourceAttributeValue 
	 */
	private ResourceHasAdditionalattributevalue createAdditionalAttributeValue(Resource resource, Attribute additionalAttribute, ResourceHasAdditionalattributevalueDTO dto){

		// create an ResourceHasAdditionalattributevalue object and set its values	
		ResourceHasAdditionalattributevalue additionalResourceAttributeValue = null;
		
		additionalResourceAttributeValue = new ResourceHasAdditionalattributevalue();
		additionalResourceAttributeValue.setResource(resource);
		additionalResourceAttributeValue.setAttribute(additionalAttribute);
		additionalResourceAttributeValue.setBooleanValue(dto.getBooleanValue());
		additionalResourceAttributeValue.setDatetimeValue(dto.getDatetimeValue());
		additionalResourceAttributeValue.setNumericValue(dto.getNumericValue());
		additionalResourceAttributeValue.setTextualValue(dto.getTextualValue());
		
		return additionalResourceAttributeValue;
	}
	
	
	
	/**
	 * Getter for hibernatenateUtil object
	 * @return hibernateUtil
	 */
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	/**
	 * Setter for hibernateutil object
	 * @param hibernateUtil
	 */
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
}
