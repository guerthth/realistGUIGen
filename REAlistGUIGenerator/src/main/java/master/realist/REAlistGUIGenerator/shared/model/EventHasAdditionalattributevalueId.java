package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueIdDTO;

/**
 * EventHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class EventHasAdditionalattributevalueId implements java.io.Serializable {

	private int eventId;
	private String attributeId;
	
	/**
	 * Added constructor to convert a EventHasAdditionalattributevalueIdDTO object to 
	 * a EventHasAdditionalattributevalueId object
	 * Needed to save in the DB
	 * @param dto
	 */
	public EventHasAdditionalattributevalueId(EventHasAdditionalattributevalueIdDTO dto){
		
		this.eventId = dto.getEventId();
		this.attributeId = dto.getAttributeId();
	}
	
	public EventHasAdditionalattributevalueId() {
	}

	public EventHasAdditionalattributevalueId(int eventId, String attributeId) {
		this.eventId = eventId;
		this.attributeId = attributeId;
	}

	@Column(name = "Event_Id", nullable = false)
	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	@Column(name = "Attribute_Id", nullable = false, length = 150)
	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventHasAdditionalattributevalueId))
			return false;
		EventHasAdditionalattributevalueId castOther = (EventHasAdditionalattributevalueId) other;

		return (this.getEventId() == castOther.getEventId())
				&& ((this.getAttributeId() == castOther.getAttributeId()) || (this
						.getAttributeId() != null
						&& castOther.getAttributeId() != null && this
						.getAttributeId().equals(castOther.getAttributeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEventId();
		result = 37
				* result
				+ (getAttributeId() == null ? 0 : this.getAttributeId()
						.hashCode());
		return result;
	}

}
