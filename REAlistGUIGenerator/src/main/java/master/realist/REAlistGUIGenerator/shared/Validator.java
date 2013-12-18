package master.realist.REAlistGUIGenerator.shared;

/**
 * Validator class
 * @author Thomas
 *
 */
public abstract class Validator {
	
	// errorMessage that can be displayed
	protected String errorMessage;
	
	/**
	 * Method validating a String (value) for correctness
	 * @param value String value
	 * @return true or false
	 */
	public abstract boolean validate(String value);
	
	/**
	 * Method returning the errorMessage
	 * @return errorMessage
	 */
    public abstract String getErrorMessage();
}
