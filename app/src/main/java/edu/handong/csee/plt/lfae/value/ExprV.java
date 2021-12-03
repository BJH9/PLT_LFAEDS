package edu.handong.csee.plt.lfae.value;

import edu.handong.csee.plt.ast.AST;

public class ExprV extends AST{
	AST expr = new AST();
	AST ds = new AST();
	
	public ExprV() {}
	
	public ExprV(AST expr, AST ds) {
		this.expr = expr;
		this.ds = ds;
	}
	
	public AST getExpr() {
		return expr;
	}
	
	public AST getDs() {
		return ds;
	}
	
	public String getASTCode() {
		return "(ExprV " + expr.getASTCode() + ds.getASTCode() + ")";
	}
}
