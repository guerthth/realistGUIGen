package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FulfillsId generated by hbm2java
 */
@Embeddable
public class FulfillsId implements java.io.Serializable {

	private int commitmentId;
	private int eventId;

	public FulfillsId() {
	}

	public FulfillsId(int commitmentId, int eventId) {
		this.commitmentId = commitmentId;
		this.eventId = eventId;
	}

	@Column(name = "Commitment_Id", nullable = false)
	public int getCommitmentId() {
		return this.commitmentId;
	}

	public void setCommitmentId(int commitmentId) {
		this.commitmentId = commitmentId;
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
		if (!(other instanceof FulfillsId))
			return false;
		FulfillsId castOther = (FulfillsId) other;

		return (this.getCommitmentId() == castOther.getCommitmentId())
				&& (this.getEventId() == castOther.getEventId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCommitmentId();
		result = 37 * result + this.getEventId();
		return result;
	}

}
