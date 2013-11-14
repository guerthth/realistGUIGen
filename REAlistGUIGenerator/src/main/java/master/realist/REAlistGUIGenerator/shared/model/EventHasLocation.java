package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

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
 * EventHasLocation generated by hbm2java
 */
@Entity
@Table(name = "event_has_location", catalog = "rea")
public class EventHasLocation implements java.io.Serializable {

	private EventHasLocationId id;
	private Event event;
	private Location location;
	private Locationtype locationtype;

	public EventHasLocation() {
	}

	public EventHasLocation(EventHasLocationId id, Event event,
			Location location, Locationtype locationtype) {
		this.id = id;
		this.event = event;
		this.location = location;
		this.locationtype = locationtype;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "eventId", column = @Column(name = "Event_Id", nullable = false)),
			@AttributeOverride(name = "locationId", column = @Column(name = "Location_Id", nullable = false)),
			@AttributeOverride(name = "locationTypeId", column = @Column(name = "LocationType_Id", nullable = false)) })
	public EventHasLocationId getId() {
		return this.id;
	}

	public void setId(EventHasLocationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Event_Id", nullable = false, insertable = false, updatable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Location_Id", nullable = false, insertable = false, updatable = false)
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationType_Id", nullable = false, insertable = false, updatable = false)
	public Locationtype getLocationtype() {
		return this.locationtype;
	}

	public void setLocationtype(Locationtype locationtype) {
		this.locationtype = locationtype;
	}

}
