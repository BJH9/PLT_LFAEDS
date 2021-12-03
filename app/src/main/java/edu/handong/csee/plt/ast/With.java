package edu.handong.csee.plt.ast;

public class With extends AST{
	String name = "0";
	AST named_expr = new AST();
	AST body = new AST();
	
	public With(String name, AST named_expr, AST body) {
		this.name = name;
		this.named_expr = named_expr;
		this.body = body;
	}
	
	public String getName() {
		return name;
	}
	
	public AST getNamed_expr() {
		return named_expr;
	}
	
	public AST getBody() {
		return body;
	}
	
	public String getASTCode() {
		return "" + "(app (fun " + name + " " + body.getASTCode() + ")" + " " + named_expr.getASTCode() + ")";
	}

}
