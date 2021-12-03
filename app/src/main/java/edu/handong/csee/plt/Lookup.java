package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defrdsub.ASub;
import edu.handong.csee.plt.defrdsub.MtSub;
import edu.handong.csee.plt.lfae.value.ClosureV;
import edu.handong.csee.plt.lfae.value.ExprV;
import edu.handong.csee.plt.lfae.value.FAE_Value;

public class Lookup extends AST{
	ClosureV closureV = new ClosureV();
	
	public AST look(String name, AST ds) {
		if(ds instanceof MtSub)
			return null;
		
		if(ds instanceof ASub) {
			if(name.equals(((ASub)ds).getName())) {
				return ((ASub)ds).getNumber();
			}
			else
				return look(name, ((ASub)ds).getSaved());
		}
		
			return null;
		
	}
	
	
}
