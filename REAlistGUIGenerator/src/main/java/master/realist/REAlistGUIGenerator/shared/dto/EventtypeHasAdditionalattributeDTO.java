package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

import master.realist.REAlistGUIGenerator.shared.model.Attribute;
import master.realist.REAlistGUIGenerator.shared.model.Eventtype;
import master.realist.REAlistGUIGenerator.shared.model.EventtypeHasAdditionalattributeId;

public class EventtypeHasAdditionalattributeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2656601128952754203L;
	
	private EventtypeHasAdditionalattributeId id;
	private EventtypeDTO eventtype;
	private AttributeDTO attribute;
	private boolean isOptional;
	private boolean isTypeProperty;
	private boolean isCommitmentProperty;
	
	public EventtypeHasAdditionalattributeDTO(){
		
	}
	
	public EventtypeHasAdditionalattributeDTO(EventtypeHasAdditionalattributeId id,EventtypeDTO eventtype,
			AttributeDTO attribute,boolean isOptional,boolean isTypeProperty,boolean isCommitmentProperty){
		
		this.id = id; 
		this.eventtype = eventtype;
		this.attribute = attribute;
		this.isOptional = isOptional;
		this.isTypeProperty = isTypeProperty;
		this.isCommitmentProperty = isCommitmentProperty;
	}

	public EventtypeHasAdditionalattributeId getId() {
		return id;
	}

	public void setId(EventtypeHasAdditionalattributeId id) {
		this.id = id;
	}

	public EventtypeDTO getEventtype() {
		return eventtype;
	}

	public void setEventtype(EventtypeDTO eventtype) {
		this.eventtype = eventtype;
	}

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public boolean isTypeProperty() {
		return isTypeProperty;
	}

	public void setTypeProperty(boolean isTypeProperty) {
		this.isTypeProperty = isTypeProperty;
	}

	public boolean isCommitmentProperty() {
		return isCommitmentProperty;
	}

	public void setCommitmentProperty(boolean isCommitmentProperty) {
		this.isCommitmentProperty = isCommitmentProperty;
	}
	
}
