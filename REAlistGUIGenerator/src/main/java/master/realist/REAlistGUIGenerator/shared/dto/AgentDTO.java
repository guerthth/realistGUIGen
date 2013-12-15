package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Agenttype;


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
	private Set<AgenttypeDTO> agenttypes;
	private Set<AgentHasAdditionalattributevalueDTO> additionalAttributeValues;
	
	/**
	 * Default Constructor
	 */
	public AgentDTO(){
		
	}
	
	public AgentDTO (Agent agent){
		this.id = agent.getId();
		this.name = agent.getName();
		
		// add agenttypeDTOs to agentDTO
		if(agent.getAgenttypes() != null){
			this.agenttypes = new LinkedHashSet<AgenttypeDTO>(agent.getAgenttypes().size());
			for(Agenttype agenttype : agent.getAgenttypes()){
				agenttypes.add(new AgenttypeDTO(agenttype));
			}
		}
		
		this.additionalAttributeValues = new LinkedHashSet<AgentHasAdditionalattributevalueDTO>();
	
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
	 * Setter for AgentDTO Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for AgentDTO name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for AgentDTO name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for AgentDTO agenttypes
	 * @return agenttypes
	 */
	public Set<AgenttypeDTO> getAgenttypes() {
		return agenttypes;
	}

	/**
	 * Setter for AgentDTO agenttypes
	 * @param agenttypes
	 */
	public void setAgenttypes(Set<AgenttypeDTO> agenttypes) {
		this.agenttypes = agenttypes;
	}

	/**
	 * Getter for AgentDTO additionalattributevalues
	 * @return additionalAttributeValues
	 */
	public Set<AgentHasAdditionalattributevalueDTO> getAdditionalAttributeValues() {
		return additionalAttributeValues;
	}

	/**
	 * Setter for AgentDTO additionalattributevalues
	 * @param additionalAttributeValues
	 */
	public void setAdditionalAttributeValues(
			Set<AgentHasAdditionalattributevalueDTO> additionalAttributeValues) {
		this.additionalAttributeValues = additionalAttributeValues;
	}

}
