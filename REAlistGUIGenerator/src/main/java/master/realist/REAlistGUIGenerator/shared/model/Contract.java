package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contract generated by hbm2java
 */
@Entity
@Table(name = "contract", catalog = "rea")
public class Contract implements java.io.Serializable {

	private int id;
	private Agent agentByInternalAgentId;
	private Agent agentByExternalAgentId;
	private Contractstatus contractstatus;
	private Contracttype contracttype;
	private Date date;
	private Set<Agent> agents = new HashSet<Agent>(0);
	private Set<Commitment> commitments = new HashSet<Commitment>(0);
	private Set<Negotiation> negotiations = new HashSet<Negotiation>(0);
	private Set<Duality> dualities = new HashSet<Duality>(0);

	public Contract() {
	}

	public Contract(int id, Agent agentByInternalAgentId,
			Agent agentByExternalAgentId, Contractstatus contractstatus,
			Contracttype contracttype) {
		this.id = id;
		this.agentByInternalAgentId = agentByInternalAgentId;
		this.agentByExternalAgentId = agentByExternalAgentId;
		this.contractstatus = contractstatus;
		this.contracttype = contracttype;
	}

	public Contract(int id, Agent agentByInternalAgentId,
			Agent agentByExternalAgentId, Contractstatus contractstatus,
			Contracttype contracttype, Date date, Set<Agent> agents,
			Set<Commitment> commitments, Set<Negotiation> negotiations,
			Set<Duality> dualities) {
		this.id = id;
		this.agentByInternalAgentId = agentByInternalAgentId;
		this.agentByExternalAgentId = agentByExternalAgentId;
		this.contractstatus = contractstatus;
		this.contracttype = contracttype;
		this.date = date;
		this.agents = agents;
		this.commitments = commitments;
		this.negotiations = negotiations;
		this.dualities = dualities;
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
	@JoinColumn(name = "InternalAgent_Id", nullable = false)
	public Agent getAgentByInternalAgentId() {
		return this.agentByInternalAgentId;
	}

	public void setAgentByInternalAgentId(Agent agentByInternalAgentId) {
		this.agentByInternalAgentId = agentByInternalAgentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ExternalAgent_Id", nullable = false)
	public Agent getAgentByExternalAgentId() {
		return this.agentByExternalAgentId;
	}

	public void setAgentByExternalAgentId(Agent agentByExternalAgentId) {
		this.agentByExternalAgentId = agentByExternalAgentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Status_Id", nullable = false)
	public Contractstatus getContractstatus() {
		return this.contractstatus;
	}

	public void setContractstatus(Contractstatus contractstatus) {
		this.contractstatus = contractstatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ContractType_Id", nullable = false)
	public Contracttype getContracttype() {
		return this.contracttype;
	}

	public void setContracttype(Contracttype contracttype) {
		this.contracttype = contracttype;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date", length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contract_has_agents", catalog = "rea", joinColumns = { @JoinColumn(name = "Contract_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Agent_Id", nullable = false, updatable = false) })
	public Set<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
	public Set<Commitment> getCommitments() {
		return this.commitments;
	}

	public void setCommitments(Set<Commitment> commitments) {
		this.commitments = commitments;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contract_has_negotiation", catalog = "rea", joinColumns = { @JoinColumn(name = "Contract_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Negotiation_Id", nullable = false, updatable = false) })
	public Set<Negotiation> getNegotiations() {
		return this.negotiations;
	}

	public void setNegotiations(Set<Negotiation> negotiations) {
		this.negotiations = negotiations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "duality_has_contract", catalog = "rea", joinColumns = { @JoinColumn(name = "Contract_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Duality_Id", nullable = false, updatable = false) })
	public Set<Duality> getDualities() {
		return this.dualities;
	}

	public void setDualities(Set<Duality> dualities) {
		this.dualities = dualities;
	}

}