/**
 * @author: Jenny Zhen
 * @name: HardcoverBook.java
 * @date: 04.05.12
 */
/**
 * $Id: HardcoverBook.java,v 1.3 2012-04-08 01:32:41 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: HardcoverBook.java,v $
 * Revision 1.3  2012-04-08 01:32:41  jxz6853
 * Fixed tags (complete).
 *
 * Revision 1.2  2012-04-07 21:16:32  jxz6853
 * Completed.
 *
 * Revision 1.1  2012-04-05 17:48:03  jxz6853
 * Templates done.
 *
 */

public class HardcoverBook extends Book {
	private String coverMaterial; //material of cover: cloth or leather
	
	/**
	 * HardcoverBook represents books with hard covers.
	 * The HardcoverBook constructor instantiates the instance by initializing 
	 * all the fields with the specified values.
	 * @param title - of the book
     * @param author - of the book
     * @param cost - of the book
     * @param coverMaterial - of the book
	 */
	public HardcoverBook(
			String title, String author, int cost, String coverMaterial) {
		//super must be instantiated first; use default parent constructor
		//must call parent before child can do anything on its own
		super(title, author, cost, Media.Hardcover);
		this.coverMaterial = coverMaterial;
		
	}
	
	/**
	 * The toString represents an HardcoverBook by adding " {cover material}." 
	 * to the standard string representation of a Book. The {cover material} in 
	 * this case is the instance's cover material. There is a trailing period(.) 
	 * at the end of the returned text.
	 * @return string representation of this hardcover book.
	 */
	public String toString() {
		return super.getTitle() + ".\n\t" + super.getAuthor() 
				+ ".\n\t" + getMedia() + "."; 
	}
	
	/**
	 * @return string representation of the hardcover material.
	 */
	public String getCoverMaterial() {
		return coverMaterial; //leather or cloth
	}
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds of 
	 * books are offered only for rent, not for sale. 
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale() {
		return true;
	}
	
	/**
	 * The getMedia method extends the standard media information string by 
	 * adding text describing the cover material of this instance. There is a 
	 * trailing period(.) at the end of the returned text.
	 * @return string representation of this book's media.
	 */
	public String getMedia() {
		return super.getMedia() + " " + getCoverMaterial();
	}
}
