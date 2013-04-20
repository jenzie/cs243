/**
 * @author: Jenny Zhen
 * @name: AddExpression.java
 * @date: 03.29.12
 */

/**
 * $Id: AddExpression.java,v 1.3 2012-04-01 04:23:56 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: AddExpression.java,v $
 * Revision 1.3  2012-04-01 04:23:56  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:09  jxz6853
 * Templates done.
 *
 */

public class AddExpression implements Expression {
	private Expression exp1;
	private Expression exp2;
	/**
	 * Construct an Add expression from the supplied subexpressions.
	 * @param exp1 First subexpression.
	 * @param exp2 Second subexpression.
	 */
	public AddExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	/**
	 * Generates code for an Add expression. The code is the code for each 
	 * subexpression, surrounded by parentheses with a plus sign in between.
	 * @return A string that represents the code for the expression.
	 */
	public String emit() {
		return "(" + exp1.emit() + " + " + exp2.emit() + ")";
	}
	/**
	 * Causes evaluation of an Add expression. The value is the 
	 * sum of the values of the subexpressions. 
	 * @return An Integer, the sum of the values of the sub-expressions.
	 */
    public Integer evaluate() {
    	return exp1.evaluate() + exp2.evaluate();
    }
}
