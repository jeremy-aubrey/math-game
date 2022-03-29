//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     AdvancedQuestionGenerator.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   A concrete class that extends the abstract MathQuestionGenerator
//                 class. It sets its operand count to 4, operator count to 3 which 
//                 is used to generate its questions.
//
//********************************************************************

public class AdvancedQuestionGenerator extends MathQuestionGenerator{

	// constructor
	public AdvancedQuestionGenerator() {
		//operand count, operator count, difficulty
		super(4, 3, "Advanced");
	}//end constructor

}//end AdvancedQuestionGenerator class