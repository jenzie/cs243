/**
 * @author: Jenny Zhen
 * @name: SubExpression.java
 * @date: 03.29.12
 */

/**
 * $Id: SubExpression.java,v 1.3 2012-04-01 04:23:55 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: SubExpression.java,v $
 * Revision 1.3  2012-04-01 04:23:55  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:08  jxz6853
 * Templates done.
 *
 */

public class SubExpression implements Expression {
	private Expression exp1;
	private Expression exp2;
	/**
	 * Construct a Sub expression from the supplied subexpressions.
	 * @param exp1 First subexpression.
	 * @param exp2 Second subexpression.
	 */
	public SubExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	/**
	 * Generates code for a Sub expression. The code is the code for each 
	 * subexpression, surrounded by parentheses with a minus sign in between.
	 * @return A string that represents the code for the expression.
	 */
	public String emit() {
		return "(" + exp1.emit() + " - " + exp2.emit() + ")";
	}
	/**
	 * Causes evaluation of a Sub expression. The value is the 
	 * difference of the values of the subexpressions. 
	 * @return An Integer, the difference of the values of the sub-expressions.
	 */
    public Integer evaluate() {
    	return exp1.evaluate() - exp2.evaluate();
    }
}
