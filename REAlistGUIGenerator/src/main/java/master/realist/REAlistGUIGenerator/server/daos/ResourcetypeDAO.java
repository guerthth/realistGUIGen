package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ResourcetypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Resourcetype;
import master.realist.REAlistGUIGenerator.shared.model.ResourcetypeHasAdditionalattribute;

/**
 * DAO for Resourcetype table of the REA DB
 * @author Thomas
 *
 */
public class ResourcetypeDAO {
	
	private HibernateUtil hibernateUtil;
	
	@SuppressWarnings("unchecked")
	public List<ResourcetypeDTO> getResourcetypeList(){
		
		Session session = null;
		List<Resourcetype> existingResourcetypes = null;
		List<ResourcetypeDTO> resourcetypeDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingResourcetypes = new ArrayList<Resourcetype>(session.createQuery("from Resourcetype").list());
			
			if(existingResourcetypes != null){
				resourcetypeDTOlist = new ArrayList<ResourcetypeDTO>();
				
				for(Resourcetype resourcetype : existingResourcetypes){
					
					// since lazy fetching is used additional attributes and attributes need to be initialized
					Hibernate.initialize(resourcetype.getResourcetypeHasAdditionalattributes());
					
					for(ResourcetypeHasAdditionalattribute rthaa : resourcetype.getResourcetypeHasAdditionalattributes()){
						Hibernate.initialize(rthaa.getAttribute());
					}
	
					// adding the created resourcetypeDTOs to the List that is returned
					resourcetypeDTOlist.add(createResourcetypeDTO(resourcetype));

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
		
		return resourcetypeDTOlist;
	}
	
	
	/**
	 * Saving a resourcetypeDTO object as resourcetype object to the REA DB
	 * @param resourcetypeDTO object that should be stored in REA DB
	 * @return resourcetypeId (String) of inserted object
	 */
	public String saveResourcetype(ResourcetypeDTO resourcetypeDTO){
		
		Session session = null;
		Resourcetype resourcetype = new Resourcetype(resourcetypeDTO);
		String resourcetypeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(resourcetype);
			resourcetypeId = resourcetype.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return resourcetypeId;
	}
	
	
	/**
	 * Deleting a resourcetype object from the REA DB 
	 * @param id of the resourcetype object that should be deleted
	 */
	public void deleteResourcetype(String id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Resourcetype deleteResourcetype = (Resourcetype) session.get(Resourcetype.class, id);
			session.delete(deleteResourcetype);
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
	 * Method transforming a resourcetype object retrieved from the REA DB 
	 * to a resourcetypeDTO object
	 * @param resourcetype
	 * @return resourcetype DTO object
	 */
	public ResourcetypeDTO createResourcetypeDTO(Resourcetype resourcetype){
		
		Set<ResourcetypeHasAdditionalattribute> additionalResourcetypeAttributes = resourcetype.getResourcetypeHasAdditionalattributes();
		// only a list of the attribute DTOs is needed
		Set<AttributeDTO> additionalResourcetypeAttributeDTOs = 
				new LinkedHashSet<AttributeDTO>(additionalResourcetypeAttributes != null ? additionalResourcetypeAttributes.size() : 0);
		
		// using the additional attributes for the rtype
		if(additionalResourcetypeAttributes != null){
			for(ResourcetypeHasAdditionalattribute rthaa : additionalResourcetypeAttributes){
				additionalResourcetypeAttributeDTOs.add(createAttributeDTO(rthaa));

			}
		}
		
		ResourcetypeDTO resourcetypeDTO = new ResourcetypeDTO(resourcetype);
		resourcetypeDTO.setAttributes(additionalResourcetypeAttributeDTOs);
		
		return resourcetypeDTO;
	}
	
	
	/**
	 * Creates an ResourceDTO object for an ResourcetypeHasAdditionalattribute object
	 * @param rthaa
	 * @return created attribute DTO
	 */
	private AttributeDTO createAttributeDTO(ResourcetypeHasAdditionalattribute rthaa){
		
		return new AttributeDTO(rthaa);
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
