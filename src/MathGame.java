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
		
		try {
			
			gameReport =  new BufferedWriter(new FileWriter("Project6-Output.txt"));
			gameReport.write("GAME RESULTS");
			
		} catch (IOException e) {
			
			System.out.println("Error creating log file");
		}
		
		boolean quit = false;
		
		while (!quit) {
			
			game.playRound(); 
			quit = game.upGradeOrQuit();
			
		}
		
		game.printAndLog("Good bye", false);
		
		try {
			
			gameReport.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void playRound() {
		
		printAndLog("", false);
		String question = qGenerator.newQuestion();
		printAndLog(question, false);
		
		int userAnswer = getValidInt();
		String loggedAnswer = "Answer: " + String.valueOf(userAnswer);
		printAndLog(loggedAnswer, true); //log only
		int expectedAnswer = qGenerator.getCurrentAnswer();
		boolean isCorrect = grader.grade(expectedAnswer, userAnswer);
		printAndLog(grader.getRandomResponse(isCorrect), false);
		
		while(!isCorrect) { // keep asking same question until user gets correct
			
			incrementIncorrectScore();
			printAndLog("", false);
			printAndLog(question, false);
			userAnswer = getValidInt();
			isCorrect = grader.grade(expectedAnswer, userAnswer);
			printAndLog(grader.getRandomResponse(isCorrect), false);

		}
		
		incrementCorrectScore(); // user has entered the correct answer, increment score
	}
	
	public int getCorrectScore() {
		return this.correctScore;
	}
	
	public int getIncorrectScore() {
		return this.incorrectScore;
	}
	
	public void resetScore() {
		this.correctScore = 0;
		this.incorrectScore = 0;
	}
	
	public void incrementCorrectScore() {
		this.correctScore++;
	}
	
	public void incrementIncorrectScore() {
		this.incorrectScore++;
	}
	
	public String getPerformance() {
		
		String report = String.format("%n%-11s%s%n%-11s%s%n",
				"Correct: ", getCorrectScore(),
				"Incorrect: ", getIncorrectScore());
		
		return report;
	}
	
	public boolean isUpgradable() {
		
		boolean isUpgradable = false;
		if(getCorrectScore() >= 5) {
			isUpgradable = true;
		}
		
		return isUpgradable;
	}
	
	public void playForUpgrade() {
		resetScore(); // score is 0
		// must answer 5 questions correctly then upgrade
		while(correctScore < 5) {
			playRound();
		}
		
		printAndLog(getPerformance(), false);
		upgradeDifficulty();
	}
	
	public boolean upGradeOrQuit() {
		
		boolean quit = false;
		String selection = null;
		
		if(isUpgradable() && qGenerator.getDifficultyLevel().equals("Advanced")) { 
			printAndLog("", false);
			printAndLog("----------------------------------------", false);
			printAndLog("Enter any key to continue or 'q' to quit", false);
			printAndLog("----------------------------------------", false);
			// allow user to quit only
			selection = getValidSelection();
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
			selection = getValidSelection();

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
	}
	
	public void upgradeDifficulty() {
		
		String oldDifficulty = qGenerator.getDifficultyLevel();
		
		if(oldDifficulty.equals("Basic")) { // Basic -> Intermediate
		
			this.qGenerator = new IntermediateQuestionGenerator();
			
		} else if(oldDifficulty.equals("Intermediate")) { // Intermediate -> Advanced
			
			this.qGenerator = new AdvancedQuestionGenerator();
		}
		
		String newDifficulty = qGenerator.getDifficultyLevel();
		printAndLog("--------------------------------------------------------", false);
		printAndLog("Difficulty upgraded from [ " + oldDifficulty + " ] -> [ " + newDifficulty + " ]", false);
		printAndLog("--------------------------------------------------------", false);;
		resetScore();
		
	}
	
	public String getUserInput(String promptMsg) {
		
		String answer = "";
		System.out.print(promptMsg);
		answer = input.nextLine();
		
		
		return answer;
		
	}
	
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

	}
	
	public String getValidSelection() {
		
		String[] options = {"u", "q"}; // list of valid options
		boolean isValid = false;
		String selection = "";
		
		while(!isValid) {
			
			String userAnswer = getUserInput("Selection: ");
			
			try {
				
				for(String option : options) {
					if(userAnswer.equalsIgnoreCase(option)) {
						selection = userAnswer;
					}
				}
				
				isValid = true;
				
			} catch (NumberFormatException e) {
				
				printAndLog("Enter 'u' to upgrade diffculty or 'q' to quit", false);
			}
		}
		
		return selection;
	}
	
	private void printAndLog(String content, boolean logOnly) {
		
		if(!logOnly) {
			System.out.println(content);
		}
		
		try {
			
			gameReport.write("\n" + content); // log to game record file
			
		} catch (IOException e) {
			
			printAndLog("Error logging to file", false);
		}
	}
	
	
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