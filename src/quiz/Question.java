package quiz;

public class Question {

	/*
	 * Instance Variables
	 */
	private String questionPrompt;
	private Answer answer;
	private String answerType;
	private String imageFile;
	
	/**
	 * Construct creates a new Question by
	 * taking in a String for the question
	 * and an Answer object that defines the answer
	 * for the question.
	 */
	public Question(String question, Answer answer)
	{
		questionPrompt = question;
		this.answer = answer;
	}
	
	/**
	 * Returns the question as a String;
	 */
	public String getQuestion()
	{
		return questionPrompt;
	}
	
	/**
	 * Returns the Answer object associated
	 * with the question.
	 */
	public Answer getAnswer()
	{
		return answer;
	}
	
	/**
	 * Returns the path of the image
	 * file as a String.
	 */
	public String getImageFile()
	{
		return imageFile;
	}
	
	
	
	
}
