package quiz;

import java.util.*;

public class QuestionResponse extends Question {

	/**
	 * Constructor creates a new QuestionResponse object,
	 * which has all of the functionality of Question.
	 */
	public QuestionResponse(String question, ArrayList<String> answers, int orderNum)
	{
		super(question, answers, orderNum);
	}
	
	/**
	 * Overwritten so returns an int corresponding
	 * to Question Response.
	 */
	public int getQuestionType()
	{
		return Question.QUESTION_RESPONSE;
	}
	
}
