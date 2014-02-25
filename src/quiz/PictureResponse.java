package quiz;

import java.util.ArrayList;

public class PictureResponse extends Question {
	
	/**
	 * Constructor creates a new PictureResponse object,
	 * which has all of the functionality of Question.
	 * The question is simply the picture. 
	 * getQuestion() will return the image URL.
	 */
	public PictureResponse(String imageURL, ArrayList<String> answers)
	{
		super(imageURL, answers);
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
