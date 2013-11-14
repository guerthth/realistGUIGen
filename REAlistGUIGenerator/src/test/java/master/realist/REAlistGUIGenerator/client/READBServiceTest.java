package master.realist.REAlistGUIGenerator.client;


import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class READBServiceTest extends GWTTestCase{	
	
	private READBServiceAsync readbService;
	private ServiceDefTarget target;
	
	/**
	 * Must refer to a valid module that sources this class.
	*/
	public String getModuleName() {
		return "master.realist.REAlistGUIGenerator.REAlistGUIGeneratorJUnit";
	}
	
	/**
	 * This test will send a request to the server using the greetServer method in
	 * READBService and verify the response.
	*/
	@Test
	public void testGetDualityTypesService() {
		
		// Create the service that we will test.
		readbService = GWT.create(READBService.class);
		target = (ServiceDefTarget) readbService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "REAlistGUIGenerator/reaDB");

		// Since RPC calls are asynchronous, we will need to wait for a response
	    // after this test method returns. This line tells the test runner to wait
	    // up to 10 seconds before timing out.
	    delayTestFinish(100000);

	    // Send a request to the server.
	    readbService.getDualitytypes(new AsyncCallback<List<DualitytypeDTO>>() {
	      public void onFailure(Throwable caught) {
	        // The request resulted in an unexpected error.
	        fail("Request failure: " + caught.getMessage());
	      }

	      public void onSuccess(List<DualitytypeDTO> result) {
	        // Verify that the response is correct.
	    	
	    	if(result.size() != 0){
	    		if(result.get(0).getName().matches("CarProductionConversion_Duality")){
	    			// Carproducer Model
	    			assertTrue(result.get(1).getName().matches("PayrollConversion_Duality"));
	    			assertTrue(result.get(2).getName().matches("PurchaseExchange_Duality"));
	    		}
	    			
	    	}

	        // Now that we have received a response, we need to tell the test runner
	        // that the test is complete. You must call finishTest() after an
	        // asynchronous test finishes successfully, or the test will time out.
	        finishTest();
	      }
	    });
	}

	@Test
	public void testSaveDuality(){
		
		// Create the service that we will test.
		readbService = GWT.create(READBService.class);
		target = (ServiceDefTarget) readbService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "REAlistGUIGenerator/reaDB");
		
		// Since RPC calls are asynchronous, we will need to wait for a response
	    // after this test method returns. This line tells the test runner to wait
	    // up to 10 seconds before timing out.
	    delayTestFinish(100000);
	    
	    // Send a request to the server.
	   // readbService.getEventtype();
	}

}
