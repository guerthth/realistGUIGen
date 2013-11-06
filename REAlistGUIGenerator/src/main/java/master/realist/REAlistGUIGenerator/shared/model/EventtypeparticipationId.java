package master.realist.REAlistGUIGenerator.shared.model;

// Generated Nov 5, 2013 2:01:09 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventtypeparticipationId generated by hbm2java
 */
@Embeddable
public class EventtypeparticipationId implements java.io.Serializable {

	private String eventTypeId;
	private String agentTypeId;

	public EventtypeparticipationId() {
	}

	public EventtypeparticipationId(String eventTypeId, String agentTypeId) {
		this.eventTypeId = eventTypeId;
		this.agentTypeId = agentTypeId;
	}

	@Column(name = "EventType_Id", nullable = false, length = 150)
	public String getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	@Column(name = "AgentType_Id", nullable = false, length = 150)
	public String getAgentTypeId() {
		return this.agentTypeId;
	}

	public void setAgentTypeId(String agentTypeId) {
		this.agentTypeId = agentTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventtypeparticipationId))
			return false;
		EventtypeparticipationId castOther = (EventtypeparticipationId) other;

		return ((this.getEventTypeId() == castOther.getEventTypeId()) || (this
				.getEventTypeId() != null && castOther.getEventTypeId() != null && this
				.getEventTypeId().equals(castOther.getEventTypeId())))
				&& ((this.getAgentTypeId() == castOther.getAgentTypeId()) || (this
						.getAgentTypeId() != null
						&& castOther.getAgentTypeId() != null && this
						.getAgentTypeId().equals(castOther.getAgentTypeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEventTypeId() == null ? 0 : this.getEventTypeId()
						.hashCode());
		result = 37
				* result
				+ (getAgentTypeId() == null ? 0 : this.getAgentTypeId()
						.hashCode());
		return result;
	}

}
