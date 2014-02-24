package quiz;

import java.util.*;

public class Answer {

	/*
	 * Instance Variables
	 */
	public static final int QUESTION_RESPONSE = 1;
	public static final int FILL_IN_THE_BLANK = 2;
	public static final int MULTIPLE_CHOICE = 3;
	public static final int PICTURE_RESPONSE = 4;
	
	/**
	 * Blank constructor because this class is
	 * not meant to be instantiated. 
	 */
	public Answer()
	{
		//TODO: DONT REALLY THINK WE NEED THIS CLASS
	}
	
	/**
	 * Returns the type of question/answer as an int.
	 * Method is meant to overwritten by subclasses.
	 */
	public int getAnswerType()
	{
		return -1;
	}
	
	/**
	 * Returns the an ArrayList of Strings for
	 * the possible answers because there can be
	 * variations.  
	 */
	public ArrayList<String> getAnswer()
	{
		return null;
	}
	
	
}

