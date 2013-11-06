package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Negotiationstatus generated by hbm2java
 */
@Entity
@Table(name = "negotiationstatus", catalog = "rea")
public class Negotiationstatus implements java.io.Serializable {

	private int id;
	private String status;
	private Set<Negotiation> negotiations = new HashSet<Negotiation>(0);

	public Negotiationstatus() {
	}

	public Negotiationstatus(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public Negotiationstatus(int id, String status,
			Set<Negotiation> negotiations) {
		this.id = id;
		this.status = status;
		this.negotiations = negotiations;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Status", nullable = false, length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "negotiationstatus")
	public Set<Negotiation> getNegotiations() {
		return this.negotiations;
	}

	public void setNegotiations(Set<Negotiation> negotiations) {
		this.negotiations = negotiations;
	}

}