/**
 * @author: Jenny Zhen
 * @name: AudioBook.java
 * @date: 04.05.12
 */
/**
 * $Id: AudioBook.java,v 1.3 2012-04-08 01:32:41 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: AudioBook.java,v $
 * Revision 1.3  2012-04-08 01:32:41  jxz6853
 * Fixed tags (complete).
 *
 * Revision 1.2  2012-04-07 21:16:32  jxz6853
 * Completed.
 *
 * Revision 1.1  2012-04-05 17:48:04  jxz6853
 * Templates done.
 *
 */

public class AudioBook extends Book {
	private int numDiscs; //number of discs in audiobook
	
	/**
	 * AudioBook is a book delivered as audio compact discs (CD).
	 * The constructor instantiates this particular kind of Book instance.
	 * @param title - of the book
     * @param author - of the book
     * @param cost - of the book
     * @param numDiscs - of the audiobook
	 */
	public AudioBook(String title, String author, int cost, int numDiscs) {
		//super must be instantiated first; use default parent constructor
		//must call parent before child can do anything on its own
		super(title, author, cost, Media.Audio);
		this.numDiscs = numDiscs;
	}
	
	/**
	 * The toString represents an AudioBook by adding ": {n} disks." to the 
	 * standard string representation of a Book. The {n} in this case is the 
	 * number of discs.
	 * @return string representation including the number of discs for the book.
	 */
	public String toString() {
		return super.getTitle() + ".\n\t" + super.getAuthor()
				+ ".\n\t" + getMedia();
	}
	
	/**
	 * AudioBook instances are never offered for sale; they are rental only.
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale() {
		return false;
	}
	
	/**
	 * The getMedia method adds the number of discs to the string representation 
	 * of this instance's media format. 
	 * @return the book's media.
	 */
	public String getMedia() {
		return super.getMedia() + ": " + numDiscs + " discs.";
	}
}
