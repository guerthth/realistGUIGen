package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 19, 2013 8:35:27 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourceHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class ResourceHasAdditionalattributevalueId implements
		java.io.Serializable {

	private int resourceTypeId;
	private String attributeId;

	public ResourceHasAdditionalattributevalueId() {
	}

	public ResourceHasAdditionalattributevalueId(int resourceTypeId,
			String attributeId) {
		this.resourceTypeId = resourceTypeId;
		this.attributeId = attributeId;
	}

	@Column(name = "ResourceType_Id", nullable = false)
	public int getResourceTypeId() {
		return this.resourceTypeId;
	}

	public void setResourceTypeId(int resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	@Column(name = "Attribute_Id", nullable = false, length = 150)
	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceHasAdditionalattributevalueId))
			return false;
		ResourceHasAdditionalattributevalueId castOther = (ResourceHasAdditionalattributevalueId) other;

		return (this.getResourceTypeId() == castOther.getResourceTypeId())
				&& ((this.getAttributeId() == castOther.getAttributeId()) || (this
						.getAttributeId() != null
						&& castOther.getAttributeId() != null && this
						.getAttributeId().equals(castOther.getAttributeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getResourceTypeId();
		result = 37
				* result
				+ (getAttributeId() == null ? 0 : this.getAttributeId()
						.hashCode());
		return result;
	}

}
