package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.model.AgentHasAdditionalattributevalue;

/**
 * data Transfer Object for AgentHasAdditionalattributevalue
 * @author Thomas
 *
 */
public class AgentHasAdditionalattributevalueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2448011731312295150L;
	
	private AttributeDTO attribute;
	private AgentDTO agent;
	private Double numericValue;
	private String textualValue;
	private Boolean booleanValue;
	private Date datetimeValue;
	
	/**
	 * Default Constructor
	 */
	public AgentHasAdditionalattributevalueDTO(){
		
	}
	
	/**
	 * Constructor transforming an AgentHasAdditionalattributevalue object to an AgentHasAdditionalattributevalueDTO
	 * (without setting agents)
	 * @param additionalAttributeValue
	 */
	public AgentHasAdditionalattributevalueDTO(AgentHasAdditionalattributevalue additionalAttributeValue){
		this.attribute = new AttributeDTO(additionalAttributeValue.getAttribute());
		this.numericValue = additionalAttributeValue.getNumericValue();
		this.textualValue = additionalAttributeValue.getTextualValue();
		this.booleanValue = additionalAttributeValue.getBooleanValue();
		this.datetimeValue = additionalAttributeValue.getDatetimeValue();
	}

	/**
	 * Constructor
	 * @param id
	 * @param attribute
	 * @param agent
	 * @param numeric
	 * @param textual
	 * @param boolean_
	 * @param datetime
	 */
	public AgentHasAdditionalattributevalueDTO(
			AttributeDTO attribute,
			AgentDTO agent, Double numeric, String textual, Boolean boolean_,
			Date datetime) {
		super();
		this.attribute = attribute;
		this.agent = agent;
		this.numericValue = numeric;
		this.textualValue = textual;
		this.booleanValue = boolean_;
		this.datetimeValue = datetime;
	}


	/**
	 * Getter for attribute of AgentHasAdditionalattributevalue
	 * @return attribute of AgentHasAdditionalattributevalue
	 */
	public AttributeDTO getAttribute() {
		return attribute;
	}

	/**
	 * Setter for attribute of AgentHasAdditionalattributevalue
	 * @param attribute for AgentHasAdditionalattributevalue
	 */
	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	/**
	 * Getter for agent of AgentHasAdditionalattributevalue
	 * @return agent of AgentHasAdditionalattributevalue
	 */
	public AgentDTO getAgent() {
		return agent;
	}

	/**
	 * Setter for agent of AgentHasAdditionalattributevalue
	 * @param agent of AgentHasAdditionalattributevalue
	 */
	public void setAgent(AgentDTO agent) {
		this.agent = agent;
	}

	/**
	 * Getter for numeric value of AgentHasAdditionalattributevalue
	 * @return numeric value of AgentHasAdditionalattributevalue
	 */
	public Double getNumericValue() {
		return numericValue;
	}

	/**
	 * Setter for numeric value of AgentHasAdditionalattributevalue
	 * @param numeric numeric value of AgentHasAdditionalattributevalue
	 */
	public void setNumericValue(Double numeric) {
		this.numericValue = numeric;
	}

	/**
	 * Getter for textual value of AgentHasAdditionalattributevalue
	 * @return textual value of AgentHasAdditionalattributevalue
	 */
	public String getTextualValue() {
		return textualValue;
	}

	/**
	 * Setter for textual value of AgentHasAdditionalattributevalue
	 * @param textual textual value of AgentHasAdditionalattributevalue
	 */
	public void setTextualValue(String textual) {
		this.textualValue = textual;
	}

	/**
	 * Getter for boolean value of AgentHasAdditionalattributevalue
	 * @return boolean value of AgentHasAdditionalattributevalue
	 */
	public Boolean getBooleanValue() {
		return booleanValue;
	}

	/**
	 * Setter for boolean value of AgentHasAdditionalattributevalue
	 * @param boolean_ boolean value of AgentHasAdditionalattributevalue
	 */
	public void setBooleanValue(Boolean boolean_) {
		this.booleanValue = boolean_;
	}

	/**
	 * Getter for datetime value of AgentHasAdditionalattributevalue
	 * @return datetime value of AgentHasAdditionalattributevalue
	 */
	public Date getDatetimeValue() {
		return datetimeValue;
	}

	/**
	 * Setter for datetime value of AgentHasAdditionalattributevalue
	 * @param datetime datetime value of AgentHasAdditionalattributevalue
	 */
	public void setDatetimeValue(Date datetime) {
		this.datetimeValue = datetime;
	}

}
