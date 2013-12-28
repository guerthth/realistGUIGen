package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class DualitytypeDAOTest {

	private static DualitytypeDAO dualitytypehandler;
	private static DualitytypeDTO dualitytypeDTO;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		dualitytypehandler = (DualitytypeDAO) SpringUtil.context.getBean("dualitytypedao");
		
		// set up dualitytypeDTO
		dualitytypeDTO = new DualitytypeDTO();
		dualitytypeDTO.setConversion(false);
		dualitytypeDTO.setId("Test_Dualitytype");
		dualitytypeDTO.setName("Test_Dualitytype_Name");
	}
	
	/**
	 * Testing if the getDualitytypeList works
	 * The result of the call should not be null
	 * Since several database setups are tested no explicit
	 * values are evaluated
	 */
	@Test
	public void testGetDualitytypeList() {
		
		// get intitial number of dualitytypes in REA DB
		int initialsize = dualitytypehandler.getDualitytypeList().size();
		
		// save dualitytypeDTO
		String savedId = dualitytypehandler.saveDualityttype(dualitytypeDTO);
		
		// get new size
		int newsize = dualitytypehandler.getDualitytypeList().size();
		
		// assert that newsize = initialsite +1
		assertTrue(newsize == initialsize +1);
		
		// delete new
		dualitytypehandler.deleteDualitytype(savedId);
		
		// get newsize
		newsize = dualitytypehandler.getDualitytypeList().size();
		
		// assert that newsize = initialsize
		assertTrue(newsize == initialsize);
	
	}

}
