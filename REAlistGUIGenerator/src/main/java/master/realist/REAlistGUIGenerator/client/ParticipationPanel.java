package master.realist.REAlistGUIGenerator.client;

import java.util.Map;

import master.realist.REAlistGUIGenerator.shared.dto.AttributeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventtypeParticipationDTO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel for all occurring Participations per event
 * @author Thomas
 *
 */
public class ParticipationPanel extends VerticalPanel{
	
	// eventtypeDTO object, the participation is created for
	private EventtypeDTO eventtypeDTO;
	
	// eventDTO object, the participation is created for
	private EventDTO eventdto;
	
	// Label for Paricipation Specification
	private Label participationSpecificationLabel = new Label("Participating agents:");
	
	// TabPanel for all existing (or new created, if series) participations
	private TabPanel participationTabPanel = new TabPanel();
	
	// map keeping track of eventtypes and their corresponding attributes + textboxes
	private Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeParticipationAttributeLabelMap;
	
	
	/**
	 * Default Constructor
	 */
	public ParticipationPanel(EventtypeDTO eventtypeDTO, EventDTO eventdto, Map<EventDTO,Map<AttributeDTO,CustomTextBox>> eventtypeParticipationAttributeLabelMap){
		
		// setting eventtypeDTO
		this.eventtypeDTO = eventtypeDTO;
		
		// setting eventDTO
		this.eventdto = eventdto;
		
		// setting eventtypeAttributeLabelMap
		this.eventtypeParticipationAttributeLabelMap = eventtypeParticipationAttributeLabelMap;
		
		// populating the ParticipationPanel
		populateParticipationPanel();
	}
	
	
	/**
	 * Method populating the ParticipationPanel for Events
	 */
	private void populateParticipationPanel(){
		
		// apply styles
		this.addStyleName("participationStockflowPanel");
		
		// apply styles for the participationSpecificationLabel
		participationSpecificationLabel.addStyleName("participationSpecificationLabel");
		participationSpecificationLabel.addStyleName("fullsizePanel");
		
		// add the participationSpecificationLabel to the ParticipationPanel
		this.add(participationSpecificationLabel);
		
		// apply styles for participationTabPanel
		participationTabPanel.addStyleName("fullsizePanel");
		
		// add a tab for each participation of the current eventtype
		for(EventtypeParticipationDTO etp : eventtypeDTO.getParticipations()){
			
			participationTabPanel.add(new ParticipationContentPanel(etp, eventdto, participationTabPanel, eventtypeParticipationAttributeLabelMap, eventtypeDTO),etp.getAgenttypeId());
		
		}
		
		// set selected tab to the first
		participationTabPanel.selectTab(0);
		
		// add the participationTabPanel to the ParticipationPanel
		this.add(participationTabPanel);
	}
}
