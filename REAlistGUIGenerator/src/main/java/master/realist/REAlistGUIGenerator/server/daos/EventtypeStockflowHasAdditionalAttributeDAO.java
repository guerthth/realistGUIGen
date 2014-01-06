package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.model.EventtypestockflowHasAdditionalattribute;
import master.realist.REAlistGUIGenerator.shared.model.EventtypestockflowHasAdditionalattributeId;

public class EventtypeStockflowHasAdditionalAttributeDAO {

	private HibernateUtil hibernateUtil;
	
	
	@SuppressWarnings("unchecked")
	public List<EventtypeStockflowHasAdditionalAttributeDTO> getList(String resourcetype, String eventtype){

		Session session = null;
		List<EventtypestockflowHasAdditionalattribute> existingAttrStockflow = null;
		List<EventtypeStockflowHasAdditionalAttributeDTO> existingAttrStockflowDTO = null;
		String queryString = "";
		
		if(resourcetype == null && eventtype == null){
			queryString = "from EventtypestockflowHasAdditionalattribute";
		} else{
			queryString = "from EventtypestockflowHasAdditionalattribute E WHERE E.id.eventTypeStockflowEventTypeId = '" + eventtype + 
					"' AND E.id.eventTypeStockflowResourceTypeId = '" + resourcetype + "'";
		}
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingAttrStockflow = new ArrayList<EventtypestockflowHasAdditionalattribute>(session.createQuery(queryString).list());
			existingAttrStockflowDTO = new ArrayList<EventtypeStockflowHasAdditionalAttributeDTO>();
			
			for(EventtypestockflowHasAdditionalattribute attr : existingAttrStockflow){
				
				Hibernate.initialize(attr.getEventtypestockflow().getEventtype());
				Hibernate.initialize(attr.getEventtypestockflow().getResourcetype());
				Hibernate.initialize(attr.getAttribute());

				existingAttrStockflowDTO.add(new EventtypeStockflowHasAdditionalAttributeDTO(attr));
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally{
			if(session != null){ session.close();}
		}
		
		return existingAttrStockflowDTO;
	}
	
	
	/**
	 * Saving an eventtypestockflowHasAdditionalAttributeDTO object as eventtypestockflowHasAdditionalAttribute object to the REA DB
	 * @param eventtypestockflowHasAdditionalAttributeDTO object that should be stored in REA DB
	 * @return eventtypestockflowHasAdditionalAttributeId (String) of inserted object
	 */
	public String saveEventtypestockflow(EventtypeStockflowHasAdditionalAttributeDTO eventtypestockflowhasadditionalattributeDTO){
		
		Session session = null;
		EventtypestockflowHasAdditionalattribute eventtypestockflow = new EventtypestockflowHasAdditionalattribute(eventtypestockflowhasadditionalattributeDTO);
		String eventtypestockflowHasAdditionalAttributeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(eventtypestockflow);
			eventtypestockflowHasAdditionalAttributeId = eventtypestockflow.getId().getEventTypeStockflowResourceTypeId() + "_" + eventtypestockflow.getId().getEventTypeStockflowEventTypeId() + "_" + eventtypestockflow.getAttribute().getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return eventtypestockflowHasAdditionalAttributeId;
	}
	
	
	/**
	 * Deleting an eventtypestockflow object from the REA DB 
	 * @param id of the eventtypestockflowtype object that should be deleted
	 */
	public void deleteEventtypestockflow(String eventId, String agentId, String attributeId){
		
		Session session = null;
		EventtypestockflowHasAdditionalattributeId id = new EventtypestockflowHasAdditionalattributeId(eventId, agentId, attributeId);
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			EventtypestockflowHasAdditionalattribute deleteEventtypestockflowHasAdditionalattribute = (EventtypestockflowHasAdditionalattribute) session.get(EventtypestockflowHasAdditionalattribute.class, id);
			session.delete(deleteEventtypestockflowHasAdditionalattribute);
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
