package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.model.Dualitystatus;

/**
 * DAO for DualityStatus table of the REA DB
 * @author Thomas
 *
 */
public class DualityStatusDAO {

	private HibernateUtil hibernateUtil;

	/**
	 * Getting all exsting DualityStatus entries in the REA DB
	 * @return list of all existing dualitystatus in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<DualityStatusDTO> getDualityStatusList(){
		
		Session session = null;
		List<Dualitystatus> existingDualityStatus = null;
		List<DualityStatusDTO> dualityStatusDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingDualityStatus = new ArrayList<Dualitystatus>(session.createQuery("from Dualitystatus").list());
			
			if(existingDualityStatus != null){
				dualityStatusDTOlist = new ArrayList<DualityStatusDTO>();
				
				for(Dualitystatus dualitystatus : existingDualityStatus){
					
					// adding the created dualitystatusDTOs to the List that is returned
					dualityStatusDTOlist.add(createDualityStatusDTO(dualitystatus));
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
		
		return dualityStatusDTOlist;
	}
	
	/**
	 * Saving dualitystatusDTO object as dualityobject to the REA DB
	 * @param dualityStatusDTO object that should be saved to REA DB
	 * @return Id of saved object
	 */
	public int saveDualityStatus(DualityStatusDTO dualityStatusDTO){
		
		Dualitystatus dualityStatus = new Dualitystatus(dualityStatusDTO);
		Session session = null;
		int dualityStatusId = 0;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(dualityStatus);
			session.getTransaction().commit();
			dualityStatusId = dualityStatus.getId();
			
		} catch (Exception e){
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null){
				session.close();
			}
		}
		
		return dualityStatusId;
	}
	
	/**
	 * Deleting a dualityStatus object from the REA DB 
	 * @param deleteId Id of dualityStatus object
	 */
	public void deleteDualityStatus(int deleteId){
		
		Session session = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Dualitystatus deleteDualityStatus = (Dualitystatus) session.get(Dualitystatus.class, deleteId);
			session.delete(deleteDualityStatus);
			session.getTransaction().commit();
			
		} catch (Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null){
				session.close();
			}
			
		}
	}
	
	
	/**
	 * Updating a dualitystatusobject from the REA DB
	 * @param dualityStatusDTO updated object
	 * @return updated object
	 */
	public DualityStatusDTO updateDualityStatus(DualityStatusDTO dualityStatusDTO){
		
		Session session = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Dualitystatus updateDualityStatus = (Dualitystatus) session.get(Dualitystatus.class, dualityStatusDTO.getId());
			updateDualityStatus.setStatus(dualityStatusDTO.getStatus());
			session.update(updateDualityStatus);
			session.getTransaction().commit();
			
		} catch (Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally {
			if(session != null){session.close();}
		}
		
		return dualityStatusDTO;
	}
	
	/**
	 * Method transforming a dualitystatus object retrieved from the REA DB 
	 * to a dualityStatusDTO object
	 * @param dualitystatus
	 * @return dualityStatusDTO object
	 */
	private DualityStatusDTO createDualityStatusDTO(Dualitystatus dualitystatus){
		
		return new DualityStatusDTO(dualitystatus.getId(),dualitystatus.getStatus(),null);
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
