package master.realist.REAlistGUIGenerator.server.daos;

import java.util.Set;

import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationDTO;
import master.realist.REAlistGUIGenerator.shared.dto.ParticipationHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowDTO;
import master.realist.REAlistGUIGenerator.shared.dto.StockflowHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;
import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.Participation;
import master.realist.REAlistGUIGenerator.shared.model.ParticipationHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.Resource;
import master.realist.REAlistGUIGenerator.shared.model.Stockflow;
import master.realist.REAlistGUIGenerator.shared.model.StockflowHasAdditionalattributevalue;

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
		Set<StockflowDTO> stockflows = eventDTO.getStockflows();
				
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
			
			// stockflows
			if(stockflows != null){
				
				for(StockflowDTO dto : stockflows){
					
					// retrieve already stored resource
					Resource affectedResource = (Resource) session.get(Resource.class, dto.getResource().getId());
					
					// create Stockflow and save it
					Stockflow stockflow = createStockflow(event, affectedResource, dto, session);
					session.save(stockflow);
					
				}
			}
			
			// participations
			if(participations != null){
				
				for(ParticipationDTO dto : participations){
					
					// retrieve already stored agent
					Agent participatingAgent = (Agent) session.get(Agent.class, dto.getAgent().getId());
					
					// create participation and save it
					Participation participation = createParticipation(event, participatingAgent, dto, session);
					session.save(participation);
					
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
	 * Method creating a Participation for a specific ParticipationDTO
	 * @param event event object the participation belongs to
	 * @param agent participating agent in participation
	 * @param participationDTO participationDTO that is converted
	 * @return participation
	 */
	private Participation createParticipation(Event event, Agent agent,  ParticipationDTO participationDTO, Session session){
		
		// create a Participation object and set its values
		Participation participation = new Participation();
		participation.setAgent(agent);
		participation.getEvents().add(event);
		
		// Additional attributes for participation
		if(participationDTO.getAdditionalAttributeValues() != null){
			for(ParticipationHasAdditionalattributevalueDTO phaavdto : participationDTO.getAdditionalAttributeValues()){
				
				// retrieve existing attribute
				Attribute addAttribute = (Attribute) session.get(Attribute.class, phaavdto.getAttribute().getId());

				// add the additional attribute value to the db
				participation.getParticipationHasAdditionalattributevalues().add(createAdditionalParticipationAttributevalue(participation, addAttribute, phaavdto));
			}
		}
		
		
		return participation;
	}
	
	
	/**
	 * Method creating a ParticipationHasAdditionalattributevalue for a specific ParticipationHasAdditionalattributevalueDTO, 
	 * participation, and attribute
	 * @param participation
	 * @param attribute
	 * @param dto
	 * @return
	 */
	private ParticipationHasAdditionalattributevalue createAdditionalParticipationAttributevalue(Participation participation, Attribute attribute, ParticipationHasAdditionalattributevalueDTO dto){
		
		// create an ParticipationHasAdditionalattributevalue object and set its values	
		ParticipationHasAdditionalattributevalue additionalParticipationAttributeValue = null;
				
		additionalParticipationAttributeValue = new ParticipationHasAdditionalattributevalue();
		additionalParticipationAttributeValue.setParticipation(participation);
		additionalParticipationAttributeValue.setAttribute(attribute);
		additionalParticipationAttributeValue.setBooleanValue(dto.getBooleanValue());
		additionalParticipationAttributeValue.setDatetimeValue(dto.getDatetimeValue());
		additionalParticipationAttributeValue.setNumericValue(dto.getNumericValue());
		additionalParticipationAttributeValue.setTextualValue(dto.getTextualValue());
				
		return additionalParticipationAttributeValue;
	}
	
	/**
	 * Method creating a Stockflow for a specific StockflowDTO
	 * @param event event object the stockflow belongs to
	 * @param resource affected resource during stockflow
	 * @param stockflowDTO stockflowDTO that is converted
	 * @return stockflow
	 */
	private Stockflow createStockflow(Event event, Resource resource, StockflowDTO stockflowDTO, Session session){
		
		// create Stockflow object and set its values
		Stockflow stockflow = new Stockflow();
		stockflow.setResource(resource);
		stockflow.setPricePerUnit(stockflowDTO.getPricePerUnit());
		stockflow.setQuantity(stockflowDTO.getQuantity());
		stockflow.getEvents().add(event);
		
		// Additional attributes for stockflow
		if(stockflowDTO.getAdditionalAttributeValues() != null){
			for(StockflowHasAdditionalattributevalueDTO sfhaavdto : stockflowDTO.getAdditionalAttributeValues()){
				
				// retrieve existing attribute
				Attribute addAttribute = (Attribute) session.get(Attribute.class, sfhaavdto.getAttribute().getId());

				// add the additional attribute value to the db
				stockflow.getStockflowHasAdditionalattributevalues().add(createAdditionalStockflowAttributevalue(stockflow, addAttribute, sfhaavdto));
			}
		}
		
		
		return stockflow;
	}
	
	/**
	 * Method creating a StockflowHasAdditionalattributevalue for a specific StckflowHasAdditionalattributevalueDTO, 
	 * stockflow, and attribute
	 * @param stockflow
	 * @param attribute
	 * @param dto
	 * @return
	 */
	private StockflowHasAdditionalattributevalue createAdditionalStockflowAttributevalue(Stockflow stockflow, Attribute attribute, StockflowHasAdditionalattributevalueDTO dto){
		
		// create an StockflowHasAdditionalattributevalue object and set its values	
		StockflowHasAdditionalattributevalue additionalStockflowAttributeValue = null;
						
		additionalStockflowAttributeValue = new StockflowHasAdditionalattributevalue();
		additionalStockflowAttributeValue.setStockflow(stockflow);
		additionalStockflowAttributeValue.setAttribute(attribute);
		additionalStockflowAttributeValue.setBooleanValue(dto.getBooleanValue());
		additionalStockflowAttributeValue.setDatetimeValue(dto.getDatetimeValue());
		additionalStockflowAttributeValue.setNumericValue(dto.getNumericValue());
		additionalStockflowAttributeValue.setTextualValue(dto.getTextualValue());
						
		return additionalStockflowAttributeValue;
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
