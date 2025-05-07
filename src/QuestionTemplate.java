import javax.swing.*;

// template class that creates a question as radio buttons or check boxes with the given options
public class QuestionTemplate {

	// fields
	private JLabel questionAsked; // the 'actual' question being asked
	private String buttonType; // radio button or check box
	private JToggleButton[] buttons; // list of answers user can select
	private String[] answers; // store the correct answers (radio buttons will have 1, check boxes have more)

	// constructor
	public QuestionTemplate(JLabel questionAsked, String buttonType, JToggleButton[] buttons, String[] answers) {
		super();
		this.questionAsked = questionAsked;
		this.buttonType = buttonType;
		this.buttons = buttons;
		this.answers = answers;
	}

	
	
	// getters and setters
	
	public JLabel getQuestionAsked() {
		return questionAsked;
	}

	public void setQuestionAsked(JLabel questionAsked) {
		this.questionAsked = questionAsked;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public JToggleButton[] getButtons() {
		return buttons;
	}

	public void setButtons(JToggleButton[] buttons) {
		this.buttons = buttons;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

}