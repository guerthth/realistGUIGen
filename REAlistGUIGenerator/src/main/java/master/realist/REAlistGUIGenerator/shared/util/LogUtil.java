package master.realist.REAlistGUIGenerator.shared.util;

import java.util.logging.Logger;

public class LogUtil {
	
	// Logger
	private final static Logger logger = Logger.getLogger("REAlistGUIGeneratorLogger");
	
	/**
	 * Logging method for all failures that occur when making RPC calls to READB service
	 * @param methodDef REAB method that is called
	 */
	@SuppressWarnings("unused")
	public static void logREADBRPCFailure(String methodDef){
		
		logger.info("Error when making RPC call to " + methodDef + " READB service method.");
	}
}
