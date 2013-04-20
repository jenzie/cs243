
/**
 * @author: Jenny Zhen
 * @name: TrollsBridge.java
 * @date: 05.09.12
 */

/**
 * $Id: TrollsBridge.java,v 1.3 2012-05-16 03:18:22 jxz6853 Exp $
 * $Log: TrollsBridge.java,v $
 * Revision 1.3  2012-05-16 03:18:22  jxz6853
 * Added last two test cases and commenting.
 *
 * Revision 1.2  2012-05-13 04:58:00  jxz6853
 * Need test case.
 *
 * Revision 1.1  2012-05-09 17:18:47  jxz6853
 * Template.
 *
 * $Revision: 1.3 $
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * TrollsBridge: Maintains the bridge structure; the bridge guardian. 
 */
public class TrollsBridge {
	private int max; //maximum woolies allowed on bridge
	private int wooliesOnBridge; //current number on bridge
	private Queue<Woolie> waitlist; //queue
	
	/**
	 * Create a TrollsBridge with a given capacity. The municipal authority 
	 * creates a TrollsBridge for each bridge that needs management.
	 * @param max - the maximum capacity of the TrollsBridge.
	 */
	public TrollsBridge(int max) {
		this.max = max;
		this.wooliesOnBridge = 0;
		this.waitlist = new LinkedList<Woolie>();
	}
	
	/**
	 * Request permission to go onto the troll's bridge. Woolies call this 
	 * method to ask the troll to put them on the queue of woolies trying to 
	 * get on the bridge. The Woolie (thread) waits until it becomes the head 
	 * of the queue and there is room on the troll's bridge.
	 * 
	 * Note: Since this class is a monitor, this method needs to ensure mutual 
	 * exclusive access by calling threads (synchronized methods are needed).
	 * 
	 * The troll of a TrollsBridge guards its bridge to make sure that woolies 
	 * get on the bridge in the order of their arrival.
	 * 
	 * The troll of a TrollsBridge prints the following message when the Woolie 
	 * shows up to get in line to cross the bridge:
	 * The troll scowls "Get in line!" when woolies_name shows up at the bridge.
	 * 
	 * Precondition:
	 * The calling thread is the Woolie instance itself.
	 * 
	 * Postcondition:
	 * The woolie got permission and has climbed onto the bridge.
	 * At some future time, the woolie must call leave() to get off. 
	 * 
	 * @param thisWoolie - Woolie trying to get on bridge (same object as 
	 * Thread calling this method).
	 */
	public synchronized void enterBridgePlease(Woolie thisWoolie) {
		waitlist.add(thisWoolie);
		System.out.println("The troll scowls \"Get in line!\" when " + 
						thisWoolie.getWoolieName() + " shows up at the bridge.");
		
		while(wooliesOnBridge == max || waitlist.peek() != thisWoolie) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(
					"InterruptedException: Could not place thread on wait().");
			}
		}
		wooliesOnBridge += 1;
		waitlist.remove(thisWoolie);
	}
	
	/**
	 * Tell the troll of a TrollsBridge that a woolie has left the bridge so 
	 * that the troll can let other woolies get on if there is room.
	 * 
	 * A well-behaved Woolie always informs the troll of a TrollsBridge that it 
	 * (the caller) is getting off the bridge.
	 * 
	 * Note: Since this class is a monitor, this method needs to ensure mutual 
	 * exclusive access by calling threads (synchronized methods are needed).
	 * 
	 * Precondition:
	 * The calling thread is a Woolie that already called enterBridgePlease(). 
	 * Because of this precondition, a Woolie argument is not needed.
	 * 
	 * Postcondition:
	 * There is one less Woolie on this TrollsBridge. 
	 */
	public synchronized void leave() {
		this.wooliesOnBridge -= 1;
		notifyAll();
	}
	
	/**
	 * Checks to see if the bridge is full.
	 * @return true if bridge is full
	 */
	public boolean isFull() {
		return this.max == wooliesOnBridge;
	}
}
