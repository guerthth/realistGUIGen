package master.realist.REAlistGUIGenerator.server.daos;

import java.util.Set;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;
import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.Participation;

public class EventDAO {
	
	private HibernateUtil hibernateUtil;
	
	/**
	 * Method saving events in the REA DB
	 * @param eventDTO
	 * @return
	 */
	public int saveEvent(EventDTO eventDTO){
		
		Event event = new Event(eventDTO);
		
		Session session = null;
		int dualityId = 0;
		
		Set<EventHasAdditionalattributevalueDTO> additionalAttributeValues = eventDTO.getAdditionalAttributeValues();
		Set<ParticipationDTO> participations = eventDTO.getParticipations();
				
		Agent provideAgent;
		Agent receiveAgent;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			// if event has additional attributes
			if(additionalAttributeValues != null){
				
				for(EventHasAdditionalattributevalueDTO dto : additionalAttributeValues){
					
					// Retrieve the already stored attribute object
					Attribute additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());
					
					// add the additional attribute values to the event objects list
					event.getEventHasAdditionalattributevalues().add(createAdditionalAttributeValue(event,additionalAttribute,dto));
				
				}
			}
			
			//provide agent
			if(eventDTO.getProvideAgent() != null){
				
				// retrieve existing provide agent
				provideAgent = (Agent) session.get(Agent.class, eventDTO.getProvideAgent().getId());
				event.setAgentByProvideAgentId(provideAgent);
			}
		
			// receive agent
			if(eventDTO.getReceiveAgent() != null){
				
				// retrieve existing receive agent
				receiveAgent = (Agent) session.get(Agent.class, eventDTO.getReceiveAgent().getId());
				event.setAgentByReceiveAgentId(receiveAgent);
			}
			
			// participations
			if(participations != null){
				
				for(ParticipationDTO dto : participations){
					
					// retrieve already stored agent
					Agent participatingAgent = (Agent) session.get(Agent.class, dto.getAgent().getId());
					
					// create participation and save it
					Participation participation = createParticipation(event, participatingAgent, dto);
					session.save(participation);
					
					// add participations to the events participation list
					//event.getParticipations().add(participation);
					
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
	 * Method creating a Participation for a soecific ParticipationDTO
	 * @param event event object the participation belongs to
	 * @param agent participating agent in participation
	 * @param participationDTO participationDTO that is converted
	 * @return participation
	 */
	private Participation createParticipation(Event event, Agent agent,  ParticipationDTO participationDTO){
		
		// create a Participation object and set its values
		Participation participation = new Participation();
		participation.setAgent(agent);
		participation.getEvents().add(event);
		
		// TODO : Additional attributes for participation
		
		return participation;
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
