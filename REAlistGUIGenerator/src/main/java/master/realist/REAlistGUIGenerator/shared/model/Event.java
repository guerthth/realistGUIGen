package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name = "event", catalog = "rea")
public class Event implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8967937366669714510L;
	private Integer id;
	private Eventtype eventtype;
	private Currency currency;
	private Agent agentByProvideAgentId;
	private Agent agentByReceiveAgentId;
	private Duality duality;
	private Double totalValue;
	private Double totalValueNet;
	private Date dateStart;
	private Date dateEnd;
	private Boolean isReconciled;
	private Set<Agent> agents = new HashSet<Agent>(0);
	private Set<EventHasLocation> eventHasLocations = new HashSet<EventHasLocation>(
			0);
	private Set<EventMaterializesClaim> eventMaterializesClaims = new HashSet<EventMaterializesClaim>(
			0);
	private Set<Stockflow> stockflows = new HashSet<Stockflow>(0);
	private Set<Fulfills> fulfillses = new HashSet<Fulfills>(0);
	private Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues = new LinkedHashSet<EventHasAdditionalattributevalue>(
			0);
	private Set<Reconciliation> reconciliationsForDecEventId = new HashSet<Reconciliation>(
			0);
	private Set<EventSettlesClaim> eventSettlesClaims = new HashSet<EventSettlesClaim>(
			0);
	private Set<Reconciliation> reconciliationsForIncEventId = new HashSet<Reconciliation>(
			0);
	private Set<Participation> participations = new LinkedHashSet<Participation>(0);

	/**
	 * Added constructor to convert a eventdto object to an event object
	 * Needed to save in the DB
	 * @param eventDTO
	 */
	public Event(EventDTO eventDTO){
		this.id = eventDTO.getId();
		this.eventtype = new Eventtype(eventDTO.getEventtype());
		this.duality = new Duality(eventDTO.getDuality());
		this.agentByProvideAgentId = new Agent(eventDTO.getProvideAgent());
		this.agentByReceiveAgentId = new Agent(eventDTO.getReceiveAgent());
		this.dateStart = eventDTO.getDateStart();
		this.dateEnd = eventDTO.getDateEnd();
	}
	
	public Event() {
	}

	public Event(Eventtype eventtype, Agent agentByProvideAgentId,
			Agent agentByReceiveAgentId, Duality duality) {
		this.eventtype = eventtype;
		this.agentByProvideAgentId = agentByProvideAgentId;
		this.agentByReceiveAgentId = agentByReceiveAgentId;
		this.duality = duality;
	}

	public Event(
			Eventtype eventtype,
			Currency currency,
			Agent agentByProvideAgentId,
			Agent agentByReceiveAgentId,
			Duality duality,
			Double totalValue,
			Double totalValueNet,
			Date dateStart,
			Date dateEnd,
			Boolean isReconciled,
			Set<Agent> agents,
			Set<EventHasLocation> eventHasLocations,
			Set<EventMaterializesClaim> eventMaterializesClaims,
			Set<Stockflow> stockflows,
			Set<Fulfills> fulfillses,
			Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues,
			Set<Reconciliation> reconciliationsForDecEventId,
			Set<EventSettlesClaim> eventSettlesClaims,
			Set<Reconciliation> reconciliationsForIncEventId,
			Set<Participation> participations) {
		this.eventtype = eventtype;
		this.currency = currency;
		this.agentByProvideAgentId = agentByProvideAgentId;
		this.agentByReceiveAgentId = agentByReceiveAgentId;
		this.duality = duality;
		this.totalValue = totalValue;
		this.totalValueNet = totalValueNet;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.isReconciled = isReconciled;
		this.agents = agents;
		this.eventHasLocations = eventHasLocations;
		this.eventMaterializesClaims = eventMaterializesClaims;
		this.stockflows = stockflows;
		this.fulfillses = fulfillses;
		this.eventHasAdditionalattributevalues = eventHasAdditionalattributevalues;
		this.reconciliationsForDecEventId = reconciliationsForDecEventId;
		this.eventSettlesClaims = eventSettlesClaims;
		this.reconciliationsForIncEventId = reconciliationsForIncEventId;
		this.participations = participations;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EventType_Id", nullable = false)
	public Eventtype getEventtype() {
		return this.eventtype;
	}

	public void setEventtype(Eventtype eventtype) {
		this.eventtype = eventtype;
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
	@JoinColumn(name = "ProvideAgent_Id", nullable = false)
	public Agent getAgentByProvideAgentId() {
		return this.agentByProvideAgentId;
	}

	public void setAgentByProvideAgentId(Agent agentByProvideAgentId) {
		this.agentByProvideAgentId = agentByProvideAgentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReceiveAgent_Id", nullable = false)
	public Agent getAgentByReceiveAgentId() {
		return this.agentByReceiveAgentId;
	}

	public void setAgentByReceiveAgentId(Agent agentByReceiveAgentId) {
		this.agentByReceiveAgentId = agentByReceiveAgentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Duality_Id", nullable = false)
	public Duality getDuality() {
		return this.duality;
	}

	public void setDuality(Duality duality) {
		this.duality = duality;
	}

	@Column(name = "TotalValue", precision = 22, scale = 0)
	public Double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	@Column(name = "TotalValueNet", precision = 22, scale = 0)
	public Double getTotalValueNet() {
		return this.totalValueNet;
	}

	public void setTotalValueNet(Double totalValueNet) {
		this.totalValueNet = totalValueNet;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateStart", length = 19)
	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateEnd", length = 19)
	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Column(name = "IsReconciled")
	public Boolean getIsReconciled() {
		return this.isReconciled;
	}

	public void setIsReconciled(Boolean isReconciled) {
		this.isReconciled = isReconciled;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_participation", catalog = "rea", joinColumns = { @JoinColumn(name = "Event_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Agent_Id", nullable = false, updatable = false) })
	public Set<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<EventHasLocation> getEventHasLocations() {
		return this.eventHasLocations;
	}

	public void setEventHasLocations(Set<EventHasLocation> eventHasLocations) {
		this.eventHasLocations = eventHasLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<EventMaterializesClaim> getEventMaterializesClaims() {
		return this.eventMaterializesClaims;
	}

	public void setEventMaterializesClaims(
			Set<EventMaterializesClaim> eventMaterializesClaims) {
		this.eventMaterializesClaims = eventMaterializesClaims;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_has_stockflow", catalog = "rea", joinColumns = { @JoinColumn(name = "Event_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Stockflow_Id", nullable = false, updatable = false) })
	public Set<Stockflow> getStockflows() {
		return this.stockflows;
	}

	public void setStockflows(Set<Stockflow> stockflows) {
		this.stockflows = stockflows;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<Fulfills> getFulfillses() {
		return this.fulfillses;
	}

	public void setFulfillses(Set<Fulfills> fulfillses) {
		this.fulfillses = fulfillses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.event", cascade=CascadeType.ALL)
	@OrderBy
	public Set<EventHasAdditionalattributevalue> getEventHasAdditionalattributevalues() {
		return this.eventHasAdditionalattributevalues;
	}

	public void setEventHasAdditionalattributevalues(
			Set<EventHasAdditionalattributevalue> eventHasAdditionalattributevalues) {
		this.eventHasAdditionalattributevalues = eventHasAdditionalattributevalues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventByDecEventId")
	public Set<Reconciliation> getReconciliationsForDecEventId() {
		return this.reconciliationsForDecEventId;
	}

	public void setReconciliationsForDecEventId(
			Set<Reconciliation> reconciliationsForDecEventId) {
		this.reconciliationsForDecEventId = reconciliationsForDecEventId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<EventSettlesClaim> getEventSettlesClaims() {
		return this.eventSettlesClaims;
	}

	public void setEventSettlesClaims(Set<EventSettlesClaim> eventSettlesClaims) {
		this.eventSettlesClaims = eventSettlesClaims;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventByIncEventId")
	public Set<Reconciliation> getReconciliationsForIncEventId() {
		return this.reconciliationsForIncEventId;
	}

	public void setReconciliationsForIncEventId(
			Set<Reconciliation> reconciliationsForIncEventId) {
		this.reconciliationsForIncEventId = reconciliationsForIncEventId;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_has_participation", catalog = "rea", joinColumns = { @JoinColumn(name = "Event_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Participation_Participation_id", nullable = false, updatable = false) })
	public Set<Participation> getParticipations() {
		return this.participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

}
