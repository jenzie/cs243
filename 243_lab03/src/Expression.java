/**
 * @author: Jenny Zhen
 * @name: Expression.java
 * @date: 03.29.12
 */

/**
 * $Id: Expression.java,v 1.3 2012-04-01 04:23:55 jxz6853 Exp $
 * $Revision: 1.3 $
 * $Log: Expression.java,v $
 * Revision 1.3  2012-04-01 04:23:55  jxz6853
 * Everything works. Need documentation.
 *
 * Revision 1.2  2012-03-29 17:27:08  jxz6853
 * Templates done.
 *
 */

public interface Expression {
	public String emit(); //Generates code for an Add expression.
	public Integer evaluate(); //Causes evaluation of an expression.
}