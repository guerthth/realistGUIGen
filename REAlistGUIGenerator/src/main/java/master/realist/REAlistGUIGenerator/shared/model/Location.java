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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "location", catalog = "rea")
public class Location implements java.io.Serializable {

	private int id;
	private Location location;
	private String name;
	private Set<ClaimHasLocation> claimHasLocations = new HashSet<ClaimHasLocation>(
			0);
	private Set<Pricelist> pricelists = new HashSet<Pricelist>(0);
	private Set<Locationofresource> locationofresources = new HashSet<Locationofresource>(
			0);
	private Set<CommitmentHasLocation> commitmentHasLocations = new HashSet<CommitmentHasLocation>(
			0);
	private Set<EventHasLocation> eventHasLocations = new HashSet<EventHasLocation>(
			0);
	private LocationAddress locationAddress;
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);
	private LocationCoordinates locationCoordinates;
	private Set<Locationofagent> locationofagents = new HashSet<Locationofagent>(
			0);
	private Set<Identifiablebulkresource> identifiablebulkresources = new HashSet<Identifiablebulkresource>(
			0);
	private Set<Location> locations = new HashSet<Location>(0);
	private Set<Locationtype> locationtypes = new HashSet<Locationtype>(0);

	public Location() {
	}

	public Location(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Location(int id, Location location, String name,
			Set<ClaimHasLocation> claimHasLocations, Set<Pricelist> pricelists,
			Set<Locationofresource> locationofresources,
			Set<CommitmentHasLocation> commitmentHasLocations,
			Set<EventHasLocation> eventHasLocations,
			LocationAddress locationAddress, Set<Stockflow> stockflows,
			LocationCoordinates locationCoordinates,
			Set<Locationofagent> locationofagents,
			Set<Identifiablebulkresource> identifiablebulkresources,
			Set<Location> locations, Set<Locationtype> locationtypes) {
		this.id = id;
		this.location = location;
		this.name = name;
		this.claimHasLocations = claimHasLocations;
		this.pricelists = pricelists;
		this.locationofresources = locationofresources;
		this.commitmentHasLocations = commitmentHasLocations;
		this.eventHasLocations = eventHasLocations;
		this.locationAddress = locationAddress;
		this.stockflows = stockflows;
		this.locationCoordinates = locationCoordinates;
		this.locationofagents = locationofagents;
		this.identifiablebulkresources = identifiablebulkresources;
		this.locations = locations;
		this.locationtypes = locationtypes;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentLocation_Id")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "Name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<ClaimHasLocation> getClaimHasLocations() {
		return this.claimHasLocations;
	}

	public void setClaimHasLocations(Set<ClaimHasLocation> claimHasLocations) {
		this.claimHasLocations = claimHasLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<Pricelist> getPricelists() {
		return this.pricelists;
	}

	public void setPricelists(Set<Pricelist> pricelists) {
		this.pricelists = pricelists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<Locationofresource> getLocationofresources() {
		return this.locationofresources;
	}

	public void setLocationofresources(
			Set<Locationofresource> locationofresources) {
		this.locationofresources = locationofresources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<CommitmentHasLocation> getCommitmentHasLocations() {
		return this.commitmentHasLocations;
	}

	public void setCommitmentHasLocations(
			Set<CommitmentHasLocation> commitmentHasLocations) {
		this.commitmentHasLocations = commitmentHasLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<EventHasLocation> getEventHasLocations() {
		return this.eventHasLocations;
	}

	public void setEventHasLocations(Set<EventHasLocation> eventHasLocations) {
		this.eventHasLocations = eventHasLocations;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "location")
	public LocationAddress getLocationAddress() {
		return this.locationAddress;
	}

	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "locationofstockflowresource", catalog = "rea", joinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) })
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "location")
	public LocationCoordinates getLocationCoordinates() {
		return this.locationCoordinates;
	}

	public void setLocationCoordinates(LocationCoordinates locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<Locationofagent> getLocationofagents() {
		return this.locationofagents;
	}

	public void setLocationofagents(Set<Locationofagent> locationofagents) {
		this.locationofagents = locationofagents;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "locationofidentifiablebulkresource", catalog = "rea", joinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IdentifiableResource_Id", nullable = false, updatable = false) })
	public Set<Identifiablebulkresource> getIdentifiablebulkresources() {
		return this.identifiablebulkresources;
	}

	public void setIdentifiablebulkresources(
			Set<Identifiablebulkresource> identifiablebulkresources) {
		this.identifiablebulkresources = identifiablebulkresources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "location_has_locationtype", catalog = "rea", joinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "LocationType_Id", nullable = false, updatable = false) })
	public Set<Locationtype> getLocationtypes() {
		return this.locationtypes;
	}

	public void setLocationtypes(Set<Locationtype> locationtypes) {
		this.locationtypes = locationtypes;
	}

}