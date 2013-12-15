package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 10, 2013 2:10:07 PM by Hibernate Tools 4.0.0

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
 * Fulfills generated by hbm2java
 */
@Entity
@Table(name = "fulfills", catalog = "rea")
public class Fulfills implements java.io.Serializable {

	private FulfillsId id;
	private Event event;
	private Currency currency;
	private Commitment commitment;
	private Double value;

	public Fulfills() {
	}

	public Fulfills(FulfillsId id, Event event, Commitment commitment) {
		this.id = id;
		this.event = event;
		this.commitment = commitment;
	}

	public Fulfills(FulfillsId id, Event event, Currency currency,
			Commitment commitment, Double value) {
		this.id = id;
		this.event = event;
		this.currency = currency;
		this.commitment = commitment;
		this.value = value;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "commitmentId", column = @Column(name = "Commitment_Id", nullable = false)),
			@AttributeOverride(name = "eventId", column = @Column(name = "Event_Id", nullable = false)) })
	public FulfillsId getId() {
		return this.id;
	}

	public void setId(FulfillsId id) {
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
	@JoinColumn(name = "Commitment_Id", nullable = false, insertable = false, updatable = false)
	public Commitment getCommitment() {
		return this.commitment;
	}

	public void setCommitment(Commitment commitment) {
		this.commitment = commitment;
	}

	@Column(name = "Value", precision = 22, scale = 0)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
