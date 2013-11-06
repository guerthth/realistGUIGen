package master.realist.REAlistGUIGenerator.server.util;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void test() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		assertNotNull(sessionFactory);
		Session session = sessionFactory.openSession();
		assertNotNull(session);
	}

}
