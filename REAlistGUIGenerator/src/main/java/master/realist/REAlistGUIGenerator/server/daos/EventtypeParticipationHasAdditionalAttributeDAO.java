package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationHasAdditionalAttributeDTO;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeparticipationHasAdditionalattribute;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeparticipationHasAdditionalattributeId;

/**
 * DAO for EventtypeParticipationHasAdditionalAttribute table of the REA DB
 * @author Thomas
 *
 */
public class EventtypeParticipationHasAdditionalAttributeDAO {
	
	private HibernateUtil hibernateUtil;
	
	@SuppressWarnings("unchecked")
	public List<EventtypeParticipationHasAdditionalAttributeDTO> getList(String agenttype, String eventtype){

		Session session = null;
		List<EventtypeparticipationHasAdditionalattribute> existingAttrPart = null;
		List<EventtypeParticipationHasAdditionalAttributeDTO> existingAttrPartDTO = null;
		String queryString = "";
		
		if(agenttype == null && eventtype == null){
			queryString = "from EventtypeparticipationHasAdditionalattribute";
		} else{
			queryString = "from EventtypeparticipationHasAdditionalattribute E WHERE E.id.eventTypeParticipationEventTypeId = '" + eventtype + 
					"' AND E.id.eventTypeParticipationAgentTypeId = '" + agenttype + "'";
		}
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingAttrPart = new ArrayList<EventtypeparticipationHasAdditionalattribute>(session.createQuery(queryString).list());
			existingAttrPartDTO = new ArrayList<EventtypeParticipationHasAdditionalAttributeDTO>();
			
			for(EventtypeparticipationHasAdditionalattribute attr : existingAttrPart){
				
				Hibernate.initialize(attr.getEventtypeparticipation().getEventtype());
				Hibernate.initialize(attr.getEventtypeparticipation().getAgenttype());
				Hibernate.initialize(attr.getAttribute());

				existingAttrPartDTO.add(new EventtypeParticipationHasAdditionalAttributeDTO(attr));
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally{
			if(session != null){ session.close();}
		}
		
		return existingAttrPartDTO;
	}
	
	
	/**
	 * Saving an eventtypeparticipationHasAdditionalAttributeDTO object as eventtypeparticipationHasAdditionalAttribute object to the REA DB
	 * @param eventtypeparticipationHasAdditionalAttributeDTO object that should be stored in REA DB
	 * @return eventtypeparticipationHasAdditionalAttributeId (String) of inserted object
	 */
	public String saveEventtypeparticipation(EventtypeParticipationHasAdditionalAttributeDTO eventtypeparticipationhasadditionalattributeDTO){
		
		Session session = null;
		EventtypeparticipationHasAdditionalattribute eventtypeparticipation = new EventtypeparticipationHasAdditionalattribute(eventtypeparticipationhasadditionalattributeDTO);
		String eventtypeparticipationHasAdditionalAttributeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(eventtypeparticipation);
			eventtypeparticipationHasAdditionalAttributeId = eventtypeparticipation.getId().getEventTypeParticipationAgentTypeId() + "_" + eventtypeparticipation.getId().getEventTypeParticipationEventTypeId() + "_" + eventtypeparticipation.getAttribute().getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return eventtypeparticipationHasAdditionalAttributeId;
	}
	
	
	/**
	 * Deleting an eventtypeparticipation object from the REA DB 
	 * @param id of the eveeventtypeparticipationnttype object that should be deleted
	 */
	public void deleteEventtypeparticipation(String eventId, String agentId, String attributeId){
		
		Session session = null;
		EventtypeparticipationHasAdditionalattributeId id = new EventtypeparticipationHasAdditionalattributeId(eventId, agentId, attributeId);
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			EventtypeparticipationHasAdditionalattribute deleteEventtypeparticipationHasAdditionalattribute = (EventtypeparticipationHasAdditionalattribute) session.get(EventtypeparticipationHasAdditionalattribute.class, id);
			session.delete(deleteEventtypeparticipationHasAdditionalattribute);
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
