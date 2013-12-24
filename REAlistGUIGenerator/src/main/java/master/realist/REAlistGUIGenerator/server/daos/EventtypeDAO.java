package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;

import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;

import org.hibernate.Session;

public class EventtypeDAO {
	
	private HibernateUtil hibernateUtil;
	
	/**
	 * Getting all existing eventtype entries in the REA DB
	 * @return eventtypeDTOlist list of all existing eventtypes in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<EventtypeDTO> getEventtypeList(){
		
		Session session = null;
		List<Eventtype> existingEventtypes = null;
		List<EventtypeDTO> eventtypeDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingEventtypes = new ArrayList<Eventtype>(session.createQuery("from Eventtype").list());
			
			if(existingEventtypes != null){
				eventtypeDTOlist = new ArrayList<EventtypeDTO>();
				
				for(Eventtype eventtype : existingEventtypes){
	
					// adding the created eventtypeDTOs to the List that is returned
					eventtypeDTOlist.add(createEventtypeDTO(eventtype));

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
		
		return eventtypeDTOlist;
	}
	
	
	/**
	 * Saving an eventtypeDTO object as eventtype object to the REA DB
	 * @param eventtypeDTO object that should be stored in REA DB
	 * @return eventtypeId (String) of inserted object
	 */
	public String saveEventtype(EventtypeDTO eventtypeDTO){
		
		Session session = null;
		Eventtype eventtype = new Eventtype(eventtypeDTO);
		String eventtypeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(eventtype);
			eventtypeId = eventtype.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return eventtypeId;
	}
	
	
	/**
	 * Deleting an eventtype object from the REA DB 
	 * @param id of the eventtype object that should be deleted
	 */
	public void deleteEventtype(String id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Eventtype deleteEventtype = (Eventtype) session.get(Eventtype.class, id);
			session.delete(deleteEventtype);
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
	 * Method transforming an eventtype object retrieved from the REA DB 
	 * to an eventtypeDTO object
	 * @param eventtype
	 * @return eventtype DTO object
	 */
	public EventtypeDTO createEventtypeDTO(Eventtype eventtype){
		
		EventtypeDTO eventtypeDTO = new EventtypeDTO(eventtype);
		
		return eventtypeDTO;
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
