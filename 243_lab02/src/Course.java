/**
 * @author: Jenny Zhen
 * @name: Course.java
 * @date: 03.22.12
 */

/**
 * $Id: Course.java,v 1.5 2012-03-27 22:20:44 jxz6853 Exp $
 * $Revision: 1.5 $
 * $Log: Course.java,v $
 * Revision 1.5  2012-03-27 22:20:44  jxz6853
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

public class Course {
	public static String dayString = "MTWRF"; //chars representing days of week
	public String name; //name of the course
	public ArrayList<Boolean> days; //days the course meets
	public int start; //start time of course
	public int end; //end time of course
	
	/**
	 * Constructor.
	 * 
	 * Example course input file:
	 * 		Calculus MWF 9 11
	 * 		CS3 MWF 8 10
	 * 		Writing TR 1 3
	 * 		WaterPolo MTWF 1 2
	 * 
	 * @param n Name of the course
     * @param days List of days that the course is held on
     * @param start Starting time (in hours, 24-hour clock)
     * @param end Ending time (in hours, 24-hour clock)
	 */
	public Course(String n, ArrayList<Boolean> days, int start, int end) {
		this.name = n;
		this.days = days;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Test for equality.
	 * @override equals in class Object.
	 * @param other Object to be tested against.
	 * @return True if the object passed in is a Course with the same 
	 * 	       name, days, start and end time as this Course.
	 */
	public boolean equals(Object other) {
		if(other instanceof Course){ //make sure it's a course
			Course toCompare = (Course)(other); //set as course to compare
		 	if(toCompare.name == name && toCompare.days == days && 
		 			toCompare.start == start && toCompare.end == end) //equal
		 		return true; //it's the same course
		}return false; //it's not the same course
	}
	
	/**
	 * Test for scheduling conflict.
	 * @param other Course to test against.
	 * @return True if the passed-in Course overlaps in time 
	 * 		   (on any day) with this Course.
	 */
	public boolean inConflict(Course other) {
		if(other instanceof Course){ //make sure that it's a course
			Course toCompare = (Course)(other); //set as course to compare
			for(int i = 0; i < days.size(); i++){ //for all of its days
				if(toCompare.days.get(i) && days.get(i)){ //meet on same day
					if(toCompare.start >= start && toCompare.start < end)
						return true; //the course conflicts
					if(toCompare.start <= start && toCompare.end > start)
						return true; //the course conflicts
				}}}return false; //the course does not conflict
	}
	
	/**
	 * Returns a string representing the time this course meets 
	 * on the given day, if any.
	 * @param day Day of the week, where 0 = Monday ... 4 = Friday.
	 * @return String as above, or the empty String if the course 
	 * 		   does not meet on the given day.
	 */
	public String inDay(int day) {
		if(days.get(day)) //if course meets that day
			return start + "-" + end + ": " + name + "\n";
		return ""; //course doesn't meet that day
	}
	
	/**
	 * String representation of the course, in the form Name: days 
	 * at start-end (for example: Calculus: MWF at 8-10)
	 * @override toString in class Object.
	 * @return String representation.
	 */
	public String toString() {
		String course; //result
		course = name + ": ";
		for(int i = 0; i < days.size(); i++){ //get days of week
			if(days.get(i)) //meets that day
				course += dayString.charAt(i); //add day of week to string
		}
		course += " at " + start + "-" + end;
		return course; //return result
	}
}