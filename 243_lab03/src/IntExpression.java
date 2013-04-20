/**
 * @author: Jenny Zhen
 * @name: IntExpression.java
 * @date: 03.29.12
 */

/**
 * $Id: IntExpression.java,v 1.3 2012-04-01 04:23:56 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: IntExpression.java,v $
 * Revision 1.3  2012-04-01 04:23:56  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:08  jxz6853
 * Templates done.
 *
 */

public class IntExpression extends Object implements Expression {
	private String valString;
	/**
	 * Construct an Int expression from the supplied string.
	 * @param valString The form the integer was written.
	 */
	public IntExpression(String valString) {
		this.valString = valString;
	}
	
	/**
	 * Causes evaluation of an Int expression. The value is its number.
	 * @return An Integer, the value of the expression.
	 */
	public Integer evaluate() {
		return Integer.parseInt(valString);
	}
	
	/**
	 * Generates code for an Int expression. The code is the valString 
	 * (the digits that were used to write it) of the int.
	 * @return A string that represents the code for the expression.
	 */
	public String emit() {
		return valString;
	}
}
