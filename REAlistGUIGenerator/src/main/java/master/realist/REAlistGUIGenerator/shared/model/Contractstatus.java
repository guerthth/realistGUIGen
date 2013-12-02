package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 19, 2013 8:35:27 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contractstatus generated by hbm2java
 */
@Entity
@Table(name = "contractstatus", catalog = "rea")
public class Contractstatus implements java.io.Serializable {

	private int id;
	private String status;
	private Set<Contract> contracts = new HashSet<Contract>(0);

	public Contractstatus() {
	}

	public Contractstatus(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public Contractstatus(int id, String status, Set<Contract> contracts) {
		this.id = id;
		this.status = status;
		this.contracts = contracts;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contractstatus")
	public Set<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

}
