package quiz;

import java.util.Date;

import org.junit.*;


public class QuizTest {
	
	private Quiz thisQuiz;
	private TopScorer topScorer1, topScorer2, topScorer3, topScorer4;

	@Before
	public void setUp() throws Exception {
		
		DAL dal = new DAL();
		
		//thisQuiz = new Quiz(dal);
		
		//HistoryObject ho = new HistoryObject("testUser",thisQuiz ,dal);
		Quiz testQuiz = new Quiz(dal, "TestQuizName","testQuizDescription",true,
				true,true,true,"Pavitra",new Date(),3);
		HistoryObject ho2 = new HistoryObject("testUser",testQuiz ,dal);
		
		topScorer4 = new TopScorer("user4", 10, 0.7, dal);
		topScorer3 = new TopScorer("user3", 10, 0.5, dal);
		topScorer2 = new TopScorer("user2", 11, 0.6, dal);
		topScorer1 = new TopScorer("user1", 19, 7.8, dal);

	}
	
	//order should be 1, 2, 3, 4
	@Test
	public void testSortOrder() {
		thisQuiz.addTopScorer(topScorer1);
		thisQuiz.addTopScorer(topScorer3);
		thisQuiz.addTopScorer(topScorer2);
		thisQuiz.addTopScorer(topScorer4);
		
		for (int i = 0; i < thisQuiz.getTopScorers().size(); i++){
			System.out.println("topScorer is " + thisQuiz.getTopScorers().get(i).getLoginName());
		}
	}

}
