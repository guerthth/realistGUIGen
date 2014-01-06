package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.Participation;

public class ParticipationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8709732209070847836L;
	
	private int id;
	private AgentDTO agent;
	private Set<EventDTO> events;
	private Set<ParticipationHasAdditionalattributevalueDTO> additionalAttributeValues;
	
	/**
	 * Default Constructor
	 */
	public ParticipationDTO(){
		
	}
	
	/**
	 * Constructor transforming a participaton object to a participationDTO object
	 * @param participation
	 */
	public ParticipationDTO(Participation participation){
		
		this.id = participation.getParticipationId();
		this.agent = new AgentDTO(participation.getAgent());
		
		// add eventDTOs to participationDTO
		if(participation.getEvents() != null){
			this.events = new LinkedHashSet<EventDTO>(participation.getEvents().size());
			for(Event event : participation.getEvents()){
				this.events.add(new EventDTO(event));
			}
		}
		
		//TODO: this.additionalAttributeValues = new LinkedHashSet<ParticipationHasAdditionattributevalueDTO>();
	}
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AgentDTO getAgent() {
		return agent;
	}

	public void setAgent(AgentDTO agent) {
		this.agent = agent;
	}

	public Set<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(Set<EventDTO> events) {
		this.events = events;
	}

	public Set<ParticipationHasAdditionalattributevalueDTO> getAdditionalAttributeValues() {
		return additionalAttributeValues;
	}

	public void setAdditionalAttributeValues(
			Set<ParticipationHasAdditionalattributevalueDTO> additionalAttributeValues) {
		this.additionalAttributeValues = additionalAttributeValues;
	}
	
	
	
	
}
