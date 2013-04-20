/**
 * @author: Jenny Zhen
 * @name: PizzaRun.java
 * @date: 03.15.12
 */

public class PizzaRun {
	private static final int SLICE_PER_PIE = 8;
	private static double price = 0.0;
	
	/**
	 * The main method gets user inputs, sums up the String arguments as they're 
	 * parsed as integers to get the total number of slices, and calls the 
	 * calcWholePies() method to get the number of pies.
	 * @param args The command line arguments, list of integers.
	 */
	public static void main(String args[]) {
		int total_slices = 0; //sum of slices
		int extra_slices = 0; //remaining slices
		int total_pizzas = 0; //whole pies
		price = Double.parseDouble(args[0]); //price per pie

		//loop through the String args[], parse as integers, and get the sum
		for (int i = 1; i < args.length; i++)
			total_slices += Integer.parseInt(args[i]);

		total_pizzas = calcWholePies(total_slices); //calculate pies needed
		extra_slices = (total_pizzas * SLICE_PER_PIE)- total_slices; //remaining
		
		//printing results
		System.out.print("Buy " + total_pizzas + " pizza");
		if (total_pizzas > 1) //pluralize "pizza" if more than one pie
			System.out.print("s");
		System.out.println(" for $" + (price * total_pizzas));
		System.out.println("There will be " + extra_slices + " extra slices.");
	}

	/**
	 * Calculates the number of pies needed to complete the orders with each 
	 * pie consisting of 8 slices.
	 * @param nSlices The number of slices that the order asks for
	 * @return The number of whole pies needed to complete the order.
	 */
	public static int calcWholePies(int nSlices) {
		return (int) Math.ceil(nSlices / (double) SLICE_PER_PIE);
	}
}
