import javax.swing.*;

// template class for creating random objects with corresponding attributes as JButtons and also a string of other random attributes
public class ObjectTemplate {

	// fields
	private String nameOfObject; // name of the object
	private JButton[] buttons; // button array of attributes
	private String[] answers; // a string of incorrect attributes (which are the 'answers' to the activity)

	// constructor method
	public ObjectTemplate(String nameOfObject, JButton[] buttons, String[] answers) {
		super();
		this.nameOfObject = nameOfObject;
		this.buttons = buttons;
		this.answers = answers;
	}

	
	
	// getters and setters
	
	public String getNameOfObject() {
		return nameOfObject;
	}

	public void setNameOfObject(String nameOfObject) {
		this.nameOfObject = nameOfObject;
	}

	public JButton[] getButtons() {
		return buttons;
	}

	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}