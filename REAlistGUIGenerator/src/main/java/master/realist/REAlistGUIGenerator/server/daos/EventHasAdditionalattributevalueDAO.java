package master.realist.REAlistGUIGenerator.server.daos;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.model.Duality;
import master.realist.REAlistGUIGenerator.shared.model.Dualitystatus;
import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalueId;

public class EventHasAdditionalattributevalueDAO {
	
	private HibernateUtil hibernateUtil;
	
	public EventHasAdditionalattributevalueDTO saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO){
		
		EventHasAdditionalattributevalue eventHasAdditionalattributevalue 
				= new EventHasAdditionalattributevalue(addattrvalDTO);
		
		Session session = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(eventHasAdditionalattributevalue);
			session.getTransaction().commit();
		
		}catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally{
			if(session != null) {
				session.close();
			}	
		}
		
		return addattrvalDTO;
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
