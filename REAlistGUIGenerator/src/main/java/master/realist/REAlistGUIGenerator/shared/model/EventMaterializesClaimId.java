package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventMaterializesClaimId generated by hbm2java
 */
@Embeddable
public class EventMaterializesClaimId implements java.io.Serializable {

	private int claimId;
	private int eventId;

	public EventMaterializesClaimId() {
	}

	public EventMaterializesClaimId(int claimId, int eventId) {
		this.claimId = claimId;
		this.eventId = eventId;
	}

	@Column(name = "Claim_Id", nullable = false)
	public int getClaimId() {
		return this.claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	@Column(name = "Event_Id", nullable = false)
	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventMaterializesClaimId))
			return false;
		EventMaterializesClaimId castOther = (EventMaterializesClaimId) other;

		return (this.getClaimId() == castOther.getClaimId())
				&& (this.getEventId() == castOther.getEventId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getClaimId();
		result = 37 * result + this.getEventId();
		return result;
	}

}