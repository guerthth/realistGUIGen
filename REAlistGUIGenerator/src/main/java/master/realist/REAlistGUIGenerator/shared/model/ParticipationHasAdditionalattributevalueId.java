package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 19, 2013 8:35:27 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ParticipationHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class ParticipationHasAdditionalattributevalueId implements
		java.io.Serializable {

	private int participationParticipationId;
	private String attributeId;

	public ParticipationHasAdditionalattributevalueId() {
	}

	public ParticipationHasAdditionalattributevalueId(
			int participationParticipationId, String attributeId) {
		this.participationParticipationId = participationParticipationId;
		this.attributeId = attributeId;
	}

	@Column(name = "Participation_Participation_id", nullable = false)
	public int getParticipationParticipationId() {
		return this.participationParticipationId;
	}

	public void setParticipationParticipationId(int participationParticipationId) {
		this.participationParticipationId = participationParticipationId;
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
		if (!(other instanceof ParticipationHasAdditionalattributevalueId))
			return false;
		ParticipationHasAdditionalattributevalueId castOther = (ParticipationHasAdditionalattributevalueId) other;

		return (this.getParticipationParticipationId() == castOther
				.getParticipationParticipationId())
				&& ((this.getAttributeId() == castOther.getAttributeId()) || (this
						.getAttributeId() != null
						&& castOther.getAttributeId() != null && this
						.getAttributeId().equals(castOther.getAttributeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getParticipationParticipationId();
		result = 37
				* result
				+ (getAttributeId() == null ? 0 : this.getAttributeId()
						.hashCode());
		return result;
	}

}