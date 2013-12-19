package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Resourcetype;

/**
 * Data Transfer Object for Resourcetype
 * @author Thomas
 *
 */
public class ResourcetypeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213461384976284268L;
	
	
	private String id; 
	private ResourcetypeDTO parentResourcetype;
	private String name;
	private boolean isBulk;
	private boolean isIdentifiable;
	private String unitOfMeasure;
	private Set<AttributeDTO> attributes;
	
	
	/**
	 * Default Constructor
	 */
	public ResourcetypeDTO(){
		
	}
	
	/**
	 * Constructor
	 * @param resourcetype object that is converted to a resourcetypeDTO
	 */
	public ResourcetypeDTO (Resourcetype resourcetype){
		
		// set simple attributes
		this.id = resourcetype.getId();
		this.name = resourcetype.getName();
		this.isBulk = resourcetype.getIsBulk();
		this.isIdentifiable = resourcetype.getIsIdentifiable();
		
		// set parent resourcetype if existing
		if(resourcetype.getResourcetype() != null){
			this.parentResourcetype = new ResourcetypeDTO(resourcetype.getResourcetype());
		}
	}

	/**
	 * Getter for ResourcetypeDTO Id
	 * @return id of the resourcetypeDTO object
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter for ResourcetypeDTO Id
	 * @param id of the resourcetypeDTO object
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Getter for ResourcetypeDTO parentresourcetype
	 * @return parentresourcetype of the resourcetypeDTO object
	 */
	public ResourcetypeDTO getParentResourcetype() {
		return parentResourcetype;
	}
	
	/**
	 * Setter for ResourcetypeDTO parentResourcetype
	 * @param parentResourcetype of the resourcetypeDTO object
	 */
	public void setParentResourcetype(ResourcetypeDTO parentResourcetype) {
		this.parentResourcetype = parentResourcetype;
	}

	/**
	 * Getter for ResourcetypeDTO name
	 * @return name of the resourcetypeDTO object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for ResourcetypeDTO name
	 * @param name of the resourcetypeDTO object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for ResourcetypeDTO isBulk flag
	 * @return ResourcetypeDTO isBulk flag
	 */
	public boolean isBulk() {
		return isBulk;
	}

	/**
	 * Setter for ResourcetypeDTO isBulk flag
	 * @param isBulk flag of ResourcetypeDTO
	 */
	public void setBulk(boolean isBulk) {
		this.isBulk = isBulk;
	}

	/**
	 * Getter for ResourcetypeDTO isIdentifiable flag
	 * @return ResourcetypeDTO isIdentifiable flag
	 */
	public boolean isIdentifiable() {
		return isIdentifiable;
	}

	/**
	 * Setter for ResourcetypeDTO isIdentifiable flag
	 * @param isIdentifiable flag of ResourcetypeDTO
	 */
	public void setIdentifiable(boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	/**
	 * Getter for ResourcetypeDTO unitOfMeasure
	 * @return unitOfMeasure of ResourcetypeDTO
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	/**
	 * Setter for ResourcetypeDTO unitOfMeasure
	 * @param unitOfMeasure of ResourcetypeDTO
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * Getter for ResourcetypeDTO attributesDTO set
	 * @return attributes of ResourcetypeDTO
	 */
	public Set<AttributeDTO> getAttributes() {
		return attributes;
	}

	/**
	 * Setter for ResourcetypeDTO attributesDTO set
	 * @param attributes of ResourcetypeDTO
	 */
	public void setAttributes(Set<AttributeDTO> attributes) {
		this.attributes = attributes;
	}
	
	
	
}
