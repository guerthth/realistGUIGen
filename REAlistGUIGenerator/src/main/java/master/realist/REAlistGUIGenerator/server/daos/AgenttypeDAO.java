package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agenttype;
import master.realist.REAlistGUIGenerator.shared.model.AgenttypeHasAdditionalattribute;

/**
 * DAO for Agenttype table of the REA DB
 * @author Thomas
 *
 */
public class AgenttypeDAO {

	private HibernateUtil hibernateUtil;
	
	
	/**
	 * Getting all existing agenttype entries in the REA DB
	 * @return agenttypeDTOlist list of all existing agenttypes in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<AgenttypeDTO> getAgenttypeList(){
		
		Session session = null;
		List<Agenttype> existingAgenttypes = null;
		List<AgenttypeDTO> agenttypeDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingAgenttypes = new ArrayList<Agenttype>(session.createQuery("from Agenttype").list());
			
			if(existingAgenttypes != null){
				agenttypeDTOlist = new ArrayList<AgenttypeDTO>();
				
				for(Agenttype agenttype : existingAgenttypes){
					
					// since lazy fetching is used additional attributes, ids, and attributes need to be initialized
					Hibernate.initialize(agenttype.getAgenttypeHasAdditionalattributes());
					
					for(AgenttypeHasAdditionalattribute athaa : agenttype.getAgenttypeHasAdditionalattributes()){
						Hibernate.initialize(athaa.getAttribute());
					}
	
					// adding the created agenttypeDTOs to the List that is returned
					agenttypeDTOlist.add(createAgenttypeDTO(agenttype));

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
		
		return agenttypeDTOlist;
	}
	
	
	/**
	 * Saving an agenttypeDTO object as agenttype object to the REA DB
	 * @param agenttypeDTO object that should be stored in REA DB
	 * @return agenttypeId (String) of inserted object
	 */
	public String saveAgenttype(AgenttypeDTO agenttypeDTO){
		
		Session session = null;
		Agenttype agenttype = new Agenttype(agenttypeDTO);
		String agenttypeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(agenttype);
			agenttypeId = agenttype.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return agenttypeId;
	}
	
	
	/**
	 * Deleting an agenttype object from the REA DB 
	 * @param id of the agenttype object that should be deleted
	 */
	public void deleteAgenttype(String id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Agenttype deleteAgenttype = (Agenttype) session.get(Agenttype.class, id);
			session.delete(deleteAgenttype);
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
	 * Method transforming a agenttype object retrieved from the REA DB 
	 * to a agenttypeDTO object
	 * @param agenttype
	 * @return agenttype DTO object
	 */
	public AgenttypeDTO createAgenttypeDTO(Agenttype agenttype){
		
		Set<AgenttypeHasAdditionalattribute> additionalAgenttypeAttributes = agenttype.getAgenttypeHasAdditionalattributes();
		// only a list of the attribute DTOs is needed
		Set<AttributeDTO> additionalAgenttypeAttributeDTOs = 
				new LinkedHashSet<AttributeDTO>(additionalAgenttypeAttributes != null ? additionalAgenttypeAttributes.size() : 0);
		
		// using the additional attributes for the agenttype
		if(additionalAgenttypeAttributes != null){
			for(AgenttypeHasAdditionalattribute athaa : additionalAgenttypeAttributes){
				additionalAgenttypeAttributeDTOs.add(createAttributeDTO(athaa));

			}
		}
		
		AgenttypeDTO agenttypeDTO = new AgenttypeDTO(agenttype);
		agenttypeDTO.setAttributes(additionalAgenttypeAttributeDTOs);
		
		return agenttypeDTO;
	}
	
	/**
	 * Creates an AttributeDTO object for an AgenttypeHasAdditionalattribute object
	 * @param athaa
	 * @return created attribute DTO
	 */
	private AttributeDTO createAttributeDTO(AgenttypeHasAdditionalattribute athaa){
		
		return new AttributeDTO(athaa);
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
