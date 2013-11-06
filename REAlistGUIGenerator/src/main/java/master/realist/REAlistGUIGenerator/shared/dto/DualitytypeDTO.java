package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Eventtype;

public class DualitytypeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3062875885108846970L;
	
	private String id;
	private String name;
	private boolean isConversion;
	private Set<EventtypeDTO> eventtypes;

	public DualitytypeDTO() {
		
	}
	
	public DualitytypeDTO(String id, String name, boolean isConversion, Set<EventtypeDTO> eventtypes){
		
		this.id = id;
		this.name = name;
		this.isConversion = isConversion;
		this.eventtypes = eventtypes;
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

	public boolean isConversion() {
		return isConversion;
	}

	public void setConversion(boolean isConversion) {
		this.isConversion = isConversion;
	}
	
	public Set<EventtypeDTO> getEventtypes() {
		return eventtypes;
	}

	public void setEventtypes(Set<EventtypeDTO> eventtypes) {
		this.eventtypes = eventtypes;
	}
	
	
}
