package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Locationofagent generated by hbm2java
 */
@Entity
@Table(name = "locationofagent", catalog = "rea")
public class Locationofagent implements java.io.Serializable {

	private LocationofagentId id;
	private Location location;
	private Locationtype locationtype;
	private Agent agent;
	private Boolean isDefault;

	public Locationofagent() {
	}

	public Locationofagent(LocationofagentId id, Location location,
			Locationtype locationtype, Agent agent) {
		this.id = id;
		this.location = location;
		this.locationtype = locationtype;
		this.agent = agent;
	}

	public Locationofagent(LocationofagentId id, Location location,
			Locationtype locationtype, Agent agent, Boolean isDefault) {
		this.id = id;
		this.location = location;
		this.locationtype = locationtype;
		this.agent = agent;
		this.isDefault = isDefault;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "locationId", column = @Column(name = "Location_Id", nullable = false)),
			@AttributeOverride(name = "agentId", column = @Column(name = "Agent_Id", nullable = false)),
			@AttributeOverride(name = "locationTypeId", column = @Column(name = "LocationType_Id", nullable = false)) })
	public LocationofagentId getId() {
		return this.id;
	}

	public void setId(LocationofagentId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Location_Id", nullable = false, insertable = false, updatable = false)
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationType_Id", nullable = false, insertable = false, updatable = false)
	public Locationtype getLocationtype() {
		return this.locationtype;
	}

	public void setLocationtype(Locationtype locationtype) {
		this.locationtype = locationtype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Agent_Id", nullable = false, insertable = false, updatable = false)
	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Column(name = "IsDefault")
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
