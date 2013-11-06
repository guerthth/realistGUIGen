package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Dualitytype;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;

public class DualitytypeDAO {
	
	private HibernateUtil hibernateUtil;
	
	
	/**
	 * Getting all the existing Dualitytypes in the REA DB
	 * @return dualitytypeDTOlist
	 */
	@SuppressWarnings("unchecked")
	public List<DualitytypeDTO> getDualitytypeList(){
		
		Session session = null;
		List<Dualitytype> existingDualitytypes = null;
		List<DualitytypeDTO> dualitytypeDTOlist = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingDualitytypes = new ArrayList<Dualitytype>(session.createQuery("from Dualitytype").list());
			
			// Using Hibernate.initialize on the eventtype lists of all
			// dualitytypes to overcoma lazy fetching
			if(existingDualitytypes != null){
				
				dualitytypeDTOlist = new ArrayList<DualitytypeDTO>();
				for(Dualitytype dt : existingDualitytypes){
					
					Hibernate.initialize(dt.getEventtypes());		
				}
			}
			
			session.beginTransaction().commit();
		}catch(Exception e){
			
			if(session != null) {session.close();}	
		}
		
		// Adding created dualitytypeDTOs to the List that is returned
		if(existingDualitytypes != null){
			
			for(Dualitytype dt : existingDualitytypes){

				dualitytypeDTOlist.add(createDualitytypeDTO(dt));
			}
		}
		
		return dualitytypeDTOlist;
	}

	
	/**
	 * Method that creates a DualitytypeDTO object for a dualitytype object
	 * @param dt
	 * @return created dualitytype
	 */
	private DualitytypeDTO createDualitytypeDTO(Dualitytype dt){
		
		Set<Eventtype> dualityEventtypes = dt.getEventtypes();
		Set<EventtypeDTO> dualityEventtypeDTOs = new HashSet<EventtypeDTO>(dualityEventtypes != null ? dualityEventtypes.size() : 0);
		
		if(dualityEventtypes != null){
			for(Eventtype et : dualityEventtypes){

				dualityEventtypeDTOs.add(createDualityEventtypeDTO(et));
			}
		}
		
		return new DualitytypeDTO(dt.getId(),dt.getName(),dt.isIsConversion(),dualityEventtypeDTOs);

	}
	
	
	/**
	 * Method that creates an EventtypeDTO object for a eventtype oject
	 * @param et
	 * @return created eventtype
	 */
	private EventtypeDTO createDualityEventtypeDTO(Eventtype et){
		
		return new EventtypeDTO(et.getId(),et.getName(),et.getIsIncrement(),et.getIsResourceUsed(),et.getIsExceptionEvent(),et.getIsSeries());

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
