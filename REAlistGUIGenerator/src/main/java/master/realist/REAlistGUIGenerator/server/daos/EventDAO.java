package master.realist.REAlistGUIGenerator.server.daos;

import java.util.Set;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;
import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalue;

public class EventDAO {
	
	private HibernateUtil hibernateUtil;
	
	public int saveEvent(EventDTO eventDTO){
		
		Event event = new Event(eventDTO);
		
		Session session = null;
		int dualityId = 0;
		
		Set<EventHasAdditionalattributevalueDTO> additionalAttributeValues = eventDTO.getAdditionalAttributeValues();
				
		Attribute additionalAttribute;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			// if event has additional attributes
			if(additionalAttributeValues != null){
				
				for(EventHasAdditionalattributevalueDTO dto : additionalAttributeValues){
					
					// Retrieve the already stored attribute object
					additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());
					
					// add the additional attribute values to the event objects list
					event.getEventHasAdditionalattributevalues().add(createAdditionalAttributeValue(event,additionalAttribute,dto));
				
				}
			}
			
			session.save(event);
			session.getTransaction().commit();
			dualityId = event.getId();
			
		}catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}	
		}
			
		return dualityId;		
	}
	
	
	/**
	 * Method creating an EventHasAdditionalattributevalue for a specific EventHasAdditionalattributevalueDTO, 
	 * event, and attribute
	 * @param agent Event object
	 * @param additionalAttribute Attribute object
	 * @param dto EventHasAdditionalattributevalueDTO containing textual, numeric, date, or boolean values
	 * @return additionalEventAttributeValue 
	 */
	private EventHasAdditionalattributevalue createAdditionalAttributeValue(Event event, Attribute additionalAttribute, EventHasAdditionalattributevalueDTO dto){

		// create an EventHasAdditionalattributevalue object and set its values	
		EventHasAdditionalattributevalue additionalEventAttributeValue = null;
		
		additionalEventAttributeValue = new EventHasAdditionalattributevalue();
		additionalEventAttributeValue.setEvent(event);
		additionalEventAttributeValue.setAttribute(additionalAttribute);
		additionalEventAttributeValue.setBooleanValue(dto.getBooleanValue());
		additionalEventAttributeValue.setDatetimeValue(dto.getDatetimeValue());
		additionalEventAttributeValue.setNumericValue(dto.getNumericValue());
		additionalEventAttributeValue.setTextualValue(dto.getTextualValue());
		
		return additionalEventAttributeValue;
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
