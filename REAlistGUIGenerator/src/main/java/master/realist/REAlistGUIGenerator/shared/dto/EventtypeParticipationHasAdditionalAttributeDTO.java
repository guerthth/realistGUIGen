package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

import master.realist.REAlistGUIGenerator.shared.model.EventtypeparticipationHasAdditionalattribute;

public class EventtypeParticipationHasAdditionalAttributeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4135121968548870629L;
	private AttributeDTO attribute;
	private String agenttypeId;
	private String eventtypeId;
	private boolean isOptional;
	private boolean isPolicyProperty;
	private boolean isReserveProperty;
	
	/**
	 * Default Constructor
	 */
	public EventtypeParticipationHasAdditionalAttributeDTO() {
		
	}
	
	public EventtypeParticipationHasAdditionalAttributeDTO(EventtypeparticipationHasAdditionalattribute eventtypeparticipationhasadditionalattribute){
		
		this.attribute = new AttributeDTO(eventtypeparticipationhasadditionalattribute.getAttribute());
		this.agenttypeId = eventtypeparticipationhasadditionalattribute.getId().getEventTypeParticipationAgentTypeId();
		this.eventtypeId = eventtypeparticipationhasadditionalattribute.getId().getEventTypeParticipationEventTypeId();
		this.isOptional = eventtypeparticipationhasadditionalattribute.isIsOptional();
		this.isPolicyProperty = eventtypeparticipationhasadditionalattribute.isIsPolicyProperty();
		this.isReserveProperty = eventtypeparticipationhasadditionalattribute.isIsReserveProperty();
	}

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public String getAgenttypeId() {
		return agenttypeId;
	}

	public void setAgenttypeId(String agenttypeId) {
		this.agenttypeId = agenttypeId;
	}

	public String getEventtypeId() {
		return eventtypeId;
	}

	public void setEventtypeId(String eventtypeId) {
		this.eventtypeId = eventtypeId;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public boolean isPolicyProperty() {
		return isPolicyProperty;
	}

	public void setPolicyProperty(boolean isPolicyProperty) {
		this.isPolicyProperty = isPolicyProperty;
	}

	public boolean isReserveProperty() {
		return isReserveProperty;
	}

	public void setReserveProperty(boolean isReserveProperty) {
		this.isReserveProperty = isReserveProperty;
	}
	
	
}
