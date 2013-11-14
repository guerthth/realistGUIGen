package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 12, 2013 8:47:49 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ReconciliationId generated by hbm2java
 */
@Embeddable
public class ReconciliationId implements java.io.Serializable {

	private int incEventId;
	private int decEventId;

	public ReconciliationId() {
	}

	public ReconciliationId(int incEventId, int decEventId) {
		this.incEventId = incEventId;
		this.decEventId = decEventId;
	}

	@Column(name = "IncEvent_Id", nullable = false)
	public int getIncEventId() {
		return this.incEventId;
	}

	public void setIncEventId(int incEventId) {
		this.incEventId = incEventId;
	}

	@Column(name = "DecEvent_Id", nullable = false)
	public int getDecEventId() {
		return this.decEventId;
	}

	public void setDecEventId(int decEventId) {
		this.decEventId = decEventId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReconciliationId))
			return false;
		ReconciliationId castOther = (ReconciliationId) other;

		return (this.getIncEventId() == castOther.getIncEventId())
				&& (this.getDecEventId() == castOther.getDecEventId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIncEventId();
		result = 37 * result + this.getDecEventId();
		return result;
	}

}
