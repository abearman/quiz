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
		//Populate instance variables from the database
		recentlyTakenQuizzes = dal.getRecentlyTakenQuizzes();
		recentlyCreatedQuizzes = dal.getRecentlyCreatedQuizzes();
		announcements = dal.getAllAnnouncements();
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
