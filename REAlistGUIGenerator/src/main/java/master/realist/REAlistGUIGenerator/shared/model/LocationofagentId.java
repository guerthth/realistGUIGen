package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LocationofagentId generated by hbm2java
 */
@Embeddable
public class LocationofagentId implements java.io.Serializable {

	private int locationId;
	private int agentId;
	private int locationTypeId;

	public LocationofagentId() {
	}

	public LocationofagentId(int locationId, int agentId, int locationTypeId) {
		this.locationId = locationId;
		this.agentId = agentId;
		this.locationTypeId = locationTypeId;
	}

	@Column(name = "Location_Id", nullable = false)
	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Column(name = "Agent_Id", nullable = false)
	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
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
		if (!(other instanceof LocationofagentId))
			return false;
		LocationofagentId castOther = (LocationofagentId) other;

		return (this.getLocationId() == castOther.getLocationId())
				&& (this.getAgentId() == castOther.getAgentId())
				&& (this.getLocationTypeId() == castOther.getLocationTypeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getLocationId();
		result = 37 * result + this.getAgentId();
		result = 37 * result + this.getLocationTypeId();
		return result;
	}

}