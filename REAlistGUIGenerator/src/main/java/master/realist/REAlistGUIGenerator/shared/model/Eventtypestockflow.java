package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Eventtypestockflow generated by hbm2java
 */
@Entity
@Table(name = "eventtypestockflow", catalog = "rea")
public class Eventtypestockflow implements java.io.Serializable {

	private EventtypestockflowId id;
	private Resourcetype resourcetype;
	private Eventtype eventtype;
	private Boolean isSeries;
	private Boolean isIdentifiable;
	private Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes = new HashSet<EventtypestockflowHasAdditionalattribute>(
			0);

	public Eventtypestockflow() {
	}

	public Eventtypestockflow(EventtypestockflowId id,
			Resourcetype resourcetype, Eventtype eventtype) {
		this.id = id;
		this.resourcetype = resourcetype;
		this.eventtype = eventtype;
	}

	public Eventtypestockflow(
			EventtypestockflowId id,
			Resourcetype resourcetype,
			Eventtype eventtype,
			Boolean isSeries,
			Boolean isIdentifiable,
			Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes) {
		this.id = id;
		this.resourcetype = resourcetype;
		this.eventtype = eventtype;
		this.isSeries = isSeries;
		this.isIdentifiable = isIdentifiable;
		this.eventtypestockflowHasAdditionalattributes = eventtypestockflowHasAdditionalattributes;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "eventTypeId", column = @Column(name = "EventType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "resourceTypeId", column = @Column(name = "ResourceType_Id", nullable = false, length = 150)) })
	public EventtypestockflowId getId() {
		return this.id;
	}

	public void setId(EventtypestockflowId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ResourceType_Id", nullable = false, insertable = false, updatable = false)
	public Resourcetype getResourcetype() {
		return this.resourcetype;
	}

	public void setResourcetype(Resourcetype resourcetype) {
		this.resourcetype = resourcetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EventType_Id", nullable = false, insertable = false, updatable = false)
	public Eventtype getEventtype() {
		return this.eventtype;
	}

	public void setEventtype(Eventtype eventtype) {
		this.eventtype = eventtype;
	}

	@Column(name = "IsSeries")
	public Boolean getIsSeries() {
		return this.isSeries;
	}

	public void setIsSeries(Boolean isSeries) {
		this.isSeries = isSeries;
	}

	@Column(name = "IsIdentifiable")
	public Boolean getIsIdentifiable() {
		return this.isIdentifiable;
	}

	public void setIsIdentifiable(Boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventtypestockflow")
	public Set<EventtypestockflowHasAdditionalattribute> getEventtypestockflowHasAdditionalattributes() {
		return this.eventtypestockflowHasAdditionalattributes;
	}

	public void setEventtypestockflowHasAdditionalattributes(
			Set<EventtypestockflowHasAdditionalattribute> eventtypestockflowHasAdditionalattributes) {
		this.eventtypestockflowHasAdditionalattributes = eventtypestockflowHasAdditionalattributes;
	}

}
