package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.Add;
import edu.handong.csee.plt.ast.App;
import edu.handong.csee.plt.ast.Fun;
import edu.handong.csee.plt.ast.Id;
import edu.handong.csee.plt.ast.Num;
import edu.handong.csee.plt.ast.Sub;
import edu.handong.csee.plt.defrdsub.ASub;
import edu.handong.csee.plt.defrdsub.MtSub;
import edu.handong.csee.plt.lfae.value.ClosureV;
import edu.handong.csee.plt.lfae.value.ExprV;
import edu.handong.csee.plt.lfae.value.FAE_Value;
import edu.handong.csee.plt.lfae.value.NumV;

import java.util.ArrayList;
import java.util.Random;

import edu.handong.csee.plt.Lookup;

public class Interpreter{
	Lookup lookup = new Lookup();
	Num_op numop = new Num_op();
	ClosureV closureV = new ClosureV();
	AST looked = new AST();
	ExprV exprV = new ExprV();
	
	
	String param = "";
	
	public AST interp(AST ast, AST ds) {
		
		if(ast instanceof Num) {
			
			return new NumV(ast);
		}
		
		if(ast instanceof Add) {
			
			Add add = (Add)ast;
			return new NumPlus(interp((add.getLhs()), ds), interp((add.getRhs()), ds));
		}
		
		if(ast instanceof Sub) {
			
			Sub sub = (Sub)ast;
			return new NumMinus(interp(sub.getLhs(), ds), interp(sub.getRhs(), ds));		
			}
		
		if(ast instanceof Id) {
			
			Id id = (Id)ast;
			System.out.println("id: " + id.getASTCode());
			looked = strict(look(id.getStrId(), ds));	
			
			
			if(looked instanceof ClosureV) {
				ClosureV cv = (ClosureV)looked;
				closureV = new ClosureV(cv.getParam(), cv.getBody(), ds);
			}
				
			return looked;
			
		}
		
				
		if(ast instanceof Fun) {
			
			Fun fun = (Fun)ast;
			closureV = new ClosureV(fun.getParam(), fun.getBody(), ds);	
			return closureV;
		}
		
		if(ast instanceof App) {
			
			App app = (App)ast;
			ClosureV fVal = (ClosureV)strict(interp(app.getFtn(), ds));
			ExprV aVal = new ExprV(app.getArg(), ds);
			
			return interp(fVal.getBody(), new ASub(fVal.getParam(), aVal, fVal.getDs()));
		}
		
		return null;
	}
	
	public String interp2(AST fae) {
		
		if(fae instanceof NumV) {
			NumV numv = (NumV)fae;
			return ((Num)numv.getStrNum()).getStrNum();
		}
		
		if(fae instanceof NumPlus) {
			NumPlus numPlus = (NumPlus)fae;
			return "" + (Integer.parseInt(interp2(strict(numPlus.getLhs()))) + Integer.parseInt(interp2(strict(numPlus.getRhs()))));
		}
		
		if(fae instanceof NumMinus) {
			NumMinus numMinus = (NumMinus)fae;
			return "" + (Integer.parseInt(interp2(strict(numMinus.getLhs()))) - Integer.parseInt(interp2(strict(numMinus.getRhs()))));
 		}
		
		
		return null;
		
		
	}
	
	public AST look(String name, AST ds) {
		if(ds instanceof MtSub) {
			System.out.println("free identifier");
			return null;
		}
			
		
		if(ds instanceof ASub) {
			if(name.equals(((ASub)ds).getName())) {
				return ((ASub)ds).getNumber();
			}
			else
				return look(name, ((ASub)ds).getSaved());
		}
		
			return null;
		
	}
	
	public AST strict(AST v) {
		
		
		if(v instanceof ExprV) {
			return strict(interp(((ExprV) v).getExpr(), ((ExprV)v).getDs()));
		}
		
		return v;
	}
	
}

