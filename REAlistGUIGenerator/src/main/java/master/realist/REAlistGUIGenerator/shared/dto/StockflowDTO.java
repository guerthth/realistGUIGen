package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Event;
import master.realist.REAlistGUIGenerator.shared.model.Stockflow;

public class StockflowDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4019870846373189038L;

	private int id;
	private ResourceDTO resource;
	private Set<EventDTO> events;
	private double pricePerUnit;
	private double quantity;
	private Set<StockflowHasAdditionalattributevalueDTO> additionalAttributeValues;
	
	/**
	 * Default Constructor
	 */
	public StockflowDTO(){
		
	}
	
	/**
	 * Constructor converting Stockflow to StockflowDTO
	 * @param stockflow
	 */
	public StockflowDTO(Stockflow stockflow){
		
		this.id = stockflow.getId();
		this.resource = new ResourceDTO(stockflow.getResource());
		
		// add eventDTOs to stockflowDTO
		if(stockflow.getEvents() != null){
			this.events = new LinkedHashSet<EventDTO>(stockflow.getEvents().size());
			for(Event event : stockflow.getEvents()){
				this.events.add(new EventDTO(event));
			}
		}
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResourceDTO getResource() {
		return resource;
	}

	public void setResource(ResourceDTO resource) {
		this.resource = resource;
	}

	public Set<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(Set<EventDTO> events) {
		this.events = events;
	}

	public Set<StockflowHasAdditionalattributevalueDTO> getAdditionalAttributeValues() {
		return additionalAttributeValues;
	}

	public void setAdditionalAttributeValues(
			Set<StockflowHasAdditionalattributevalueDTO> additionalAttributeValues) {
		this.additionalAttributeValues = additionalAttributeValues;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	
}
