package quiz;

public class Answer {

	/*
	 * Instance Variables
	 */
	public static final int QUESTION_RESPONSE = 1;
	public static final int FILL_IN_THE_BLANK = 2;
	public static final int MULTIPLE_CHOICE = 3;
	public static final int PICTURE_RESPONSE = 4;
	
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
	
}
