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

import java.util.Scanner;

public class MathGame {
	
	private Scanner input = new Scanner(System.in);
	private MathQuestionGenerator qGenerator = new BasicQuestionGenerator();
	private MathGrader grader = new MathGrader();
	private int score = 0;
	
	public static void main(String[] args) {
		
		MathGame game = new MathGame();
		game.developerInfo();
		
		boolean quit = false;
		
		while (!quit) {
			
			game.playRound(); 
			quit = game.upGradeOrQuit();
		}
		
		System.out.println("Good bye");
	}
	
	public void playRound() {
		System.out.println("");
		String question = qGenerator.newQuestion();
		System.out.println(question);
		
		int userAnswer = getValidInt();
		int expectedAnswer = qGenerator.getCurrentAnswer();
		boolean isCorrect = grader.grade(expectedAnswer, userAnswer);
		System.out.println(grader.getRandomResponse(isCorrect));
		
		while(!isCorrect) { // keep asking same question until user gets correct
			
			System.out.println("");
			System.out.println(question);
			userAnswer = getValidInt();
			isCorrect = grader.grade(expectedAnswer, userAnswer);
			System.out.println(grader.getRandomResponse(isCorrect));

		}
		
		score++; // user has entered the correct answer, increment score
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public boolean isUpgradable() {
		
		boolean isUpgradable = false;
		if(getScore() >= 5) {
			isUpgradable = true;
		}
		
		return isUpgradable;
	}
	
	public void playForUpgrade() {
		resetScore(); // score is 0
		// must answer 5 questions correctly then upgrade
		while(score < 5) {
			playRound();
		}
		
		upgradeDifficulty();
	}
	
	public boolean upGradeOrQuit() {
		
		boolean quit = false;
		String selection = null;
		
		if(isUpgradable() && qGenerator.getDifficultyLevel().equals("Advanced")) { 
			System.out.println("Enter any key to continue or q to quit");
			// allow user to quit only
			selection = getValidSelection();
			if(selection.equalsIgnoreCase("q")) {
				quit = true;
			} else if(selection.equals("u")) {
				System.out.println("Unable to upgrade");
			}
			
		} else if(isUpgradable()){ // upgrade conditions are met
			
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("Enter 'u' to upgrade difficulty");
			System.out.println("Enter 'q' to quit");
			System.out.println("( Or enter any key to continue )");
			System.out.println("---------------------------------");
			selection = getValidSelection();

			if(selection.equalsIgnoreCase("u")) { // user selected upgrade difficulty
				System.out.println("-----------------------------------------------");
				System.out.println("Answer 5 questions correctly to upgrade...");
				System.out.println("-----------------------------------------------");
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
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("Difficulty upgraded from [ " + oldDifficulty + " ] -> [ " + newDifficulty + " ]");
		System.out.println("--------------------------------------------------------------");;
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
				
				System.out.println("( Must enter an integer )");
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
				
				System.out.println("Enter 'u' to upgrade diffculty or 'q' to quit");
			}
		}
		
		return selection;
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