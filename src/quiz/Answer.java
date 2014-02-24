package quiz;

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
	}
	
	/**
	 * Method is mean to overwritten by subclasses.
	 */
	public int getAnswerType()
	{
		return -1;
	}
	
	/**
	 * Returns the answer which could be in the form
	 * of a String, ArrayList, etc., depending on the
	 * type of question. Should be typecasted by the user. 
	 */
	public Object getAnswer()
	{
		return null;
	}
	
	
}

