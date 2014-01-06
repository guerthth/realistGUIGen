package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeStockflowDTO;
import master.realist.REAlistGUIGenerator.shared.model.Dualitytype;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeHasAdditionalattribute;
import master.realist.REAlistGUIGenerator.shared.model.Eventtypeparticipation;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeparticipationHasAdditionalattribute;
import master.realist.REAlistGUIGenerator.shared.model.Eventtypestockflow;
import master.realist.REAlistGUIGenerator.shared.model.EventtypestockflowHasAdditionalattribute;

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
					// initialze eventtypes for dualitytypes
					Hibernate.initialize(dt.getEventtypes());
					for(Eventtype et : dt.getEventtypes()){
						
						// initialize additional attributes for each eventtype
						Hibernate.initialize(et.getEventtypeHasAdditionalattributes());
						for(EventtypeHasAdditionalattribute ethaa : et.getEventtypeHasAdditionalattributes()){
							// initialize each attribute 
							Hibernate.initialize(ethaa.getAttribute());
						}
						
						// initialize stockflows
						Hibernate.initialize(et.getEventtypestockflows());
						for(Eventtypestockflow etsf : et.getEventtypestockflows()){
							// initialize additional stockflow attributes
							Hibernate.initialize(etsf.getEventtypestockflowHasAdditionalattributes());
							for(EventtypestockflowHasAdditionalattribute atsfaa : etsf.getEventtypestockflowHasAdditionalattributes()){
								// initialize each attribute
								Hibernate.initialize(atsfaa.getAttribute());
							}
						}
						
						// initialize participations
						Hibernate.initialize(et.getEventtypeparticipations());
						for(Eventtypeparticipation etp : et.getEventtypeparticipations()){
							// initialize additional participation attributes
							Hibernate.initialize(etp.getEventtypeparticipationHasAdditionalattributes());
							for(EventtypeparticipationHasAdditionalattribute etphaa : etp.getEventtypeparticipationHasAdditionalattributes()){
								// initialize each attribute
								Hibernate.initialize(etphaa.getAttribute());
							}
						}
						
						
					}
					
					// Adding created dualitytypeDTOs to the List that is returned
					dualitytypeDTOlist.add(createDualitytypeDTO(dt));
				}
			}
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();	
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}

		return dualitytypeDTOlist;
	}
	
	
	/**
	 * Saving an dualitytypeDTO object as dualitytype object to the REA DB
	 * @param dualitytypeDTO object that should be stored in REA DB
	 * @return dualitytypeId (String) of inserted object
	 */
	public String saveDualityttype(DualitytypeDTO dualitytypeDTO){
		
		Session session = null;
		Dualitytype dualitytype = new Dualitytype(dualitytypeDTO);
		String dualitytypeId = "";
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(dualitytype);
			dualitytypeId = dualitytype.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return dualitytypeId;
	}
	
	
	/**
	 * Deleting an dualitytype object from the REA DB 
	 * @param id of the dualitytype object that should be deleted
	 */
	public void deleteDualitytype(String id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Dualitytype deleteDualitytype = (Dualitytype) session.get(Dualitytype.class, id);
			session.delete(deleteDualitytype);
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
	 * Method that creates a DualitytypeDTO object for a dualitytype object
	 * @param dt
	 * @return created dualitytype
	 */
	private DualitytypeDTO createDualitytypeDTO(Dualitytype dt){

		Set<Eventtype> dualityEventtypes = dt.getEventtypes();
		Set<EventtypeDTO> dualityEventtypeDTOs = new HashSet<EventtypeDTO>(dualityEventtypes != null ? dualityEventtypes.size() : 0);
		
		if(dualityEventtypes != null){
			for(Eventtype et : dualityEventtypes){
				
				dualityEventtypeDTOs.add(createEventtypeDTO(et));
			}
		}
		
		return new DualitytypeDTO(dt.getId(),dt.getName(),dt.isIsConversion(),dualityEventtypeDTOs);

	}
	
	
	/**
	 * Method that creates an EventtypeDTO object for an eventtype oject
	 * @param et
	 * @return created eventtype
	 */
	private EventtypeDTO createEventtypeDTO(Eventtype et){

		// list of additional attributes
		Set<EventtypeHasAdditionalattribute> additionalEventtypeAttributes = et.getEventtypeHasAdditionalattributes();
		
		// only a list of the attribute DTOs is needed
		Set<AttributeDTO> additionalEventtypeAttributeDTOs = 
				new LinkedHashSet<AttributeDTO>(additionalEventtypeAttributes != null ? additionalEventtypeAttributes.size() : 0);
				
		// using the additional attributes for the eventtype
		if(additionalEventtypeAttributes != null){
			for(EventtypeHasAdditionalattribute ethaa : additionalEventtypeAttributes){
						
				additionalEventtypeAttributeDTOs.add(new AttributeDTO(ethaa));

			}
		}
		
		// STOCKFLOWS
		// list of existing stockflows
		Set<Eventtypestockflow> eventtypestockflows = et.getEventtypestockflows();
		
		// only a list of the attribute DTOs is needed
		Set<EventtypeStockflowDTO> eventtypestockflowDTOs = 
				new LinkedHashSet<EventtypeStockflowDTO>(eventtypestockflows != null ? eventtypestockflows.size() : 0);
						
		// using the stockflows for the eventtype
		if(eventtypestockflowDTOs != null){
			for(Eventtypestockflow etsf : eventtypestockflows){
				
				eventtypestockflowDTOs.add(createEventtypeStockflowDTO(etsf));
			}
		}		
		
		// PARTICIPATIONS
		// list of existing participations
		Set<Eventtypeparticipation> eventtypeparticipations =  et.getEventtypeparticipations();

		// only a list of the attribute DTOs is needed
		Set<EventtypeParticipationDTO> eventtypeparticipationDTOs = 
				new LinkedHashSet<EventtypeParticipationDTO>(eventtypeparticipations != null ? eventtypeparticipations.size() : 0);
				
		// using the participations for the eventtype
		if(eventtypeparticipations != null){
			for(Eventtypeparticipation etp : eventtypeparticipations){
				
				eventtypeparticipationDTOs.add(createEventtypePartipationDTO(etp));
			}
		}
		
		// setting everything for eventtypedto
		EventtypeDTO eventtypeDTO = new EventtypeDTO(et);
		eventtypeDTO.setAttributes(additionalEventtypeAttributeDTOs);
		eventtypeDTO.setParticipations(eventtypeparticipationDTOs);
		eventtypeDTO.setStockflows(eventtypestockflowDTOs);

		return eventtypeDTO;
	}
	
	
	/**
	 * Creates an EventtypeParticipationDTO object for an Eventtypeparticipation object
	 * @param etp
	 * @return created EventtypeParticipationDTO
	 */
	private EventtypeParticipationDTO createEventtypePartipationDTO(Eventtypeparticipation etp){
		
		// Set of additional attributes
		Set<EventtypeparticipationHasAdditionalattribute> eventtypeParticipationAttributes = etp.getEventtypeparticipationHasAdditionalattributes();
		
		// only a list of the attribute DTOs is needed
		Set<AttributeDTO> additionalEventtypeParticipationAttributeDTOs = 
				new LinkedHashSet<AttributeDTO>(eventtypeParticipationAttributes != null ? eventtypeParticipationAttributes.size() : 0);
		
		if(eventtypeParticipationAttributes != null){
			for(EventtypeparticipationHasAdditionalattribute etphaa : etp.getEventtypeparticipationHasAdditionalattributes()){
				
				additionalEventtypeParticipationAttributeDTOs.add(new AttributeDTO(etphaa));
			}
		}
		
		EventtypeParticipationDTO eventtypeparticipationDTO = new EventtypeParticipationDTO(etp);
		eventtypeparticipationDTO.setEventtypeParticipationAttributes(additionalEventtypeParticipationAttributeDTOs);
		
		return eventtypeparticipationDTO;
		
	}
	
	/**
	 * Creates an EventtypeStockflowDTO object for an Eventtypestockflow object
	 * @param etsf
	 * @return
	 */
	private EventtypeStockflowDTO createEventtypeStockflowDTO(Eventtypestockflow etsf){
		
		// Set of additional attributes
		Set<EventtypestockflowHasAdditionalattribute> eventtypeStockflowAttributes = etsf.getEventtypestockflowHasAdditionalattributes();
				
		// only a list of the attribute DTOs is needed
		Set<AttributeDTO> additionalEventtypeStockflowAttributeDTOs = 
				new LinkedHashSet<AttributeDTO>(eventtypeStockflowAttributes != null ? eventtypeStockflowAttributes.size() : 0);
		
		if(eventtypeStockflowAttributes != null){
			for(EventtypestockflowHasAdditionalattribute etsfhaa : etsf.getEventtypestockflowHasAdditionalattributes()){
						
				additionalEventtypeStockflowAttributeDTOs.add(new AttributeDTO(etsfhaa));
			}
		}
				
		EventtypeStockflowDTO eventtypestockflowDTO = new EventtypeStockflowDTO(etsf);
		eventtypestockflowDTO.setEventtypeStockflowAttributes(additionalEventtypeStockflowAttributeDTOs);
				
		return eventtypestockflowDTO;		
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
