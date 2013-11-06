package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CommitmentHasLocationId generated by hbm2java
 */
@Embeddable
public class CommitmentHasLocationId implements java.io.Serializable {

	private int commitmentId;
	private int locationId;
	private int locationTypeId;

	public CommitmentHasLocationId() {
	}

	public CommitmentHasLocationId(int commitmentId, int locationId,
			int locationTypeId) {
		this.commitmentId = commitmentId;
		this.locationId = locationId;
		this.locationTypeId = locationTypeId;
	}

	@Column(name = "Commitment_Id", nullable = false)
	public int getCommitmentId() {
		return this.commitmentId;
	}

	public void setCommitmentId(int commitmentId) {
		this.commitmentId = commitmentId;
	}

	@Column(name = "Location_Id", nullable = false)
	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Column(name = "LocationType_Id", nullable = false)
	public int getLocationTypeId() {
		return this.locationTypeId;
	}

	public void setLocationTypeId(int locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CommitmentHasLocationId))
			return false;
		CommitmentHasLocationId castOther = (CommitmentHasLocationId) other;

		return (this.getCommitmentId() == castOther.getCommitmentId())
				&& (this.getLocationId() == castOther.getLocationId())
				&& (this.getLocationTypeId() == castOther.getLocationTypeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCommitmentId();
		result = 37 * result + this.getLocationId();
		result = 37 * result + this.getLocationTypeId();
		return result;
	}

}
