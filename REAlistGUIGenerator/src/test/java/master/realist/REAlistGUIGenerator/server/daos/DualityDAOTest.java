package master.realist.REAlistGUIGenerator.server.daos;

import static org.junit.Assert.*;

import java.util.Date;

import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DualityDAOTest {
	
	private static DualityDAO dualityhandler;
	private static DualitytypeDAO dualitytypehandler;
	private static DualityStatusDAO dualitystatushandler;
	
	private static DualitytypeDTO dualitytypeDTO;
	private static DualityDTO dualityDTO;
	private static DualityStatusDTO dualitystatusDTO;
	
	/**
	 * Setting up dualitytypehandler used by every test method
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		dualityhandler = (DualityDAO) SpringUtil.context.getBean("dualitydao");	
		dualitytypehandler = (DualitytypeDAO) SpringUtil.context.getBean("dualitytypedao");
		dualitystatushandler = (DualityStatusDAO) SpringUtil.context.getBean("dualitystatusdao");
		
		// dualitystatusDTO
		dualitystatusDTO = new DualityStatusDTO();
		dualitystatusDTO.setStatus("Test_Status");
		
		// dualitytypeDTO
		dualitytypeDTO = new DualitytypeDTO();
		dualitytypeDTO.setId("Test_Dualitytype");
		dualitytypeDTO.setName("Test_Dualitytype_Name");
		dualitytypeDTO.setConversion(false);
		
		// dualityDTO
		dualityDTO = new DualityDTO();
		dualityDTO.setDate(new Date());
		dualityDTO.setDualitytype(dualitytypeDTO);
		dualityDTO.setDualitystatus(dualitystatusDTO);
		
		// save dualitystatus and dualitytype
		int dualitystatusId = dualitystatushandler.saveDualityStatus(dualitystatusDTO);
		dualitystatusDTO.setId(dualitystatusId);
		dualitytypehandler.saveDualityttype(dualitytypeDTO);
	}
	
	@Test
	public void testSaveDuality(){
		
		// get initial number of dualities in REA DB
		int initialsize = dualityhandler.getDualityList().size();
		
		// add new
		int savedId = dualityhandler.saveDuality(dualityDTO);
		
		// get new size 
		int newsize = dualityhandler.getDualityList().size();
		
		// assert that newsize = initialsite +1
		assertTrue(newsize == initialsize +1);
		
		// delete new
		dualityhandler.deleteDuality(savedId);
		
		// get new size
		newsize = dualityhandler.getDualityList().size();
		
		// assert newsize = initialsize
		assertTrue(newsize == initialsize);
		
	}
	
	@AfterClass
	public static void doAfterClass(){
		
		// delete testdata in REA DB
		dualitystatushandler.deleteDualityStatus(dualitystatusDTO.getId());
		dualitytypehandler.deleteDualitytype(dualitytypeDTO.getId());
	}

}
