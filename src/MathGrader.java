
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
	}
	
	public boolean grade(int expectedAnswer, int actualAnswer) {
		
		boolean isCorrect = false;
		if(expectedAnswer == actualAnswer) {
			isCorrect = true;
		}
		
		return isCorrect;
	}
}
