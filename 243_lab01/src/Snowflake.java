/**
 * @author: Jenny Zhen
 * @name: Snowflake.java
 * @date: 03.15.12
 */

import java.util.Scanner; //import scanner to get user input

public class Snowflake {	
	private static Scanner input; //scanner to take user input
	private static Turtle turtle; //turtle to draw graphics
	
	/**
	 * The main method gets user inputs and draws the snowflake part by part.
	 * @param args The command line arguments (unused, replaced by scanner).
	 */
	public static void main(String[] args) {
		int S = 0; //side length
		int N = 0; //number of recursions
		input = new Scanner(System.in); //scanner to take user input
		System.out.print("Enter S: ");
		S = input.nextInt(); //take in value of S as an integer
		System.out.print("Enter N: ");
		N = input.nextInt(); //take in value of N as an integer
		
		turtle = init(S); //initialize the canvas
		snowflake(S, N, turtle); //draw the snowflake
	}
	
	/**
	 * Initialize the graphics.
	 * @param S The length of the main snowflake branch.
	 * @return A turtle object to draw with.
	 */
	public static Turtle init(int S) {
		turtle = new Turtle(-S/2, 0, 0); //new Turtle(x,y,angle) object
		turtle.setCanvasTitle("Snowflake.java"); //name of canvas
		turtle.setCanvasSize(300, 300); //size of canvas
		//(x,y) lower-left; (x,y) upper-right coordinates
		turtle.setWorldCoordinates(-150, -150, 150, 150);
		return turtle; //return instance of turtle
	}
	
	/**
	 * Draw a snowflake.
	 * @param S The length of the snowflake branch.
	 * @param N The depth of recursion.
	 * @param t The turtle object to draw with.
	 */
	public static void snowflake(int S, int N, Turtle t) {
		turtle.turnLeft(60); //turn the angle left 60 degrees
		for(int i = 0; i < 3; i++){ //for each recursion depth, loop
			snowflakeSide(S, N); //create one side of the snowflake
			turtle.turnRight(120); //rotate before next loop
		}
	}
	
	/**
	 * Draw a snowflake side using recursive calls to snowflakeSide().
	 * @param S The length of the snowflake branch.
	 * @param N The depth of recursion.
	 */
	public static void snowflakeSide(int S, int N) {
		if(N == 1)
			turtle.goForward(S);
		else if(N > 1){
			//recursively call until desired depth
			S /= 3;
			snowflakeSide(S, N-1);
			turtle.turnLeft(60);
			snowflakeSide(S, N-1);
			turtle.turnRight(120);
			snowflakeSide(S, N-1);
			turtle.turnLeft(60);
			snowflakeSide(S, N-1);
		}
	}
}