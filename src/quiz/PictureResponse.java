package quiz;

import java.util.ArrayList;

public class PictureResponse extends Question {

	/*
	 * Instance Variables
	 */
	private String imageFile;
	
	/**
	 * Constructor creates a new PictureResponse object,
	 * which has all of the functionality of Question.
	 * It takes in an extra String that hold the URL path
	 * for displaying the picture.
	 */
	public PictureResponse(String question, ArrayList<String> answers, String imageFile)
	{
		super(question, answers);
		this.imageFile = imageFile;
	}
	
	/**
	 * Returns a String that holds the
	 * image URL path. 
	 */
	public String getImageFile()
	{
		return imageFile;
	}
	
	/**
	 * Overwritten so returns an int corresponding
	 * to picture-response.
	 */
	public int getQuestionType()
	{
		return Question.PICTURE_RESPONSE;
	}
}
