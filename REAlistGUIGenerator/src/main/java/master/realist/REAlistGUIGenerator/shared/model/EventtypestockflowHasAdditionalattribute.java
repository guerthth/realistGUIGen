package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

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
 * EventtypestockflowHasAdditionalattribute generated by hbm2java
 */
@Entity
@Table(name = "eventtypestockflow_has_additionalattribute", catalog = "rea")
public class EventtypestockflowHasAdditionalattribute implements
		java.io.Serializable {

	private EventtypestockflowHasAdditionalattributeId id;
	private Eventtypestockflow eventtypestockflow;
	private Attribute attribute;
	private boolean isOptional;
	private boolean isPolicyProperty;
	private boolean isReserveProperty;

	public EventtypestockflowHasAdditionalattribute() {
	}

	public EventtypestockflowHasAdditionalattribute(
			EventtypestockflowHasAdditionalattributeId id,
			Eventtypestockflow eventtypestockflow, Attribute attribute,
			boolean isOptional, boolean isPolicyProperty,
			boolean isReserveProperty) {
		this.id = id;
		this.eventtypestockflow = eventtypestockflow;
		this.attribute = attribute;
		this.isOptional = isOptional;
		this.isPolicyProperty = isPolicyProperty;
		this.isReserveProperty = isReserveProperty;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "eventTypeStockflowEventTypeId", column = @Column(name = "EventTypeStockflow_EventType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "eventTypeStockflowResourceTypeId", column = @Column(name = "EventTypeStockflow_ResourceType_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "attributeId", column = @Column(name = "Attribute_Id", nullable = false, length = 150)) })
	public EventtypestockflowHasAdditionalattributeId getId() {
		return this.id;
	}

	public void setId(EventtypestockflowHasAdditionalattributeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "EventTypeStockflow_EventType_Id", referencedColumnName = "EventType_Id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "EventTypeStockflow_ResourceType_Id", referencedColumnName = "ResourceType_Id", nullable = false, insertable = false, updatable = false) })
	public Eventtypestockflow getEventtypestockflow() {
		return this.eventtypestockflow;
	}

	public void setEventtypestockflow(Eventtypestockflow eventtypestockflow) {
		this.eventtypestockflow = eventtypestockflow;
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