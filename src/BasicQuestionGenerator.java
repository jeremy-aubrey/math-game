//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     BasicQuestionGenerator.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   A concrete class that extends the abstract MathQuestionGenerator
//                 class. It sets its operand count to 2, operator count to 1 which 
//                 is used to generate its questions.
//
//********************************************************************

public class BasicQuestionGenerator extends MathQuestionGenerator{
	
	// constructor
	public BasicQuestionGenerator() {
		//operand count, operator count, difficulty
		super(2, 1, "Basic");
	}//end constructor
	
}//end BasicQuestionGenerator class