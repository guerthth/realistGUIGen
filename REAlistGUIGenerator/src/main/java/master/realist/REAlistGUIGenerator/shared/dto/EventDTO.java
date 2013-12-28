package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Currency;
import master.realist.REAlistGUIGenerator.shared.model.Duality;
import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;
import master.realist.REAlistGUIGenerator.shared.model.Participation;

public class EventDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1681094168790318146L;
	private Integer id;
	private EventtypeDTO eventtype;
	private Currency currency;
	private AgentDTO provideAgent;
	private AgentDTO receiveAgent;
	private DualityDTO duality;
	private Double totalValue;
	private Double totalValueNet;
	private Date dateStart;
	private Date dateEnd;
	private Boolean isReconciled;
	private Set<EventHasAdditionalattributevalueDTO> additionalAttributeValues;
	private Set<ParticipationDTO> participations = new LinkedHashSet<ParticipationDTO>();
	
	/**
	 * Constructor transforming an Event object to an EventDTO object
	 * @param event
	 */
	public EventDTO(Event event){
		this.id = event.getId();
		this.eventtype = new EventtypeDTO(event.getEventtype());
		this.provideAgent = new AgentDTO(event.getAgentByProvideAgentId());
		this.receiveAgent = new AgentDTO(event.getAgentByReceiveAgentId());
		this.duality = new DualityDTO(event.getDuality());
		this.dateStart = event.getDateStart();
		this.dateEnd = event.getDateEnd();
		
		// participations
		if(event.getParticipations() != null){
			for(Participation participation : event.getParticipations()){
				this.participations.add(new ParticipationDTO(participation));
			}
		}
	}
	
	/**
	 * Default Constructor
	 */
	public EventDTO(){
		
	}

	public EventDTO(Integer id, EventtypeDTO eventtype, Currency currency,
			AgentDTO provideAgent, AgentDTO receiveAgent, DualityDTO duality,
			Double totalValue, Double totalValueNet, Date dateStart,
			Date dateEnd, Boolean isReconciled) {
		super();
		this.id = id;
		this.eventtype = eventtype;
		this.currency = currency;
		this.provideAgent = provideAgent;
		this.receiveAgent = receiveAgent;
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

	public AgentDTO getProvideAgent() {
		return provideAgent;
	}

	public void setProvideAgent(AgentDTO provideAgent) {
		this.provideAgent = provideAgent;
	}

	public AgentDTO getReceiveAgent() {
		return receiveAgent;
	}

	public void setReceiveAgent(AgentDTO receiveAgent) {
		this.receiveAgent = receiveAgent;
	}

	public Set<EventHasAdditionalattributevalueDTO> getAdditionalAttributeValues() {
		return additionalAttributeValues;
	}

	public void setAdditionalAttributeValues(
			Set<EventHasAdditionalattributevalueDTO> additionalAttributeValues) {
		this.additionalAttributeValues = additionalAttributeValues;
	}

	public Set<ParticipationDTO> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<ParticipationDTO> participations) {
		this.participations = participations;
	}
	
	
	
	
	
}
