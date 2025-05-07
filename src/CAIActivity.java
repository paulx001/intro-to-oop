import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// this class controls the activity screen
@SuppressWarnings("serial")
public class CAIActivity extends JFrame implements ActionListener {

	// create timers
	private Timer countdownTimer = new Timer(1000, this); // 2 minute timer for activity - tick every 1 second (1000 ms)
	private Timer delay = new Timer(300, this); // after you click a button, delay 300 ms
	
	// GUI Components \\
	
	// create JLabels & JTextArea
	private JLabel titleLabel = new JLabel("The Odd One Out", JLabel.CENTER); // title label
	private JLabel imageLabel = new JLabel(); // image in activity home screen
	private JLabel classLabel = new JLabel(); // display the object name during the actual activity
	private JLabel highscoreLabel = new JLabel(); // display the user's session high score
	private JLabel scoreLabel = new JLabel(); // display the user's score
	private JLabel scoreMessage = new JLabel(); // displays how well the user did in activity end screen
	private JLabel countdownLabel = new JLabel(); // display the time left
	private JTextArea instructionsLabel = new JTextArea(); // text area to explain how game works

	// create JButtons
	private JButton instructionsButton = new JButton("How to Play"); // click to see instructions label
	private JButton startButton = new JButton("Start Game"); // click to begin game
	private JButton playAgain = new JButton("Play Again"); // shows up at activity end screen
	private JButton backButton = new JButton("Back"); // go to previous frame or main menu
	private JButton buttonClicked; // debounce holder

	// create JPanels
	private JPanel mainPanel = new JPanel(); // format components in the center
	private JPanel buttonHolder = new JPanel(); // store all buttons in activity

	// variables
	private int lastClassIndex; // used to find another object different from previous
	private int highscore; // session high score
	private int score; // score
	private int timeLeft = 120; // in seconds
	private int streak = 0; // correct answers in a row
	private int timeSpentAnswering = 0; // how many seconds spent answering (10 seconds max)
	private boolean inStartScreen = true; // redirect where back button goes
	

	// constructor method
	public CAIActivity() {

		// setup frames
		setSize(1280, 720); // set up JFrame size (16:9 ratio)
		setTitle("CAI Tool: Classes & Objects - Activity"); // set the title of the frame
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
		
		// method loads in the main menu in this screen
		loadActivityHomeScreen(); 
		
		// create the back button
		backButton.setBounds(50, 580, 200, 50); // set the position and size
		backButton.setBackground(Color.orange); // set the button color to orange
		backButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		backButton.setFocusable(false); // disable outline
		backButton.addActionListener(this); // detect when clicked
		add(backButton); // add to the frame

		// make the frame visible after everything loaded
		setVisible(true);

	}
	
	// method loads in the main menu in this screen
	private void loadActivityHomeScreen() {
		
		// reset/empty the frame
		buttonHolder.setVisible(false); // hide the buttonHolder (not stored on mainPanel)
		instructionsLabel.setVisible(false); // hide the instructionsLabel (not stored on mainPanel)
		mainPanel.removeAll(); // remove all components from mainPanel
		mainPanel.repaint(); // repaint to visually remove components
		
		inStartScreen = true; // change boolean to redirect where back button leads to
		
		// set the title label
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setText("Classes & Objects - Assessment"); // set the text of title
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(titleLabel); // add to the main panel
		
		// set the image icon
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25))); // create a gap above this label
		Image newImg = new ImageIcon("images/activityimage.png").getImage().getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH); // scale image
		imageLabel.setIcon(new ImageIcon(newImg));
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(imageLabel); // add to the main panel
		
		
		// set the instructions button
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25))); // create a gap above this button
		instructionsButton.setMargin(new Insets(10,75,10,75)); // make button bigger
		instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		instructionsButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of text
		instructionsButton.setBackground(Color.orange); // set the button color to orange
		instructionsButton.setFocusable(false); // disable outline
		instructionsButton.removeActionListener(this); // remove previous action listener (prevent clicking multiple times)
		instructionsButton.addActionListener(this); // detect when clicked
		mainPanel.add(instructionsButton); // add to the main panel
		
		// set the start button
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this button
		startButton.setMargin(new Insets(10,75,10,75)); // make button bigger
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		startButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of text
		startButton.setBackground(Color.orange); // set the button color to orange
		startButton.setFocusable(false); // disable outline
		startButton.removeActionListener(this); // remove previous action listener (prevent clicking multiple times)
		startButton.addActionListener(this); // detect when clicked
		mainPanel.add(startButton); // add to the main panel
	}
	
	// method displays instructionLabel that explains how to play the game
	private void loadInstructionScreen() {
		
		// reset/empty the frame
		mainPanel.removeAll(); // remove all components from mainPanel
		mainPanel.repaint(); // repaint to visually remove components
		
		inStartScreen = false; // change boolean to redirect where back button leads to
		
		// set the title label
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setText("The Odd One Out"); // set the text of title
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(titleLabel); // add to the main panel
		
		// set the instructions label
		instructionsLabel.setBounds(335, 150, 600, 400); // set the size and position
		instructionsLabel.setFont(new Font("Verdana", Font.PLAIN, 18)); // set the size of the text
		instructionsLabel.setBackground(new Color(102, 153, 204)); // set the background color to dark blue
		instructionsLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // create some padding between edges and text
		instructionsLabel.setLineWrap(true); // text goes to next line
		instructionsLabel.setWrapStyleWord(true); // make sure every character in a word are together
		instructionsLabel.setHighlighter(null); // disable text highlighting
		instructionsLabel.setEditable(false); // disable editing text area
		instructionsLabel.setVisible(true); // make sure to set visible if invisible previously
		add(instructionsLabel); // add to the frame
		
		// set the text of label
		instructionsLabel.setText("1. A random object will be chosen and displayed to you \n\n"
				+ "2. A group of 8 buttons representing an attribute or method will also be displayed \n\n"
				+ "3. Click on the button that does not match the object or is 'the odd one out' \n\n"
				+ "4. You will gain points for getting the correct answer, but lose 50 points for getting it wrong \n\n"
				+ "5. More points will be given depending on your streak and time spent choosing an option \n\n"
				+ "6. Aim for the highest score you can before the 2 minute timer runs out \n\n");
		
	}
	
	// this method creates the actual activity
	private void loadActivityScreen() {

		// reset/empty the frame
		mainPanel.removeAll(); // remove all components from mainPanel
		mainPanel.repaint(); // repaint to visually remove components
		
		inStartScreen = false; // change boolean to redirect where back button leads to
		timeLeft = 120; // reset the time left
		score = 0; // reset the score
		
		// create the title
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setText("The Odd One Out"); // set the text of title
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(titleLabel); // add to the main panel
		
		// display the high score
		mainPanel.add(Box.createRigidArea(new Dimension(0, 35))); // create a gap above this label
		highscoreLabel.setFont(new Font("Verdana", Font.PLAIN, 16)); // set the size of the text
		highscoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		highscoreLabel.setText("Highscore: " + highscore); // set score
		mainPanel.add(highscoreLabel); // add to the main panel
		
		// display the score
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // create a gap above this label
		scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 16)); // set the size of the text
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		scoreLabel.setText("Score: " + score); // set score
		mainPanel.add(scoreLabel); // add to the main panel
		
		// display the name of object
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40))); // create a gap above this label
		classLabel.setFont(new Font("Verdana", Font.PLAIN, 50)); // set the size of the text
		classLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(classLabel); // add to the main panel
		
		// show the time left
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // create a gap above this label
		countdownLabel.setFont(new Font("Verdana", Font.PLAIN, 18)); // set the size of the text
		countdownLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(countdownLabel); // add to the main panel
		
		// set panel that holds all field/method buttons
		buttonHolder.setBounds(50, 380, 1160, 120); // set the position and size
		buttonHolder.setBackground(new Color(102, 153, 204)); // set the background to dark blue
		buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // set the layout
		buttonHolder.setVisible(true); // make sure to set visible if invisible previously
		add(buttonHolder); // add to the frame
		
		// start the game
		generateNewObject(); // choose object to start game
		countdownTimer.start(); // begin the timer
		countdownLabel.setText(String.format("%02dm %02ds", timeLeft/60%60, timeLeft%60)); // format seconds into timer
	}
	
	// method is called when activity runs out of time and creates/displays the end screen
	private void loadActivityEndScreen() {
		
		// game is over, stop the timer
		countdownTimer.stop();
		
		// update high score if current score is highest (or no high score yet)
		if (score >= highscore || highscore == 0)
			highscore = score;
		
		highscoreLabel.setText("Highscore: " + highscore); // set the high score label
		
		Audio.Play(Audio.FINISHED); // play a sound
		
		// reset/empty the frame
		buttonHolder.setVisible(false); // hide the button holder
		mainPanel.removeAll(); // remove all components from mainPanel
		mainPanel.repaint(); // repaint to visually remove components
		
		// create the title
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(titleLabel); // add to the main panel
		
		// display the high score
		mainPanel.add(Box.createRigidArea(new Dimension(0, 75))); // create a gap above this label
		highscoreLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		highscoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		highscoreLabel.setText("Highscore: " + highscore); // set score
		mainPanel.add(highscoreLabel); // add to the main panel
		
		// display the score
		mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // create a gap above this label
		scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 60)); // set the size of the text
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		scoreLabel.setText("Score: " + score); // set score
		mainPanel.add(scoreLabel); // add to the main panel
		
		// set the message according to user's score
		if (score <= 200)
			scoreMessage.setText("Better luck next time...");
		else if (score > 200 && score <= 500)
			scoreMessage.setText("Good attempt!");
		else if (score > 500 && score <= 1000)
			scoreMessage.setText("Amazing job!");
		else if (score > 1000 && score <= 1500)
			scoreMessage.setText("WOW you're good at this!");
		else
			scoreMessage.setText("You might be the next Einstein!");
		
		// display the score message
		mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // create a gap above this label
		scoreMessage.setFont(new Font("Verdana", Font.PLAIN, 20)); // set the size of the text
		scoreMessage.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(scoreMessage); // add to the main panel
		
		// display button to play again
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this button
		playAgain.setMargin(new Insets(10,75,10,75)); // make button bigger
		playAgain.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		playAgain.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of text
		playAgain.setBackground(Color.orange); // set the button color to orange
		playAgain.setFocusable(false); // disable outline
		playAgain.removeActionListener(this); // remove previous action listener (prevent clicking multiple times)
		playAgain.addActionListener(this); // detect when clicked
		mainPanel.add(playAgain); // add to the main panel
		
	}
	
	// this method chooses a random object from objects array
	private void generateNewObject() {
		
		// reset/empty the button holder
		buttonHolder.removeAll(); // remove old stuff
		buttonHolder.repaint(); // makes it so old buttons aren't showing anymore
		
		// declare index of chosen object
		int chosenClassIndex;
		
		// get a random index that is different from previous object
		do {

			chosenClassIndex = (int) (Math.random() * (CAIApplication.ObjectsArray.length - 1));

		} while (chosenClassIndex == lastClassIndex);

		// set this chosen class index as the old one (next one should be different than this)
		lastClassIndex = chosenClassIndex;
		
		// find object in array using random index
		ObjectTemplate chosenObject = CAIApplication.ObjectsArray[chosenClassIndex];
		
		// set the text of class label to chosen object
		classLabel.setText(chosenObject.getNameOfObject());
		
		// source: https://www.digitalocean.com/community/tutorials/shuffle-array-java
		List<JButton> buttonList = Arrays.asList(chosenObject.getButtons()); // move button array into a list
		Collections.shuffle(buttonList); // randomize the index of each button so position is different each time
		
		// loop through all the buttons
		for (int index = 0; index < chosenObject.getButtons().length; index++) {

			// declare the button of current index
			JButton newButton;

			// create the answer button if index has nothing in index (answer button does not have a set value)
			if (chosenObject.getButtons()[index] == null) {

				// chose a random answer from the answer array
				newButton = new JButton(chosenObject.getAnswers()[(int) (Math.random() * (chosenObject.getAnswers().length))]);
				
			}
			
			// otherwise new button should be the button found in the array
			else {

				newButton = new JButton(chosenObject.getButtons()[index].getText());
				
			}
			
			// set the new button
			newButton.setFont(new Font("Verdana", Font.PLAIN, 20)); // set the size of the text
			newButton.setMargin(new Insets(5, 30, 5, 30)); // make button bigger
			newButton.setFocusable(false); // disable outline
			newButton.removeActionListener(this); // remove old action listener
			newButton.addActionListener(this); // detect when clicked
			buttonHolder.add(newButton); // add to button holder
			
		}
	}

	// (for activity) this method checks if button clicked was the correct answer
	private void checkCorrectProperty(JButton clickedButton) {
		
		// get the answer string from the button text
		String answer = clickedButton.getText();
		
		// if selected option is one of the answers
		if (Arrays.stream(CAIApplication.ObjectsArray[lastClassIndex].getAnswers()).anyMatch(answer::equals)) { 

			score += (50 + (10 - timeSpentAnswering)) + (streak / 10) ; // increase score for correct answer
			streak++;
			clickedButton.setBackground(new Color(0, 255, 0)); // change color to green
			Audio.Play(Audio.DING); // play ding sound
			
		}
		
		// if selected is not found in the answers then questions is wrong
		else { 
			
			score -= 50; // decrease score
			streak = 0; // reset streak
			clickedButton.setBackground(new Color(255, 0, 0)); // change color to red
			Audio.Play(Audio.WRONG_ANSWER); // play wrong answer sound
			
		}

		scoreLabel.setText("Score: " + score); // set the score label with new score
		buttonClicked = clickedButton; // set debounce
		delay.start(); // wait for delay until going to next object
		
	}

	// method is called when any button is pressed (or timers)
	public void actionPerformed(ActionEvent event) {
		
		// check for timer called (check first to play sound)
		if (event.getSource() == countdownTimer) {
			
			timeLeft--; // decrement time left
			timeSpentAnswering++; // increment time spent
			countdownLabel.setText(String.format("%02dm %02ds", timeLeft/60%60, timeLeft%60)); // format the text
			
			// limit time spent at 10 seconds
			if (timeSpentAnswering > 10) {
				
				timeSpentAnswering = 10;
				
			}
			
			// check if no time left and end the game
			if (timeLeft <= 0) {
				
				loadActivityEndScreen(); // show the end screen
				
			}
			
			return; // end action performed here
			
		}
		
		// delay when activity button is clicked
		else if (event.getSource() == delay) {
			
			delay.stop(); // stop after first tick
			buttonClicked = null; // remove debounce
			timeSpentAnswering = 0; // reset time spent
			generateNewObject(); // get a new object for next 'round'
			return; // end action performed here
			
		}
		
		// if a button and not a timer then play click sound
		if (buttonClicked == null) 
			Audio.Play(Audio.MOUSE_CLICK);
			
		// check if back button was clicked and go back depending on curent screen
		if (event.getSource() == backButton) {
			
			if (inStartScreen) {
				
				countdownTimer.stop(); // stop game from running when exiting activity
				dispose(); // close window and open next
				new CAIMainMenu(); // go to previous frame (main menu)
			}
			
			else
				loadActivityHomeScreen(); // go to activity main menu
		}
		
		// check if start game button was clicked
		else if (event.getSource() == startButton || event.getSource() == playAgain) {
			
			loadActivityScreen(); // load in activity
			
		}
		
		// instructions button was clicked
		else if (event.getSource() == instructionsButton) {
			
			loadInstructionScreen(); // load in instructions
			
		}
		
		// buttons for activity were clicked
		else {
			
			// wait for debounce and still time left
			if (buttonClicked == null && timeLeft > 0) {
				
				JButton clickedButton = (JButton) event.getSource();
				checkCorrectProperty(clickedButton);
			
			}
		}
	}
}	