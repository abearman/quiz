package quiz;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuestionTest {

	DAL dal;
	Quiz quiz;
	
	
	@Before
	public void setUp() throws Exception {
		dal = new DAL();
	}
	
	@Test
	public void testSetUp()
	{
		quiz = new Quiz(dal, "bhaven's quiz", "i like chocolate", false, false, false, false, 
				"Bhaven", new Date() ,0);
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("correct");
		quiz.addQuestion(new QuestionResponse("new question and response", answers, 1));
		quiz.addQuestion(new FillInTheBlank("new fill-in-the-blank", answers, 2));
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("choice1");
		choices.add("choice2");
		quiz.addQuestion(new MultipleChoice("new multiple choice", answers, 3, choices));
		quiz.addQuestion(new PictureResponse("new picture response", answers, 4, "url.com"));
		System.out.println("setup worked!");
	}
	
	@Test
	public void testGettingQuestionsFromDB()
	{
		Quiz quiz2 = new Quiz(dal, "bhaven's quiz");
		ArrayList<Question> questions = quiz2.getQuestions();
		for(Question q : questions)
		{
			System.out.print("question: "+q.getQuestion()+" type: "+ q.getQuestionType());
			if(q.getQuestionType()==Question.MULTIPLE_CHOICE)
			{
				ArrayList<String> options = ((MultipleChoice)q).getChoices();
				System.out.print(" options: ");
				for(int i = 0; i < options.size(); i++)
				{
					System.out.print(options.get(i)+", ");
				}
			}
			if(q.getQuestionType()==Question.PICTURE_RESPONSE)
			{
				String url = ((PictureResponse)q).getImageURL();
				System.out.print(" url: "+url);
			}
			System.out.println();
		}
	}
	
	@Test
	public void testRemovingQuestionsFromDB()
	{
		Quiz quiz2 = new Quiz(dal, "bhaven's quiz");
		ArrayList<Question> questions = quiz2.getQuestions();
		for(Question q : questions)
		{
			dal.removeQuestion(quiz2.getQuizName(), q);
		}
		assertTrue(dal.doesQuizExist("bhaven's quiz"));
		dal.removeQuiz("bhaven's quiz");
		Quiz quiz3 = new Quiz(dal, "bhaven's quiz");
		assertFalse(dal.doesQuizExist("bhaven's quiz"));
		assertEquals("", quiz3.getQuizName());
	}
	

}
