package edu.handong.csee.plt.ast;

import edu.handong.csee.plt.defrdsub.ASub;
import edu.handong.csee.plt.defrdsub.MtSub;
import edu.handong.csee.plt.lfae.value.ClosureV;
import edu.handong.csee.plt.lfae.value.ExprV;

public class AST {
	
	public String getASTCode() {
		String astCode="";
		if(this instanceof Add)
			astCode = ((Add)this).getASTCode();
		
		if(this instanceof Sub)
			astCode = ((Sub)this).getASTCode();
		
		if(this instanceof Num)
			astCode = ((Num)this).getASTCode();

		if(this instanceof Id)
			astCode = ((Id)this).getASTCode();
		
		if(this instanceof With)
			astCode = ((With)this).getASTCode();
		
		if(this instanceof App)
			astCode = ((App)this).getASTCode();
		
		if(this instanceof Fun)
			astCode = ((Fun)this).getASTCode();
		
		if(this instanceof ASub)
			astCode = ((ASub)this).getASTCode();
		
		if(this instanceof MtSub)
			astCode = ((MtSub)this).getASTCode();
		
		if(this instanceof ExprV)
			astCode = ((ExprV)this).getASTCode();
		
		if(this instanceof ClosureV)
			astCode = ((ClosureV)this).getASTCode();
		
		return astCode;
	}
}

