package master.realist.REAlistGUIGenerator.shared;

/**
 * Validator checking if the content of a textbox is a Double or an Integer
 * @author Thomas
 *
 */
public class NumericValidator extends Validator{

	/**
	 * Default Constructor
	 */
	public NumericValidator(){
		
	}
	
	/**
	 * Method checking if a String can be converted to a Double value. 
	 * If not, false is returned.
	 * @return isDouble
	 */
	public boolean validate(String value) {
		
		boolean isDouble = true;
		
		try{  
			
			Double.parseDouble(value);
			errorMessage = "";
			
		} catch(NumberFormatException nfe) { 
			
			isDouble = false;  
			errorMessage = "Enter a valid Double String";
			
		}  
		
		return isDouble;  
	}

	/**
	 * Method returning the errorMessage
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		
		return errorMessage;
	}

}
