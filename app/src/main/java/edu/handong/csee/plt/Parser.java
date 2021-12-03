package edu.handong.csee.plt;

import java.util.ArrayList;
import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.Add;
import edu.handong.csee.plt.ast.App;
import edu.handong.csee.plt.ast.Fun;
import edu.handong.csee.plt.ast.Id;
import edu.handong.csee.plt.ast.Num;
import edu.handong.csee.plt.ast.Sub;
import edu.handong.csee.plt.ast.With;

public class Parser {
	private String param = "0";

	public AST parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);
		
		// nm
		if(subExpressions.size() == 1 && isNumeric(subExpressions.get(0))) {
			
			return new Num(subExpressions.get(0));
		}
		
		//id
		if(subExpressions.size() == 1 && !isNumeric(subExpressions.get(0))) {
			
			return new Id(subExpressions.get(0));
		}

		// add
		if(subExpressions.get(0).equals("+")) {
			
			return new Add(parse(subExpressions.get(1)),parse(subExpressions.get(2)));
		}
		
		//sub
		if(subExpressions.get(0).equals("-")) {
			
			return new Sub(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		
		//fun
		if(subExpressions.get(0).equals("fun")) {
			
			param = subExpressions.get(1);
			if(param.startsWith("{") && param.length() > 1)
				param = param.substring(1, param.length() - 1);
			return new Fun(param, parse(subExpressions.get(2)));
		}
		
		if(subExpressions.get(0).equals("with")) {
			
			return new App ((new Fun(subExpressions.get(1), parse(subExpressions.get(3)))), parse(subExpressions.get(2)));
			//return new With(subExpressions.get(1), parse(subExpressions.get(2)), parse(subExpressions.get(3)));
		}
		
		if(subExpressions.size() == 2) {
			
			return new App(parse(subExpressions.get(0)), parse(subExpressions.get(1)));
		}
		
		// TODO implement all other cases....

		
		return null;
	}

	private ArrayList<String> splitExpressionAsSubExpressions(String exampleCode) {

		// deal with brackets first.
		if((exampleCode.startsWith("{") && !exampleCode.endsWith("}"))
				|| (!exampleCode.startsWith("{") && exampleCode.endsWith("}"))) {
			System.out.println("Syntax error");
			System.exit(0);
		}

		if(exampleCode.startsWith("{"))
			exampleCode = exampleCode.substring(1, exampleCode.length()-1);


		return getSubExpressions(exampleCode);
	}



	/**
	 * This method return a list of sub-expression from the given expression.
	 * For example, {+ 3 {+ 3 4}  -> +, 2, {+ 3 4}
	 * TODO JC was sleepy while implementing this method...it has complex logic and might be buggy...
	 * You can do better or find an external library.
	 * @param exampleCode
	 * @return list of sub expressions 
	 */
	private ArrayList<String> getSubExpressions(String exampleCode) {

		ArrayList<String> sexpressions = new ArrayList<String>();
		int openingParenthesisCount = 0;
		String strBuffer = "";
		for(int i=0; i < exampleCode.length()  ;i++) {
			if(i==0 || (i==0 && exampleCode.charAt(i) == '{')) {
				strBuffer = strBuffer + exampleCode.charAt(i);
				continue;
			} else if(exampleCode.charAt(i)==' ' && openingParenthesisCount==0){
				if(!strBuffer.isEmpty()) {
					sexpressions.add(strBuffer);
					strBuffer = "";
				}
				continue;
			} else {
				if(exampleCode.charAt(i)=='{' && openingParenthesisCount==0){
					openingParenthesisCount++;
					strBuffer = "" + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='{') {
					openingParenthesisCount++;
					strBuffer = strBuffer + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='}' && openingParenthesisCount>0) {
					openingParenthesisCount--;
					strBuffer = strBuffer + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='}') {
					// buffer is ready to be a subexpression
					continue;
				} else if(exampleCode.charAt(i) == ' ' && sexpressions.size() > 0 && sexpressions.get(sexpressions.size() - 1).equals("with")) {
					openingParenthesisCount--;
					strBuffer = strBuffer.substring(1, strBuffer.length());
					sexpressions.add(strBuffer);
					strBuffer = "";
					continue;
				}
			}
			strBuffer = strBuffer + exampleCode.charAt(i);
		}
		
		sexpressions.add(strBuffer);

		return sexpressions;
	}

	public static boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}
