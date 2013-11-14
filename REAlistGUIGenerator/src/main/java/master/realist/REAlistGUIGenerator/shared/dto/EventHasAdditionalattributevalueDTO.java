package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.model.EventHasAdditionalattributevalueId;

public class EventHasAdditionalattributevalueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477127386241401089L;
	
	private EventHasAdditionalattributevalueIdDTO id;
	private EventDTO event;
	private AttributeDTO attribute;
	private Double numeric;
	private String textual;
	private Boolean boolean_;
	private Date datetime;
	
	/**
	 * Default Constructor
	 */
	public EventHasAdditionalattributevalueDTO(){
		
	}

	public EventHasAdditionalattributevalueDTO(
			EventHasAdditionalattributevalueIdDTO id, EventDTO event,
			AttributeDTO attribute, Double numeric, String textual,
			Boolean boolean_, Date datetime) {
		super();
		this.id = id;
		this.event = event;
		this.attribute = attribute;
		this.numeric = numeric;
		this.textual = textual;
		this.boolean_ = boolean_;
		this.datetime = datetime;
	}

	public EventHasAdditionalattributevalueIdDTO getId() {
		return id;
	}

	public void setId(EventHasAdditionalattributevalueIdDTO id) {
		this.id = id;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public Double getNumeric() {
		return numeric;
	}

	public void setNumeric(Double numeric) {
		this.numeric = numeric;
	}

	public String getTextual() {
		return textual;
	}

	public void setTextual(String textual) {
		this.textual = textual;
	}

	public Boolean getBoolean_() {
		return boolean_;
	}

	public void setBoolean_(Boolean boolean_) {
		this.boolean_ = boolean_;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
