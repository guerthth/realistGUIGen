package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FactorAgenttype generated by hbm2java
 */
@Entity
@Table(name = "factor_agenttype", catalog = "rea")
public class FactorAgenttype implements java.io.Serializable {

	private int id;
	private Agenttype agenttype;
	private Policy policy;
	private Set<FactorPropertyconstraint> factorPropertyconstraints = new HashSet<FactorPropertyconstraint>(
			0);

	public FactorAgenttype() {
	}

	public FactorAgenttype(int id, Agenttype agenttype, Policy policy) {
		this.id = id;
		this.agenttype = agenttype;
		this.policy = policy;
	}

	public FactorAgenttype(int id, Agenttype agenttype, Policy policy,
			Set<FactorPropertyconstraint> factorPropertyconstraints) {
		this.id = id;
		this.agenttype = agenttype;
		this.policy = policy;
		this.factorPropertyconstraints = factorPropertyconstraints;
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
	@JoinColumn(name = "AgentType_Id", nullable = false)
	public Agenttype getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Agenttype agenttype) {
		this.agenttype = agenttype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Policy_Id", nullable = false)
	public Policy getPolicy() {
		return this.policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factorAgenttype")
	public Set<FactorPropertyconstraint> getFactorPropertyconstraints() {
		return this.factorPropertyconstraints;
	}

	public void setFactorPropertyconstraints(
			Set<FactorPropertyconstraint> factorPropertyconstraints) {
		this.factorPropertyconstraints = factorPropertyconstraints;
	}

}
