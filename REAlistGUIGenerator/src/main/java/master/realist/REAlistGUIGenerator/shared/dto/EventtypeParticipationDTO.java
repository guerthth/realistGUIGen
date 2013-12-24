package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Eventtypeparticipation;

/**
 * Data Transfer Object for EventtypeParticipations
 * @author Thomas
 *
 */
public class EventtypeParticipationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6662591559947446651L;

	private String eventtypeId;
	private String agenttypeId;
	private Boolean isSeries;
	private Boolean isIdentifiable;
	private Set<AttributeDTO> eventtypeParticipationAttributes;
	
	/**
	 * Default Constructor
	 */
	public EventtypeParticipationDTO(){
		
	}
	
	/**
	 * Constructor converting a Participation to a ParticipationDTO
	 * @param participation
	 */
	public EventtypeParticipationDTO(Eventtypeparticipation participation){
		
		this.eventtypeId = participation.getEventtype().getId();
		this.agenttypeId = participation.getAgenttype().getId();
		this.isSeries = participation.getIsSeries();
		this.isIdentifiable = participation.getIsIdentifiable();
	}

	/**
	 * eventtypeId Getter
	 * @return eventtypeId
	 */
	public String getEventtypeId() {
		return eventtypeId;
	}

	/**
	 * eventtypeId Setter
	 * @param eventtypeId
	 */
	public void setEventtypeId(String eventtypeId) {
		this.eventtypeId = eventtypeId;
	}

	/**
	 * agenttypeId Getter
	 * @return agenttypeId
	 */
	public String getAgenttypeId() {
		return agenttypeId;
	}

	/**
	 * agenttypeId Setter
	 * @param agenttypeId
	 */
	public void setAgenttypeId(String agenttypeId) {
		this.agenttypeId = agenttypeId;
	}

	/**
	 * isSeries Getter
	 * @return isSeries
	 */
	public Boolean getIsSeries() {
		return isSeries;
	}

	/**
	 * isSeries Setter
	 * @param isSeries
	 */
	public void setIsSeries(Boolean isSeries) {
		this.isSeries = isSeries;
	}

	/**
	 * isIdentifiable Getter
	 * @return isIdentifiable
	 */
	public Boolean getIsIdentifiable() {
		return isIdentifiable;
	}

	/**
	 * isIdentifiable Setter
	 * @param isIdentifiable
	 */
	public void setIsIdentifiable(Boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}

	/**
	 * eventtypeParticipationAttributes Getter
	 * @return eventtypeParticipationAttributes
	 */
	public Set<AttributeDTO> getEventtypeParticipationAttributes() {
		return eventtypeParticipationAttributes;
	}

	/**
	 * eventtypeParticipationAttributes Setter
	 * @param eventtypeParticipationAttributes
	 */
	public void setEventtypeParticipationAttributes(
			Set<AttributeDTO> eventtypeParticipationAttributes) {
		this.eventtypeParticipationAttributes = eventtypeParticipationAttributes;
	}
	
	
	
}
