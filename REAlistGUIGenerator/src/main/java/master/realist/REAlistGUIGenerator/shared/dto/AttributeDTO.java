package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

import master.realist.REAlistGUIGenerator.shared.model.AgenttypeHasAdditionalattribute;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;

public class AttributeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5238857491890776326L;
	
	private String id;
	private String name;
	private String datatype;
	
	/**
	 * Constructor converting an Attribute object to an AttributeDTO object
	 * @param attribute
	 */
	public AttributeDTO(Attribute attribute){
		this.id = attribute.getId();
		this.name = attribute.getName();
		this.datatype = attribute.getDatatype();
	}
	
	/**
	 * Constructor converting a AgenttypeHasAdditionalattribute object to a AttributeDTO object
	 * @param athaa
	 */
	public AttributeDTO(AgenttypeHasAdditionalattribute athaa){
		this.id = athaa.getAttribute().getId();
		this.name = athaa.getAttribute().getName();
		this.datatype = athaa.getAttribute().getDatatype();
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
	
	/**
	 * Default Constructor
	 */
	public AttributeDTO(){
		
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
