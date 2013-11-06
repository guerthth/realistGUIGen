package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourceComposedOfId generated by hbm2java
 */
@Embeddable
public class ResourceComposedOfId implements java.io.Serializable {

	private int resourceId;
	private int resourcePartId;

	public ResourceComposedOfId() {
	}

	public ResourceComposedOfId(int resourceId, int resourcePartId) {
		this.resourceId = resourceId;
		this.resourcePartId = resourcePartId;
	}

	@Column(name = "Resource_Id", nullable = false)
	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "ResourcePart_Id", nullable = false)
	public int getResourcePartId() {
		return this.resourcePartId;
	}

	public void setResourcePartId(int resourcePartId) {
		this.resourcePartId = resourcePartId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceComposedOfId))
			return false;
		ResourceComposedOfId castOther = (ResourceComposedOfId) other;

		return (this.getResourceId() == castOther.getResourceId())
				&& (this.getResourcePartId() == castOther.getResourcePartId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getResourceId();
		result = 37 * result + this.getResourcePartId();
		return result;
	}

}