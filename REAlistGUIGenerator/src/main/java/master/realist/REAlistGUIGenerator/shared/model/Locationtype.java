package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Locationtype generated by hbm2java
 */
@Entity
@Table(name = "locationtype", catalog = "rea")
public class Locationtype implements java.io.Serializable {

	private int id;
	private String name;
	private Set<Locationofagent> locationofagents = new HashSet<Locationofagent>(
			0);
	private Set<EventHasLocation> eventHasLocations = new HashSet<EventHasLocation>(
			0);
	private Set<Location> locations = new HashSet<Location>(0);
	private Set<CommitmentHasLocation> commitmentHasLocations = new HashSet<CommitmentHasLocation>(
			0);
	private Set<ClaimHasLocation> claimHasLocations = new HashSet<ClaimHasLocation>(
			0);

	public Locationtype() {
	}

	public Locationtype(int id) {
		this.id = id;
	}

	public Locationtype(int id, String name,
			Set<Locationofagent> locationofagents,
			Set<EventHasLocation> eventHasLocations, Set<Location> locations,
			Set<CommitmentHasLocation> commitmentHasLocations,
			Set<ClaimHasLocation> claimHasLocations) {
		this.id = id;
		this.name = name;
		this.locationofagents = locationofagents;
		this.eventHasLocations = eventHasLocations;
		this.locations = locations;
		this.commitmentHasLocations = commitmentHasLocations;
		this.claimHasLocations = claimHasLocations;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locationtype")
	public Set<Locationofagent> getLocationofagents() {
		return this.locationofagents;
	}

	public void setLocationofagents(Set<Locationofagent> locationofagents) {
		this.locationofagents = locationofagents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locationtype")
	public Set<EventHasLocation> getEventHasLocations() {
		return this.eventHasLocations;
	}

	public void setEventHasLocations(Set<EventHasLocation> eventHasLocations) {
		this.eventHasLocations = eventHasLocations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "location_has_locationtype", catalog = "rea", joinColumns = { @JoinColumn(name = "LocationType_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) })
	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locationtype")
	public Set<CommitmentHasLocation> getCommitmentHasLocations() {
		return this.commitmentHasLocations;
	}

	public void setCommitmentHasLocations(
			Set<CommitmentHasLocation> commitmentHasLocations) {
		this.commitmentHasLocations = commitmentHasLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locationtype")
	public Set<ClaimHasLocation> getClaimHasLocations() {
		return this.claimHasLocations;
	}

	public void setClaimHasLocations(Set<ClaimHasLocation> claimHasLocations) {
		this.claimHasLocations = claimHasLocations;
	}

}
