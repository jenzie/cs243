/**
 * @author: Jenny Zhen
 * @name: Book.java
 * @date: 04.05.12
 */
/**
 * $Id: Book.java,v 1.3 2012-04-08 01:32:40 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: Book.java,v $
 * Revision 1.3  2012-04-08 01:32:40  jxz6853
 * Fixed tags (complete).
 *
 * Revision 1.2  2012-04-07 21:16:32  jxz6853
 * Completed.
 *
 * Revision 1.1  2012-04-05 17:48:02  jxz6853
 * Templates done.
 *
 */

public abstract class Book {
	
	private String title; //title of book
	private String author; //author of book
	private int cost; //cost if book is for sale 
	private Media media; //type of book
	
	/**
	 * The Book class represents the common characteristics of a book, 
	 * which may be produced in various media formats.
	 * @param title - the title of the book
     * @param author - the author of the book
     * @param cost - the cost of the book in cents
     * @param media - the media format of the book
	 */
	public Book(String title, String author, int cost, Media media) {
		this.title = title;
		this.author = author;
		this.cost = cost;
		this.media = media;
	}
	
	/**
	 * The standard string representation prints the title on the first line, 
	 * followed by the author on the second line, and the book media on the 
	 * third line. The second and subsequent lines are indented by a single 
	 * TAB character. Each line should be terminated with a period(.).
	 * @return standard string representation of Book
	 */
	public String toString() {
		return getTitle() + ".\n\t" + getAuthor() + ".\n\t" + getMedia() + ".";
	}
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds 
	 * of books are offered only for rent, not for sale. 
	 * @return true if this instance is for a final sale.
	 */
	public abstract boolean isForSale();
	
	/**
	 * @return the book title enclosed in double quotes(").
	 */
	public String getTitle() {
		return "\"" + title + "\"";
	}
	
	/**
	 * @return the book author name.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @return the book's cost converted into dollars and cents.
	 */
	public double getCost() {
		return cost / 100.0;
	}
	
	/**
	 * getMedia gets the 'display string representation' of the book's media.
	 * @return the book's media.
	 */
	public String getMedia() {
		return media.name();
	}
}
