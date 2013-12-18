package master.realist.REAlistGUIGenerator.shared;

/**
 * Validator checking if a String is a boolean or not
 * @author Thomas
 *
 */
public class BooleanValidator extends Validator{

	/**
	 * Method validating the value of a String and returning true if the value is 'true' (ignoring case)
	 * or 'false' (ignoring case)
	 */
	public boolean validate(String value) {

		if(value.toLowerCase().equals("true") || value.toLowerCase().equals("false")){
			errorMessage = "";
			return true;
		}else{
			errorMessage = "Enter a valid Boolean String (true or false)";
			return false;
			
		}
	}

	/**
	 * Method returning the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
