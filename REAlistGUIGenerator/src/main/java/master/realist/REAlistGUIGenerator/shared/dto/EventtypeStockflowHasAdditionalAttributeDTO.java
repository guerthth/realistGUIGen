package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

import master.realist.REAlistGUIGenerator.shared.model.EventtypestockflowHasAdditionalattribute;

public class EventtypeStockflowHasAdditionalAttributeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584486607065809376L;

	private AttributeDTO attribute;
	private String resourcetypeId;
	private String eventtypeId;
	private boolean isOptional;
	private boolean isPolicyProperty;
	private boolean isReserveProperty;
	
	/**
	 * Default Constructor
	 */
	public EventtypeStockflowHasAdditionalAttributeDTO() {
		
	}
	
	/**
	 * Constructor converting EventtypestockflowHasAdditionalattribute to EventtypeStockflowHasAdditionalAttributeDTO
	 * @param eventtypestockflowhasadditionalattribute
	 */
	public EventtypeStockflowHasAdditionalAttributeDTO(EventtypestockflowHasAdditionalattribute eventtypestockflowhasadditionalattribute){
		
		this.attribute = new AttributeDTO(eventtypestockflowhasadditionalattribute.getAttribute());
		this.resourcetypeId = eventtypestockflowhasadditionalattribute.getId().getEventTypeStockflowResourceTypeId();
		this.eventtypeId = eventtypestockflowhasadditionalattribute.getId().getEventTypeStockflowEventTypeId();
		this.isOptional = eventtypestockflowhasadditionalattribute.isIsOptional();
		this.isPolicyProperty = eventtypestockflowhasadditionalattribute.isIsPolicyProperty();
		this.isReserveProperty = eventtypestockflowhasadditionalattribute.isIsReserveProperty();
	}

	// GETTER AND SETTERS
	
	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public String getResourcetypeId() {
		return resourcetypeId;
	}

	public void setResourcetypeId(String resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public String getEventtypeId() {
		return eventtypeId;
	}

	public void setEventtypeId(String eventtypeId) {
		this.eventtypeId = eventtypeId;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public boolean isPolicyProperty() {
		return isPolicyProperty;
	}

	public void setPolicyProperty(boolean isPolicyProperty) {
		this.isPolicyProperty = isPolicyProperty;
	}

	public boolean isReserveProperty() {
		return isReserveProperty;
	}

	public void setReserveProperty(boolean isReserveProperty) {
		this.isReserveProperty = isReserveProperty;
	}
	
	
	
	
}
