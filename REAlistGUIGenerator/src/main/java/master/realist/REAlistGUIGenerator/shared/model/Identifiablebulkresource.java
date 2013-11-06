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
import javax.persistence.Table;

/**
 * Identifiablebulkresource generated by hbm2java
 */
@Entity
@Table(name = "identifiablebulkresource", catalog = "rea")
public class Identifiablebulkresource implements java.io.Serializable {

	private int id;
	private Resource resource;
	private String serialNumber;
	private Boolean isOnStock;
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);
	private Set<Attribute> attributes = new HashSet<Attribute>(0);
	private Set<Location> locations = new HashSet<Location>(0);

	public Identifiablebulkresource() {
	}

	public Identifiablebulkresource(int id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}

	public Identifiablebulkresource(int id, Resource resource,
			String serialNumber, Boolean isOnStock, Set<Stockflow> stockflows,
			Set<Attribute> attributes, Set<Location> locations) {
		this.id = id;
		this.resource = resource;
		this.serialNumber = serialNumber;
		this.isOnStock = isOnStock;
		this.stockflows = stockflows;
		this.attributes = attributes;
		this.locations = locations;
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
	@JoinColumn(name = "Resource_Id", nullable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Column(name = "SerialNumber", length = 45)
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "IsOnStock")
	public Boolean getIsOnStock() {
		return this.isOnStock;
	}

	public void setIsOnStock(Boolean isOnStock) {
		this.isOnStock = isOnStock;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "stockflow_has_identifiablebulkresource", catalog = "rea", joinColumns = { @JoinColumn(name = "IdentifiableBulkResource_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) })
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "identifiablebulkresource_has_additionalattributevalue", catalog = "rea", joinColumns = { @JoinColumn(name = "IdentifiableBulkResource_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Attribute_Id", nullable = false, updatable = false) })
	public Set<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "locationofidentifiablebulkresource", catalog = "rea", joinColumns = { @JoinColumn(name = "IdentifiableResource_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) })
	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

}