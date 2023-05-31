package edu.handong.csee.java.hw5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.cli.*;

/**Class to create and run the apache CLI options*/
public class OptionRunner {
	
	private boolean help;
	//private boolean data;
	private boolean countrylist;
	private boolean numsort;
	//new ones for HW5
	private boolean confirmed;
	private boolean death;
	private boolean recovered;
	private boolean output;
	
	
	private String dataPathC;
	private String dataPathD;
	private String dataPathR;
	//private String dataPath;
	private String listPath;
	private String outputPath;
	
	private ArrayList<ThreadForAnalyze> threads = new ArrayList<ThreadForAnalyze>();


	/**Basic run method. create options, Perform an action appropriate to the entered option.*/
	public void run(String[] args) {
		Options options = createOptions();
		
		
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				//System.exit(0);
			}
			
			if(output) {
				//If the file extension name of the file path is zip, generate a real zip file after creating a text file. 
				//The text file name can be any name but can use the same name as the zip file. 
				//(For example, aaa.zip contains aaa.txt file) Otherwise, generate a text file 
				//(Extension name can be any, e.g., csv or txt)
				
				MainRunner.output = true;
				MainRunner.outputPath += outputPath;
				
			}
			
			/*
			if(data) {
				//System.out.println("[You selected Data option.]");
				// TODO Set the data file for the confirmed patient numbers. [Always required option]
				
				MainRunner.analyzer = new Analyzer(new File(dataPath));
				//MainRunner.printBasicInfo(1);
				
			}*/
			
			if (confirmed || death || recovered) {
				if(confirmed) {
					//MainRunner.type = "patients";
					//MainRunner.analyzer = new Analyzer(new File(dataPathC));
					System.out.println("@@@@@@@@@@@@@@@confirmed input exist");
					
					ThreadForAnalyze thread = new ThreadForAnalyze();
					thread.setData("patients", dataPathC);
					threads.add(thread);
					thread.run();
					
				}
				
				
				if(death) {
					//MainRunner.type = "deaths";
					//MainRunner.analyzer = new Analyzer(new File(dataPathD));
					System.out.println("@@@@@@@@@@@@@@@death input exist");
					
					ThreadForAnalyze thread = new ThreadForAnalyze();
					thread.setData("deaths", dataPathD);
					threads.add(thread);
					thread.run();
					
				}
				
				
				if(recovered) {
					//MainRunner.type = "recovered patients";
					//MainRunner.analyzer = new Analyzer(new File(dataPathR));
					System.out.println("@@@@@@@@@@@@@@@recovered input exist");
					
					ThreadForAnalyze thread = new ThreadForAnalyze();
					thread.setData("recovered patients", dataPathR);
					threads.add(thread);
					thread.run();
					
				}
			}else {
				printHelp(options);
				System.exit(0);
			}

			
			
			if(numsort) {
				//System.out.println("[You selected numsort option.]");
				MainRunner.numsort = numsort; //for csv output... 
				
				//DataNode.sortCountryList(2);
				//if(output); //TODO
				//else System.out.println("The total number of patients by the selected countries (Sorted by the number of confirmed patients.)");
				ThreadForAnalyze.numsort = true;
			}else{
				//DataNode.sortCountryList(1);
				//if(output); //TODO
				//else System.out.println("The total number of patients by the selected countries (Sorted by country names in alphabetical order.)");
				
			}

			
			if(countrylist) {
				//TODO Set the csv file that contains the country names. 
				//One country name in one line. This file may contain a wrong country name and empty or space lines. 
				//Your program only needs to show the result for the existing country names from the file set by the '-countrylist' option 
				//(ignore wrong country names and no need to print out any error message.).

				//System.out.println("[You selected country list option.]");
				//let MainRunner know that list is inputed.
				MainRunner.listInput = true;
				MainRunner.listPath = listPath;
				

			}
			
			
			if(threads.size() > 0) {
				for(ThreadForAnalyze x : threads) {
					System.out.println("#About "+ x.t);
					x.printInfo();
					System.out.println();
				}
			}
		
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			help = cmd.hasOption("h");
			//data = cmd.hasOption("d");
			countrylist = cmd.hasOption("l");
			numsort = cmd.hasOption("s");
			
			confirmed = cmd.hasOption("c");
			death = cmd.hasOption("d");
			recovered = cmd.hasOption("r");
			output = cmd.hasOption("o");
			
			//dataPath = cmd.getOptionValue("d");
			outputPath = cmd.getOptionValue("o");
			dataPathC = cmd.getOptionValue("c");
			dataPathD = cmd.getOptionValue("d");
			dataPathR = cmd.getOptionValue("r");
			listPath = cmd.getOptionValue("l");

		} catch (Exception e) {
			printHelp(options);
			System.exit(0);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();
		
		/*
		// add options by using OptionBuilder - data
		options.addOption(Option.builder("d").longOpt("data")
				.desc("Set the data file for the confirmed patient numbers")
				.hasArg()
				.argName("data path")
				.required()
				.build());
		*/
		
		//new ones for HW5*********************************
		//-confirmed [filepath] or -c [filepath]: "A data file path for the confirmed patient" (Use this as the CLI description text)
		options.addOption(Option.builder("c").longOpt("confirmed")
				.desc("A data file path for the confirmed patient")
				.hasArg()
				.argName("filepath")
				//.required()
				.build());
		
		//-death [filepath] or -d [filepath]: "A data file path for the death patient" (Use this as the CLI description text)
		options.addOption(Option.builder("d").longOpt("death")
				.desc("A data file path for the death patient")
				.hasArg()
				.argName("filepath")
				//.required()
				.build());
		
		//-recovered [filepath] or -r [filepath]: "A data file path for the recovered patient" (Use this as the CLI description text)
		options.addOption(Option.builder("r").longOpt("recovered")
				.desc("A data file path for the recovered patient")
				.hasArg()
				.argName("filepath")
				//.required()
				.build());
		
		
		//-output [filepath] and -o [filepath]: "Save the results as a file" (Use this as the CLI description text) 
		//If the file extension name of the file path is zip, generate a real zip file after creating a text file. 
		//The text file name can be any name but can use the same name as the zip file. 
		//(For example, aaa.zip contains aaa.txt file) 
		//Otherwise, generate a text file (Extension name can be any, e.g., csv or txt)
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Save the results as a file")
				.hasArg()
				.argName("filepath")
				//.required()
				.build());
		//******************************************************
		
		
		// add options by using OptionBuilder - countrylist
		options.addOption(Option.builder("l").longOpt("countrylist")
				.desc("Set the csv file that contains the country names")
				.hasArg()
				.argName("country list path")
				.build());

		// add options by using OptionBuilder - numsort
		options.addOption(Option.builder("s").longOpt("numsort")
				.desc("Sort by the number of patients of each country")
				.build());
		
		// add options by using OptionBuilder - help
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a help page")
		        .build());
		
		

		

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "COVID-19 program";
		//String footer ="\n Please report issues at https://github.com/ISEL-JAVA/2022-1-java-hw4-leeejjju\n\n";
		String footer ="\n https://github.com/ISEL-JAVA/2022-1-java-hw4-HGUISEL\n\n";
		
		
		formatter.printHelp("COVID-19 program", header, options, footer, true);
	}

}