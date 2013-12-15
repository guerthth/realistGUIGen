package master.realist.REAlistGUIGenerator.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TestPanel extends VerticalPanel{
	
	private Label testLabel;
	
	public TestPanel(String text){
		populateTestPanel(text);
	}
	
	private void populateTestPanel(String text){
		testLabel = new Label(text);
		this.add(testLabel);
	}
	
	
}
