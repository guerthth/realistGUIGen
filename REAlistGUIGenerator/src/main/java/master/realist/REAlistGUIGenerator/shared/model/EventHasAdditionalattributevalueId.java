package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 3:42:47 PM by Hibernate Tools 4.0.0

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * EventHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class EventHasAdditionalattributevalueId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3285901094423083927L;
	private Event event;
	private Attribute attribute;

	/**
	 * Default Constructor
	 */
	public EventHasAdditionalattributevalueId() {
	}

	/**
	 * Constructor
	 * @param event
	 * @param attribute
	 */
	public EventHasAdditionalattributevalueId(Event event, Attribute attribute) {
		this.event = event;
		this.attribute = attribute;
	}
	
	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	@ManyToOne
	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        EventHasAdditionalattributevalueId that = (EventHasAdditionalattributevalueId) o;
 
        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (event != null ? event.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

	

}
