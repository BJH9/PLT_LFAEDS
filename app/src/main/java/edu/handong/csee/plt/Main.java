package edu.handong.csee.plt;

import org.apache.commons.cli.Options;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.defrdsub.MtSub;
import edu.handong.csee.plt.lfae.value.NumV;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;

public class Main {
	private boolean help;
	
	private String parsed = "";
	private String interpreted = "";
	private String dInterpreted = "";
	private String result = "";
	AST lfae = new AST();

	public static void main(String[] args) {
		Main runner = new Main();
		runner.run(args);
	}
	
	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOption(options, args)) {
			if(help) {
				printHelp(options);
				return;
			}
			
			AST ast = new AST();
			Parser parser = new Parser();
			MtSub mtsub = new MtSub();
			NumV numV = new NumV();
			Interpreter interpreter = new Interpreter();
			DynamicInterpreter dInterpreter = new DynamicInterpreter();
			
			
			if(!(parsed == null)) {
				ast = parser.parse(parsed);
				System.out.println(ast.getASTCode());
			}
				
			if(!(interpreted == null)) {
				ast = parser.parse(interpreted);
				lfae = interpreter.interp(ast, mtsub);
				result = interpreter.interp2(lfae);
				System.out.println("interpreted: " + numV.nV(result));
			}
			
			else if(!(dInterpreted == null)) {
				ast = parser.parse(dInterpreted);
				lfae = dInterpreter.interp(ast, mtsub);
				result = dInterpreter.interp2(lfae);
				System.out.println("interpreted: " + numV.nV(result));
			}
			
			
			
			
		}
		
		
		
		
	}
	
	private boolean parseOption(Options options, String[] args) {
		CommandLineParser clParser = new DefaultParser();
		
		try {
			CommandLine cmd = clParser.parse(options, args);
			AST ast = new AST();
			
			parsed = cmd.getOptionValue("p");
			
			interpreted = cmd.getOptionValue("i");
			
			dInterpreted = cmd.getOptionValue("d");
			
			help = cmd.hasOption("h");
		} catch(Exception e) {
			printHelp(options);
			return false;
		}
		
		return true;
		
		
	}
	
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("p").longOpt("parse")
				.desc("parsing")
				.hasArg()
				.argName("parsing")
				.build());
		
		
		options.addOption(Option.builder("i").longOpt("interp")
				.desc("interpreter")
				.hasArg()
				.argName("interpreter")
				.build());
		
		options.addOption(Option.builder("d").longOpt("dInterp")
				.desc("dynamic interpreter")
				.hasArg()
				.argName("dynamic interpreter")
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "PL";
		String footer = "\nPlease report issues";
		formatter.printHelp("PL", header, options, footer, true);
	}
}
	