/**
 * @author: Jenny Zhen
 * @name: Redact.java
 * @date: 05.02.12
 */

/**
 * $Id: Redact.java,v 1.6 2012-05-07 02:51:32 jxz6853 Exp $
 * $Log: Redact.java,v $
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
 * Revision 1.2  2012-05-06 07:04:42  jxz6853
 * Fix writing to file once censored words are found.
 *
 * Revision 1.1  2012-05-02 17:22:22  jxz6853
 * Template.
 *
 * $Revision: 1.6 $
 */

/**
 * A redact program.
 */
import java.io.*;
import java.util.ArrayList;

public class Redact {
	
	private BufferedReader inP; //reads input from censored list
	private BufferedReader in; //reads input from file to censor
	private PrintWriter outP; //write output to redact
	private RedactWriter out; //writes output to redacted file
	private String[] line; //current line of input file
	private ArrayList<String> censored; //list of words to be redacted
	private String curr; //current word on line
	
	/**
	 * Constructor to create an instance of Redact.
	 * @param args - list of file names from command line
	 */
	public Redact(String[] args) {
		censored = new ArrayList<String>();
		
		//create input/output streams for files
		try{
			outP = new PrintWriter(new FileWriter(args[2]));
			out = new RedactWriter(outP, censored);
			inP = new BufferedReader(new FileReader(args[0]));
			in = new BufferedReader(new FileReader(args[1]));
			while(hasNextLine(inP))
				censored.add(inP.readLine());
		}
		catch(FileNotFoundException fnfe){
			System.err.println("FileNotFoundException: Check the name of file.");
		}
		catch(IOException ioe){
			System.err.println("IOException: Unable to open file.");
		}
		
		processFile();
		
		//flush the output stream
		try {
			out.flush();
		} catch (IOException e) {
			System.err.println("Could not flush output stream.");
		}
	}
	
	/**
	 * Processes the file and redacts all censored words listed.
	 */
	private void processFile() {
		while(getNextLine()){
			
			//get next line
			if(line.length != 0)
			
			//censor words
			isCensored();
			
			//write to file
			writeLine();
		}
	}

	/**
	 * Checks to see if it is the end of file.
	 * @param br - the buffered reader instance
	 * @return true if not at end of file
	 */
	private boolean hasNextLine(BufferedReader br){
		try {
			br.mark(2);
			int c = br.read();
			
			if(c == -1){
				br.reset();
				return false;
			}
			else{
				br.reset();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				br.reset();
				br.close();
			} catch (IOException e1) {
				System.err.println("IOException: Unable to close file.");
			}
			System.err.println("IOException: Unable to close file.");
		} return false;
	}
	
	/**
	 * Gets the next line of the file as array of all words.
	 * @return list of words from the next line
	 */
	private boolean getNextLine() {
		String text = ""; //line of file
		try {
			text = in.readLine();
		} catch (IOException e) {
			System.err.println("File read error in reading next line.");
			return false;
		}
		if(text == null)
			return false;
		
		//split line into array of string words
		line = text.split(" ");
		return true;
	}
	
	/**
	 * Removes all the punctuation marks from a string.
	 * @param word - string to check/remove punctuation
	 * @return string with punctuation removed
	 */
	private String remPunctuation(String word) {
		int index; //index of apostrophe
		if(word.contains("'S") || word.contains("'s") || 
				word.contains("'ll") || word.contains("'LL")) {
			index = word.indexOf('\'');
			return word.substring(0, index);
		}
		return word.replaceAll("[^a-zA-Z]", "");
	}
	
	/**
	 * Checks to see if any of the words on the line has to be censored.
	 */
	private void isCensored() {
		String hyphenWord = "";
		String midWord = "";
		int index;
		
		for(int i = 0; i < line.length; i++) {
			curr = line[i];
			//if there is a hyphen in the string
			if(curr.contains("-")) {
				while(true){
					index = curr.indexOf("-");
					if(index == -1)
						midWord = curr;
					else
						midWord = curr.substring(0, index);
					if(censored.contains(midWord))
						midWord = redact(midWord);
					hyphenWord += midWord;
					if(index != -1)
						hyphenWord += "-";
					curr = curr.substring(index + 1, curr.length());
					if(curr.isEmpty() || index == -1)
						break;
				}
				line[i] = hyphenWord;
				curr = "";
				hyphenWord = "";
			//otherwise, just remove punctuation and redact
			} else {
				curr = remPunctuation(curr);
				if(censored.contains(curr)){
					line[i] = redact(line[i]);
				}
			}
		}
	}
	
	/**
	 * Redacts a censored word by replacing all the letters with 'X's.
	 * @param word - string to redact
	 * @return redacted string
	 */
	private String redact(String word) {
		if(word.contains("\'S") || word.contains("\'s"))
			return word.replaceAll("[a-zA-Z&&[^(\'S)(\'s)]]", "X");
		else if(word.contains("'ll") || word.contains("'LL"))
			return word.replaceAll("[a-zA-Z&&[^(\'ll)(\'LL)]]", "X");
		return word.replaceAll("[a-zA-Z]", "X");
	}
	
	/**
	 * Writes the line to the output file.
	 * @return true if the line was written to file
	 */
	private boolean writeLine() {
		char[] curr;
		for(String word : line) {
			curr = new char[word.length() + 1];
			for(int i = 0; i < word.length(); i++)
				curr[i] = word.charAt(i);
			curr[word.length()] = ' ';
			try {
				out.write(curr, 0, word.length() + 1);
			} catch (IOException e) {
				System.err.println("IOException: Unable to write word to file.");
				return false;
			}
		}
		try {
			out.write('\n');
			return true;
		} catch (IOException e) {
			System.err.println("IOException: Unable to write newline to file.");
			return false;
		}
	}
	
	/**
	 * The main method of a program to redact.
	 * Usage: java Redact <word-file> <input-file> <output-file>
	 * @param args - The command line arguments
	 * 			word-file: The word-file, containing words to be redacted.
	 * 			input-file: The input file (or - for System.in).
	 * 			output-file: The output file (or - for System.out). 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if(args.length == 3)
			new Redact(args);
		else
			System.err.println("InvalidNumberOfArguments: " +
				"Enter arguments in format \"word-file input-file output-file\"");
	}
}
