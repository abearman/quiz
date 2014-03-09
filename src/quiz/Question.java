package quiz;

import java.util.*;

public class Question {

	/*
	 * Instance Variables
	 */
	private String questionPrompt;
	private ArrayList<String> answers;
	private int questionNumber; //allows us to order the questions for the quiz
	
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
	public Question(String question, ArrayList<String> qAnswers, int orderNum)
	{
		questionPrompt = question;
		answers = qAnswers;
		questionNumber = orderNum;
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
	 * Returns boolean whether or not the given
	 * answer is correct.
	 */
	public boolean answerIsCorrect(String answer)
	{
		boolean answerCorrect = false;
		for (int i = 0; i < answers.size(); i++){
			if (answer.equalsIgnoreCase(answers.get(i))){
				answerCorrect = true;
			}
		}
		return answerCorrect;
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
	
	/**
	 * Returns an int that corresponds to the 
	 * question number of the question in the quiz.
	 * This is needed to properly order the quiz.
	 */
	public int getQuestionNumber()
	{
		return questionNumber;
	}
	
	/**
	 * Returns a String that contains all of the answers
	 * separated by endline escape sequences ("\n").
	 */
	public String createAnswerString()
	{
		String result = "";
		for(int i = 0; i < answers.size(); i++)
		{
			result += (answers.get(i)+"\n");
		}
		return result;
	}
	
	/**
	 * Parses the String and puts the strings separated by "\n" into
	 * an ArrayList<String> that is returned.
	 */
	public static ArrayList<String> createArray(String answers)
	{
		ArrayList<String> result = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(answers, "\n");
		while(st.hasMoreTokens())
		{
			result.add(st.nextToken());
		}
		return result;
	}
}
