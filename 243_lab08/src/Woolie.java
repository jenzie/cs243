/**
 * @author: Jenny Zhen
 * @name: Woolies.java
 * @date: 05.09.12
 */

/**
 * $Id: Woolie.java,v 1.4 2012-05-16 03:18:22 jxz6853 Exp $
 * $Log: Woolie.java,v $
 * Revision 1.4  2012-05-16 03:18:22  jxz6853
 * Added last two test cases and commenting.
 *
 * Revision 1.3  2012-05-13 05:04:15  jxz6853
 * Fix numbering.
 *
 * Revision 1.2  2012-05-13 04:58:00  jxz6853
 * Need test case.
 *
 * Revision 1.1  2012-05-09 17:18:47  jxz6853
 * Template.
 *
 * $Revision: 1.4 $
 */

/**
 * The Woolie simulates a Woolie crossing a TrollsBridge.
 * 
 * Each woolie object is constructed with a name, length of time it takes the 
 * woolie to cross a bridge, a destination, and a reference to a TrollsBridge 
 * whose troll coordinates how woolies get on and off their bridge.
 * 
 * A Woolie extends the Thread class and executes as an active object.
 * 
 * Before crossing, a woolie must ask troll guarding the bridge for permission 
 * to cross. After the troll grants permission, the woolie begins crossing the 
 * bridge. After reaching the other side, the woolie must leave and notify the 
 * troll and everyone that it is no longer on the bridge.
 */
public class Woolie extends Thread implements Runnable {
	private String name; //name of woolie
	private int crossTime; //time to cross bridge
	private String destination; //woolie destination
	private TrollsBridge bridgeGuard; //troll/guard
	
	/**
	 * Construct a new Woolie object that can run as a thread. 
	 * The constructor simply initializes all of the instance's fields.
	 * 
	 * Preconditions:
	 * destination = "Sicstine" or "Merctran"
	 * crossTime >= 0
	 * name != null
	 * bridgeGuard != null 
	 * 
	 * @param name - the name of this Woolie
	 * @param crossTime - the number of seconds it takes the Woolie 
	 * 					  to cross after it has climbed onto the bridge
	 * @param destination - the Woolie's destination city
	 * @param bridgeGuard - the TrollsBridge that the Woolie is crossing 
	 */
	public Woolie(String name, int crossTime, String destination, 
				TrollsBridge bridgeGuard) {
		this.name = name;
		this.crossTime = crossTime;
		this.destination = destination;
		this.bridgeGuard = bridgeGuard;
	}
	
	/**
	 * The run method handles a Woolie's behavior as it crosses the bridge. 
	 * The well-behaved Woolie asks the troll at the bridge to cross. While it 
	 * is crossing the bridge, it reports its progress each second as it works 
	 * its way across, and the woolie lastly tells the troll that it has gotten 
	 * off the bridge.
	 * 
	 * There are several messages that a Woolie thread must display to describe 
	 * their progress crossing the bridge.
	 * 
	 * Note: In all the following messages "name" is the representation of the 
	 * Woolie returned by its getName() method. The Woolie prints the first 
	 * message immediately after telling the troll it wants to enter.
	 * 
	 * When the Woolie starts crossing the bridge, at time 0, display the message
	 * "name is starting to cross".
	 * 
	 * For every one second interval, beyond time 0, that the Woolie is on the 
	 * bridge, display the message "name xyz seconds." where "xyz" is the number 
	 * of seconds that the Woolie has been on the bridge.
	 * 
	 * When the Woolie reaches its destination, display the message "name leaves 
	 * at city." where "city" is the Woolie's destination. After printing this 
	 * final message, the woolie tells the troll that it is leaving the bridge.
	 */
	public void run() {
		int elapsedTime = 0;
		
		bridgeGuard.enterBridgePlease(this);
		System.out.println(this.name + " is starting to cross.");
		
		while(elapsedTime < this.crossTime - 1) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(
					"InterruptedException: Could not place thread on sleep().");
			}
			System.out.println("\t" + this.name + " " + (elapsedTime + 1) + " seconds.");
			elapsedTime += 1;
		}
		System.out.println(this.name + " leaves at " + this.destination + ".");
		
		bridgeGuard.leave();
	}
	
	/**
	 * Gets the name of the woolie.
	 * @return name of woolie
	 */
	public String getWoolieName() {
		return this.name;
	}
}
