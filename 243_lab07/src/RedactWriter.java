/**
 * @author: Jenny Zhen
 * @name: RedactWriter.java
 * @date: 05.02.12
 */

/**
 * $Id: RedactWriter.java,v 1.6 2012-05-07 02:51:32 jxz6853 Exp $
 * $Log: RedactWriter.java,v $
 * Revision 1.6  2012-05-07 02:51:32  jxz6853
 * *** empty log message ***
 *
 * Revision 1.5  2012-05-07 02:50:42  jxz6853
 * Finished.
 *
 * Revision 1.4  2012-05-07 02:13:20  jxz6853
 * Fix "she'll"
 *
 * Revision 1.3  2012-05-06 23:57:44  jxz6853
 * Fix end of file.
 *
 * Revision 1.2  2012-05-06 07:04:41  jxz6853
 * Fix writing to file once censored words are found.
 *
 * Revision 1.1  2012-05-02 17:22:21  jxz6853
 * Template.
 *
 * $Revision: 1.6 $
 */

/**
 * A redact writer.
 */

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public class RedactWriter {
	
	private Writer writer;
	Collection<String> words;
	
	/**
	 * Creates a new redact writer.
	 * @param wr - The underlying Writer.
	 * @param redacts - A Collection of words to be redacted.
	 * @throws IllegalArgumentException - Thrown if redacts includes a string 
	 * 		that is empty or is not exclusively comprised of characters in the 
	 * 		range 'a' through 'z' and 'A' through 'Z'.
	 */
	public RedactWriter(Writer wr, Collection<String> redacts) 
				throws IllegalArgumentException {
		this.writer = wr;
		this.words = redacts;
	}
	
	/**
	 * Closes the stream, flushing it first. Because no additional characters 
	 * may be written to a closed Writer, any buffered characters should be 
	 * written to the underlying Writer as though they were a completed word. 
	 * @throws IOException
	 */
	public void close() throws IOException {
		writer.close();
	}
	
	/**
	 * Flushes the stream. The flush method does not adhere to the complete 
	 * specification given by Flushable.flush and Writer.flush, because it is 
	 * impossible to know if the buffered characters correspond to a word that 
	 * should be redacted until subsequent written characters are observed. The 
	 * flush method does, however, flush the underlying Writer.
	 * @throws IOException
	 */
	public void flush() throws IOException {
		writer.flush();
	}
	
	/**
	 * Writes a portion of an array of characters.
	 * @param cbuf - Array of characters
	 * @param off - Offset from which to start writing characters
	 * @param len - Number of characters to write
	 * @throws IOException
	 */
	public void write(char[] cbuf, int off, int len) throws IOException {
		writer.write(cbuf, off, len);
	}
	
	/**
	 * Writes a single character. If the character belongs to a redacted word, 
	 * writes 'X' to the underlying Writer. Otherwise, writes the character to 
	 * the underlying Writer.
	 * @param c - int specifying a character to be written
	 * @throws IOException
	 */
	public void write(int c) throws IOException {
		writer.write(c);
	}
}
