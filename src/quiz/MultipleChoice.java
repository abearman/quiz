package quiz;

import java.util.ArrayList;

public class MultipleChoice extends Question {

	/*
	 * Instance Variables
	 */
	private ArrayList<String> choices;
	
	/**
	 * Constructor creates a new MultipleChoice object,
	 * which has all of the functionality of Question
	 * and keeps track of the user's possible choices.
	 * It takes in an extra ArrayList of Strings that 
	 * represent the choices for answers.
	 */
	public MultipleChoice(String question, ArrayList<String> answers, int orderNum, ArrayList<String> qChoices)
	{
		super(question, answers, orderNum);
		choices = qChoices;
	}
	
	/**
	 * Returns an ArrayList of Strings that
	 * hold the choices to the question.
	 */
	public ArrayList<String> getChoices()
	{
		return choices;
	}
	
	/**
	 * Overwritten so returns an int corresponding
	 * to multiple choice.
	 */
	public int getQuestionType()
	{
		return Question.MULTIPLE_CHOICE;
	}
	
	public String createChoicesString()
	{
		String result = "";
		for(int i = 0; i < choices.size(); i++)
		{
			result += (choices.get(i)+"\n");
		}
		return result;
			 
	}
}
