package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Eventtypestockflow;

public class EventtypeStockflowDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8050674882627638495L;
	
	private String eventtypeId;
	private String resourcetypeId;
	private Boolean isSeries;
	private Boolean isIdentifiable;
	private Set<AttributeDTO> eventtypeStockflowAttributes;
	
	/**
	 * Default Constructor
	 */
	public EventtypeStockflowDTO(){
		
	}
	
	/**
	 * Constructor convertiing a Eventtypestockflow to a EventtypestockflowDTO
	 * @param stockflow
	 */
	public EventtypeStockflowDTO(Eventtypestockflow stockflow){
		
		this.eventtypeId = stockflow.getEventtype().getId();
		this.resourcetypeId = stockflow.getResourcetype().getId();
		this.isSeries = stockflow.getIsSeries();
		this.isIdentifiable = stockflow.getIsIdentifiable();
	}

	public String getEventtypeId() {
		return eventtypeId;
	}

	public void setEventtypeId(String eventtypeId) {
		this.eventtypeId = eventtypeId;
	}

	public String getResourcetypeId() {
		return resourcetypeId;
	}

	public void setResourcetypeId(String resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public Boolean getIsSeries() {
		return isSeries;
	}

	public void setIsSeries(Boolean isSeries) {
		this.isSeries = isSeries;
	}

	public Boolean getIsIdentifiable() {
		return isIdentifiable;
	}

	public void setIsIdentifiable(Boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	public Set<AttributeDTO> getEventtypeStockflowAttributes() {
		return eventtypeStockflowAttributes;
	}

	public void setEventtypeStockflowAttributes(
			Set<AttributeDTO> eventtypeStockflowAttributes) {
		this.eventtypeStockflowAttributes = eventtypeStockflowAttributes;
	}
	
	
}
