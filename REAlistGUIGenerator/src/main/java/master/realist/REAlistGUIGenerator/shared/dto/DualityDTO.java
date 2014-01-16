package master.realist.REAlistGUIGenerator.shared.dto;

import java.io.Serializable;
import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.model.Duality;

public class DualityDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4319584924061824516L;
	
	private int id;
	private DualitytypeDTO dualitytype;
	private Date date;
	private DualityStatusDTO dualitystatus;
	// TODO: add eventDTOs
	
	
	/**
	 * Method creating a DualityDTO object from a Duality object
	 * @param duality
	 */
	public DualityDTO(Duality duality){
		
		this.id = duality.getId();
		this.dualitytype = new DualitytypeDTO(duality.getDualitytype());
		this.date = duality.getDate();
		this.dualitystatus = new DualityStatusDTO(duality.getDualitystatus());
	}
	
	
	/**
	 * Default Constructor
	 */
	public DualityDTO(){
		
	}
	

	/**
	 * Constructor
	 * @param id 
	 * @param parentdualitytype_id
	 * @param date
	 */
	public DualityDTO(int id, DualitytypeDTO dualitytype, Date date){
		
		this.id = id;
		this.dualitytype = dualitytype;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DualitytypeDTO getDualitytype() {
		return dualitytype;
	}

	public void setDualitytype(DualitytypeDTO dualitytype) {
		this.dualitytype = dualitytype;
	}
	
	public DualityStatusDTO getDualitystatus() {
		return dualitystatus;
	}

	public void setDualitystatus(DualityStatusDTO dualitystatus) {
		this.dualitystatus = dualitystatus;
	}
	
	
	
}
