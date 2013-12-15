package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Stockflow generated by hbm2java
 */
@Entity
@Table(name = "stockflow", catalog = "rea")
public class Stockflow implements java.io.Serializable {

	private Integer id;
	private Currency currency;
	private Resource resource;
	private Tax tax;
	private double quantity;
	private Double pricePerUnit;
	private Double totalValue;
	private Double totalValueNetto;
	private Set<Claim> claims = new HashSet<Claim>(0);
	private Set<Commitment> commitments = new HashSet<Commitment>(0);
	private Set<Discountoraddition> discountoradditions = new HashSet<Discountoraddition>(
			0);
	private Set<Location> locations = new HashSet<Location>(0);
	private Set<Identifiablebulkresource> identifiablebulkresources = new HashSet<Identifiablebulkresource>(
			0);
	private Set<Event> events = new HashSet<Event>(0);
	private Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues = new HashSet<StockflowHasAdditionalattributevalue>(
			0);

	public Stockflow() {
	}

	public Stockflow(Resource resource, double quantity) {
		this.resource = resource;
		this.quantity = quantity;
	}

	public Stockflow(
			Currency currency,
			Resource resource,
			Tax tax,
			double quantity,
			Double pricePerUnit,
			Double totalValue,
			Double totalValueNetto,
			Set<Claim> claims,
			Set<Commitment> commitments,
			Set<Discountoraddition> discountoradditions,
			Set<Location> locations,
			Set<Identifiablebulkresource> identifiablebulkresources,
			Set<Event> events,
			Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues) {
		this.currency = currency;
		this.resource = resource;
		this.tax = tax;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.totalValue = totalValue;
		this.totalValueNetto = totalValueNetto;
		this.claims = claims;
		this.commitments = commitments;
		this.discountoradditions = discountoradditions;
		this.locations = locations;
		this.identifiablebulkresources = identifiablebulkresources;
		this.events = events;
		this.stockflowHasAdditionalattributevalues = stockflowHasAdditionalattributevalues;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Currency_Id")
	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Resource_Id", nullable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Tax_Id")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	@Column(name = "Quantity", nullable = false, precision = 22, scale = 0)
	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PricePerUnit", precision = 22, scale = 0)
	public Double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Column(name = "TotalValue", precision = 22, scale = 0)
	public Double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	@Column(name = "TotalValueNetto", precision = 22, scale = 0)
	public Double getTotalValueNetto() {
		return this.totalValueNetto;
	}

	public void setTotalValueNetto(Double totalValueNetto) {
		this.totalValueNetto = totalValueNetto;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "claim_has_stockflow", catalog = "rea", joinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Claim_Id", nullable = false, updatable = false) })
	public Set<Claim> getClaims() {
		return this.claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "commitment_has_stockflow", catalog = "rea", joinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Commitment_Id", nullable = false, updatable = false) })
	public Set<Commitment> getCommitments() {
		return this.commitments;
	}

	public void setCommitments(Set<Commitment> commitments) {
		this.commitments = commitments;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "position_has_discountoraddition", catalog = "rea", joinColumns = { @JoinColumn(name = "Position_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "DiscountOrAddition_Id", nullable = false, updatable = false) })
	public Set<Discountoraddition> getDiscountoradditions() {
		return this.discountoradditions;
	}

	public void setDiscountoradditions(
			Set<Discountoraddition> discountoradditions) {
		this.discountoradditions = discountoradditions;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "locationofstockflowresource", catalog = "rea", joinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Location_Id", nullable = false, updatable = false) })
	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "stockflow_has_identifiablebulkresource", catalog = "rea", joinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IdentifiableBulkResource_Id", nullable = false, updatable = false) })
	public Set<Identifiablebulkresource> getIdentifiablebulkresources() {
		return this.identifiablebulkresources;
	}

	public void setIdentifiablebulkresources(
			Set<Identifiablebulkresource> identifiablebulkresources) {
		this.identifiablebulkresources = identifiablebulkresources;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_has_stockflow", catalog = "rea", joinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Event_Id", nullable = false, updatable = false) })
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockflow")
	public Set<StockflowHasAdditionalattributevalue> getStockflowHasAdditionalattributevalues() {
		return this.stockflowHasAdditionalattributevalues;
	}

	public void setStockflowHasAdditionalattributevalues(
			Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues) {
		this.stockflowHasAdditionalattributevalues = stockflowHasAdditionalattributevalues;
	}

}
