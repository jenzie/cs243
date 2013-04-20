/**
 * @author: Jenny Zhen
 * @name: Schedule.java
 * @date: 03.22.12
 */

/**
 * $Id: Schedule.java,v 1.5 2012-03-27 22:20:43 jxz6853 Exp $
 * $Revision: 1.5 $
 * $Log: Schedule.java,v $
 * Revision 1.5  2012-03-27 22:20:43  jxz6853
 * Completed.
 *
 * Revision 1.4  2012-03-27 03:50:08  jxz6853
 * Worked on inConflict() in Course.java. Documentation needed.
 *
 * Revision 1.3  2012-03-27 03:05:09  jxz6853
 * All code works properly except for inConflict() in Course.java. Documentation needed.
 *
 * Revision 1.2  2012-03-26 05:05:12  jxz6853
 * Templates done.
 *
 */
import java.util.ArrayList;

public class Schedule {
	private static ArrayList<ArrayList<Course>> schedule;
	/**
	 * Constructor - creates/initializes any necessary data structures.
	 */
	public Schedule() {
		schedule = new ArrayList<ArrayList<Course>>(); //2D ArrayList
		for(int i = 0; i < 5; i++){ //adds 5 ArrayLists into the ArrayList
			schedule.add(new ArrayList<Course>());
		}
	}
	
	/**
	 * Adds the given course to the schedule only if it is not in conflict 
	 * with courses currently on the schedule.
	 * @param c Course to attempt to add. 
	 * @return Whether the course was successfully added.
	 */
	public boolean add(Course c) {
		Course current; //course to check against
		if(!contains(c)){ //if course isn't on schedule, check conflicts
			for(int i = 0; i < c.days.size(); i++){ //everyday new course meets
				for(int j = 0; j < schedule.get(i).size(); j++){//courses for day
					current = schedule.get(i).get(j); //current course to check
					if(current.inConflict(c)) //if conflicts, don't add
						return false;
				}}//otherwise, continue checking all days until out of all loops
			for(int i = 0; i < c.days.size(); i++){ //for every day course meets
				if(c.days.get(i)) //add course to schedule
					schedule.get(i).add(c);
			}return true;//added course
		}return false; //course is on the schedule already
	}
	
	/**
	 * Tests whether a given Course is currently on the schedule.
	 * @param c Course to test if already on schedule.
	 * @return True if the course is on the schedule.
	 */
	public boolean contains(Course c) {
		for(int i = 0; i < c.days.size(); i++){ //everyday of week
			if(c.days.get(i)){ //if course meets
				for(int j = 0; j < schedule.get(i).size(); j++){ //check schedule
					if(schedule.get(i).get(j).name == c.name) //same course
						return true; //course on schedule
				}}}return false; //course not on schedule
	}
	
	/**
	 * Prints a day-by-day schedule (using relevant functions from the Course 
	 * class), in the following format.
	 * ----Monday----
	 * 1-2: WaterPolo
	 * 9-11: Calculus
	 * ----Tuesday----
	 * 1-2: WaterPolo
	 * ----Wednesday----
	 * 1-2: WaterPolo
	 * 9-11: Calculus
	 * ----Thursday----
	 * ----Friday----
	 * 1-2: WaterPolo
	 * 9-11: Calculus
	 * Note that sorting the classes on each day is not required.
	 */
	public void prettyPrint() {
		String result = "";
		Course current;
		String[] daysOfWeek = new String[]
				{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		for(int i = 0; i < schedule.size(); i++){ //everyday of week
			result += "----" + daysOfWeek[i] + "----\n"; //list the day
			for(int j = 0; j < schedule.get(i).size(); j++){ //courses for day
				current = schedule.get(i).get(j);
				result += current.inDay(i);
			}}  //number of courses on schedule + schedule
		System.out.println(toString() + result);
	}
	
	/**
	 * Simple string representation: "Schedule with n courses" where n is the 
	 * number of courses on the schedule.
	 * @override toString in class Object.
	 * @return String as above.
	 */
	public String toString() {
		int count = 0; //number of courses
		Course current; //current course
		ArrayList<String> allCourses = new ArrayList<String>(); //listOfCourses
		
		for(int i = 0; i < schedule.size(); i++){ //each day of week
			for(int j = 0; j < schedule.get(i).size(); j++){//each course of day
				current = schedule.get(i).get(j); //set as current
				if(!allCourses.contains(current.name)){ //check if on list
					allCourses.add(current.name); //not on list, add to list
					count += 1; //increment count
				}}}
		if(count == 1) //singular
			return "Schedule with " + count + " course.\n\n";
		return "Schedule with " + count + " courses.\n\n"; //plural
	}
}//end Schedule.java
