
public abstract class MathQuestionGenerator {
	
	private String difficultyLevel;
	private int difficultyIndex;
	private String currentQuestion;
	private int currentAnswer;
	
	private char[] operators = {'*', '%', '+', '-'};
	private int[] currentOperands;
	private char[] currentOperators;
	
	public MathQuestionGenerator(int operandsCount, int operatorsCount, String difficultyLevel, int difficultyIndex) {
		
		this.currentOperands = new int[operandsCount]; // set size based on difficulty
		this.currentOperators = new char[operatorsCount]; // set size based on difficulty
		this.difficultyLevel = difficultyLevel; // set descriptor based on difficulty
		this.difficultyIndex = difficultyIndex;
		
		setOperands();// initial values 
		setOperators();// initial values
		
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
	
	public void displayOperands() {
		System.out.print("[");
		for(int i : currentOperands) {
			System.out.print(" " + i + " ");
		}
		System.out.print("]");
	}
	
	public void displayOperators() {
		System.out.print("[");
		for(char c : currentOperators) {
			System.out.print(" " + c + " ");
		}
		System.out.print("]");
	}
	
	public void dispalyData() {
		System.out.println();
		displayOperands();
		System.out.println();
		displayOperators();
		System.out.println();
	}
	
	
	
}
