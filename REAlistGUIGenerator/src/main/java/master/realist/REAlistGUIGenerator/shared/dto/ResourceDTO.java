package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Resource;

public class ResourceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7624403536301790743L;
	
	// simple attributes
	private int id;
	private String name;
	private boolean isBulk;
	private boolean isIdentifiable;
	private double qoH;
	private Boolean isComposed;
	
	// Resourcetype and Attributes
	private ResourcetypeDTO resourcetype;
	private Set<ResourceHasAdditionalattributevalueDTO> additionalAttributeValues;
	
	// TODO: tax, servicetype, labor, producttype, cashtype, policies, discountoraddition
	// are permitted in this work
	// private int taxClass;
	
	/**
	 * Constructor
	 */
	public ResourceDTO(){
		
	}
	
	/**
	 * Constructor creating an ResourceDTO from an existing Resource
	 * @param resource
	 */
	public ResourceDTO(Resource resource){
		this.id = resource.getId();
		this.name = resource.getName();
		this.isBulk = resource.getIsBulk();
		this.isIdentifiable = resource.getIsIdentifiable();
		this.qoH = resource.getQoH();
		this.isComposed = resource.getIsComposed();
		
		// add resourcetypeDTOs to resourceDTO
		this.resourcetype = new ResourcetypeDTO(resource.getResourcetype());
		
		// initialize additionalAttributes Set
		this.additionalAttributeValues = new LinkedHashSet<ResourceHasAdditionalattributevalueDTO>();
		
	}

	/**
	 * Getter for ResourceDTO Id
	 * @return id of ResourceDTO
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for ResourceDTO Id
	 * @param id of ResourceDTO
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for ResourceDTO name
	 * @return name of ResourceDTO
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for ResourceDTO name
	 * @param name of ResourceDTO
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for ResourceDTO isBulk flag
	 * @return isBulk
	 */
	public boolean isBulk() {
		return isBulk;
	}

	/**
	 * Setter for ResourceDTO isBulk flag
	 * @param isBulk
	 */
	public void setBulk(boolean isBulk) {
		this.isBulk = isBulk;
	}

	/**
	 * Getter for ResourceDTO isIdentifiable flag
	 * @return isIdentifiable
	 */
	public boolean isIdentifiable() {
		return isIdentifiable;
	}

	/**
	 * Setter for ResourceDTO isIdentifiable flag
	 * @param isIdentifiable
	 */
	public void setIdentifiable(boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	/**
	 * Getter for ResourceDTO Quantity on Hand
	 * @return QoH
	 */
	public double getQoH() {
		return qoH;
	}

	/**
	 * Setter for ResourceDTO Quantitiy on Hand
	 * @param qoH 
	 */
	public void setQoH(double qoH) {
		this.qoH = qoH;
	}

	/**
	 * Getter for ResourceDTO isComposed flag
	 * @return isComposed
	 */
	public Boolean getIsComposed() {
		return isComposed;
	}

	/**
	 * Getter for ResourceDTO isComposed flag
	 * @param isComposed
	 */
	public void setIsComposed(Boolean isComposed) {
		this.isComposed = isComposed;
	}

	/**
	 * Getter for ResourceDTO ResourcetypeDTO
	 * @return resourcetype
	 */
	public ResourcetypeDTO getResourcetype() {
		return resourcetype;
	}

	/**
	 * Setter for ResourceDTO ResourcetypeDTO
	 * @param resourcetype
	 */
	public void setResourcetype(ResourcetypeDTO resourcetype) {
		this.resourcetype = resourcetype;
	}

	/**
	 * Getter for ResourceDTO additionalAttributes Set
	 * @return additionalAttributes
	 */
	public Set<ResourceHasAdditionalattributevalueDTO> getAdditionalAttributeValues() {
		return additionalAttributeValues;
	}

	/**
	 * Setter for ResourceDTO additionalAttributes Set
	 * @param additionalAttributes
	 */
	public void setAdditionalAttributeValues(
			Set<ResourceHasAdditionalattributevalueDTO> additionalAttributeValues) {
		this.additionalAttributeValues = additionalAttributeValues;
	}
	
	
	
	
}
