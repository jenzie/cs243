/**
 * @author: Jenny Zhen
 * @name: PaperbackBook.java
 * @date: 04.05.12
 */
/**
 * $Id: PaperbackBook.java,v 1.3 2012-04-08 01:32:41 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: PaperbackBook.java,v $
 * Revision 1.3  2012-04-08 01:32:41  jxz6853
 * Fixed tags (complete).
 *
 * Revision 1.2  2012-04-07 21:16:32  jxz6853
 * Completed.
 *
 * Revision 1.1  2012-04-05 17:48:02  jxz6853
 * Templates done.
 *
 */

public class PaperbackBook extends Book {
	/**
	 * PaperbackBook represents a basic paperback book.
	 * The PaperbackBook constructor instantiates and initializes the instance.
	 * @param title - of the book
     * @param author - of the book
     * @param cost - of the book
	 */
	PaperbackBook(String title, String author, int cost) {
		//super must be instantiated first; use default parent constructor
		//must call parent before child can do anything on its own
		super(title, author, cost, Media.Paperback);
	}
	
	/**
	 * toString adds a trailing period(.) at the end of the returned text.
	 * @return string representation of this book
	 */
	public String toString() {
		return super.toString();
	}
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds 
	 * of books are offered only for rent, not for sale. 
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale() {
		return true;
	}
}
