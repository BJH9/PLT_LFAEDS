package edu.handong.csee.plt.lfae.value;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defrdsub.ASub;

public class ClosureV extends AST{
	String param = "0";
	AST body = new AST();
	AST ds = new AST();
	
	public ClosureV() {}
	
	public ClosureV(String param, AST body, AST ds) {
		this.param = param;
		this.body = body;
		this.ds = ds;
	}

	public String getParam() {
		return param;
	}
	
	public AST getBody() {
		return body;
	}
	
	public AST getDs() {
		return ds;
	}
	
	public String getASTCode() {
		return "(closureV " + param + " " + body.getASTCode() + " " + ds.getASTCode() + ")"; 
	}
	
}
