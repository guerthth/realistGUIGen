package master.realist.REAlistGUIGenerator.client;

import java.util.ArrayList;
import java.util.List;

import master.realist.REAlistGUIGenerator.shared.Validator;

import com.google.gwt.user.client.ui.TextBox;

public class CustomTextBox extends TextBox {
	
	private static final String TEXTBOX_VALIDATION_ERROR_STYLE = "error-text-box";
	private String errorMessage = "";
	private List<Validator> validators = new ArrayList<Validator>();

	/**
	 * Default Constructor
	 */
	public CustomTextBox(){
		
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public CustomTextBox(String name){
		setName(name);
	}
	
	/**
	 * Getter for ErrorMessage
	 * @return
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/**
	 * Setter for errorMessage
	 * @param errorMessage 
	 */
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Method adding a validator to the validators arraylist
	 * @param validator
	 */
	public void addValidator(Validator validator){
		validators.add(validator);
	}
	
	/**
	 * Validation Method
	 * @return validationResult
	 */
	public boolean validate(){
		boolean validationResult = true;
		for(Validator validator : validators){
			validationResult = validator.validate(getValue().trim());
			if(!validationResult){
				errorMessage = validator.getErrorMessage();
	            break;
			}
			errorMessage = validator.getErrorMessage();
		}
		setErrorStyles(validationResult);
	    return validationResult;
	}
	
	/**
	 * Apply styles for TextBox error
	 * @param validationResult
	 */
	private void setErrorStyles(boolean validationResult) {
	    if (validationResult) {
	        removeStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
	        setTitle("");
	    } else {
	        addStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
	        setTitle(errorMessage);
	    }
	}
	
	/**
	 * Setter for textbox value
	 */
	@Override
	public void setValue(String s) {
	    removeStyleDependentName(TEXTBOX_VALIDATION_ERROR_STYLE);
	    super.setValue(s);
	}

	/**
	 * Getter for textbox value
	 */
	@Override
	public String getValue() {
	    return super.getValue().trim();
	}
	
	
}
