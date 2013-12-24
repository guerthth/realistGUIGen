package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgentHasAdditionalattributevalueDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.AgentHasAdditionalattributevalue;
import master.realist.REAlistGUIGenerator.shared.model.Agenttype;
import master.realist.REAlistGUIGenerator.shared.model.Attribute;

/**
 * DAO for Agent table of the REA DB
 * @author Thomas
 *
 */
public class AgentDAO {

	private HibernateUtil hibernateUtil;

	/**
	 * Getting all existing agents entries in the REA DB
	 * @return agentDTOlist list of all existing agents in REA DB
	 */
	@SuppressWarnings("unchecked")
	public List<AgentDTO> getAgentList(){
		
		Session session = null;
		List<Agent> existingAgents = null;
		List<AgentDTO> agentDTOlist = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingAgents = new ArrayList<Agent>(session.createQuery("from Agent").list());
			
			if(existingAgents != null){
				agentDTOlist = new ArrayList<AgentDTO>();
				
				for(Agent agent : existingAgents){
					
					Hibernate.initialize(agent.getAgenttypes());
					Hibernate.initialize(agent.getAgentHasAdditionalattributevalues());
					
					// adding the created agentDTOs to the List that is returned
					agentDTOlist.add(createAgentDTO(agent));
				}
			}
			
			session.getTransaction().commit();
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}	
		}
		
		return agentDTOlist;
	}
	
	
	
	/**
	 * Saving an agentDTO object as agentobject to the REA DB
	 * @param agentDTO object that should be stored in REA DB
	 * @return agentId of inserted object
	 */
	public int saveAgent(AgentDTO agentDTO){
		
		Session session = null;
		Agent agent = new Agent(agentDTO);
		int agentId = 0;
		
		Set<AgentHasAdditionalattributevalueDTO> additionalAttributeValues = agentDTO.getAdditionalAttributeValues();
		
		Attribute additionalAttribute;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			// if agent has additional attributes
			if(additionalAttributeValues != null){
																						
				//Hibernate.initialize(agent.getAgentHasAdditionalattributevalues());
																						
				for(AgentHasAdditionalattributevalueDTO dto : additionalAttributeValues){
																							
					// Retrieve the already stored attribute object
					additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());					
																				
					// add the additional attribute values to the agent objects list
					agent.getAgentHasAdditionalattributevalues().add(createAdditionalAttributeValue(agent,additionalAttribute,dto));
											
				}
			}
			
			// save the agent
			session.save(agent);	
				
			agentId = agent.getId();
			session.getTransaction().commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
			
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return agentId;
	}
	
	
	/**
	 * Deleting an agent object from the REA DB 
	 * @param id of the agent object that should be deleted
	 */
	public void deleteAgent(int id){
		
		Session session = null;
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Agent deleteAgent = (Agent) session.get(Agent.class, id);
			session.delete(deleteAgent);
			session.getTransaction().commit();
			
		} catch(Exception e){
			
			e.printStackTrace();
			if(session != null){session.getTransaction().rollback();}
		} finally {
			if(session != null){
				session.close();
			}
		}
	}
	
	/**
	 * Updating an existing AgentDTO object
	 * @param updatedAgentDTO updated object that should be stored in the REA DB
	 * @return updated agentDTO
	 */
	public AgentDTO updateAgent(AgentDTO agentDTO){
		
		Session session = null;
		AgentDTO updatedAgentDTO = null;
		
		Set<AgentHasAdditionalattributevalueDTO> additionalAttributeValues = agentDTO.getAdditionalAttributeValues();

		Attribute additionalAttribute;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Agent updateAgent = (Agent) session.get(Agent.class, agentDTO.getId());
			updateAgent.setName(agentDTO.getName());
			updateAgent.setAgenttypes(createAgenttypeDTOSet(agentDTO.getAgenttypes()));	
			
			// if agent has additional attributes
			if(additionalAttributeValues != null){
				
				Set<AgentHasAdditionalattributevalue> updatedAdditionalAttributeValues = 
						new LinkedHashSet<AgentHasAdditionalattributevalue>(additionalAttributeValues.size());
																			
				for(AgentHasAdditionalattributevalueDTO dto : additionalAttributeValues){
																				
					// Retrieve the already stored attribute object
					additionalAttribute = (Attribute) session.get(Attribute.class, dto.getAttribute().getId());					
					
					// add the updated additional attribute values to the agent objects list
					updatedAdditionalAttributeValues.add(createAdditionalAttributeValue(updateAgent,additionalAttribute,dto));
								
				}
				
				// set the updated additional attribute values set for the updateAgent object
				updateAgent.setAgentHasAdditionalattributevalues(updatedAdditionalAttributeValues);
			}
			
			session.update(updateAgent);
			session.getTransaction().commit();
			updatedAgentDTO = agentDTO;
			
		} catch(Exception e){
			e.printStackTrace();
			if(session != null){ session.getTransaction().rollback();}
		} finally {
			if(session != null){ session.close();}
		}
		
		return updatedAgentDTO;
	}
	
	/**
	 * Method transforming a agent object retrieved from the REA DB 
	 * to a agentDTO object
	 * @param agent
	 * @return agentDTO object
	 */
	private AgentDTO createAgentDTO(Agent agent){
		
		Set<AgentHasAdditionalattributevalue> additionalAttributes = 
				new LinkedHashSet<AgentHasAdditionalattributevalue>(agent.getAgentHasAdditionalattributevalues().size());
		additionalAttributes = agent.getAgentHasAdditionalattributevalues();	
		Set<AgentHasAdditionalattributevalueDTO> additionalAttributeDTOs = 
				new LinkedHashSet<AgentHasAdditionalattributevalueDTO>(additionalAttributes != null ? additionalAttributes.size() : 0);
		
		// create AgentDTO object
		AgentDTO createdAgentDTO = new AgentDTO(agent);
		
		// add additional attribute DTOs to agentDTO
		for(AgentHasAdditionalattributevalue additionalAttribute : additionalAttributes){
			
			AgentHasAdditionalattributevalueDTO additionalAttributeDTO = new AgentHasAdditionalattributevalueDTO(additionalAttribute);
			additionalAttributeDTO.setAgent(createdAgentDTO);

			additionalAttributeDTOs.add(additionalAttributeDTO);
		}
		
		createdAgentDTO.setAdditionalAttributeValues(additionalAttributeDTOs);
		
		return createdAgentDTO;
		
	}	
	
	/**
	 * Method creating an AgentHasAdditionalattributevalue for a specific AgentHasAdditionalattributevalueDTO, 
	 * agent, and attribute
	 * @param agent Agent object
	 * @param additionalAttribute Attribute object
	 * @param dto AgentHasAdditionalattributevalueDTO containing textual, numeric, date, or boolean values
	 * @return additionalAgentAttributeValue 
	 */
	private AgentHasAdditionalattributevalue createAdditionalAttributeValue(Agent agent, Attribute additionalAttribute, AgentHasAdditionalattributevalueDTO dto){

		// create an AgentHasAdditionalattributevalue object and set its values	
		AgentHasAdditionalattributevalue additionalAgentAttributeValue = null;
		
		additionalAgentAttributeValue = new AgentHasAdditionalattributevalue();
		additionalAgentAttributeValue.setAgent(agent);
		additionalAgentAttributeValue.setAttribute(additionalAttribute);
		additionalAgentAttributeValue.setBooleanValue(dto.getBooleanValue());
		additionalAgentAttributeValue.setDatetimeValue(dto.getDatetimeValue());
		additionalAgentAttributeValue.setNumericValue(dto.getNumericValue());
		additionalAgentAttributeValue.setTextualValue(dto.getTextualValue());
		
		return additionalAgentAttributeValue;
	}
	
	/**
	 * Method transforming a agenttypeDTO set to a agenttype set
	 * @param agenttypeDTOSet agenttypeDTO Set
	 * @return created Agenttype Set
	 */
	private Set<Agenttype> createAgenttypeDTOSet(Set<AgenttypeDTO> agenttypeDTOSet){
		
		// TODO: the REA DB is capable of representing agents with more than one agenttype
		// for this prototype it is assumed that each agent has only one agenttype
		Set<Agenttype> createdAgenttypeSet = new LinkedHashSet<Agenttype>(1);
		createdAgenttypeSet.add(new Agenttype(agenttypeDTOSet.iterator().next()));
		return createdAgenttypeSet;
	}

	
	/**
	 * Getter for hibernatenateUtil object
	 * @return hibernateUtil
	 */
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	/**
	 * Setter for hibernateutil object
	 * @param hibernateUtil
	 */
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
}
