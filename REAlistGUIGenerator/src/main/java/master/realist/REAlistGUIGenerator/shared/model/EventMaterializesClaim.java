package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 19, 2013 8:35:27 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EventMaterializesClaim generated by hbm2java
 */
@Entity
@Table(name = "event_materializes_claim", catalog = "rea")
public class EventMaterializesClaim implements java.io.Serializable {

	private EventMaterializesClaimId id;
	private Event event;
	private Currency currency;
	private Claim claim;
	private Double value;

	public EventMaterializesClaim() {
	}

	public EventMaterializesClaim(EventMaterializesClaimId id, Event event,
			Claim claim) {
		this.id = id;
		this.event = event;
		this.claim = claim;
	}

	public EventMaterializesClaim(EventMaterializesClaimId id, Event event,
			Currency currency, Claim claim, Double value) {
		this.id = id;
		this.event = event;
		this.currency = currency;
		this.claim = claim;
		this.value = value;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "claimId", column = @Column(name = "Claim_Id", nullable = false)),
			@AttributeOverride(name = "eventId", column = @Column(name = "Event_Id", nullable = false)) })
	public EventMaterializesClaimId getId() {
		return this.id;
	}

	public void setId(EventMaterializesClaimId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Event_Id", nullable = false, insertable = false, updatable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
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
	@JoinColumn(name = "Claim_Id", nullable = false, insertable = false, updatable = false)
	public Claim getClaim() {
		return this.claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	@Column(name = "Value", precision = 22, scale = 0)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
