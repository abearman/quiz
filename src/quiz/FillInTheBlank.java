package quiz;

import java.util.ArrayList;

public class FillInTheBlank extends Question{

	/**
	 * Constructor creates a new FillInTheBlank object,
	 * which has all of the functionality of Question.
	 */
	public FillInTheBlank(String question, ArrayList<String> answers, int orderNum)
	{
		super(question, answers, orderNum);
	}
	
	/**
	 * Overwritten so returns an int corresponding
	 * to fill-in-the-blank.
	 */
	public int getQuestionType()
	{
		return Question.FILL_IN_THE_BLANK;
	}
	
	//should we break the question up into two strings for display purposes
}
