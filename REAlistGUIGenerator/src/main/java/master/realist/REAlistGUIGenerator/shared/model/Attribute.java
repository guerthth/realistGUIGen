package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 2:38:04 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.LinkedHashSet;
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

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;

/**
 * Attribute generated by hbm2java
 */
@Entity
@Table(name = "attribute", catalog = "rea")
public class Attribute implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472555218994259563L;
	private String id;
	private String name;
	private String datatype;
	private Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues = new LinkedHashSet<EventHasAdditionalattributevalue>(
			0);
	private Set<AgentHasAdditionalattributevalue> agentHasAdditionalattributevalues = new LinkedHashSet<AgentHasAdditionalattributevalue>(
			0);
	private Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes = new LinkedHashSet<ResourcetypeHasAdditionalattribute>(
			0);
	private Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes = new HashSet<EventtypestockflowHasAdditionalattribute>(
			0);
	private Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes = new HashSet<EventtypeparticipationHasAdditionalattribute>(
			0);
	private Set<ParticipationHasAdditionalattributevalue> participationHasAdditionalattributevalues = new HashSet<ParticipationHasAdditionalattributevalue>(
			0);
	private Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues = new LinkedHashSet<StockflowHasAdditionalattributevalue>(
			0);
	private Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues = new HashSet<ResourceHasAdditionalattributevalue>(
			0);
	private Set<Identifiableresource> identifiableresources = new HashSet<Identifiableresource>(
			0);
	private Set<AgenttypeHasAdditionalattribute> agenttypeHasAdditionalattributes = new HashSet<AgenttypeHasAdditionalattribute>(
			0);
	private Set<EventtypeHasAdditionalattribute> eventtypeHasAdditionalattributes = new HashSet<EventtypeHasAdditionalattribute>(
			0);

	/**
	 * Added constructor to convert a dualitydto object to a duality object
	 * Needed to save in the DB
	 * @param dualityDTO
	 */
	public Attribute(AttributeDTO attributeDTO){
		this.id = attributeDTO.getId();
		this.name = attributeDTO.getName();
		this.datatype = attributeDTO.getDatatype();
	}
	
	
	public Attribute() {
	}
	

	public Attribute(String id) {
		this.id = id;
	}

	public Attribute(
			String id,
			String name,
			String datatype,
			Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues,
			Set<AgentHasAdditionalattributevalue> agentHasAdditionalattributevalues,
			Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes,
			Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes,
			Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes,
			Set<ParticipationHasAdditionalattributevalue> participationHasAdditionalattributevalues,
			Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues,
			Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues,
			Set<Identifiableresource> identifiableresources,
			Set<AgenttypeHasAdditionalattribute> agenttypeHasAdditionalattributes,
			Set<EventtypeHasAdditionalattribute> eventtypeHasAdditionalattributes) {
		this.id = id;
		this.name = name;
		this.datatype = datatype;
		this.eventHasAdditionalattributevalues = eventHasAdditionalattributevalues;
		this.agentHasAdditionalattributevalues = agentHasAdditionalattributevalues;
		this.resourcetypeHasAdditionalattributes = resourcetypeHasAdditionalattributes;
		this.eventtypestockflowHasAdditionalattributes = eventtypestockflowHasAdditionalattributes;
		this.eventtypeparticipationHasAdditionalattributes = eventtypeparticipationHasAdditionalattributes;
		this.participationHasAdditionalattributevalues = participationHasAdditionalattributevalues;
		this.stockflowHasAdditionalattributevalues = stockflowHasAdditionalattributevalues;
		this.resourceHasAdditionalattributevalues = resourceHasAdditionalattributevalues;
		this.identifiableresources = identifiableresources;
		this.agenttypeHasAdditionalattributes = agenttypeHasAdditionalattributes;
		this.eventtypeHasAdditionalattributes = eventtypeHasAdditionalattributes;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false, length = 150)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Name", length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Datatype", length = 45)
	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	public Set<EventHasAdditionalattributevalue> getEventHasAdditionalattributevalues() {
		return this.eventHasAdditionalattributevalues;
	}

	public void setEventHasAdditionalattributevalues(
			Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues) {
		this.eventHasAdditionalattributevalues = eventHasAdditionalattributevalues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	public Set<AgentHasAdditionalattributevalue> getAgentHasAdditionalattributevalues() {
		return this.agentHasAdditionalattributevalues;
	}

	public void setAgentHasAdditionalattributevalues(
			Set<AgentHasAdditionalattributevalue> agentHasAdditionalattributevalues) {
		this.agentHasAdditionalattributevalues = agentHasAdditionalattributevalues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<ResourcetypeHasAdditionalattribute> getResourcetypeHasAdditionalattributes() {
		return this.resourcetypeHasAdditionalattributes;
	}

	public void setResourcetypeHasAdditionalattributes(
			Set<ResourcetypeHasAdditionalattribute> resourcetypeHasAdditionalattributes) {
		this.resourcetypeHasAdditionalattributes = resourcetypeHasAdditionalattributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<EventtypestockflowHasAdditionalattribute> getEventtypestockflowHasAdditionalattributes() {
		return this.eventtypestockflowHasAdditionalattributes;
	}

	public void setEventtypestockflowHasAdditionalattributes(
			Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes) {
		this.eventtypestockflowHasAdditionalattributes = eventtypestockflowHasAdditionalattributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<EventtypeparticipationHasAdditionalattribute> getEventtypeparticipationHasAdditionalattributes() {
		return this.eventtypeparticipationHasAdditionalattributes;
	}

	public void setEventtypeparticipationHasAdditionalattributes(
			Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes) {
		this.eventtypeparticipationHasAdditionalattributes = eventtypeparticipationHasAdditionalattributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	public Set<ParticipationHasAdditionalattributevalue> getParticipationHasAdditionalattributevalues() {
		return this.participationHasAdditionalattributevalues;
	}

	public void setParticipationHasAdditionalattributevalues(
			Set<ParticipationHasAdditionalattributevalue> participationHasAdditionalattributevalues) {
		this.participationHasAdditionalattributevalues = participationHasAdditionalattributevalues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	public Set<StockflowHasAdditionalattributevalue> getStockflowHasAdditionalattributevalues() {
		return this.stockflowHasAdditionalattributevalues;
	}

	public void setStockflowHasAdditionalattributevalues(
			Set<StockflowHasAdditionalattributevalue> stockflowHasAdditionalattributevalues) {
		this.stockflowHasAdditionalattributevalues = stockflowHasAdditionalattributevalues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	public Set<ResourceHasAdditionalattributevalue> getResourceHasAdditionalattributevalues() {
		return this.resourceHasAdditionalattributevalues;
	}

	public void setResourceHasAdditionalattributevalues(
			Set<ResourceHasAdditionalattributevalue> resourceHasAdditionalattributevalues) {
		this.resourceHasAdditionalattributevalues = resourceHasAdditionalattributevalues;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "identifiableresource_has_additionalattributevalue", catalog = "rea", joinColumns = { @JoinColumn(name = "Attribute_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IdentifiableResource_Id", nullable = false, updatable = false) })
	public Set<Identifiableresource> getIdentifiableresources() {
		return this.identifiableresources;
	}

	public void setIdentifiableresources(
			Set<Identifiableresource> identifiableresources) {
		this.identifiableresources = identifiableresources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<AgenttypeHasAdditionalattribute> getAgenttypeHasAdditionalattributes() {
		return this.agenttypeHasAdditionalattributes;
	}

	public void setAgenttypeHasAdditionalattributes(
			Set<AgenttypeHasAdditionalattribute> agenttypeHasAdditionalattributes) {
		this.agenttypeHasAdditionalattributes = agenttypeHasAdditionalattributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<EventtypeHasAdditionalattribute> getEventtypeHasAdditionalattributes() {
		return this.eventtypeHasAdditionalattributes;
	}

	public void setEventtypeHasAdditionalattributes(
			Set<EventtypeHasAdditionalattribute> eventtypeHasAdditionalattributes) {
		this.eventtypeHasAdditionalattributes = eventtypeHasAdditionalattributes;
	}

}
