package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class DualityStatusDAOTest {
	
	private static DualityStatusDAO dualityStatushandler;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		dualityStatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");	
	}
	
	/**
	 * Testing if the testGetDualitytypeList method works
	 * The result of the call should be null
	 * Since several database setups are tested no explicit
	 * values are evaluated
	 */
	@Test
	public void testGetDualityStatusList() {
		
		// TODO: Testen für konkrete REA-DSL Modelle
		assertNotNull(dualityStatushandler.getDualityStatusList());
	}
	
	/**
	 * Testing if the saveDualityStatus method works correctly
	 * Two entries are inserted to the dualityStatus table
	 * Therefore number of all elements should be larger (by two) than before
	 */
	@Test
	public void testSaveDualityStatus(){
		
		int dualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		DualityStatusDTO ds = new DualityStatusDTO();
		ds.setStatus("status1");
		int firstId = dualityStatushandler.saveDualityStatus(ds);
		
		int newDualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		assertTrue(dualityStatusCount + 1 == newDualityStatusCount);
		
		DualityStatusDTO ds2 = new DualityStatusDTO();
		ds2.setStatus("status2");
		int secondId = dualityStatushandler.saveDualityStatus(ds2);
		
		newDualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		assertTrue(dualityStatusCount + 2 == newDualityStatusCount);
		
		// deleting the objects to reverse the REA DB changes
		dualityStatushandler.deleteDualityStatus(firstId);
		dualityStatushandler.deleteDualityStatus(secondId);
		
		newDualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		assertTrue(dualityStatusCount  == newDualityStatusCount);
		
	}
	
	/**
	 * Testing if the deleteDualityStatus functionality works as intended
	 * adding a new dualitystatus to the REA DB and removing it afterwards
	 */
	@Test
	public void testDeleteDualityStatus(){
		
		int dualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		DualityStatusDTO ds = new DualityStatusDTO();
		ds.setStatus("status1");
		int addedId = dualityStatushandler.saveDualityStatus(ds);
		
		int updatedStatusCount = dualityStatushandler.getDualityStatusList().size();
		assertTrue(updatedStatusCount == dualityStatusCount + 1);
		
		dualityStatushandler.deleteDualityStatus(addedId);
		updatedStatusCount = dualityStatushandler.getDualityStatusList().size();
		assertTrue(dualityStatusCount == updatedStatusCount);

	}
	
	/**
	 * Testin if the updateDualityStatus functionality works as intended
	 * adding new dualitystatus, updating it, and removing it afterwards
	 */
	@Test
	public void testUpdateDualityStatus(){
		
		int dualityStatusCount = dualityStatushandler.getDualityStatusList().size();
		
		DualityStatusDTO ds = new DualityStatusDTO();
		ds.setStatus("status1");
		int addedId = dualityStatushandler.saveDualityStatus(ds);
		ds.setId(addedId);
		
		ds.setStatus("updated status");
		DualityStatusDTO updatedDualityStatusDTO = dualityStatushandler.updateDualityStatus(ds);
		
		assertEquals(dualityStatushandler.getDualityStatusList()
				.get(dualityStatushandler.getDualityStatusList().size()-1).getStatus(),updatedDualityStatusDTO.getStatus());
		
		int updatedStatusCount = dualityStatushandler.getDualityStatusList().size();
		assertTrue(updatedStatusCount == dualityStatusCount + 1);
		
		dualityStatushandler.deleteDualityStatus(addedId);
		
		updatedStatusCount = dualityStatushandler.getDualityStatusList().size();
		assertTrue(updatedStatusCount == dualityStatusCount);
	}
}
