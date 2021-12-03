package edu.handong.csee.plt.lfae.value;

import edu.handong.csee.plt.Interpreter;
import edu.handong.csee.plt.StrictV;
import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.Num;

public class NumV extends AST{
	String numb = "0";
	AST numV = new AST();
	
	public NumV() {}
	
	public NumV(AST numV){
		this.numV = numV;
	}
	
	public AST getStrNum() {
		return numV;
	}
	
	public String nV(String result) {
		if(!result.startsWith("( "))
			return "(numV " + result + ")";
		return result;
	}
	
	public String getASTCode() {
		numb = numV.getASTCode().substring(4, numV.getASTCode().length() - 1).trim();
		return "(numV " + numb +")";
	}
}
