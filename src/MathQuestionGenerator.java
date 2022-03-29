//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     MathQuestionGenerator.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      04/03/2022
//
//  Instructor:    Fred Kumi 
//
//  Description:   An abstract class that generates basic math questions
//                 and calculates answers.
//
//********************************************************************

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MathQuestionGenerator {
	
	private String difficultyLevel; // Basic, Intermediate, or Advanced
	private String currentQuestion;
	private int currentAnswer;
	
	private String[] operators = {"*", "%", "+", "-"};
	private int[] currentOperands;
	private String[] currentOperators;
	
	// constructor
	public MathQuestionGenerator(int operandsCount, int operatorsCount, String difficultyLevel) {
		
		this.currentOperands = new int[operandsCount]; // set size based on difficulty
		this.currentOperators = new String[operatorsCount]; // set size based on difficulty
		this.difficultyLevel = difficultyLevel; // set descriptor based on difficulty
		
		setOperands();// initial values 
		setOperators();// initial values
		
	}// end constructor
	
    //***************************************************************
    //
    //  Method:       getCurrentAnswer (Non Static)
    // 
    //  Description:  Returns the current question's answer.
    //
    //  Parameters:   None
    //
    //  Returns:      int
    //
    //***************************************************************
	public int getCurrentAnswer() {
		return currentAnswer;
	}// end getCurrentAnswer method
	
    //***************************************************************
    //
    //  Method:       getCurrentQuestion (Non Static)
    // 
    //  Description:  Returns the current question.
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //***************************************************************
	public String getCurrentQuestion() {
		return currentQuestion;
	}// end getCurrentQuestion method
	
    //***************************************************************
    //
    //  Method:       getDifficultyLevel (Non Static)
    // 
    //  Description:  Returns the current difficulty level.
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //***************************************************************
	public String getDifficultyLevel() {
		return difficultyLevel;
	}// end getDifficultyLevel method
	
    //***************************************************************
    //
    //  Method:       setCurrentQuestion (Non Static)
    // 
    //  Description:  Setter for current question.
    //
    //  Parameters:   Sting (new question)
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void setCurrentQuestion(String question) {
		this.currentQuestion = question;
	}// end setCurrentQuestion method
	
    //***************************************************************
    //
    //  Method:       setCurrentAnswer (Non Static)
    // 
    //  Description:  Setter for current answer.
    //
    //  Parameters:   int (new answer)
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void setCurrentAnswer(int answer) {
		this.currentAnswer = answer;
	}// end setCurrentAnswer method
	
    //***************************************************************
    //
    //  Method:       setOperands (Non Static)
    // 
    //  Description:  Sets operands to random numbers between 1 and 9.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void setOperands() {
		
		SecureRandom rand = new SecureRandom();
		int operandsSize = currentOperands.length;
		for(int i = 0; i < operandsSize; i++) {
			int randomNum = rand.nextInt(9) + 1; // 1 - 9
			currentOperands[i] = randomNum;
		}
	}// end setOperands method
	
    //***************************************************************
    //
    //  Method:       setOperators (Non Static)
    // 
    //  Description:  Sets operators to one of available operators (*, %, 
    //                +, -) using randomly (using a random index).
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void setOperators() {
		
		int operatorsSize = currentOperators.length;
		int availableOperators = operators.length;
		for(int i = 0; i < operatorsSize; i++) {
			int randomIndex = (int)(Math.random() * 100) % availableOperators; //random index
			currentOperators[i] = operators[randomIndex];
		}
	}// end setOperators method
	
    //***************************************************************
    //
    //  Method:       newQuestion (Non Static)
    // 
    //  Description:  Returns a new math question.
    //
    //  Parameters:   None
    //
    //  Returns:      String (new question)
    //
    //***************************************************************
	public String newQuestion() {
		
		generateNewQuestion(); // generates and sets a new question 
		int answer = calculateCurrentAnswer(); // calculates answer
		setCurrentAnswer(answer); // sets answer
		
		return getCurrentQuestion(); // return new question
		
	}// end newQuestion method
	
    //***************************************************************
    //
    //  Method:       generateNewQuestion (Non Static)
    // 
    //  Description:  Generates a new math question and sets it as the
    //                current question.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
	private void generateNewQuestion() {
		
		setOperands(); // populate random operands
		setOperators(); // populate random operators
		
		String question = "";
		StringBuilder builder = new StringBuilder();
		builder.append(currentOperands[0]); //first operand, now arrays are of even length
		for(int i = 0; i < currentOperators.length; i++) {
			builder.append(" " + currentOperators[i] + " ");
			builder.append(currentOperands[i + 1]);
		}
		builder.append(" ?");
		question = builder.toString();
		
		setCurrentQuestion(question);
		
	}// end generateNewQuestion method
	
    //***************************************************************
    //
    //  Method:       calculateCurrentAnswer (Non Static)
    // 
    //  Description:  Converts current operators and operands to lists 
    //                and passes them to a helper method for calculation. 
    //                Returns the result of the calculation.
    //
    //  Parameters:   None
    //
    //  Returns:      int (current answer)
    //
    //***************************************************************
	private int calculateCurrentAnswer() {
		
		int answer = 0;

		List<String> operators = new ArrayList<String>(Arrays.asList(currentOperators));
		List<Integer> operands = Arrays.stream(currentOperands)
				.boxed()
				.collect(Collectors.toList());

		answer = calculate(operators, operands);
		
		return answer;
		
	}// end calculateCurrentAnswer method
	
    //***************************************************************
    //
    //  Method:       calculate (Non Static)
    // 
    //  Description:  Applies math operator precedence to calculate an 
    //                answer recursively from a list of operators and a
    //                list of operands.
    //
    //  Parameters:   List<String> (operators), List<Integer> (operands)
    //
    //  Returns:      int (result)
    //
    //***************************************************************
	private int calculate(List<String> operators, List<Integer> operands) {
		
		if(operators.isEmpty()) {
			
			return (int)operands.get(0); // last element will be calculated result
			
		} else {
			
			if(operators.contains("*") || operators.contains("%")) { /* MULTIPLICATION / MODULUS */
				
				//determine indexes 
				int multiplyIndex = operators.indexOf("*");
				int modulusIndex = operators.indexOf("%");
				
				if(operators.contains("*") && multiplyIndex < modulusIndex || !operators.contains("%")) { //modulo not present or multiply is first
					
					// perform multiplication operation 
					int product = operands.get(multiplyIndex) * operands.get(multiplyIndex + 1);
					// reduce operators
					operators.remove(multiplyIndex); // remove * operation 
					// reduce operands
					operands.set(multiplyIndex, product); // insert product at 1st operand index [..., x, y, ...] -> [..., prod, y, ...]
					operands.remove(multiplyIndex + 1); // remove 2nd operand [..., prod, y, ...] -> [..., prod, ...]
					// recurse with updated lists
					return (int)calculate(operators, operands); 
					
				} else { // modulo is first or multiply not present
					
					// perform modulus operation 
					int remainder = operands.get(modulusIndex) % operands.get(modulusIndex + 1);
					// reduce operators
					operators.remove(modulusIndex);
					// reduce operands
					operands.set(modulusIndex, remainder); // insert remainder at 1st operand index [..., x, y, ...] -> [..., rem, y, ...]
					operands.remove(modulusIndex + 1); // remove 2nd operand [..., rem, y, ...] -> [..., rem, ...]
					// recurse with updated lists
					return (int)calculate(operators, operands); 
					
				}
					
			}  else  { /* ADDITION / SUBTRACTION */
				
				//determine indexes
				int additionIndex = operators.indexOf("+");
				int subtractionIndex = operators.indexOf("-");
				
				if(operators.contains("+") && additionIndex < subtractionIndex || !operators.contains("-")) { //subtraction not present or add is first
					
					// perform addition
					int sum = operands.get(additionIndex) + operands.get(additionIndex + 1);
					// reduce operators
					operators.remove(additionIndex);
					// reduce operands
					operands.set(additionIndex, sum); // insert sum at 1st operand index [..., x, y, ...] -> [..., sum, y, ...]
					operands.remove(additionIndex + 1); // remove 2nd operand [..., sum, y, ...] -> [..., sum, ...]
					// recurse with updated lists
					return (int)calculate(operators, operands); 
				
				} else { // subtraction is first, or addition is not present

					// perform subtraction
					int difference = operands.get(subtractionIndex) - operands.get(subtractionIndex + 1);
					// reduce operators
					operators.remove(subtractionIndex);
					// reduce operands
					operands.set(subtractionIndex, difference); // insert difference at 1st operand [..., x, y, ...] -> [..., diff, y, ...]
					operands.remove(subtractionIndex + 1); // remove 2nd operand [..., diff, y, ...] -> [..., diff, ...]
					// recurse with updated lists
					return (int)calculate(operators, operands); 
				}	
			}
		}
	}// end calculate method
			
}//	end MathQuestionGenerator Class