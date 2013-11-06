package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * LocationCoordinates generated by hbm2java
 */
@Entity
@Table(name = "location_coordinates", catalog = "rea")
public class LocationCoordinates implements java.io.Serializable {

	private int id;
	private Location location;
	private Double x;
	private Double y;

	public LocationCoordinates() {
	}

	public LocationCoordinates(Location location) {
		this.location = location;
	}

	public LocationCoordinates(Location location, Double x, Double y) {
		this.location = location;
		this.x = x;
		this.y = y;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "location"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(name = "X", precision = 22, scale = 0)
	public Double getX() {
		return this.x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	@Column(name = "Y", precision = 22, scale = 0)
	public Double getY() {
		return this.y;
	}

	public void setY(Double y) {
		this.y = y;
	}

}