//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     MathGame.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   A driver class for a in interactive Math program to 
//                 help users learn to evaluate integer arithmetic expressions.
//
//********************************************************************


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MathGame {
	
	private Scanner input = new Scanner(System.in);
	private MathQuestionGenerator qGenerator = new BasicQuestionGenerator();
	private MathGrader grader = new MathGrader();
	private int correctScore = 0;
	private int incorrectScore = 0;
	private static BufferedWriter gameReport;
	
	public static void main(String[] args) {
		
		MathGame game = new MathGame();
		game.developerInfo();
		
		try { // instantiate game log file
			
			gameReport =  new BufferedWriter(new FileWriter("Project6-Output.txt"));
			gameReport.write("GAME RESULTS"); // add report header
			
		} catch (IOException e) {
			System.out.println("Error creating log file");
		}
		
		// play game until user quits
		boolean quit = false;
		while (!quit) {
			game.playRound(); 
			quit = game.upgradeOrQuit();
		}
		
		game.printAndLog("Good bye", false); // exit game
		try { //close resources		
			
			gameReport.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end main method
	
	
    //***************************************************************
    //
    //  Method:       getCorrectScore (Non Static)
    // 
    //  Description:  Returns the current number of correct questions
    //                answered by the user for the current round.
    //
    //  Parameters:   None
    //
    //  Returns:      int
    //
    //***************************************************************
	public int getCorrectScore() {
		return this.correctScore;
	}// end getCorrectScore method
	
    //***************************************************************
    //
    //  Method:       getIncorrectScore (Non Static)
    // 
    //  Description:  Returns the current number of incorrect questions
    //                answered by the user for the current round.
    //
    //  Parameters:   None
    //
    //  Returns:      int
    //
    //***************************************************************
	public int getIncorrectScore() {
		return this.incorrectScore;
	}//end getIncorrectScore
	
    //***************************************************************
    //
    //  Method:       resetScore (Non Static)
    // 
    //  Description:  Resets the correct and incorrect scores to 0.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	public void resetScore() {
		this.correctScore = 0;
		this.incorrectScore = 0;
	}// end resetScore method
	
    //***************************************************************
    //
    //  Method:       incrementCorrectScore (Non Static)
    // 
    //  Description:  Increments the user's correct questions score by 1.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	public void incrementCorrectScore() {
		this.correctScore++;
	}// end incrementCorrectScore method
	
    //***************************************************************
    //
    //  Method:       incrementCorrectScore (Non Static)
    // 
    //  Description:  Increments the user's incorrect questions score by 1.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	public void incrementIncorrectScore() {
		this.incorrectScore++;
	}// end incrementIncorrectScore method
	
    //***************************************************************
    //
    //  Method:       playRound (Non Static)
    // 
    //  Description:  
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public void playRound() {
		
		// get, display, and log question 
		printAndLog("", false);
		String question = qGenerator.newQuestion();
		printAndLog(question, false);
		
		// get, grade, and log answer
		int userAnswer = getValidInt();
		int expectedAnswer = qGenerator.getCurrentAnswer();
		boolean isCorrect = grader.grade(expectedAnswer, userAnswer); // checks if answer is correct
		printAndLog(grader.getRandomResponse(isCorrect), false); 
		
		while(!isCorrect) { // keep displaying same question until user gets it correct
			
			incrementIncorrectScore();
			printAndLog("", false);
			printAndLog(question, false);
			userAnswer = getValidInt();
			isCorrect = grader.grade(expectedAnswer, userAnswer);
			printAndLog(grader.getRandomResponse(isCorrect), false);

		}
		
		incrementCorrectScore(); // user has entered the correct answer, increment score
		
	}// end playRound method
	
    //***************************************************************
    //
    //  Method:       getPerformance (Non Static)
    // 
    //  Description:  Returns the number of correct and incorrect answers
    //                given by the user for the round.
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //***************************************************************
	public String getPerformance() {
		
		String report = String.format("%n%-11s%s%n%-11s%s%n",
				"Correct: ", getCorrectScore(),
				"Incorrect: ", getIncorrectScore());
		
		return report;
		
	}// end getPerformance method
	
    //***************************************************************
    //
    //  Method:       isUpgradable (Non Static)
    // 
    //  Description:  Checks if the user's current score makes the user
    //                eligible for an upgrade (difficulty) round.
    //
    //  Parameters:   None
    //
    //  Returns:      boolean
    //
    //***************************************************************
	public boolean isUpgradable() {
		
		boolean isUpgradable = false;
		if(getCorrectScore() >= 5) {
			isUpgradable = true;
		}
		
		return isUpgradable;
		
	}// end isUpgradable method
	
    //***************************************************************
    //
    //  Method:       playForUpgrade (Non Static)
    // 
    //  Description:  Initiates a special round of questions (5) that 
    //                the user must answer correctly in order to 
    //                upgrade to the next difficulty level.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	public void playForUpgrade() {
		
		resetScore();
		while(correctScore < 5) {// must answer 5 questions correctly then upgrade
			playRound();
		}
		
		//user has passed upgrade round
		printAndLog(getPerformance(), false);
		upgradeDifficulty();
		
	}// end playForUpgrade method
	
    //***************************************************************
    //
    //  Method:       upgradeOrQuit (Non Static)
    // 
    //  Description:  Prompts the user to choose between upgrading 
    //                difficulty level or quitting the program. 
    //
    //  Parameters:   None
    //
    //  Returns:      boolean
    //
    //***************************************************************
	public boolean upgradeOrQuit() {
		
		boolean quit = false;
		String selection = null;
		
		if(isUpgradable() && qGenerator.getDifficultyLevel().equals("Advanced")) { // upgrading is not allowed (already advanced)
			printAndLog("", false);
			printAndLog("----------------------------------------", false);
			printAndLog("Enter any key to continue or 'q' to quit", false);
			printAndLog("----------------------------------------", false);
			// allow user to quit only
			selection = getUserInput("Selection: ");
			if(selection.equalsIgnoreCase("q")) {
				quit = true;
			} else if(selection.equals("u")) {
				printAndLog("----------------------------------------", false);
				printAndLog("Unable to upgrade", false);
				printAndLog("----------------------------------------", false);
			}
			
		} else if(isUpgradable()){ // upgrade conditions are met
			
			printAndLog(getPerformance(), false);
			printAndLog("---------------------------------", false);
			printAndLog("Enter 'u' to upgrade difficulty", false);
			printAndLog("Enter 'q' to quit", false);
			printAndLog("( Or enter any key to continue )", false);
			printAndLog("---------------------------------", false);
			selection = getUserInput("Selection: ");

			if(selection.equalsIgnoreCase("u")) { // user selected upgrade difficulty
				printAndLog("-----------------------------------------------", false);
				printAndLog("Answer 5 questions correctly to upgrade...", false);
				printAndLog("-----------------------------------------------", false);
				playForUpgrade();
			} else if(selection.equalsIgnoreCase("q")) {
				quit = true;
			}
		}
		
		return quit;
		
	}// end upgradeOrQuit method
	
    //***************************************************************
    //
    //  Method:       upgradeDifficulty (Non Static)
    // 
    //  Description:  Instantiates a new MathQuestionGenerator based on
    //                the difficulty level to be upgraded to.
    //
    //  Parameters:   None
    //
    //  Returns:      void
    //
    //***************************************************************
	public void upgradeDifficulty() {
		
		String oldDifficulty = qGenerator.getDifficultyLevel(); // current (old) difficulty level
		
		if(oldDifficulty.equals("Basic")) { // Basic -> Intermediate
		
			this.qGenerator = new IntermediateQuestionGenerator();
			
		} else if(oldDifficulty.equals("Intermediate")) { // Intermediate -> Advanced
			
			this.qGenerator = new AdvancedQuestionGenerator();
		}
		
		// Report action to user
		String newDifficulty = qGenerator.getDifficultyLevel();
		printAndLog("--------------------------------------------------------", false);
		printAndLog("Difficulty upgraded from [ " + oldDifficulty + " ] -> [ " + newDifficulty + " ]", false);
		printAndLog("--------------------------------------------------------", false);;
		resetScore();
		
	}// end upgradeDifficulty method
	
    //***************************************************************
    //
    //  Method:       getUserInput (Non Static)
    // 
    //  Description:  Gets a response from the user. Logs response to 
    //                game log file using helper (printAndLog) method.
    //
    //  Parameters:   String (a message to be prompted to user)
    //
    //  Returns:      String (user response)
    //
    //***************************************************************
	public String getUserInput(String promptMsg) {
		
		String answer = "";
		System.out.print(promptMsg);
		answer = input.nextLine();
		
		printAndLog((promptMsg + answer), true); // log prompt and response
		
		return answer;
		
	}// end getUserInput method
	
    //***************************************************************
    //
    //  Method:       getValidInt (Non Static)
    // 
    //  Description:  Gets a response from the user.
    //
    //  Parameters:   String (a message to be prompted to user)
    //
    //  Returns:      String (user response)
    //
    //***************************************************************
	public int getValidInt() {
		
		boolean isValid = false;
		int answer = 0;
		
		while(!isValid) {
			String userAnswer = getUserInput("Answer: ");
			try {
				answer = Integer.parseInt(userAnswer);
				isValid = true;
			} catch (NumberFormatException e) {
				printAndLog("( Must enter an integer )", false);
			}
		}
		
		return answer;

	}// end getValidInt method
	
    //***************************************************************
    //
    //  Method:       printAndLog (Non Static)
    // 
    //  Description:  A method used to print (optional) and log a String
    //                to the game log file.
    //
    //  Parameters:   String (content to be printed (optional) and logged)
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void printAndLog(String content, boolean logOnly) {
		
		if(!logOnly) { // print to console if logOnly is false
			System.out.println(content); 
		}
		
		try {
			gameReport.write("\n" + content); // log to game record file
		} catch (IOException e) {
			printAndLog("Error logging to file", false);
		}
		
	}// end printAndLog method
	
	
    //***************************************************************
    //
    //  Method:       developerInfo (Non Static)
    // 
    //  Description:  The developer information method of the program.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:    Jeremy Aubrey");
       System.out.println("Course:  COSC 4301 Modern Programming");
       System.out.println("Program: 6");

    } // end developerInfo method
    
}// end MathGame class