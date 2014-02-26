package quiz;

import java.util.ArrayList;

public class PictureResponse extends Question {
	
	/*
	 * Instance Variables
	 */
	private String imageURL;
	
	/**
	 * Constructor creates a new PictureResponse object,
	 * which has all of the functionality of Question.
	 * The question is simply the picture. 
	 * getQuestion() will return the image URL.
	 */
	public PictureResponse(String question, ArrayList<String> answers, int orderNum, String imageURL)
	{
		super(question, answers, orderNum);
		this.imageURL = imageURL;
	}
	
	
	/**
	 * Overwritten so returns an int corresponding
	 * to picture-response.
	 */
	public int getQuestionType()
	{
		return Question.PICTURE_RESPONSE;
	}
	
	/**
	 * Returns the image URL for the question as a String. 
	 */
	public String getImageURL()
	{
		return imageURL;
	}
}
