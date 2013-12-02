package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Data Transfer Object for Agent
 * @author Thomas
 *
 */
public class AgentDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6588906273249563187L;
	
	private int id;
	private String name;
	private Set<AgenttypeDTO>agenttypes;
	
	/**
	 * Default Constructor
	 */
	public AgentDTO(){
		
	}

	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param agenttypes
	 */
	public AgentDTO(int id, String name, Set<AgenttypeDTO> agenttypes) {
		super();
		this.id = id;
		this.name = name;
		this.agenttypes = agenttypes;
	}

	/**
	 * Getter for AgentDTO Id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for DualityStatusDTO Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for DualityStatusDTO name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for DualityStatusDTO name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for DualityStatusDTO agenttypes
	 * @return agenttypes
	 */
	public Set<AgenttypeDTO> getAgenttypes() {
		return agenttypes;
	}

	/**
	 * Setter for DualityStatusDTO agenttypes
	 * @param agenttypes
	 */
	public void setAgenttypes(Set<AgenttypeDTO> agenttypes) {
		this.agenttypes = agenttypes;
	}
	
	
}
