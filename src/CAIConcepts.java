import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

// class explains object oriented programming with provided explanations, images, and even extra video sources
@SuppressWarnings("serial")
public class CAIConcepts extends JFrame implements ActionListener {

	// GUI Components \\
	
	// create the JLabels
	private JLabel titleLabel = new JLabel("Classes & Objects - Concepts", JLabel.CENTER); // title of screen
	private JLabel subHeader = new JLabel("Sub-Header"); // display what concept is currently being displayed
	private JButton videoLink = new JButton("Check out this video on:"); // click for extra video help (opens browser)
	private JLabel image = new JLabel(); // image for all concept sections
	private JTextArea descriptionLabel = new JTextArea(); // label that explains everything
		
	// create the JButtons
	private JButton oopButton = new JButton("Object-Oriented Programming");
	private JButton classesButton1 = new JButton("What is a class?");
	private JButton classesButton2 = new JButton("How do you set up a class?");
	private JButton objectsButton1 = new JButton("What is an object?");
	private JButton objectsButton2 = new JButton("How to create an object");
	private JButton backButton = new JButton("Back");

	// create JPanels
	private JPanel mainPanel = new JPanel(); // format components in the center
	private JPanel buttonHolder = new JPanel(); // store all buttons

	// constructor method
	public CAIConcepts() {

		// setup frames
		setSize(1280, 720); // set up JFrame size (16:9 ratio)
		setTitle("CAI Tool: Classes & Objects - Concepts");
		setLayout(null);
		getContentPane().setBackground(new Color(173, 216, 230));
		setIconImage(new ImageIcon("images/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // end program when application closed
		setLocationRelativeTo(null); // display the JFrame at the center of the screen
		setResizable(false); // disable button that resizes application

		
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

		// set the sub header
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25))); // create a gap above this label
		subHeader.setFont(new Font("Verdana", Font.PLAIN, 26)); // set the size of the text
		subHeader.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		mainPanel.add(subHeader); // add to the main panel

		// set the video link at bottom
		mainPanel.add(Box.createRigidArea(new Dimension(0, 450))); // create a large gap above this label
		videoLink.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of the text
		videoLink.setForeground(Color.BLUE); // set the color of text to blue
		videoLink.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
		videoLink.setOpaque(false);
		videoLink.setContentAreaFilled(false);
		videoLink.setBorder(null);
		videoLink.addActionListener(this);
		mainPanel.add(videoLink); // add to the main panel

		// set the image
		image.setBounds(908, 150, 350, 450); // set the position and size
		add(image); // add to main frame

		
		// set button holder panel
		buttonHolder.setOpaque(false); // make background transparent
		buttonHolder.setLayout(null); // manually position components
		buttonHolder.setBounds(20, 200, 260, 350); // set the position and size
		add(buttonHolder); // add to main frame

		// object oriented programming button
		oopButton.setBounds(0, 0, 260, 30); // set the size and position
		oopButton.setBackground(Color.orange); // set the button color to orange
		oopButton.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of text
		oopButton.setFocusable(false); // disable outline
		oopButton.addActionListener(this); // call event when clicked
		buttonHolder.add(oopButton); // add to button holder panel

		// what is a class button
		classesButton1.setBounds(0, 40, 260, 30); // set the size and position
		classesButton1.setBackground(Color.orange); // set the button color to orange
		classesButton1.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of text
		classesButton1.setFocusable(false); // disable outline
		classesButton1.addActionListener(this); // detect when clicked
		buttonHolder.add(classesButton1); // add to button holder panel
		
		// how to set up template class button
		classesButton2.setBounds(0, 80, 260, 30); // set the size and position
		classesButton2.setBackground(Color.orange); // set the button color to orange
		classesButton2.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of text
		classesButton2.setFocusable(false); // disable outline
		classesButton2.addActionListener(this); // detect when clicked
		buttonHolder.add(classesButton2); // add to button holder panel
	
		// what is an object button
		objectsButton1.setBounds(0, 120, 260, 30); // set the size and position
		objectsButton1.setBackground(Color.orange); // set the button color to orange
		objectsButton1.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of text
		objectsButton1.setFocusable(false); // disable outline
		objectsButton1.addActionListener(this); // detect when clicked
		buttonHolder.add(objectsButton1); // add to button holder panel
		
		// how to create an object button
		objectsButton2.setBounds(0, 160, 260, 30); // set the size and position
		objectsButton2.setBackground(Color.orange); // set the button color to orange
		objectsButton2.setFont(new Font("Verdana", Font.PLAIN, 15)); // set the size of text
		objectsButton2.setFocusable(false); // disable outline
		objectsButton2.addActionListener(this); // detect when clicked
		buttonHolder.add(objectsButton2); // add to button holder panel
		
		// call method to set display as oop button at beginning
		updateDisplay(oopButton); 
		
		// description label
		descriptionLabel.setBounds(300, 200, 600, 380); // set the size and position
		descriptionLabel.setLineWrap(true); // text goes to next line
		descriptionLabel.setWrapStyleWord(true); // make sure every character in a word are together
		descriptionLabel.setHighlighter(null); // disable text highlighting
		descriptionLabel.setFont(new Font("Verdana", Font.PLAIN, 16)); // set the size of text
		descriptionLabel.setBackground(new Color(102, 153, 204)); // change background color to dark blue
		descriptionLabel.setEditable(false); // disable editing
		descriptionLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // create some padding between edges and text
		add(descriptionLabel); // add to main frame
		
		// create the back button
		backButton.setBounds(50, 580, 200, 50); // set the position and size
		backButton.setBackground(Color.orange); // set the button color to orange
		backButton.setFont(new Font("Verdana", Font.PLAIN, 30)); // set the size of the button text
		backButton.setFocusable(false); // disable outline
		backButton.addActionListener(this); // detect when clicked
		add(backButton); // add to the frame
		
		// make the frame visible
		setVisible(true);

	}
	
	// this message updates what text and image to display depending on what button was clicked
	private void updateDisplay(JButton buttonClicked) {
		
		// set sub-header text the same as the button that was clicked (the same for each button)
		subHeader.setText(buttonClicked.getText());
		
		// update text description to explain what OOP is
		if (buttonClicked == oopButton) {
			subHeader.setText("What is Object Oriented Programming (OOP)?"); // set specific sub header
			videoLink.setText("Check out this video on: Object Oriented Programming"); // change text of hyper link text
			videoLink.setName("https://www.youtube.com/watch?v=X3cFiJnxUBY"); // store the wanted link inside the component name
			Image newImg = new ImageIcon("images/oopimage.png").getImage().getScaledInstance(350, 240, java.awt.Image.SCALE_SMOOTH); // scale image
			image.setIcon(new ImageIcon(newImg)); // set image
			
			descriptionLabel.setText("Object-oriented programming (OOP) is a programming model that is based on the concept of \"objects\", "
					+ "which can contain data and code that manipulates that data. In OOP, objects are created from templates called classes, "
					+ "which define the properties/attributes and behavior of the objects.\n\n"
					+ "One of the main benefits of OOP is that it allows for code reuse and modularity. "
					+ "By creating classes, you can define objects that have a specific set of attributes and behaviors using field variables and methods, "
					+ "which all objects created using the class has access to them. \n\n"
					+ "As a result, objects can make it easier to write, maintain, and troubleshoot your code.");
		}
		
		// update text description to explain what a class is
		else if (buttonClicked == classesButton1) {
			videoLink.setText("Check out this video on: Classes"); // change text of hyper link text
			videoLink.setName("https://www.youtube.com/watch?v=7KnkdXXeQio"); // store the wanted link inside the component name
			image.setIcon(new ImageIcon("images/classimage.png")); // set image
			
			descriptionLabel.setText("A class is a template or blueprint that defines the properties and behaviors of an object "
					+ "without specifying the type of data it will hold. \n"
					+ "In other words, a class defines the structure and behavior of an object, "
					+ "but it does not define any specific values for the object's properties. "
					+ "It is just a description of what an object should look like and how it should behave. \n\n"
					+ "For example, if you wanted to create multiple objects of a dog, "
					+ "you would provide some attributes like the type of breed, its age, its gender, "
					+ "along with methods such as bark() or wagTail(). \n\n"
					+ "Every object made from this dog class would be able to define its own individual attributes "
					+ "and also have access to bark and wagTail().");
		}
		
		// update text description to explain how to create a template class
		else if (buttonClicked == classesButton2) {
			videoLink.setText("Check out this video on: Creating a Class in Java"); // change text of hyper link text
			videoLink.setName("https://www.youtube.com/watch?v=dDL4gpuQyzA"); // store the wanted link inside the component name
			image.setIcon(new ImageIcon("images/classexample.png")); // set image
			
			descriptionLabel.setText("To create a template class, there are 3 main things you need: "
					+ "your attributes or field variables, your constructor method, and your getters & setters \n\n"
					+ "Your field variables are declared at the top with whatever data type you need (int, String, boolean). "
					+ "Field variables are usually declared as private. \n\n"
					+ "Next is the constructor method. Only 1 constructor method can have the same name as your class and this "
					+ "is a special method that is used to initialize the object when it is created. "
					+ "The parameters of the constructor method should include all your field variables you declared. "
					+ "You should also call super() in your constructor because it calls the constructor of the superclass. \n\n"
					+ "Finally, you also have your getters and setters. Your getters and setters get the values or set the values of the object");
		}
		
		// update text description to explain what an object is
		else if (buttonClicked == objectsButton1) {
			videoLink.setText("Check out this video on: Introduction to Objects in Java"); // change text of hyper link text
			videoLink.setName("https://www.youtube.com/watch?v=EMoHBbSp6ZE"); // store the wanted link inside the component name
			Image newImg = new ImageIcon("images/objectimage.png").getImage().getScaledInstance(350, 240, java.awt.Image.SCALE_SMOOTH); // scale image
			image.setIcon(new ImageIcon(newImg)); // set image
			
			descriptionLabel.setText("An object is a virtual representation of real life objects created by a template class. "
					+ "Examples of an object would be animals, people, locations, a desk, a chair, and pretty much anything. \n\n"
					+ "Objects are a specific item that you create following the blueprint or template defined by a class. "
					+ "They have their own set of properties and behaviors. \n\n"
					+ "So a class is the blueprint, while an object is an instance of a class. ");
		}
		
		// update text description to explain how to create an object
		else if (buttonClicked == objectsButton2) {
			videoLink.setText("Check out this video on: Java objects (OOP)"); // change text of hyper link text
			videoLink.setName("https://www.youtube.com/watch?v=kd3dr39rgrk"); // store the wanted link inside the component name
			Image newImg = new ImageIcon("images/objectexample.png").getImage().getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH); // scale image
			image.setIcon(new ImageIcon(newImg)); // set image
			
			descriptionLabel.setText("An object is created using a class by using the \"new\" keyword and calling the constructor. "
					+ "The basic syntax to create an object is:\n\n"
					+ "ClassName objectName = new ClassName(); \n\n"
					+ "For example, if you have a class called \"Dog\", you can create an object of that class like this:\n\n"
					+ "Dog myDog = new Dog();\n\n"
//					+ "You can also use your constructor while creating an object and initialize your attribute values like: \n\n"
//					+ "ClassName objectName = new ClassName(param1, param2, ...);\n\n"
					+ "Also, if the class Dog has a constructor that takes a name, breed and age, you can create an object and set its attributes like this:\n\n"
					+ "Dog myDog = new Dog(\"Fido\", \"Labrador\", 3);\n\n"
					+ "This creates the object \"myDog\" of class \"Dog\" and sets the value in the class' constructor by sending the arguements.");
		}
	}

	// handles all button clicks
	public void actionPerformed(ActionEvent event) {
		
		// play click sound
		Audio.Play(Audio.MOUSE_CLICK);
		
		// check if back button was clicked
		if (event.getSource() == backButton) {
			
			dispose(); // close window and open next
			new CAIMainMenu(); // go to previous frame (main menu)
			
		}
		
		// check if back button was clicked
		else if (event.getSource() == videoLink) {
			String hyperlink = videoLink.getName(); // get hyperlink stored in the label's name
			
			// attempt to open up browser with link
			try {
				
				Desktop.getDesktop().browse(new URI(hyperlink)); // open new tab with source

			} catch (IOException | URISyntaxException e1) {
				
				e1.printStackTrace();
			}
		}
		
		// check if any concept button was clicked and update the display
		else {
		
			updateDisplay((JButton) event.getSource());
		
		}
	}
}