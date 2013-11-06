package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FactorResourcetype generated by hbm2java
 */
@Entity
@Table(name = "factor_resourcetype", catalog = "rea")
public class FactorResourcetype implements java.io.Serializable {

	private int id;
	private Resourcetype resourcetype;
	private Policy policy;
	private String factorResourceTypecol;

	public FactorResourcetype() {
	}

	public FactorResourcetype(int id, Resourcetype resourcetype, Policy policy) {
		this.id = id;
		this.resourcetype = resourcetype;
		this.policy = policy;
	}

	public FactorResourcetype(int id, Resourcetype resourcetype, Policy policy,
			String factorResourceTypecol) {
		this.id = id;
		this.resourcetype = resourcetype;
		this.policy = policy;
		this.factorResourceTypecol = factorResourceTypecol;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ResourceType_Id", nullable = false)
	public Resourcetype getResourcetype() {
		return this.resourcetype;
	}

	public void setResourcetype(Resourcetype resourcetype) {
		this.resourcetype = resourcetype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Policy_Id", nullable = false)
	public Policy getPolicy() {
		return this.policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	@Column(name = "Factor_ResourceTypecol", length = 45)
	public String getFactorResourceTypecol() {
		return this.factorResourceTypecol;
	}

	public void setFactorResourceTypecol(String factorResourceTypecol) {
		this.factorResourceTypecol = factorResourceTypecol;
	}

}