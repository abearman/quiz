package quiz;

import java.util.ArrayList;

public class Webpage {

	/* Constants */
	public static final int NUM_RECENTLY_TAKEN_QUIZZES = 10;
	public static final int NUM_RECENTLY_CREATED_QUIZZES = 10;
	
	/* Instance variables */
	private ArrayList<String> recentlyTakenQuizzes; 
	private ArrayList<String> recentlyCreatedQuizzes; 
	private ArrayList<String> announcements;
	
	/* Constructor */
	public Webpage(DAL dal) {
		recentlyTakenQuizzes = new ArrayList<String>();
		recentlyCreatedQuizzes = new ArrayList<String>();
		
		recentlyTakenQuizzes = dal.getRecentlyTakenQuizzes();
		announcements = dal.getAllAnnouncements();
	
		//Populate instance variables from the database
	}
	
	/* Getters */
	public ArrayList<String> getPopularQuizzes() {
		return this.recentlyTakenQuizzes;
	}
	
	public ArrayList<String> getRecentlyCreatedQuizzes() {
		return this.recentlyCreatedQuizzes;
	}
	
	public ArrayList<String> getAnnouncements() {
		return this.announcements;
	}
	
}
