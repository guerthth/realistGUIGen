package master.realist.REAlistGUIGenerator.server.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	
	/**
	 * Creating a SessionFactory if not existing and returning it
	 * @return
	 */
	public SessionFactory getSessionFactory()
    {
		try
		{			
			if(this.sessionFactory == null){
				
				Configuration configuration = new Configuration().configure();
	            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
		}catch(HibernateException he){
			System.err.println("Error creating Session: " + he);
            throw new ExceptionInInitializerError(he);
		}
		
        return sessionFactory;
    } 
}
