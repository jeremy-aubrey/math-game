//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     MathGrader.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   A class that performs grading operations and provides
//                 responses to correct and incorrect answers.
//
//********************************************************************

public class MathGrader {
	
	private final String[] correctResponses = {
			"Excellent!",
			"Very good!",
			"Nice work!",
			"Way to go!",
			"Keep up the good work"
	};
	
	private final String[] incorrectResponses = {
		"That is incorrect!",
		"No. Please try again!",
		"Wrong, Try once more!",
		"No. Don't give up!",
		"Incorrect. Keep tyring!"
	};
	
    //***************************************************************
    //
    //  Method:       getRandomResponse (Non Static)
    // 
    //  Description:  Returns a random response based on the value of the
    //                argument (correct or incorrect).
    //
    //  Parameters:   boolean (correct or incorrect)
    //
    //  Returns:      String (response)
    //
    //***************************************************************
	public String getRandomResponse(boolean isCorrect) {
		
		String response = "";
		int indexRange = 0;
		
		if(isCorrect) {
			indexRange = correctResponses.length - 1;
			response = correctResponses[(int)(Math.random() * 100) % indexRange];
		} else {
			indexRange = incorrectResponses.length - 1;
			response = incorrectResponses[(int)(Math.random() * 100) % indexRange];
		}
		
		return response;
		
	}// end getRandomResponse method
	
    //***************************************************************
    //
    //  Method:       grade (Non Static)
    // 
    //  Description:  Returns true if expected answer and actual answer 
    //                match, otherwise false.
    //
    //  Parameters:   int (expected), int (actual)
    //
    //  Returns:      boolean
    //
    //***************************************************************
	public boolean grade(int expectedAnswer, int actualAnswer) {
		
		boolean isCorrect = false;
		if(expectedAnswer == actualAnswer) {
			isCorrect = true;
		}
		
		return isCorrect;
		
	}// end grade method
	
}//end MathGrader class