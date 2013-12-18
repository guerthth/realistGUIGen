package master.realist.REAlistGUIGenerator.shared;

/**
 * Validator class for Email Strings
 * @author Thomas
 *
 */
public class EmailValidator extends Validator{
	
	/**
	 * Method validating a String (value) for correctness
	 * Length of value is also checked, since maximal length of adittional textual values is
	 * 45 in the REA DB
	 * @param value String value
	 * @return true or false
	 */
	public boolean validate(String value) {
	    if (value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && 
	    		value.length() <= 45 ) {
	        errorMessage = "";
	        return true;
	    } else {
	        errorMessage = "Enter valid email Id";
	        return false;
	    }
	}
	
	/**
	 * Method returning the errorMessage
	 * @return errorMessage
	 */
	public String getErrorMessage() {
	    return errorMessage;
	}
}
