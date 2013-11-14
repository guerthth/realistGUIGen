package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Currency;
import master.realist.REAlistGUIGenerator.shared.model.Duality;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;

public class EventDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1681094168790318146L;
	private Integer id;
	private EventtypeDTO eventtype;
	private Currency currency;
	private Agent agentByProvideAgentId;
	private Agent agentByReceiveAgentId;
	private DualityDTO duality;
	private Double totalValue;
	private Double totalValueNet;
	private Date dateStart;
	private Date dateEnd;
	private Boolean isReconciled;
	
	/**
	 * Default Constructor
	 */
	public EventDTO(){
		
	}

	public EventDTO(Integer id, EventtypeDTO eventtype, Currency currency,
			Agent agentByProvideAgentId, Agent agentByReceiveAgentId,
			DualityDTO duality, Double totalValue, Double totalValueNet,
			Date dateStart, Date dateEnd, Boolean isReconciled) {
		super();
		this.id = id;
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
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventtypeDTO getEventtype() {
		return eventtype;
	}

	public void setEventtype(EventtypeDTO eventtype) {
		this.eventtype = eventtype;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Agent getAgentByProvideAgentId() {
		return agentByProvideAgentId;
	}

	public void setAgentByProvideAgentId(Agent agentByProvideAgentId) {
		this.agentByProvideAgentId = agentByProvideAgentId;
	}

	public Agent getAgentByReceiveAgentId() {
		return agentByReceiveAgentId;
	}

	public void setAgentByReceiveAgentId(Agent agentByReceiveAgentId) {
		this.agentByReceiveAgentId = agentByReceiveAgentId;
	}

	public DualityDTO getDuality() {
		return duality;
	}

	public void setDuality(DualityDTO duality) {
		this.duality = duality;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Double getTotalValueNet() {
		return totalValueNet;
	}

	public void setTotalValueNet(Double totalValueNet) {
		this.totalValueNet = totalValueNet;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Boolean getIsReconciled() {
		return isReconciled;
	}

	public void setIsReconciled(Boolean isReconciled) {
		this.isReconciled = isReconciled;
	}
	
	
}
