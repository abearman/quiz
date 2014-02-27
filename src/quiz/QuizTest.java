package quiz;

import org.junit.*;


public class QuizTest {
	
	private Quiz thisQuiz;
	private TopScorer topScorer1, topScorer2, topScorer3, topScorer4;

	@Before
	public void setUp() throws Exception {
<<<<<<< HEAD
		DAL dal = new DAL();
		
		thisQuiz = new Quiz(dal);
		
		User user1 = new User("user1","pwd1", dal);
		User user2 = new User("user2","pwd2", dal);
		User user3 = new User("user3","pwd3", dal);
		User user4 = new User("user4","pwd4", dal);
		
		topScorer4 = new TopScorer("user4", 10, 0.7, dal);
		topScorer3 = new TopScorer("user3", 10, 0.5, dal);
		topScorer2 = new TopScorer("user2", 11, 0.6, dal);
		topScorer1 = new TopScorer("user1", 19, 7.8, dal);
=======
		thisQuiz = new Quiz(new DAL());
		
		User user1 = new User("user1","pwd1", new DAL());
		User user2 = new User("user2","pwd2", new DAL());
		User user3 = new User("user3","pwd3", new DAL());
		User user4 = new User("user4","pwd4", new DAL());
		
		topScorer4 = new TopScorer("user4", 10, 0.7, new DAL());
		topScorer3 = new TopScorer("user3", 10, 0.5, new DAL());
		topScorer2 = new TopScorer("user2", 11, 0.6, new DAL());
		topScorer1 = new TopScorer("user1", 19, 7.8, new DAL());
>>>>>>> 0a724130ef92727f9014e51463398de0dca74a14
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
