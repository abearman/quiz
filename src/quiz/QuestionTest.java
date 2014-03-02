package quiz;

import java.util.*;
import org.junit.*;

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
			System.out.println("question: "+q.getQuestion()+"type: "+ q.getQuestionType());
		}
	}
	
	

}
