package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

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
 * Locationofresource generated by hbm2java
 */
@Entity
@Table(name = "locationofresource", catalog = "rea")
public class Locationofresource implements java.io.Serializable {

	private LocationofresourceId id;
	private Resource resource;
	private Location location;
	private Double amount;

	public Locationofresource() {
	}

	public Locationofresource(LocationofresourceId id, Resource resource,
			Location location) {
		this.id = id;
		this.resource = resource;
		this.location = location;
	}

	public Locationofresource(LocationofresourceId id, Resource resource,
			Location location, Double amount) {
		this.id = id;
		this.resource = resource;
		this.location = location;
		this.amount = amount;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "resourceTypeId", column = @Column(name = "ResourceType_Id", nullable = false)),
			@AttributeOverride(name = "locationId", column = @Column(name = "Location_Id", nullable = false)) })
	public LocationofresourceId getId() {
		return this.id;
	}

	public void setId(LocationofresourceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ResourceType_Id", nullable = false, insertable = false, updatable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Location_Id", nullable = false, insertable = false, updatable = false)
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "Amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
