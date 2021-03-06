package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 2:38:04 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import master.realist.REAlistGUIGenerator.shared.dto.ResourceDTO;

/**
 * Resource generated by hbm2java
 */
@Entity
@Table(name = "resource", catalog = "rea")
public class Resource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5414429287934833444L;
	private Integer id;
	private Resourcetype resourcetype;
	private String name;
	private Boolean isBulk;
	private Boolean isIdentifiable;
	private Double qoH;
	private Boolean isComposed;
	private Integer taxClass;
	private Set<FactorResource> factorResources = new HashSet<FactorResource>(0);
	private Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues = new LinkedHashSet<ResourceHasAdditionalattributevalue>(
			0);
	private Tax tax;
	private Servicetype servicetype;
	private Set<Identifiableresource> identifiableresources = new HashSet<Identifiableresource>(
			0);
	private Set<ResourceComposedOf> resourceComposedOfsForResourceId = new HashSet<ResourceComposedOf>(
			0);
	private Set<ResourceComposedOf> resourceComposedOfsForResourcePartId = new HashSet<ResourceComposedOf>(
			0);
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);
	private Set<Locationofresource> locationofresources = new HashSet<Locationofresource>(
			0);
	private Labor labor;
	private Producttype producttype;
	private Cashtype cashtype;
	private Set<Pricepolicy> pricepolicies = new HashSet<Pricepolicy>(0);
	private Set<Agent> agents = new HashSet<Agent>(0);
	private Discountoraddition discountoraddition;

	/**
	 * Added constructor to convert a resourcedto object to a resource object
	 * Needed to save in the DB
	 * @param resourceDTO
	 */
	public Resource (ResourceDTO resourceDTO){
		
		// simple attributes
		this.id = resourceDTO.getId();
		this.name = resourceDTO.getName();
		this.isBulk = resourceDTO.isBulk();
		this.isIdentifiable = resourceDTO.isIdentifiable();
		this.qoH = resourceDTO.getQoH();
		this.isComposed = resourceDTO.getIsComposed();

		// parent resource type
		if(resourceDTO.getResourcetype() != null){
			this.resourcetype = new Resourcetype(resourceDTO.getResourcetype());
		}
		
	}
	
	public Resource() {
	}

	public Resource(Resourcetype resourcetype) {
		this.resourcetype = resourcetype;
	}

	public Resource(
			Resourcetype resourcetype,
			String name,
			Boolean isBulk,
			Boolean isIdentifiable,
			Double qoH,
			Boolean isComposed,
			Integer taxClass,
			Set<FactorResource> factorResources,
			Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues,
			Tax tax, Servicetype servicetype,
			Set<Identifiableresource> identifiableresources,
			Set<ResourceComposedOf> resourceComposedOfsForResourceId,
			Set<ResourceComposedOf> resourceComposedOfsForResourcePartId,
			Set<Stockflow> stockflows,
			Set<Locationofresource> locationofresources, Labor labor,
			Producttype producttype, Cashtype cashtype,
			Set<Pricepolicy> pricepolicies, Set<Agent> agents,
			Discountoraddition discountoraddition) {
		this.resourcetype = resourcetype;
		this.name = name;
		this.isBulk = isBulk;
		this.isIdentifiable = isIdentifiable;
		this.qoH = qoH;
		this.isComposed = isComposed;
		this.taxClass = taxClass;
		this.factorResources = factorResources;
		this.resourceHasAdditionalattributevalues = resourceHasAdditionalattributevalues;
		this.tax = tax;
		this.servicetype = servicetype;
		this.identifiableresources = identifiableresources;
		this.resourceComposedOfsForResourceId = resourceComposedOfsForResourceId;
		this.resourceComposedOfsForResourcePartId = resourceComposedOfsForResourcePartId;
		this.stockflows = stockflows;
		this.locationofresources = locationofresources;
		this.labor = labor;
		this.producttype = producttype;
		this.cashtype = cashtype;
		this.pricepolicies = pricepolicies;
		this.agents = agents;
		this.discountoraddition = discountoraddition;
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
	@JoinColumn(name = "ResourceType_Id", nullable = false)
	public Resourcetype getResourcetype() {
		return this.resourcetype;
	}

	public void setResourcetype(Resourcetype resourcetype) {
		this.resourcetype = resourcetype;
	}

	@Column(name = "Name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IsBulk")
	public Boolean getIsBulk() {
		return this.isBulk;
	}

	public void setIsBulk(Boolean isBulk) {
		this.isBulk = isBulk;
	}

	@Column(name = "IsIdentifiable")
	public Boolean getIsIdentifiable() {
		return this.isIdentifiable;
	}

	public void setIsIdentifiable(Boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	@Column(name = "QoH", precision = 22, scale = 0)
	public Double getQoH() {
		return this.qoH;
	}

	public void setQoH(Double qoH) {
		this.qoH = qoH;
	}

	@Column(name = "IsComposed")
	public Boolean getIsComposed() {
		return this.isComposed;
	}

	public void setIsComposed(Boolean isComposed) {
		this.isComposed = isComposed;
	}

	@Column(name = "TaxClass")
	public Integer getTaxClass() {
		return this.taxClass;
	}

	public void setTaxClass(Integer taxClass) {
		this.taxClass = taxClass;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<FactorResource> getFactorResources() {
		return this.factorResources;
	}

	public void setFactorResources(Set<FactorResource> factorResources) {
		this.factorResources = factorResources;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.resource", cascade=CascadeType.ALL)
	@OrderBy
	public Set<ResourceHasAdditionalattributevalue> getResourceHasAdditionalattributevalues() {
		return this.resourceHasAdditionalattributevalues;
	}

	public void setResourceHasAdditionalattributevalues(
			Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues) {
		this.resourceHasAdditionalattributevalues = resourceHasAdditionalattributevalues;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<Identifiableresource> getIdentifiableresources() {
		return this.identifiableresources;
	}

	public void setIdentifiableresources(
			Set<Identifiableresource> identifiableresources) {
		this.identifiableresources = identifiableresources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceByResourceId")
	public Set<ResourceComposedOf> getResourceComposedOfsForResourceId() {
		return this.resourceComposedOfsForResourceId;
	}

	public void setResourceComposedOfsForResourceId(
			Set<ResourceComposedOf> resourceComposedOfsForResourceId) {
		this.resourceComposedOfsForResourceId = resourceComposedOfsForResourceId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceByResourcePartId")
	public Set<ResourceComposedOf> getResourceComposedOfsForResourcePartId() {
		return this.resourceComposedOfsForResourcePartId;
	}

	public void setResourceComposedOfsForResourcePartId(
			Set<ResourceComposedOf> resourceComposedOfsForResourcePartId) {
		this.resourceComposedOfsForResourcePartId = resourceComposedOfsForResourcePartId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<Locationofresource> getLocationofresources() {
		return this.locationofresources;
	}

	public void setLocationofresources(
			Set<Locationofresource> locationofresources) {
		this.locationofresources = locationofresources;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Labor getLabor() {
		return this.labor;
	}

	public void setLabor(Labor labor) {
		this.labor = labor;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Producttype getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Cashtype getCashtype() {
		return this.cashtype;
	}

	public void setCashtype(Cashtype cashtype) {
		this.cashtype = cashtype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<Pricepolicy> getPricepolicies() {
		return this.pricepolicies;
	}

	public void setPricepolicies(Set<Pricepolicy> pricepolicies) {
		this.pricepolicies = pricepolicies;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "agent_has_resource", catalog = "rea", joinColumns = { @JoinColumn(name = "Resource_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Agent_Id", nullable = false, updatable = false) })
	public Set<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resource")
	public Discountoraddition getDiscountoraddition() {
		return this.discountoraddition;
	}

	public void setDiscountoraddition(Discountoraddition discountoraddition) {
		this.discountoraddition = discountoraddition;
	}

}
