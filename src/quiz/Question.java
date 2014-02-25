package quiz;

import java.util.*;

public class Question {

	/*
	 * Instance Variables
	 */
	private String questionPrompt;
	private ArrayList<String> answers;
	
	/*
	 * Question Labels
	 */
	public static final int QUESTION_RESPONSE = 1;
	public static final int FILL_IN_THE_BLANK = 2;
	public static final int MULTIPLE_CHOICE = 3;
	public static final int PICTURE_RESPONSE = 4;
	
	/**
	 * Construct creates a new Question by
	 * taking in a String for the question
	 * and an ArrayList of Strings that hold the
	 * answers.
	 */
	public Question(String question, ArrayList<String> qAnswers)
	{
		questionPrompt = question;
		answers = qAnswers;
	}
	
	/**
	 * Returns the question as a String.
	 */
	public String getQuestion()
	{
		return questionPrompt;
	}
	
	/**
	 * Returns the Answer object associated
	 * with the question.
	 */
	public ArrayList<String> getAnswer()
	{
		return answers;
	}
	
	/**
	 * Takes in the user's answer,
	 * compares it to the answers for the question,
	 * and returns true if there is a match or false,
	 * if there is no match.
	 */
	public boolean isCorrect(String input)
	{
		for(String answer: answers)
		{
			if(answer.equalsIgnoreCase(input))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a int corresponding to the Question type for
	 * subclasses.
	 */
	public int getQuestionType()
	{
		return 0;
	}
	
}
