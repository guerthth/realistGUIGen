package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

public class EventtypeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623109853699657704L;
	
	
	private String id;
	// TODO implement eventtype hierarchies
	//private Eventtype eventtype;
	private String name;
	private boolean isIncrement;
	private boolean isResourceUsed;
	private boolean isExceptionEvent;
	private boolean isSeries;
	
	public EventtypeDTO(){
		
	}
	
	public EventtypeDTO(String id, String name, boolean isIncrement, boolean isResourceUsed, boolean isExceptionEvent, boolean isSeries){
		this.id = id;
		this.name = name; 
		this.isIncrement = isIncrement;
		this.isResourceUsed = isResourceUsed;
		this.isExceptionEvent = isExceptionEvent;
		this.isSeries = isSeries;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsIncrement() {
		return isIncrement;
	}

	public void setIsIncrement(Boolean isIncrement) {
		this.isIncrement = isIncrement;
	}

	public boolean isResourceUsed() {
		return isResourceUsed;
	}

	public void setResourceUsed(boolean isResourceUsed) {
		this.isResourceUsed = isResourceUsed;
	}

	public boolean isExceptionEvent() {
		return isExceptionEvent;
	}

	public void setExceptionEvent(boolean isExceptionEvent) {
		this.isExceptionEvent = isExceptionEvent;
	}

	public boolean isSeries() {
		return isSeries;
	}

	public void setSeries(boolean isSeries) {
		this.isSeries = isSeries;
	}
	
	
	
}
