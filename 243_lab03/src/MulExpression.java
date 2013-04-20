/**
 * @author: Jenny Zhen
 * @name: MulExpression.java
 * @date: 03.29.12
 */

/**
 * $Id: MulExpression.java,v 1.3 2012-04-01 04:23:56 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: MulExpression.java,v $
 * Revision 1.3  2012-04-01 04:23:56  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:09  jxz6853
 * Templates done.
 *
 */

public class MulExpression implements Expression {
	private Expression exp1;
	private Expression exp2;
	/**
	 * Construct a Mul expression from the supplied subexpressions.
	 * @param exp1 First subexpression.
	 * @param exp2 Second subexpression.
	 */
	public MulExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	/**
	 * Generates code for a Mul expression. The code is the code for each 
	 * subexpression, surrounded by parentheses with an asterisk in between.
	 * @return A string that represents the code for the expression.
	 */
	public String emit() {
		return "(" + exp1.emit() + " * " + exp2.emit() + ")";
	}
	
	/**
	 * Causes evaluation of a Mul expression. The value is the 
	 * product of the values of the subexpressions. 
	 * @return An Integer, the product of the values of the sub-expressions.
	 */
    public Integer evaluate() {
    	return exp1.evaluate() * exp2.evaluate();
    }
}
