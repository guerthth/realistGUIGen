package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class DualitytypeDAOTest {

	private static DualitytypeDAO dualitytypehandler;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		dualitytypehandler = (DualitytypeDAO) SpringUtil.context.getBean("dualitytypedao");	
	}
	
	/**
	 * Testing if the getDualitytypeList works
	 * The result of the call should not be null
	 * Since several database setups are tested no explicit
	 * values are evaluated
	 */
	@Test
	public void testGetDualitytypeList() {
		
		assertNotNull(dualitytypehandler.getDualitytypeList());
	}

}
