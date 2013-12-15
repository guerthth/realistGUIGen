package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventtypeHasAdditionalattributeId generated by hbm2java
 */
@Embeddable
public class EventtypeHasAdditionalattributeId implements java.io.Serializable {

	private String attributeId;
	private String eventTypeId;

	public EventtypeHasAdditionalattributeId() {
	}

	public EventtypeHasAdditionalattributeId(String attributeId,
			String eventTypeId) {
		this.attributeId = attributeId;
		this.eventTypeId = eventTypeId;
	}

	@Column(name = "Attribute_Id", nullable = false, length = 150)
	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "EventType_Id", nullable = false, length = 150)
	public String getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventtypeHasAdditionalattributeId))
			return false;
		EventtypeHasAdditionalattributeId castOther = (EventtypeHasAdditionalattributeId) other;

		return ((this.getAttributeId() == castOther.getAttributeId()) || (this
				.getAttributeId() != null && castOther.getAttributeId() != null && this
				.getAttributeId().equals(castOther.getAttributeId())))
				&& ((this.getEventTypeId() == castOther.getEventTypeId()) || (this
						.getEventTypeId() != null
						&& castOther.getEventTypeId() != null && this
						.getEventTypeId().equals(castOther.getEventTypeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAttributeId() == null ? 0 : this.getAttributeId()
						.hashCode());
		result = 37
				* result
				+ (getEventTypeId() == null ? 0 : this.getEventTypeId()
						.hashCode());
		return result;
	}

}
