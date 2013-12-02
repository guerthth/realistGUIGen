package master.realist.REAlistGUIGenerator.server.daos;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.model.Duality;
import master.realist.REAlistGUIGenerator.shared.model.Dualitystatus;

public class DualityDAO {
	
	private HibernateUtil hibernateUtil;
	
	public int saveDuality(DualityDTO dualityDTO){
		
		// Helper status since no entries exists yet
		// TODO: crosscheck with Dieter
		Dualitystatus status = new Dualitystatus();
		status.setId(1);
		
		Duality duality = new Duality(dualityDTO);
		duality.setDualitystatus(status);
		Session session = null;
		int dualityId = 0;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(duality);
			session.getTransaction().commit();
			dualityId = duality.getId();
		
		}catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		}finally{
			if(session != null) {
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
