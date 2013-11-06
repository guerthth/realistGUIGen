package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resourcetype generated by hbm2java
 */
@Entity
@Table(name = "resourcetype", catalog = "rea")
public class Resourcetype implements java.io.Serializable {

	private String id;
	private Resourcetype resourcetype;
	private String name;
	private Boolean isBulk;
	private Boolean isBulkIdentifiable;
	private String unitOfMeasure;
	private Set<Resourcetype> resourcetypes = new HashSet<Resourcetype>(0);
	private Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes = new HashSet<ResourcetypeHasAdditionalattribute>(
			0);
	private Set<FactorResourcetype> factorResourcetypes = new HashSet<FactorResourcetype>(
			0);
	private Set<Eventtypestockflow> eventtypestockflows = new HashSet<Eventtypestockflow>(
			0);
	private Set<Resource> resources = new HashSet<Resource>(0);

	public Resourcetype() {
	}

	public Resourcetype(String id) {
		this.id = id;
	}

	public Resourcetype(
			String id,
			Resourcetype resourcetype,
			String name,
			Boolean isBulk,
			Boolean isBulkIdentifiable,
			String unitOfMeasure,
			Set<Resourcetype> resourcetypes,
			Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes,
			Set<FactorResourcetype> factorResourcetypes,
			Set<Eventtypestockflow> eventtypestockflows, Set<Resource> resources) {
		this.id = id;
		this.resourcetype = resourcetype;
		this.name = name;
		this.isBulk = isBulk;
		this.isBulkIdentifiable = isBulkIdentifiable;
		this.unitOfMeasure = unitOfMeasure;
		this.resourcetypes = resourcetypes;
		this.resourcetypeHasAdditionalattributes = resourcetypeHasAdditionalattributes;
		this.factorResourcetypes = factorResourcetypes;
		this.eventtypestockflows = eventtypestockflows;
		this.resources = resources;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false, length = 150)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentResourceType_Id")
	public Resourcetype getResourcetype() {
		return this.resourcetype;
	}

	public void setResourcetype(Resourcetype resourcetype) {
		this.resourcetype = resourcetype;
	}

	@Column(name = "Name", length = 150)
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

	@Column(name = "IsBulkIdentifiable")
	public Boolean getIsBulkIdentifiable() {
		return this.isBulkIdentifiable;
	}

	public void setIsBulkIdentifiable(Boolean isBulkIdentifiable) {
		this.isBulkIdentifiable = isBulkIdentifiable;
	}

	@Column(name = "UnitOfMeasure", length = 45)
	public String getUnitOfMeasure() {
		return this.unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcetype")
	public Set<Resourcetype> getResourcetypes() {
		return this.resourcetypes;
	}

	public void setResourcetypes(Set<Resourcetype> resourcetypes) {
		this.resourcetypes = resourcetypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcetype")
	public Set<ResourcetypeHasAdditionalattribute> getResourcetypeHasAdditionalattributes() {
		return this.resourcetypeHasAdditionalattributes;
	}

	public void setResourcetypeHasAdditionalattributes(
			Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes) {
		this.resourcetypeHasAdditionalattributes = resourcetypeHasAdditionalattributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcetype")
	public Set<FactorResourcetype> getFactorResourcetypes() {
		return this.factorResourcetypes;
	}

	public void setFactorResourcetypes(
			Set<FactorResourcetype> factorResourcetypes) {
		this.factorResourcetypes = factorResourcetypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcetype")
	public Set<Eventtypestockflow> getEventtypestockflows() {
		return this.eventtypestockflows;
	}

	public void setEventtypestockflows(
			Set<Eventtypestockflow> eventtypestockflows) {
		this.eventtypestockflows = eventtypestockflows;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcetype")
	public Set<Resource> getResources() {
		return this.resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}