package master.realist.REAlistGUIGenerator.server.daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.AgentDTO;
import master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Agent;
import master.realist.REAlistGUIGenerator.shared.model.Agenttype;

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
		
		try{
			
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
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
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Agent updateAgent = (Agent) session.get(Agent.class, agentDTO.getId());
			updateAgent.setName(agentDTO.getName());
			updateAgent.setAgenttypes(createAgenttypeDTOSet(agentDTO.getAgenttypes()));
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
		
		Set<Agenttype> agenttypes = agent.getAgenttypes();
		Set<AgenttypeDTO> agenttypeDTOs = new HashSet<AgenttypeDTO>(agenttypes != null ? agenttypes.size() : 0);
		
		for(Agenttype agenttype : agenttypes){
			agenttypeDTOs.add(new AgenttypeDTO(agenttype));
		}
		
		return new AgentDTO(agent.getId(),agent.getName(),agenttypeDTOs);
	}	
	
	/**
	 * Method transforming a agenttypeDTO set to a agenttype set
	 * @param agenttypeDTOSet agenttypeDTO Set
	 * @return created Agenttype Set
	 */
	private Set<Agenttype> createAgenttypeDTOSet(Set<AgenttypeDTO> agenttypeDTOSet){
		
		// TODO: the REA DB is capable of representing agents with more than one agenttype
		// for this prototype it is assumed that each agent has only one agenttype
		Set<Agenttype> createdAgenttypeSet = new HashSet<Agenttype>(1);
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
