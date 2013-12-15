package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;

public class AttributeDAO {
	
	private HibernateUtil hibernateUtil;
	
	/**
	 * Retrievind all existing Attributes from REA DB
	 * @return savedAttributes
	 */
	@SuppressWarnings("unchecked")
	public List<Attribute> getAttributes(){
		
		List<Attribute> savedAttributes = null;
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			savedAttributes = new ArrayList<Attribute>(session.createQuery("FROM Attribute").list());
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally {
			if(session != null) { session.close();}
		}
		
		return savedAttributes;
	}
	
	
	/**
	 * Saving an AttributeDTO as Atteribute to the REA DB
	 * @param attributeDTO
	 * @return
	 */
	public String saveAttribute(AttributeDTO attributeDTO){

		Session session = null;
		Attribute attribute = new Attribute(attributeDTO);
		String attributeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(attribute);
			attributeId = attribute.getId();
			session.getTransaction().commit();
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally{
			if(session != null) {session.close();}
		}
		return attributeId;
	}
	
	/**
	 * Deleting an existing Attribute with specific id from REA DB
	 * @param id id of attribute that should be deleted
	 */
	public void deleteAttribute(String id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Attribute deleteAttribute = (Attribute) session.get(Attribute.class, id);
			session.delete(deleteAttribute);
			session.getTransaction().commit();
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null) {session.getTransaction().rollback();}
		} finally{
			if(session != null){session.close();}
		}
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
