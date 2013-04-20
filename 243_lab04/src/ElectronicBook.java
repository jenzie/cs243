/**
 * @author: Jenny Zhen
 * @name: ElectronicBook.java
 * @date: 04.05.12
 */
/**
 * $Id: ElectronicBook.java,v 1.3 2012-04-08 01:32:40 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: ElectronicBook.java,v $
 * Revision 1.3  2012-04-08 01:32:40  jxz6853
 * Fixed tags (complete).
 *
 * Revision 1.2  2012-04-07 21:16:31  jxz6853
 * Completed.
 *
 * Revision 1.1  2012-04-05 17:48:01  jxz6853
 * Templates done.
 *
 */

public class ElectronicBook extends Book {
	String theURL; //url where ebook was from
	
	/**
	 * ElectronicBook represents an electronic book instance, an e-book.
	 * The ElectronicBook constructor instantiates the instance and 
	 * initializes all its fields using the supplied arguments.
	 * @param title - of the book
     * @param author - of the book
     * @param cost - of the book
     * @param theURL - of the book, which must contain the text "://".
	 */
	public ElectronicBook(String title, String author, int cost, String theURL) {
		//super must be instantiated first; use default parent constructor
		//must call parent before child can do anything on its own
		super(title, author, cost, Media.Electronic);
		this.theURL = theURL;
	}
	
	/**
	 * The toString represents an ElectronicBook by adding " from {URL}" to the 
	 * standard string representation of a Book. The {URL} in this case is the 
	 * resource locator of this instance.
	 * @return string representation of this electronic book
	 */
	public String toString() {
		return super.getTitle() + ".\n\t" + super.getAuthor() + ".\n\t"
				+ super.getMedia() + " from " + theURL;
	}
	
	/**
	 * ElectronicBook instances are never offered for sale.
	 * You may rent them instead.
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale() {
		return false;
	}
	
	/**
	 * The getMedia method returns the standard string media representation 
	 * plus the text " : {URL}". The {URL} in this case is the resource locator 
	 * of this instance.
	 * @return string representation of the media of this book
	 */
	public String getMedia() {
		return super.getMedia() + " : " + theURL;
	}
}
