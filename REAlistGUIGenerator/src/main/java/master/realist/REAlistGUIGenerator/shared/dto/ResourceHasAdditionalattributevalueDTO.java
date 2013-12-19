package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.model.ResourceHasAdditionalattributevalue;

public class ResourceHasAdditionalattributevalueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4817173595505309254L;

	private ResourceDTO resource;
	private AttributeDTO attribute;
	private Double numericValue;
	private String textualValue;
	private Boolean booleanValue;
	private Date datetimeValue;
	
	/**
	 * Default Constructor
	 */
	public ResourceHasAdditionalattributevalueDTO() {
		
	}
	
	/**
	 * Constructor transforming a ResourceHasAdditionalattributevalue object to an ResourceHasAdditionalattributevalueDTO
	 * (without setting resources)
	 * @param additionalAttributeValue
	 */
	public ResourceHasAdditionalattributevalueDTO(ResourceHasAdditionalattributevalue additionalAttributeValue){
		this.attribute = new AttributeDTO(additionalAttributeValue.getAttribute());
		this.numericValue = additionalAttributeValue.getNumericValue();
		this.textualValue = additionalAttributeValue.getTextualValue();
		this.booleanValue = additionalAttributeValue.getBooleanValue();
		this.datetimeValue = additionalAttributeValue.getDatetimeValue();
	}

	public ResourceDTO getResource() {
		return resource;
	}

	public void setResource(ResourceDTO resource) {
		this.resource = resource;
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
