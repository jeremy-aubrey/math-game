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
	
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MathGame game = new MathGame();
		game.developerInfo();
		
		//get grader
		MathGrader grader = new MathGrader();
		
		// get a MathQuestionGenerator
		MathQuestionGenerator qGenerator = new BasicQuestionGenerator();
		
		String question = qGenerator.newQuestion();
		
		while (true) {
			
			int ans;
			try {
				
				System.out.println(question);
				ans = Integer.parseInt(game.getUserAnswer());
				int expected = qGenerator.getCurrentAnswer();
				boolean isCorrect = grader.grade(ans, expected);
				System.out.println(grader.getRandomResponse(isCorrect));
 				
				if(isCorrect) {
					System.out.println("getting new question");
 					question = qGenerator.newQuestion();
 					System.out.println(qGenerator.getCurrentQuestion());
 				}
				
				
			} catch (NumberFormatException e) {

				e.printStackTrace();
			}
		}
		
		
	}
	
	public String getUserAnswer() {
		
		String answer = "";
		System.out.print("Please enter a number: ");
		answer = input.nextLine();

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