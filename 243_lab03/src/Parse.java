/**
 * @author: Jenny Zhen
 * @name: MulExpression.java
 * @date: 03.29.12
 */

/**
 * $Id: Parse.java,v 1.5 2012-04-07 20:46:44 jxz6853 Exp $
 * $Revision: 1.5 $
 * $Log: Parse.java,v $
 * Revision 1.5  2012-04-07 20:46:44  jxz6853
 * *** empty log message ***
 *
 * Revision 1.4  2012-04-03 23:34:39  jxz6853
 * Completed.
 *
 * Revision 1.3  2012-04-01 04:23:55  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:08  jxz6853
 * Templates done.
 * 
 */

public class Parse {
	/**
	 * A container for the static methods to parse interp expressions.
	 */
	public Parse() {		
	}

	/**
	 * Parse a prefix expression.
	 * @param s A String representing the expression.
	 * @return An Expression tree representing the token stream, or null.
	 */
	public static Expression parseString(String s) {
		Expression e = null; //expression nodes
		String[] strTemp = s.split(" "); //get temporary values
		String newStr = s.substring(s.indexOf(" ")+1); //rebuild string
		String current = strTemp[0]; //current value to be processed
		
		if(current.equals("")) //stop
			return e;
		if(current.equals("+")){ // check if addition
			e = new AddExpression(parseString(newStr), parseString(newStr.substring(
					newStr.indexOf(" ")+1)));
		} else if(current.equals("-")) //if subtraction
			e = new SubExpression(parseString(newStr), parseString(newStr.substring(
					newStr.indexOf(" ")+1)));
		else if(current.equals("*")) //if multiplication
			e = new MulExpression(parseString(newStr), parseString(newStr.substring(
					newStr.indexOf(" ")+1)));
		else{ //if integer value
			e = new IntExpression(current);
		} return e; //return expression
	}
}
