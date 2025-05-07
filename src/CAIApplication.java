// Programmer: Paul Xiong
// Date: January 20th, 2023
// Project: CAI Tool: Modular Programming - Classes & Objects
// Description: A GUI application that teaches about object oriented programming with concepts, an activity, and assessment
		// Concepts: Providing explanations, images, and video source about object oriented programming
		// Activity: Displayed 8 buttons where 7 match with the chosen object and 1 doesn't. Click the odd one out
		// Assessment: A series of 10 questions multiple choice questions in a scrolling frame based on the concepts
// Major Skills: Arrays, Swing GUI Components, Template Class, Reading from a file, Enhanced for-loop, Try-and-catch

// -- Added Features -- \\
// File input. Reads in a csv file of objects for activity, and questions for assessment. Easily add more information to file to add more objects/questions
// Audio Class. Plays sound when: a button is clicked, finishing assessment or activity, and getting an activity answer correct/wrong
// Hyper link at bottom of concepts screen. Each concept has its own youtube video that you can click on to open up a browser with the video
// Activity end screen. After timer runs out in activity, an end screen will show, displaying score, high score, and more.

// -- Areas of Concern -- \\
// Make sure all images and data files are downloaded

// this class calls the main menu and load in arrays
public class CAIApplication {

	// declare global arrays
	public static QuestionTemplate[] QuestionsArray; // used for assessment questions
	public static ObjectTemplate[] ObjectsArray; // used for activity objects

	// main method
	public static void main(String[] args) {

		// read in files and load in global arrays only once at the start
		CAIFileInput.loadQuestions();
		CAIFileInput.loadObjects();
		
		// open main menu of application
		new CAIMainMenu();
		
	}
}
