import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


// this class displays questions in a scrolling frame and will grade the user based on how much questions they got correct
@SuppressWarnings("serial")
public class CAIAssessment extends JFrame implements ActionListener {

	// GUI Components \\
	
	// create JLabels
	private JLabel titleLabel = new JLabel("Classes & Objects - Assessment", JLabel.CENTER); // title of screen
	private JLabel markLabel = new JLabel("Mark: -/-", JLabel.CENTER); // displays how many questions user got correct
	private JLabel percentageLabel = new JLabel("Percentage: -%", JLabel.CENTER); // displays the percentage of mark

	// create JButtons
	private JButton backButton = new JButton("Back"); // go back to main menu
	private JButton submitButton = new JButton("Submit"); // submit answers
	
	// create JPanels
	private JPanel mainPanel = new JPanel();
	private JPanel questionsPanel = new JPanel();
	
	// scroll pane
	private JScrollPane scrollPane; 

	// constructor method
	public CAIAssessment() {

		// setup frames
		setSize(1280, 720); // set up JFrame size (16:9 ratio)
		setTitle("CAI Tool: Classes & Objects - Assessment"); // set the title of the frame
		setIconImage(new ImageIcon("images/logo.png").getImage()); // set the icon of the frame
		setLayout(null); // set layout null (manually position some components)
		getContentPane().setBackground(new Color(173, 216, 230)); // change color of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // end program when application closed
		setLocationRelativeTo(null); // display the JFrame at the center of the screen
		setResizable(false); // disable the button that resizes application


		// set up main panel
		mainPanel.setSize(1280, 720); // set its size the same as the frame
		mainPanel.setOpaque(false); // make its background transparent
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); // set layout to center components vertically
		add(mainPanel); // add to the frame

		// create the title
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(titleLabel); // add to the main panel
				
		// set the mark label
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // create a gap above this label
		markLabel.setFont(new Font("Verdana", Font.PLAIN, 20)); // set the size of the text
		markLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(markLabel); // add to main panel

		// set the percentage label
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // create a gap above this label
		percentageLabel.setFont(new Font("Verdana", Font.PLAIN, 20)); // set the size of the text
		percentageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(percentageLabel); // add to main panel

		// set the questions panel
		questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.PAGE_AXIS)); // vertically align components
		add(questionsPanel); // add to the frame

		// set the scroll pane
		scrollPane = new JScrollPane(questionsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10); // quicker scrolling
		scrollPane.setBounds(325, 200, 630, 450); // set the position and size
		add(scrollPane); // add to the frame

		// create the back button
		backButton.setBounds(50, 580, 200, 50); // set the position and size
		backButton.setBackground(Color.orange); // set the button color to orange
		backButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		backButton.setFocusable(false); // disable outline
		backButton.addActionListener(this); // detect when clicked
		add(backButton); // add to the frame

		// create the submit button
		submitButton.setBounds(1010, 580, 200, 50); // set the position and size
		submitButton.setBackground(Color.orange); // set the button color to orange
		submitButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		submitButton.setFocusable(false); // disable outline
		submitButton.addActionListener(this); // detect when clicked
		add(submitButton); // add to the frame

		// start creating the quiz in scrolling frame
		for (QuestionTemplate question : CAIApplication.QuestionsArray) {

			// add the label for question being asked
			questionsPanel.add(Box.createRigidArea(new Dimension(50, 5))); // add small gap between question and options
			question.getQuestionAsked().setText(question.getQuestionAsked().getText().replace("✔️ ", "")); // remove check mark
			question.getQuestionAsked().setText(question.getQuestionAsked().getText().replace("❌ ", "")); // remove x
			questionsPanel.add(question.getQuestionAsked()); // add to question panel

			// create a button group for radio buttons
			ButtonGroup buttonGroup = new ButtonGroup();

			// loop through the options
			for (int optionNumber = 0; optionNumber < question.getButtons().length; optionNumber++) {

				// check if radio button and insert into button group
				if (question.getButtonType().equals("Radiobutton")) {
					buttonGroup.add(question.getButtons()[optionNumber]);
					buttonGroup.clearSelection(); // this clears already selected Radio buttons
				}

				question.getButtons()[optionNumber].setSelected(false); // this clears already selected Check boxes
				question.getButtons()[optionNumber].setFocusable(false); // disable outline
				question.getButtons()[optionNumber].setEnabled(true); // make sure buttons are enabled
				question.getButtons()[optionNumber].setBackground(null); // reset background color
				question.getButtons()[optionNumber].removeActionListener(this); // make sure to remove old action listener
				question.getButtons()[optionNumber].addActionListener(this); // play click sound when clicked

				questionsPanel.add(question.getButtons()[optionNumber]); // add to questions panel
			}

			questionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add gap between each question
		}

		// make the frame visible
		setVisible(true);

	}

	// this method makes sure user answered every question first, then evaluates how many of their responses were correct/wrong
	private void evaluateSurveyAnswers() {

		// loop through all questions the first time to make sure all questions were answered (don't display results unless all questions are answered)
		for (QuestionTemplate question : CAIApplication.QuestionsArray) {

			// check if the current question is answered
			boolean questionAnswered = false; 

			// loop through all options and see if any of the options are selected
			for (int buttonNumber = 0; buttonNumber < question.getButtons().length; buttonNumber++) {

				// if any button is selected, the question is answered and will check for all the others too
				if (question.getButtons()[buttonNumber].isSelected()) { 
					
					questionAnswered = true; // skip checking the rest of the options and move on
					break;
					
				}	
			}

			// after going through each button of the question and question is still not answered
			if (questionAnswered == false) {
				
				// play a sound and display a message
				Audio.Play(Audio.WRONG_ANSWER);
				JOptionPane.showMessageDialog(this, "Please answer all the questions.");
				return; // stop
			}
		}
		
		
		// all questions have been answered so hide submit button
		submitButton.setVisible(false);
		
		// play sound
		Audio.Play(Audio.FINISHED);
		
		// initialize variable to count how many questions were answered correctly
		int questionsCorrect = 0;

		// loop through questions again, this time assessing the answers
		for (QuestionTemplate question : CAIApplication.QuestionsArray) {

			// count if number of correct answers equal to how many answers there should be (check boxes require you to select all)
			int correctAnswers = 0;

			// loop through all buttons
			for (int buttonNumber = 0; buttonNumber < question.getButtons().length; buttonNumber++) {

				// disable option from being clicked or changed
				question.getButtons()[buttonNumber].setEnabled(false); 
				
				// check here if the option is one of the answers
				if (Arrays.stream(question.getAnswers()).anyMatch(Integer.toString(buttonNumber)::equals)) { // if selected option is one of the answers
					
					// if this option answer was selected
					if (question.getButtons()[buttonNumber].isSelected()) { 

						correctAnswers++; // increment correct answers
						question.getButtons()[buttonNumber].setBackground(new Color(144, 238, 144)); // set color to green

					}
					
					// otherwise option is the answer and was not selected
					else { 
						
						correctAnswers = 0; // reset corrected answers (answer is wrong)
						question.getButtons()[buttonNumber].setBackground(new Color(255, 255, 102)); // set color to yellow

					}
				}
				
				// else if option was not one of the answers 
				else {
					
					// check if user selected a wrong answer
					if (question.getButtons()[buttonNumber].isSelected()) {

						correctAnswers = 0; // reset corrected answers (answer is wrong)
						question.getButtons()[buttonNumber].setBackground(new Color(255, 128, 128)); // set color to red

					}
					
				}

			} // end of looping through options

			
			
			// if number of correct answers matches the answers (1 radio button correct or all check box option answers were selected)
			if (correctAnswers == question.getAnswers().length) {

				questionsCorrect++; // increment number of questions that were correct
				question.getQuestionAsked().setText(String.format("✔️ %s", question.getQuestionAsked().getText())); // add check mark to question
				
			} 
			
			// otherwise did not get question correct
			else
				question.getQuestionAsked().setText(String.format("❌ %s", question.getQuestionAsked().getText())); // add x to question

		} // end of evaluating responses
		

		// set mark label
		markLabel.setText(String.format("Mark: %d / %d", questionsCorrect, CAIApplication.QuestionsArray.length));

		// set percentage and format to 2 decimal places
		percentageLabel.setText(String.format("Percentage: %.2f%%", (double) questionsCorrect / CAIApplication.QuestionsArray.length * 100));

	}

	// handles all button clicks
	public void actionPerformed(ActionEvent event) {

		// play click sound
		Audio.Play(Audio.MOUSE_CLICK);
		
		// check if submit button was clicked
		if (event.getSource() == submitButton) {
			evaluateSurveyAnswers(); // look at responses
		}

		// check if back button was clicked
		else if (event.getSource() == backButton) {
			dispose(); // close window and open next
			new CAIMainMenu(); // go to previous frame (main menu)
		}
	}
}