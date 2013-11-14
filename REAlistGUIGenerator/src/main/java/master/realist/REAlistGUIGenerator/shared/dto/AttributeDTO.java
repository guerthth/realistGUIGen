package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

public class AttributeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5238857491890776326L;
	
	private String id;
	private String name;
	private String datatype;
	
	/**
	 * Default Constructor
	 */
	public AttributeDTO(){
		
	}
	
	/**
	 * Constructor
	 * @param id attribute id
	 * @param name attribute name
	 * @param datatype attribute datatype
	 */
	public AttributeDTO(String id, String name, String datatype){
		
		this.id = id;
		this.name = name;
		this.datatype = datatype;
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

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	
}
