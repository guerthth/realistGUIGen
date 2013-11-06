package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

public class DualitytypeDTO implements Serializable{
	
	private String id;
	private String name;
	private boolean isConversion;
	
	public DualitytypeDTO() {
		
	}
	
	public DualitytypeDTO(String id, String name, boolean isConversion){
		
		this.id = id;
		this.name = name;
		this.isConversion = isConversion;
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
	
	
}
