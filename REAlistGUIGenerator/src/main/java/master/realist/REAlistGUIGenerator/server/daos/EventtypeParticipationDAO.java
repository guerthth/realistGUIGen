package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.model.Eventtypeparticipation;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeparticipationId;

/**
 * DAO for Participation table of the REA DB
 * @author Thomas
 *
 */
public class EventtypeParticipationDAO {

	
	private HibernateUtil hibernateUtil;
	
	/**
	 * Method retrning all existing Participation objects in REA DB
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EventtypeParticipationDTO> getEventtypeParticiations(){
		
		Session session = null;
		List<Eventtypeparticipation> existingParticipations = null;
		List<EventtypeParticipationDTO> eventtypeparticipationDTOs = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingParticipations = new ArrayList<Eventtypeparticipation>(session.createQuery("from Eventtypeparticipation").list());
			eventtypeparticipationDTOs = new ArrayList<EventtypeParticipationDTO>();
			
			for(Eventtypeparticipation eventtypeparticipation : existingParticipations){
				eventtypeparticipationDTOs.add(new EventtypeParticipationDTO(eventtypeparticipation));
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally{
			if(session != null){ session.close();}
		}
		
		return eventtypeparticipationDTOs;
	}
	
	
	/**
	 * Saving an eventtypeparticipationDTO object as eventtypparticipation object to the REA DB
	 * @param eventtypeparticipationDTO object that should be stored in REA DB
	 * @return eventtypeparticipationId (String) of inserted object
	 */
	public String saveEventtypeparticipation(EventtypeParticipationDTO eventtypeparticipationDTO){
		
		Session session = null;
		Eventtypeparticipation eventtypeparticipation = new Eventtypeparticipation(eventtypeparticipationDTO);
		String eventtypeparticipationId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(eventtypeparticipation);
			eventtypeparticipationId = eventtypeparticipation.getId().getAgentTypeId() + eventtypeparticipation.getId().getEventTypeId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return eventtypeparticipationId;
	}
	
	
	/**
	 * Deleting an eventtypeparticipation object from the REA DB 
	 * @param id of the eveeventtypeparticipationnttype object that should be deleted
	 */
	public void deleteEventtypeparticipation(String eventId, String agentId){
		
		Session session = null;
		EventtypeparticipationId id = new EventtypeparticipationId(eventId, agentId);
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Eventtypeparticipation deleteEventtypeparticipation = (Eventtypeparticipation) session.get(Eventtypeparticipation.class, id);
			session.delete(deleteEventtypeparticipation);
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
