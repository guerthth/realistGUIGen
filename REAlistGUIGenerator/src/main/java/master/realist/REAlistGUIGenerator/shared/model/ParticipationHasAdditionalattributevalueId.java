package master.realist.REAlistGUIGenerator.shared.model;

// Generated Dec 18, 2013 3:42:47 PM by Hibernate Tools 4.0.0

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * ParticipationHasAdditionalattributevalueId generated by hbm2java
 */
@Embeddable
public class ParticipationHasAdditionalattributevalueId implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3530873644985478075L;
	private Participation participation;
	private Attribute attribute;
	
	/**
	 * Default Constructor
	 */
	public ParticipationHasAdditionalattributevalueId(){
		
	}
	
	public ParticipationHasAdditionalattributevalueId(Participation participation, Attribute attribute){
		this.participation = participation;
		this.attribute = attribute;
	}

	@ManyToOne
	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}

	@ManyToOne
	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        ParticipationHasAdditionalattributevalueId that = (ParticipationHasAdditionalattributevalueId) o;
 
        if (participation != null ? !participation.equals(that.participation) : that.participation != null) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (participation != null ? participation.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

}
