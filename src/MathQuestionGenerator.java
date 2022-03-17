import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MathQuestionGenerator {
	
	private String difficultyLevel;
	private int difficultyIndex;
	private String currentQuestion;
	private int currentAnswer;
	
	private String[] operators = {"*", "%", "+", "-"};
	private int[] currentOperands;
	private String[] currentOperators;
	
	public MathQuestionGenerator(int operandsCount, int operatorsCount, String difficultyLevel, int difficultyIndex) {
		
		this.currentOperands = new int[operandsCount]; // set size based on difficulty
		this.currentOperators = new String[operatorsCount]; // set size based on difficulty
		this.difficultyLevel = difficultyLevel; // set descriptor based on difficulty
		this.difficultyIndex = difficultyIndex;
		
		setOperands();// initial values 
		setOperators();// initial values
		
	}
	
	private void setCurrentQuestion(String question) {
		this.currentQuestion = question;
	}
	
	private void setCurrentAnswer(int answer) {
		this.currentAnswer = answer;
	}
	
	public int getCurrentAnswer() {
		return currentAnswer;
	}
	
	public String getCurrentQuestion() {
		return currentQuestion;
	}
	
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	
	private void setOperands() {
		
		int operandsSize = currentOperands.length;
		for(int i = 0; i < operandsSize; i++) {
			int randomNum = (int)(Math.random() * this.difficultyIndex); //random number
			currentOperands[i] = randomNum;
		}
	}
	
	private void setOperators() {
		
		int operatorsSize = currentOperators.length;
		int availableOperators = operators.length;
		for(int i = 0; i < operatorsSize; i++) {
			int randomIndex = (int)(Math.random() * 100) % availableOperators; //random index
			currentOperators[i] = operators[randomIndex];
		}
	}
		
	public String generateNewQuestion() {
		
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
		
		return question;
	}
	
	public int calculateCurrentAnswer() {
		int answer = 0;

		List<String> operators = new ArrayList<String>(Arrays.asList(currentOperators));
		List<Integer> operands = Arrays.stream(currentOperands)
				.boxed()
				.collect(Collectors.toList());

		answer = calculate(operators, operands);
		setCurrentAnswer(answer);
		
		return answer;
	}
	
	public int calculate(List<String> operators, List<Integer> operands) {
		
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
					operators.remove(multiplyIndex);
					// reduce operands
					operands.set(multiplyIndex, product); // insert product at 1st operand
					operands.remove(multiplyIndex + 1); // remove 2nd operand
					return (int)calculate(operators, operands);
					
				} else { // modulo is first or multiply not present
					
					// perform modulus operation 
					int remainder = operands.get(modulusIndex) % operands.get(modulusIndex + 1);
					// reduce operators
					operators.remove(modulusIndex);
					// reduce operands
					operands.set(modulusIndex, remainder); // insert product at 1st operand
					operands.remove(modulusIndex + 1); // remove 2nd operand
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
					operands.set(additionIndex, sum); // insert product at 1st operand
					operands.remove(additionIndex + 1); // remove 2nd operand
					return (int)calculate(operators, operands);
				
				} else { // subtraction is first, or addition is not present

					// perform subtraction
					int difference = operands.get(subtractionIndex) - operands.get(subtractionIndex + 1);
					// reduce operators
					operators.remove(subtractionIndex);
					// reduce operands
					operands.set(subtractionIndex, difference); // insert product at 1st operand
					operands.remove(subtractionIndex + 1); // remove 2nd operand
					return (int)calculate(operators, operands);
				}
				
			}
				
		}
	
	}// end calculate method
	
	/* DEV METHODS -- REMOVE ALL BELOW -- */ 
	
	//DEV ONLY - REMOVE
		public void displayOperands() {
			System.out.print("[");
			for(int i : currentOperands) {
				System.out.print(" " + i + " ");
			}
			System.out.print("]");
		}
		
		//DEV ONLY - REMOVE
		public void displayOperators() {
			System.out.print("[");
			for(String str : currentOperators) {
				System.out.print(" " + str + " ");
			}
			System.out.print("]");
		}
		
		//DEV ONLY - REMOVE
		public void dispalyData() {
			System.out.println();
			displayOperands();
			System.out.println();
			displayOperators();
			System.out.println();
			System.out.println(currentQuestion);
			System.out.println();
			System.out.println(calculateCurrentAnswer());
		}
		
		//DEV ONLY - REMOVE
		public void dispalyEquation(String[] arr) {
			
			System.out.print("EQ: [");
			for(String str : arr) {
				System.out.print(" " + str + " ");
			}
			System.out.print("]");
		}
		
}//	end MathQuestionGenerator Class