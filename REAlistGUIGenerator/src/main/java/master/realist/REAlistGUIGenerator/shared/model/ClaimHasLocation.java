package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

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
 * ClaimHasLocation generated by hbm2java
 */
@Entity
@Table(name = "claim_has_location", catalog = "rea")
public class ClaimHasLocation implements java.io.Serializable {

	private ClaimHasLocationId id;
	private Location location;
	private Locationtype locationtype;
	private Claim claim;

	public ClaimHasLocation() {
	}

	public ClaimHasLocation(ClaimHasLocationId id, Location location,
			Locationtype locationtype, Claim claim) {
		this.id = id;
		this.location = location;
		this.locationtype = locationtype;
		this.claim = claim;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "claimId", column = @Column(name = "Claim_Id", nullable = false)),
			@AttributeOverride(name = "locationId", column = @Column(name = "Location_Id", nullable = false)),
			@AttributeOverride(name = "locationTypeId", column = @Column(name = "LocationType_Id", nullable = false)) })
	public ClaimHasLocationId getId() {
		return this.id;
	}

	public void setId(ClaimHasLocationId id) {
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
	@JoinColumn(name = "Claim_Id", nullable = false, insertable = false, updatable = false)
	public Claim getClaim() {
		return this.claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

}
