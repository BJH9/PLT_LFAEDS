package edu.handong.csee.plt.ast;

import edu.handong.csee.plt.lfae.value.FAE_Value;

public class Num extends AST {
	String num = "0";
	
	public Num(String num){
		this.num = num;
	}
	
	public String getStrNum() {
		return num;
	}
	
	public String getASTCode() {
		return "(num " + num +")";
	}
}
