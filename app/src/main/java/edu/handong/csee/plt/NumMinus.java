package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.lfae.value.FAE_Value;

public class NumMinus extends AST{
	AST lhs = new AST();
	AST rhs = new AST();
	
	public NumMinus(AST lhs, AST rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public AST getLhs() {
		return lhs;
	}
	
	public AST getRhs() {
		return rhs;
	}
	
}
