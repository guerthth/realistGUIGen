package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

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
 * Eventtypeparticipation generated by hbm2java
 */
@Entity
@Table(name = "eventtypeparticipation", catalog = "rea")
public class Eventtypeparticipation implements java.io.Serializable {

	private EventtypeparticipationId id;
	private Eventtype eventtype;
	private Agenttype agenttype;
	private Boolean isSeries;
	private Boolean isIdentifiable;
	private Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes = new HashSet<EventtypeparticipationHasAdditionalattribute>(
			0);

	public Eventtypeparticipation() {
	}

	public Eventtypeparticipation(EventtypeparticipationId id,
			Eventtype eventtype, Agenttype agenttype) {
		this.id = id;
		this.eventtype = eventtype;
		this.agenttype = agenttype;
	}

	public Eventtypeparticipation(
			EventtypeparticipationId id,
			Eventtype eventtype,
			Agenttype agenttype,
			Boolean isSeries,
			Boolean isIdentifiable,
			Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes) {
		this.id = id;
		this.eventtype = eventtype;
		this.agenttype = agenttype;
		this.isSeries = isSeries;
		this.isIdentifiable = isIdentifiable;
		this.eventtypeparticipationHasAdditionalattributes = eventtypeparticipationHasAdditionalattributes;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "eventTypeId", column = @Column(name = "EventType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "agentTypeId", column = @Column(name = "AgentType_Id", nullable = false, length = 150)) })
	public EventtypeparticipationId getId() {
		return this.id;
	}

	public void setId(EventtypeparticipationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EventType_Id", nullable = false, insertable = false, updatable = false)
	public Eventtype getEventtype() {
		return this.eventtype;
	}

	public void setEventtype(Eventtype eventtype) {
		this.eventtype = eventtype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgentType_Id", nullable = false, insertable = false, updatable = false)
	public Agenttype getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Agenttype agenttype) {
		this.agenttype = agenttype;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventtypeparticipation")
	public Set<EventtypeparticipationHasAdditionalattribute> getEventtypeparticipationHasAdditionalattributes() {
		return this.eventtypeparticipationHasAdditionalattributes;
	}

	public void setEventtypeparticipationHasAdditionalattributes(
			Set<EventtypeparticipationHasAdditionalattribute> eventtypeparticipationHasAdditionalattributes) {
		this.eventtypeparticipationHasAdditionalattributes = eventtypeparticipationHasAdditionalattributes;
	}

}
