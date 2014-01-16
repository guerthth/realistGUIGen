package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.model.Duality;

public class DualityDAO {
	
	private HibernateUtil hibernateUtil;
	
	
	/**
	 * Getting all existing duality entries in the REA DB
	 * @return dualityDTOlist list of all existing dualities in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<DualityDTO> getDualityList(){
		
		Session session = null;
		List<Duality> existingDualities = null;
		List<DualityDTO> dualityDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingDualities = new ArrayList<Duality>(session.createQuery("from Duality").list());
			
			if(existingDualities != null){
				dualityDTOlist = new ArrayList<DualityDTO>();
				
				for(Duality duality : existingDualities){
					
					// since lazy fetching is used, status, types, and events need to be initialized
					Hibernate.initialize(duality.getEvents());
					Hibernate.initialize(duality.getDualitystatus());
					Hibernate.initialize(duality.getDualitytype());
	
					// adding the created dualityDTOs to the List that is returned
					dualityDTOlist.add(createDualityDTO(duality));

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
		
		return dualityDTOlist;
	}
	
	
	/**
	 * Method saving a new duality object to the REA DB
	 * @param dualityDTO
	 * @return dualityId of the new inserted duality object
	 */
	public int saveDuality(DualityDTO dualityDTO){
		
		Duality duality = new Duality(dualityDTO);
		
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
	 * Deleting a duality object from the REA DB 
	 * @param id of the duality object that should be deleted
	 */
	public void deleteDuality(int id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Duality deleteDuality = (Duality) session.get(Duality.class, id);
			session.delete(deleteDuality);
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
	 * Method creating a DualityDTO object from 
	 * @param duality
	 * @return
	 */
	private DualityDTO createDualityDTO(Duality duality){
		
		return new DualityDTO(duality);
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
