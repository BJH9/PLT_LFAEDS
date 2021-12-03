package edu.handong.csee.plt.defrdsub;

import edu.handong.csee.plt.Interpreter;
import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.lfae.value.FAE_Value;

	public class ASub extends AST{
		String name = "0";
		AST number = new AST();
		AST saved = new AST();
		
	public ASub(String name, AST number, AST saved) {
		this.name = name;
		this.number = number;
		this.saved = saved;
	}
	
	public String getName() {
		return name;
	}
	
	public AST getNumber() {
		return number;
	}
	
	public AST getSaved() {
		return saved;
	}
	
	public String getASTCode() {
		return "(ASub " + name + " " + number.getASTCode() + " " + saved.getASTCode() + ")";
	}
}
