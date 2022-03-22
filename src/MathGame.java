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
		
		boolean isPlaying = true;
		
		while (isPlaying) {
			
			game.playRound(); // score ++ 
//			isPlaying = game.upGradeOrQuit();
		}
		
		
	}
	
	public void playRound() {

		String question = qGenerator.newQuestion();
		System.out.println(question);
		
		int userAnswer = getValidInt();
		int expectedAnswer = qGenerator.getCurrentAnswer();
		boolean isCorrect = grader.grade(expectedAnswer, userAnswer);
		System.out.println(grader.getRandomResponse(isCorrect));
		
		while(!isCorrect) { // keep asking same question until user gets correct
			
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
	
	public boolean isUpgradable() {
		
		boolean isUpgradable = false;
		if(getScore() >= 5 && !qGenerator.getDifficultyLevel().equals("Advanced")) {
			isUpgradable = true;
		}
		
		return isUpgradable;
	}
	
	public boolean upGradeOrQuit() {
		
		boolean quit = false;
		
		if(qGenerator.getDifficultyLevel().equals("Advanced")) {
			// allow user to quit only
			
		} else { // difficulty is either basic or intermediate 
			
			
			
		}
		
		return quit;
	}
	
	public void upGradeDifficulty() {
		
		String oldDifficulty = qGenerator.getDifficultyLevel();
		
		if(oldDifficulty.equals("Basic")) { // Basic -> Intermediate
		
			this.qGenerator = new IntermediateQuestionGenerator();
			
		} else if(oldDifficulty.equals("Intermediate")) { // Intermediate -> Advanced
			
			this.qGenerator = new AdvancedQuestionGenerator();
		}
		
		String newDifficulty = qGenerator.getDifficultyLevel();
		
		System.out.println("Difficulty upgraded from [ " + oldDifficulty + " ] -> [ " + newDifficulty + " ]");
		
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
			
			System.out.print("Answer: ");
			String userAnswer = input.nextLine();
			
			try {
				
				answer = Integer.parseInt(userAnswer);
				isValid = true;
				
			} catch (NumberFormatException e) {
				
				System.out.println("Must enter an integer");
			}
		}
		
		return answer;

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