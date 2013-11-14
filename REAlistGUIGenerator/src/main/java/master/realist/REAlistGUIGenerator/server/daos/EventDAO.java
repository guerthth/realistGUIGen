package master.realist.REAlistGUIGenerator.server.daos;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Duality;
import master.realist.REAlistGUIGenerator.shared.model.Dualitystatus;
import master.realist.REAlistGUIGenerator.shared.model.Event;

public class EventDAO {
	
	private HibernateUtil hibernateUtil;
	
	public int saveEvent(EventDTO eventDTO){
		
		// Helper agents since no entries exists yet
		// TODO: crosscheck with Dieter
		Agent provideagent = new Agent();
		provideagent.setId(1);
		Agent receiveagent = new Agent();
		receiveagent.setId(2);
					
		Event event = new Event(eventDTO);
		event.setAgentByProvideAgentId(provideagent);
		event.setAgentByReceiveAgentId(receiveagent);
		
		Session session = null;
		int dualityId = 0;
				
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
			dualityId = event.getId();
			
		}catch(Exception e){
			e.printStackTrace();
			if(session != null) {
				session.getTransaction().rollback();
				session.close();
			}	
		}
			
		return dualityId;		
	}
	
	/**
	 * Getter for HibernateUtil object
	 * @return hibernateUtil
	 */
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	
	/**
	 * Setter for Hibernateutil object
	 * @param hibernateUtil
	 */
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
}
