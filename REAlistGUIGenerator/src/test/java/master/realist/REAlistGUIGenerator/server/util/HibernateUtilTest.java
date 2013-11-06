package master.realist.REAlistGUIGenerator.server.util;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void test() {

		HibernateUtil hibernateUtil = (HibernateUtil) SpringUtil.context.getBean("hibernateutil");
		SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
		assertNotNull(sessionFactory);
		Session session = sessionFactory.openSession();
		assertNotNull(session);
	}

}
