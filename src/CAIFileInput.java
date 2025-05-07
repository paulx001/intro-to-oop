import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

// this class reads both the questions and object csv files and stores them into the global arrays in CAIApplication
public class CAIFileInput {

	// call this method to read in questions file and store in QuestionsArray
	public static void loadQuestions() {

		// search for file
		try {

			// read in file
			Scanner scanNumOfLines = new Scanner(new File("data/questions.csv")); 	
			
			// initialize variable used to find number of objects in file (used later to set size of the array)
			int numOfQuestions = 0;

			// loop through until no more data left
			while (scanNumOfLines.hasNextLine()) {

				scanNumOfLines.nextLine();
				numOfQuestions++; // increment number of questions

			}
			
			// close the file
			scanNumOfLines.close();
			
			// read in file a 2nd time
			Scanner inputFile = new Scanner(new File("data/questions.csv")); 
			
			// initialize object array size using number of questions
			CAIApplication.QuestionsArray = new QuestionTemplate[numOfQuestions];

			for (int questionNumber = 0; questionNumber < CAIApplication.QuestionsArray.length; questionNumber++) {
				
				String[] questionInfo = inputFile.nextLine().split(","); // split each entry into a table
				JToggleButton[] buttons = new JToggleButton[questionInfo.length - 3]; // number of options is excluding the button type, question itself, & answer index 
				String[] answers = null; // store the correct answers (radio buttons will have 1 answer, check boxes have more)
				JLabel questionAsked = null; // the 'actual' question being asked
				String buttonType = null; // radio button or check box
				
				// looping through file a 2nd time to determine what values to be set at index
				for (int index = 0; index < questionInfo.length; index++) {

					// in entry, replace the comma placeholder '|' to become a comma (since commas can't be used directly in csv file)
					questionInfo[index] = questionInfo[index].replace("|", ",");
					
					// the question will always be first
					if (index == 0) {
						questionAsked = new JLabel(questionInfo[index]);
						questionAsked.setText(String.format("%d. %s", questionNumber + 1, questionInfo[index]));
					}

					// the button type will be the next index
					else if (index == 1) 
						buttonType = questionInfo[index];

					// the 3rd entry will be the index of the answers
					else if (index == 2) 
						answers = questionInfo[index].split("");

					// everything else after are one of the options
					else { 
						
						// create a radio button or check box depending on button type
						if (buttonType.equals("Radiobutton"))  
							buttons[index - 3] = new JRadioButton(questionInfo[index]);
					
						else 
							buttons[index - 3] = new JCheckBox(questionInfo[index]);
					}
				}

				// set the same index with the number into a new question
				CAIApplication.QuestionsArray[questionNumber] = new QuestionTemplate(questionAsked, buttonType, buttons, answers);

			}

			// close the file
			inputFile.close();

		} catch (FileNotFoundException e) {
			// display error message
			System.out.println("File Error");

		}

	}

	// call this method to read in objects file and store in ObjectsArray
	public static void loadObjects() {
		
		// search for file
		try {

			// read in file
			Scanner scanNumOfLines = new Scanner(new File("data/objects.csv")); 	
			
			// initialize variable used to find number of objects in file (used later to set size of the array)
			int numOfObjects = 0;

			// loop through each line until no more data is left
			while (scanNumOfLines.hasNextLine()) {

				scanNumOfLines.nextLine();
				numOfObjects++; // increment number of objects

			}
			
			// close file
			scanNumOfLines.close(); 
			
			// read in file a 2nd time
			Scanner inputFile = new Scanner(new File("data/objects.csv")); 

			// initialize object array size using number of objects
			CAIApplication.ObjectsArray = new ObjectTemplate[numOfObjects];

			// looping through file a 2nd time to determine what values to be set at index
			for (int objectNumber = 0; objectNumber < CAIApplication.ObjectsArray.length; objectNumber++) {
				
				String[] objectInfo = inputFile.nextLine().split(","); // split each entry into a table
				JButton[] buttons = new JButton[8]; // number of buttons will always be 8
				String[] answers = new String[5]; // number of 'incorrect answers' will always be 5
				String nameOfObject = null; // initialize name of object
				
				// determine what is in each entry (file is set in a specific way)
				for (int index = 0; index < objectInfo.length; index++) {
					
					// index 0 will always be the name of the object
					if (index == 0)
						nameOfObject = objectInfo[index];

					// index 1-7 are the object's normal attributes
					else if (index <= 7)
						buttons[index - 1] = new JButton(objectInfo[index]);

					// everything else are part of the answers
					else
						answers[index - 8] = objectInfo[index];
				}

				// set the same index with the number into a new object
				CAIApplication.ObjectsArray[objectNumber] = new ObjectTemplate(nameOfObject, buttons, answers);

			}

			// close the file
			inputFile.close();

		} catch (FileNotFoundException e) {
			// display error message
			System.out.println("File Error");

		}
	}
	
}