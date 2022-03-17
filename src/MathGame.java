import java.util.Scanner;

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

public class MathGame {
	
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MathGame game = new MathGame();
		game.developerInfo();
		
		//get grader
		MathGrader grader = new MathGrader();
		
		// get a MathQuestionGenerator
		MathQuestionGenerator qGenerator = new BasicQuestionGenerator();
		MathQuestionGenerator qGenerator2 = new IntermediateQuestionGenerator();
		MathQuestionGenerator qGenerator3 = new AdvancedQuestionGenerator();
		
		qGenerator.generateNewQuestion();
		qGenerator2.generateNewQuestion();
		qGenerator3.generateNewQuestion();
		
		qGenerator.dispalyData();
		qGenerator2.dispalyData();
		qGenerator3.dispalyData();
		
				
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