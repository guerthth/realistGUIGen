package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

public class EventHasAdditionalattributevalueIdDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4300661660049783114L;
	private int eventId;
	private String attributeId;
	
	public EventHasAdditionalattributevalueIdDTO(){
		
	}

	public EventHasAdditionalattributevalueIdDTO(int eventId, String attributeId) {
		super();
		this.eventId = eventId;
		this.attributeId = attributeId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	
	
}
