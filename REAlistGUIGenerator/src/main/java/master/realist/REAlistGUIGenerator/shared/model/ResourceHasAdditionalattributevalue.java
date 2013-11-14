package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ResourceHasAdditionalattributevalue generated by hbm2java
 */
@Entity
@Table(name = "resource_has_additionalattributevalue", catalog = "rea")
public class ResourceHasAdditionalattributevalue implements
		java.io.Serializable {

	private ResourceHasAdditionalattributevalueId id;
	private Resource resource;
	private Attribute attribute;
	private Double numeric;
	private String textual;
	private Boolean boolean_;
	private Date datetime;

	public ResourceHasAdditionalattributevalue() {
	}

	public ResourceHasAdditionalattributevalue(
			ResourceHasAdditionalattributevalueId id, Resource resource,
			Attribute attribute) {
		this.id = id;
		this.resource = resource;
		this.attribute = attribute;
	}

	public ResourceHasAdditionalattributevalue(
			ResourceHasAdditionalattributevalueId id, Resource resource,
			Attribute attribute, Double numeric, String textual,
			Boolean boolean_, Date datetime) {
		this.id = id;
		this.resource = resource;
		this.attribute = attribute;
		this.numeric = numeric;
		this.textual = textual;
		this.boolean_ = boolean_;
		this.datetime = datetime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "resourceTypeId", column = @Column(name = "ResourceType_Id", nullable = false)),
			@AttributeOverride(name = "attributeId", column = @Column(name = "Attribute_Id", nullable = false, length = 150)) })
	public ResourceHasAdditionalattributevalueId getId() {
		return this.id;
	}

	public void setId(ResourceHasAdditionalattributevalueId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ResourceType_Id", nullable = false, insertable = false, updatable = false)
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Attribute_Id", nullable = false, insertable = false, updatable = false)
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Column(name = "Numeric", precision = 22, scale = 0)
	public Double getNumeric() {
		return this.numeric;
	}

	public void setNumeric(Double numeric) {
		this.numeric = numeric;
	}

	@Column(name = "Textual", length = 45)
	public String getTextual() {
		return this.textual;
	}

	public void setTextual(String textual) {
		this.textual = textual;
	}

	@Column(name = "Boolean")
	public Boolean getBoolean_() {
		return this.boolean_;
	}

	public void setBoolean_(Boolean boolean_) {
		this.boolean_ = boolean_;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Datetime", length = 19)
	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
