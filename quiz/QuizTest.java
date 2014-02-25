package quiz;

import org.junit.*;


public class QuizTest {
	
	private Quiz thisQuiz;
	private TopScorer topScorer1, topScorer2, topScorer3, topScorer4;

	@Before
	public void setUp() throws Exception {
		thisQuiz = new Quiz();
		
		User user1 = new User("user1","pwd1");
		User user2 = new User("user2","pwd2");
		User user3 = new User("user3","pwd3");
		User user4 = new User("user4","pwd4");
		
		topScorer4 = new TopScorer(user4, 10, 0.7);
		topScorer3 = new TopScorer(user3, 10, 0.5);
		topScorer2 = new TopScorer(user2, 11, 0.6);
		topScorer1 = new TopScorer(user1, 19, 7.8);
	}
	
	//order should be 1, 2, 3, 4
	@Test
	public void testSortOrder() {
		thisQuiz.addTopScorer(topScorer1);
		thisQuiz.addTopScorer(topScorer3);
		thisQuiz.addTopScorer(topScorer2);
		thisQuiz.addTopScorer(topScorer4);
		
		for (int i = 0; i < thisQuiz.getTopScorers().size(); i++){
			System.out.println("topScorer is " + thisQuiz.getTopScorers().get(i).getUser().getLoginName());
		}
	}

}
