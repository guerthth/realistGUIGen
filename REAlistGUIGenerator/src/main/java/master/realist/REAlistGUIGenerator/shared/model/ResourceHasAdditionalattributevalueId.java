package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 3:42:47 PM by Hibernate Tools 4.0.0

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * ResourceHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class ResourceHasAdditionalattributevalueId implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9103807488835439204L;
	private Resource resource;
	private Attribute attribute;

	public ResourceHasAdditionalattributevalueId() {
	}

	public ResourceHasAdditionalattributevalueId(Resource resource,
			Attribute attribute) {
		this.resource = resource;
		this.attribute = attribute;
	}

	@ManyToOne
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@ManyToOne
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        ResourceHasAdditionalattributevalueId that = (ResourceHasAdditionalattributevalueId) o;
 
        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

}
