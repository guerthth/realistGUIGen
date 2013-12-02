package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 21, 2013 10:11:07 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;

/**
 * Dualitystatus generated by hbm2java
 */
@Entity
@Table(name = "dualitystatus", catalog = "rea")
public class Dualitystatus implements java.io.Serializable {

	private Integer id;
	private String status;
	private Set<Duality> dualities = new HashSet<Duality>(0);

	/**
	 * Added constructor to convert a dualitystatusdto object to a dualitystatus object
	 * Needed to save in the DB
	 * @param dualityStatusDTO
	 */
	public Dualitystatus(DualityStatusDTO dualityStatusDTO){
		this.id = dualityStatusDTO.getId();
		this.status = dualityStatusDTO.getStatus();
		// TODO: Dealing with dualities
	}
	
	public Dualitystatus() {
	}

	public Dualitystatus(String status) {
		this.status = status;
	}

	public Dualitystatus(String status, Set<Duality> dualities) {
		this.status = status;
		this.dualities = dualities;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Status", nullable = false, length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dualitystatus")
	public Set<Duality> getDualities() {
		return this.dualities;
	}

	public void setDualities(Set<Duality> dualities) {
		this.dualities = dualities;
	}

}
