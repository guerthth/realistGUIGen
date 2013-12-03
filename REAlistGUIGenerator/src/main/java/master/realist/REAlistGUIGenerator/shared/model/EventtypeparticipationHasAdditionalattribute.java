package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 19, 2013 8:35:27 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EventtypeparticipationHasAdditionalattribute generated by hbm2java
 */
@Entity
@Table(name = "eventtypeparticipation_has_additionalattribute", catalog = "rea")
public class EventtypeparticipationHasAdditionalattribute implements
		java.io.Serializable {

	private EventtypeparticipationHasAdditionalattributeId id;
	private Eventtypeparticipation eventtypeparticipation;
	private Attribute attribute;
	private boolean isOptional;
	private boolean isPolicyProperty;
	private boolean isReserveProperty;

	public EventtypeparticipationHasAdditionalattribute() {
	}

	public EventtypeparticipationHasAdditionalattribute(
			EventtypeparticipationHasAdditionalattributeId id,
			Eventtypeparticipation eventtypeparticipation, Attribute attribute,
			boolean isOptional, boolean isPolicyProperty,
			boolean isReserveProperty) {
		this.id = id;
		this.eventtypeparticipation = eventtypeparticipation;
		this.attribute = attribute;
		this.isOptional = isOptional;
		this.isPolicyProperty = isPolicyProperty;
		this.isReserveProperty = isReserveProperty;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "eventTypeParticipationEventTypeId", column = @Column(name = "EventTypeParticipation_EventType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "eventTypeParticipationAgentTypeId", column = @Column(name = "EventTypeParticipation_AgentType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "attributeId", column = @Column(name = "Attribute_Id", nullable = false, length = 150)) })
	public EventtypeparticipationHasAdditionalattributeId getId() {
		return this.id;
	}

	public void setId(EventtypeparticipationHasAdditionalattributeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "EventTypeParticipation_EventType_Id", referencedColumnName = "EventType_Id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "EventTypeParticipation_AgentType_Id", referencedColumnName = "AgentType_Id", nullable = false, insertable = false, updatable = false) })
	public Eventtypeparticipation getEventtypeparticipation() {
		return this.eventtypeparticipation;
	}

	public void setEventtypeparticipation(
			Eventtypeparticipation eventtypeparticipation) {
		this.eventtypeparticipation = eventtypeparticipation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Attribute_Id", nullable = false, insertable = false, updatable = false)
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Column(name = "IsOptional", nullable = false)
	public boolean isIsOptional() {
		return this.isOptional;
	}

	public void setIsOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	@Column(name = "IsPolicyProperty", nullable = false)
	public boolean isIsPolicyProperty() {
		return this.isPolicyProperty;
	}

	public void setIsPolicyProperty(boolean isPolicyProperty) {
		this.isPolicyProperty = isPolicyProperty;
	}

	@Column(name = "IsReserveProperty", nullable = false)
	public boolean isIsReserveProperty() {
		return this.isReserveProperty;
	}

	public void setIsReserveProperty(boolean isReserveProperty) {
		this.isReserveProperty = isReserveProperty;
	}

}