package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import master.realist.REAlistGUIGenerator.shared.model.Dualitystatus;

/**
 * Data Transfer Object for DualityStatus
 * @author Thomas
 *
 */
public class DualityStatusDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093728550935264612L;
	
	private int id;
	private String status;
	private Set<DualityDTO> dualities = new HashSet<DualityDTO>(0);	
	
	/**
	 * Default Constructor
	 */
	public DualityStatusDTO(){
		
	}
	
	/**
	 * Constructor transforming a Dualitystatus from the REA DB to a DualityStatusDTO
	 * @param dualitystatus
	 */
	public DualityStatusDTO(Dualitystatus dualitystatus){
		this.id = dualitystatus.getId();
		this.status = dualitystatus.getStatus();
	}
	
	/**
	 * Constructor
	 * @param id
	 * @param status
	 * @param dualities
	 */
	public DualityStatusDTO(int id, String status, Set<DualityDTO> dualities){
		this.id = id;
		this.status = status;
		this.dualities = dualities;
	}

	/**
	 * Getter for DualityStatusDTO Id
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
	 * Getter for DualityStatusDTO status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter for DualityStatusDTO status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for DualityStatusDTO dualities
	 * @return dualities
	 */
	public Set<DualityDTO> getDualities() {
		return dualities;
	}
	
	/**
	 * Setter for DualityStatusDTO dualities
	 * @param dualities
	 */
	public void setDualities(Set<DualityDTO> dualities) {
		this.dualities = dualities;
	}
	
	
}
