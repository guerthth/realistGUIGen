package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

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
 * Claim generated by hbm2java
 */
@Entity
@Table(name = "claim", catalog = "rea")
public class Claim implements java.io.Serializable {

	private int id;
	private Currency currency;
	private Claimtype claimtype;
	private Claimstatus claimstatus;
	private Agent agentBySettleAgentId;
	private Agent agentByMaterializeAgentId;
	private Date date;
	private Date dueDate;
	private Double totalValue;
	private Boolean isReconciled;
	private Set<ClaimHasLocation> claimHasLocations = new HashSet<ClaimHasLocation>(
			0);
	private Set<EventMaterializesClaim> eventMaterializesClaims = new HashSet<EventMaterializesClaim>(
			0);
	private Set<Duality> dualities = new HashSet<Duality>(0);
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);
	private Set<EventSettlesClaim> eventSettlesClaims = new HashSet<EventSettlesClaim>(
			0);

	public Claim() {
	}

	public Claim(int id, Claimtype claimtype, Claimstatus claimstatus,
			Agent agentBySettleAgentId, Agent agentByMaterializeAgentId) {
		this.id = id;
		this.claimtype = claimtype;
		this.claimstatus = claimstatus;
		this.agentBySettleAgentId = agentBySettleAgentId;
		this.agentByMaterializeAgentId = agentByMaterializeAgentId;
	}

	public Claim(int id, Currency currency, Claimtype claimtype,
			Claimstatus claimstatus, Agent agentBySettleAgentId,
			Agent agentByMaterializeAgentId, Date date, Date dueDate,
			Double totalValue, Boolean isReconciled,
			Set<ClaimHasLocation> claimHasLocations,
			Set<EventMaterializesClaim> eventMaterializesClaims,
			Set<Duality> dualities, Set<Stockflow> stockflows,
			Set<EventSettlesClaim> eventSettlesClaims) {
		this.id = id;
		this.currency = currency;
		this.claimtype = claimtype;
		this.claimstatus = claimstatus;
		this.agentBySettleAgentId = agentBySettleAgentId;
		this.agentByMaterializeAgentId = agentByMaterializeAgentId;
		this.date = date;
		this.dueDate = dueDate;
		this.totalValue = totalValue;
		this.isReconciled = isReconciled;
		this.claimHasLocations = claimHasLocations;
		this.eventMaterializesClaims = eventMaterializesClaims;
		this.dualities = dualities;
		this.stockflows = stockflows;
		this.eventSettlesClaims = eventSettlesClaims;
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
	@JoinColumn(name = "Currency_Id")
	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClaimType_Id", nullable = false)
	public Claimtype getClaimtype() {
		return this.claimtype;
	}

	public void setClaimtype(Claimtype claimtype) {
		this.claimtype = claimtype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Status_Id", nullable = false)
	public Claimstatus getClaimstatus() {
		return this.claimstatus;
	}

	public void setClaimstatus(Claimstatus claimstatus) {
		this.claimstatus = claimstatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SettleAgent_Id", nullable = false)
	public Agent getAgentBySettleAgentId() {
		return this.agentBySettleAgentId;
	}

	public void setAgentBySettleAgentId(Agent agentBySettleAgentId) {
		this.agentBySettleAgentId = agentBySettleAgentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaterializeAgent_Id", nullable = false)
	public Agent getAgentByMaterializeAgentId() {
		return this.agentByMaterializeAgentId;
	}

	public void setAgentByMaterializeAgentId(Agent agentByMaterializeAgentId) {
		this.agentByMaterializeAgentId = agentByMaterializeAgentId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DueDate", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "TotalValue", precision = 22, scale = 0)
	public Double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	@Column(name = "IsReconciled")
	public Boolean getIsReconciled() {
		return this.isReconciled;
	}

	public void setIsReconciled(Boolean isReconciled) {
		this.isReconciled = isReconciled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "claim")
	public Set<ClaimHasLocation> getClaimHasLocations() {
		return this.claimHasLocations;
	}

	public void setClaimHasLocations(Set<ClaimHasLocation> claimHasLocations) {
		this.claimHasLocations = claimHasLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "claim")
	public Set<EventMaterializesClaim> getEventMaterializesClaims() {
		return this.eventMaterializesClaims;
	}

	public void setEventMaterializesClaims(
			Set<EventMaterializesClaim> eventMaterializesClaims) {
		this.eventMaterializesClaims = eventMaterializesClaims;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "duality_has_claim", catalog = "rea", joinColumns = { @JoinColumn(name = "Claim_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Duality_Id", nullable = false, updatable = false) })
	public Set<Duality> getDualities() {
		return this.dualities;
	}

	public void setDualities(Set<Duality> dualities) {
		this.dualities = dualities;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "claim_has_stockflow", catalog = "rea", joinColumns = { @JoinColumn(name = "Claim_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) })
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "claim")
	public Set<EventSettlesClaim> getEventSettlesClaims() {
		return this.eventSettlesClaims;
	}

	public void setEventSettlesClaims(Set<EventSettlesClaim> eventSettlesClaims) {
		this.eventSettlesClaims = eventSettlesClaims;
	}

}
