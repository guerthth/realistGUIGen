package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Eventtype;

/**
 * Eventtype Data Transfer Object
 * @author Thomas
 *
 */
public class EventtypeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623109853699657704L;
	
	
	private String id;
	// TODO implement eventtype hierarchies
	//private EventtypeDTO eventtype;
	private String name;
	private boolean isIncrement;
	private boolean isResourceUsed;
	private boolean isExceptionEvent;
	private boolean isSeries;
	private Set<AttributeDTO> attributes;
	private Set<EventtypeParticipationDTO> participations;
	
	/**
	 * Default Constructor
	 */
	public EventtypeDTO(){
		
	}
	
	public Set<EventtypeParticipationDTO> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<EventtypeParticipationDTO> participations) {
		this.participations = participations;
	}

	/**
	 * Constructor transforming an Eventtype object to an EventtypeDTO
	 * @param eventtype
	 */
	public EventtypeDTO(Eventtype eventtype){
		this.id = eventtype.getId();
		this.name = eventtype.getName();
		this.isIncrement = eventtype.getIsIncrement();
		this.isResourceUsed = eventtype.getIsResourceUsed();
		this.isExceptionEvent = eventtype.getIsExceptionEvent();
		this.isSeries = eventtype.getIsSeries();
	}
	
	public EventtypeDTO(String id, String name, boolean isIncrement, boolean isResourceUsed, boolean isExceptionEvent, boolean isSeries, Set<AttributeDTO> attributes){
		this.id = id;
		this.name = name; 
		this.isIncrement = isIncrement;
		this.isResourceUsed = isResourceUsed;
		this.isExceptionEvent = isExceptionEvent;
		this.isSeries = isSeries;
		this.attributes = attributes;
		//this.eventtype = eventtype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsIncrement() {
		return isIncrement;
	}

	public void setIsIncrement(Boolean isIncrement) {
		this.isIncrement = isIncrement;
	}

	public boolean isResourceUsed() {
		return isResourceUsed;
	}

	public void setResourceUsed(boolean isResourceUsed) {
		this.isResourceUsed = isResourceUsed;
	}

	public boolean isExceptionEvent() {
		return isExceptionEvent;
	}

	public void setExceptionEvent(boolean isExceptionEvent) {
		this.isExceptionEvent = isExceptionEvent;
	}

	public boolean isSeries() {
		return isSeries;
	}

	public void setSeries(boolean isSeries) {
		this.isSeries = isSeries;
	}

	public Set<AttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<AttributeDTO> attributes) {
		this.attributes = attributes;
	}

	
}
