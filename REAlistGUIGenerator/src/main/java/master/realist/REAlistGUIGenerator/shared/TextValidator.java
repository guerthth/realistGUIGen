package master.realist.REAlistGUIGenerator.shared;

public class TextValidator extends Validator {
	
	// maximal length of the string
	private int maxtextLength;
	
	/**
	 * Constructor
	 * @param maxTextLength maximum textlength of textbox
	 */
	public TextValidator(int maxTextLength){
		this.maxtextLength = maxTextLength;
	}
	
	/**
	 * Method validating a String (value) for correctness
	 * Length of value is also checked, since maximal length of additional textual values is
	 * 45 in the REA DB
	 * @param value String value
	 * @return true or false
	 */
	public boolean validate(String value) {
		if(value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || (value.matches("^[a-zA-Z0-9][a-zA-Z0-9-_ ]+$") 
				|| value.matches("^[a-zA-Z0-9]+")) 	&& value.length() <= maxtextLength){
			
			errorMessage = "";
	        return true;
		} else{
			errorMessage = "Enter valid Text (minimum length: 1, maximum length: " + maxtextLength + ")";
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
