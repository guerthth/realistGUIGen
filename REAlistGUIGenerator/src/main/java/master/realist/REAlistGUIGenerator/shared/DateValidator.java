package master.realist.REAlistGUIGenerator.shared;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Validator checking if a Stirng is a Date
 * @author Thomas
 *
 */
public class DateValidator extends Validator{

	/**
	 * Method checking if a String value can be converted to Date or not
	 */
	public boolean validate(String value) {
		
		boolean isDate = true;
		
		try{
			//DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			DateTimeFormat.getFormat("yyyy-MM-dd").parse(value);
			errorMessage = "";
		} catch(Exception e){
			isDate = false;
			//errorMessage = "Enter a valid Date String (yyyy-MM-dd HH:mm:ss)";
			errorMessage = "Enter a valid Date String (yyyy-MM-dd)";
		}
		
		return isDate;
		
	}

	/**
	 * Method returning error Message
	 */
	public String getErrorMessage() {

		return errorMessage;
	}

}
