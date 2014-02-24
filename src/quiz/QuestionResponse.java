package quiz;

import java.util.*;

public class QuestionResponse extends Answer {

	/*
	 * Instance Variables
	 */
	private ArrayList<String> answers;
	
	
	/**
	 * Constructor creates
	 */
	public QuestionResponse()
	{
		answers = new ArrayList<String>();
	}
	
	/**
	 * Returns the answer type.
	 */
	public int getAnswerType()
	{
		return Answer.QUESTION_RESPONSE;
	}
	
	/**
	 * 
	 */
	public ArrayList<String> getAnswer()
	{
		return answers;
	}
	
}
