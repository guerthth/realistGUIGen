package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;

import master.realist.REAlistGUIGenerator.shared.model.Agenttype;

/**
 * Data Transfer Object for Agenttype
 * @author Thomas
 *
 */
public class AgenttypeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6380946815737402428L;
	
	private String id;
	private AgenttypeDTO parentAgenttypeId;
	private String name;
	private boolean isExternal;
	
	/**
	 * Default Constructor
	 */
	public AgenttypeDTO(){
		
	}
	
	/**
	 * Constructor
	 * @param agenttype object that is converted to an agenttypeDTO
	 */
	public AgenttypeDTO(Agenttype agenttype){
		
		this.id = agenttype.getId();
		this.name = agenttype.getName();
		this.isExternal = agenttype.getIsExternal();
		
		if(agenttype.getAgenttype() != null){
			this.parentAgenttypeId = new AgenttypeDTO(agenttype.getAgenttype());
		}
	}

	/**
	 * Getter for AgenttypeDTO Id
	 * @return id of the agenttypeDTO object
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for AgenttypeDTO Id
	 * @param id of the agenttypeDTO object
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for AgenttypeDTO parentagenttypeId
	 * @return parentAgenttypeId object
	 */
	public AgenttypeDTO getParentAgenttypeId() {
		return parentAgenttypeId;
	}

	/**
	 * Setter for AgenttypeDTO parentagenttypeId
	 * @param parentAgenttypeId object
	 */
	public void setParentAgenttypeId(AgenttypeDTO parentAgenttypeId) {
		this.parentAgenttypeId = parentAgenttypeId;
	}

	/**
	 * Getter for AgenttypeDTO name
	 * @return name of the agenttypeDTO object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for AgenttypeDTO name
	 * @param name of the agenttypeDTO object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for AgenttypeDTO isExternal flag
	 * @return isExternal flag of the agenttypeDTO object
	 */
	public boolean isExternal() {
		return isExternal;
	}

	/**
	 * Setter for AgenttypeDTO isExternal flag
	 * @param isExternal flag of the agenttypeDTO object
	 */
	public void setExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}
	
}
