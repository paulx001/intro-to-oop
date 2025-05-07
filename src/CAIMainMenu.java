import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// this class creates the main menu of the application and allows navigation between other 3 main frames
@SuppressWarnings("serial")
public class CAIMainMenu extends JFrame implements ActionListener {

	// GUI Components \\
	
	// JLabels
	private JLabel titleLabel = new JLabel("Modular Programming - Classes & Objects", JLabel.CENTER); // title of screen
	private JLabel logoImage = new JLabel(); // image of main menu
	
	// JButtons
	private JButton conceptButton = new JButton("Concepts"); // go to concepts screen
	private JButton activityButton = new JButton("Activity"); // go to activity screen
	private JButton assessmentButton = new JButton("Assessment"); // go to assessment screen

	// JPanel
	private JPanel buttonHolder = new JPanel(); // format buttons

	// constructor method
	public CAIMainMenu() {

		// setup frames
		setSize(1280, 720); // set up JFrame size (16:9 ratio)
		setTitle("CAI Tool: Classes & Objects - Main Menu"); // set the title of the frame
		setIconImage(new ImageIcon("images/logo.png").getImage()); // set the icon of the frame
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)); // set frame to box layout
		getContentPane().setBackground(new Color(173, 216, 230)); // change color of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // end program when application closed
		setLocationRelativeTo(null); // display the JFrame at the center of the screen
		setResizable(false); // disable the button that resizes application

		
		// set the title label
		add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this label
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 40)); // set the size of the text
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		add(titleLabel); // add to the frame

		
		// create the image/logo
		add(Box.createRigidArea(new Dimension(0, 50))); // create a gap above this image
		Image newImg = new ImageIcon("images/logo.png").getImage().getScaledInstance(250, 273, java.awt.Image.SCALE_SMOOTH); // scale image
		logoImage.setSize(250, 273); // set the size
		logoImage.setIcon(new ImageIcon(newImg)); // set image with new scaled image
		logoImage.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		add(logoImage); // add to the frame
		

		// set the button holder
		add(Box.createRigidArea(new Dimension(0, 75))); // create a gap above the panel
		buttonHolder.setBackground(new Color(102, 153, 204)); // change color of background
		buttonHolder.setOpaque(false); // hide the background
		add(buttonHolder); // add to the frame

		// set the concepts button (leftmost side)
		conceptButton.setMargin(new Insets(10,75,10,75)); // make button bigger
		conceptButton.setBackground(Color.orange); // set button to orange
		conceptButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		conceptButton.setFocusable(false); // disable outline
		conceptButton.addActionListener(this); // detect when clicked
		buttonHolder.add(conceptButton); // add to the button holder

		// set the activity button (at center)
		buttonHolder.add(Box.createRigidArea(new Dimension(100, 0))); // create a gap between the previous button
		activityButton.setMargin(new Insets(10,85,10,85)); // make button bigger
		activityButton.setBackground(Color.orange); // set button to orange
		activityButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		activityButton.setFocusable(false); // disable outline
		activityButton.addActionListener(this); // detect when clicked
		buttonHolder.add(activityButton); // add to the button holder

		// set the assessment button (rightmost side0
		buttonHolder.add(Box.createRigidArea(new Dimension(100, 0))); // create a gap between the previous button
		assessmentButton.setMargin(new Insets(10,50,10,50)); // make button bigger
		assessmentButton.setBackground(Color.orange); // set the button to orange
		assessmentButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		assessmentButton.setFocusable(false); // disable outline
		assessmentButton.addActionListener(this); // detect when clicked
		buttonHolder.add(assessmentButton); // add to the button holder

		// make the frame visible
		setVisible(true);

	}

	// fire when any button was clicked
	public void actionPerformed(ActionEvent event) {

		// play a click sound
		Audio.Play(Audio.MOUSE_CLICK);
		
		// close window and open next main frame
		dispose();

		// check if concept button was clicked
		if (event.getSource() == conceptButton) {
			
			new CAIConcepts(); // go to concept frame
			
		}

		// check if activity button was clicked
		else if (event.getSource() == activityButton) {
			
			new CAIActivity(); // go to activity frame
			
		}

		// check if assessment button was clicked
		else if (event.getSource() == assessmentButton) {
			
			new CAIAssessment(); // go to assessment frame
			
		}
	}
}