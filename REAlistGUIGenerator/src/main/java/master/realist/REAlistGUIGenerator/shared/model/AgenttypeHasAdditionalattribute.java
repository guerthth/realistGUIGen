package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

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
 * AgenttypeHasAdditionalattribute generated by hbm2java
 */
@Entity
@Table(name = "agenttype_has_additionalattribute", catalog = "rea")
public class AgenttypeHasAdditionalattribute implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6556631098606017408L;
	private AgenttypeHasAdditionalattributeId id;
	private Agenttype agenttype;
	private Attribute attribute;
	private boolean isOptional;
	private boolean isTypeProperty;

	public AgenttypeHasAdditionalattribute() {
	}

	public AgenttypeHasAdditionalattribute(
			AgenttypeHasAdditionalattributeId id, Agenttype agenttype,
			Attribute attribute, boolean isOptional, boolean isTypeProperty) {
		this.id = id;
		this.agenttype = agenttype;
		this.attribute = attribute;
		this.isOptional = isOptional;
		this.isTypeProperty = isTypeProperty;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "attributeId", column = @Column(name = "Attribute_Id", nullable = false, length = 150)),
			@AttributeOverride(name = "agentTypeId", column = @Column(name = "AgentType_Id", nullable = false, length = 150)) })
	public AgenttypeHasAdditionalattributeId getId() {
		return this.id;
	}

	public void setId(AgenttypeHasAdditionalattributeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgentType_Id", nullable = false, insertable = false, updatable = false)
	public Agenttype getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Agenttype agenttype) {
		this.agenttype = agenttype;
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

	@Column(name = "IsTypeProperty", nullable = false)
	public boolean isIsTypeProperty() {
		return this.isTypeProperty;
	}

	public void setIsTypeProperty(boolean isTypeProperty) {
		this.isTypeProperty = isTypeProperty;
	}

}
