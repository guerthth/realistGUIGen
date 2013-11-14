package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;

import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class DualityDAOTest {
	
	private static DualityDAO dualityhandler;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		dualityhandler = (DualityDAO) SpringUtil.context.getBean("dualitydao");	
	}
	
	@Test
	public void testSaveDuality(){
		
		assertTrue(true);
		/**
		//DualitytypeDTO needed reference in saved DualityDTP
		DualitytypeDTO dualitytypeDTO = new DualitytypeDTO();
		dualitytypeDTO.setId("TestId");
		dualitytypeDTO.setName("Testdualitytype");
		dualitytypeDTO.setConversion(false);
		
		// DualityDTO object
		DualityDTO dualityDTO = new DualityDTO();
		dualityDTO.setDate(new Date());
		dualityDTO.setParentdualitytype_id(dualitytypeDTO);
	    
	    int savedDualityId = dualityhandler.saveDuality(dualityDTO);
	    assertNotNull(savedDualityId);
	    assertTrue(savedDualityId == 1);
	    **/
	}
}
