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
	public static void main(String[] args) {
		
		MathGame game = new MathGame();
		game.developerInfo();
		
		// get a MathQuestionGenerator
		MathQuestionGenerator qGenerator = new BasicQuestionGenerator();
		MathQuestionGenerator qGenerator2 = new IntermmediateQuestionGenerator();
		MathQuestionGenerator qGenerator3 = new AdvancedQuestionGenerator();
		
		System.out.println(qGenerator.getQuestion());
		System.out.println(qGenerator2.getQuestion());
		System.out.println(qGenerator3.getQuestion());
		
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