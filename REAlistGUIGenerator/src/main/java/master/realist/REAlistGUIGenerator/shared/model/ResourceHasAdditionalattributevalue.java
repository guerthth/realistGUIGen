package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 3:42:47 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * ResourceHasAdditionalattributevalue generated by hbm2java
 */
@Entity
@Table(name = "resource_has_additionalattributevalue", catalog = "rea")
@AssociationOverrides({
	@AssociationOverride(name = "id.resource", 
		joinColumns = @JoinColumn(name = "ResourceType_Id")),
	@AssociationOverride(name = "id.attribute", 
		joinColumns = @JoinColumn(name = "Attribute_Id")) })
public class ResourceHasAdditionalattributevalue implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3555432618437631014L;
	private ResourceHasAdditionalattributevalueId id = new ResourceHasAdditionalattributevalueId();
	private Double numericValue;
	private String textualValue;
	private Boolean booleanValue;
	private Date datetimeValue;

	public ResourceHasAdditionalattributevalue() {
	}

	public ResourceHasAdditionalattributevalue(
			ResourceHasAdditionalattributevalueId id, Resource resource,
			Attribute attribute) {
		this.id = id;
		this.getId().setResource(resource);
		this.getId().setAttribute(attribute);
	}

	public ResourceHasAdditionalattributevalue(
			ResourceHasAdditionalattributevalueId id, Resource resource,
			Attribute attribute, Double numericValue, String textualValue,
			Boolean booleanValue, Date datetimeValue) {
		this.id = id;
		this.getId().setResource(resource);
		this.getId().setAttribute(attribute);
		this.numericValue = numericValue;
		this.textualValue = textualValue;
		this.booleanValue = booleanValue;
		this.datetimeValue = datetimeValue;
	}

	@EmbeddedId
	public ResourceHasAdditionalattributevalueId getId() {
		return this.id;
	}

	public void setId(ResourceHasAdditionalattributevalueId id) {
		this.id = id;
	}

	@Transient
	public Resource getResource() {
		return this.getId().getResource();
	}

	public void setResource(Resource resource) {
		this.getId().setResource(resource);
	}

	@Transient
	public Attribute getAttribute() {
		return this.getId().getAttribute();
	}

	public void setAttribute(Attribute attribute) {
		this.getId().setAttribute(attribute);
	}

	@Column(name = "Numeric_Value", precision = 22, scale = 0)
	public Double getNumericValue() {
		return this.numericValue;
	}

	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	@Column(name = "Textual_Value", length = 45)
	public String getTextualValue() {
		return this.textualValue;
	}

	public void setTextualValue(String textualValue) {
		this.textualValue = textualValue;
	}

	@Column(name = "Boolean_Value")
	public Boolean getBooleanValue() {
		return this.booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Datetime_Value", length = 19)
	public Date getDatetimeValue() {
		return this.datetimeValue;
	}

	public void setDatetimeValue(Date datetimeValue) {
		this.datetimeValue = datetimeValue;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		ResourceHasAdditionalattributevalue that = (ResourceHasAdditionalattributevalue) o;
 
		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;
 
		return true;
	}
 
	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}

}
