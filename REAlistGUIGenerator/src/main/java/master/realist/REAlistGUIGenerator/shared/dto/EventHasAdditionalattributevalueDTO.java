package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class EventHasAdditionalattributevalueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477127386241401089L;
	
	private EventDTO event;
	private AttributeDTO attribute;
	private Double numericValue;
	private String textualValue;
	private Boolean booleanValue;
	private Date datetimeValue;
	
	/**
	 * Default Constructor
	 */
	public EventHasAdditionalattributevalueDTO(){
		
	}

	
	// Getters and Setters
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

	public Double getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	public String getTextualValue() {
		return textualValue;
	}

	public void setTextualValue(String textualValue) {
		this.textualValue = textualValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public Date getDatetimeValue() {
		return datetimeValue;
	}

	public void setDatetimeValue(Date datetimeValue) {
		this.datetimeValue = datetimeValue;
	}


	

	

}
