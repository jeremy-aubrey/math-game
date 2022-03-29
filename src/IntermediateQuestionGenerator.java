//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     IntermediateQuestionGenerator.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   A concrete class that extends the abstract MathQuestionGenerator
//                 class. It sets its operand count to 3, operator count to 2 which 
//                 is used to generate its questions.
//
//********************************************************************

public class IntermediateQuestionGenerator extends MathQuestionGenerator{

	// constructor
	public IntermediateQuestionGenerator() {
		//operand count, operator count, difficulty
		super(3, 2, "Intermediate");
	}// end constructor
	
}// end IntermediateQuestionGenerator class