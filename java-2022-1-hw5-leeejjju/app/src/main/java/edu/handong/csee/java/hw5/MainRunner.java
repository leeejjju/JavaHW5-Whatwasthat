package edu.handong.csee.java.hw5;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/**Main Runner Class include main method*/
public class MainRunner {
	
	//TODO using thread for read files at the same time.
	//OK: using file I/O for making output file
	//OK: study how to make options required at least one @@@@
	//TODO study how to get .zip file and make .zip file
	
	
	
	Analyzer analyzer = null;
	
	static boolean listInput = false;
	static boolean output = false; //if output option is typed
	static String outputPath = "";
	File outFile = null;
    static BufferedWriter bw = null;
    static boolean numsort = false;
    static String listPath ="";
    
    
    public String type = "";
	
	
	
    /**Make csv file as output values.*/
	public void saveTheCSV(char option){

		outFile = new File("./" + outputPath + ".txt");
		//System.out.println("enterd path is "+ outFile);

        try{
            bw = new BufferedWriter(new FileWriter(outFile));
            
            bw.write("The total number of countries: " + analyzer.getNumberOfCountries()+"\n");
			bw.write("The total number of the accumulated " + type+ " until now: " +analyzer.getNumberOfAllPatients()+"\n");
		
            
            if(numsort) bw.write("The total number of " + type+ " by the selected countries (Sorted by the number of confirmed patients.)\n");
            else bw.write("The total number of " + type+ " by the selected countries (Sorted by country names in alphabetical order.)\n");

            
            
            if(option == 'a'){
    			for (Map.Entry<String, Integer> entry : analyzer.CountriesOfAnalyer.entrySet()) {
    	            if(output) bw.write("- " + entry.getKey() + ": " + entry.getValue() + "\n");
    	            else System.out.println("- " + entry.getKey() + ": " + entry.getValue());
    			}
    			
    		}else if(option == 'l'){
    			for (Map.Entry<String, Integer> entry : analyzer.CountriesOfAnalyer.entrySet()) {
    				if(Analyzer.selectedList.contains(entry.getKey())) {
    					if(output) bw.write("- " + entry.getKey() + ": " + entry.getValue() + "\n");
    					else System.out.println("- " + entry.getKey() + ": " + entry.getValue());
    				}
    			}
    		}else{
    			System.out.println("[Error] Option not found");
    		}
            

            bw.flush();
            bw.close();

        }catch(Exception e){
        	System.out.println("@somthing wrong with writing csv...!!");
            e.printStackTrace();
        }
        
    }
	

	/**main method. run the this.runner()*/
	public static void main(String[] args) {
		
		MainRunner runner = new MainRunner();
		OptionRunner myOptionRunner = new OptionRunner();
		
		//run the option
		myOptionRunner.run(args);
		//runner.runrun();
		
	}

	
	/**run method. Activate the option and call the required method.*/
	public void runrun (){
				
		
		
		/*
		ZipFile outFile;
		//TODO @@
		if(output) {
			//TODO open the output file stream
			try {
				outFile = new ZipFile("filename.zip");
				outFile.addFile("filename.ext");
				//new ZipFile("filename.zip").addFile(new File("filename.ext"));
				//Creating a zip file by adding a folder to it / Adding a folder to an existing zip
				new ZipFile("filename.zip").addFolder(new File("/users/some_user/folder_to_add"));
				//Creating a zip file from stream / Adding a stream to an existing zip
				//new ZipFile("filename.zip").addStream(inputStream, new ZipParameters());
				//https://github.com/srikanth-lingala/zip4j 
				
			} catch (ZipException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//@TODO ready to write after things on the file
		}*/

		
		//print(or write) the basic info texts
		if(output) {
			
			if(listInput) {
				setSelectedList();
				saveTheCSV('l');
			}
			else saveTheCSV('a');
			System.out.println("The result file is saved in " + outputPath + ".txt");
			
		}else {
			
			printBasicInfo();
			if(numsort) System.out.println("The total number of " + type + " by the selected countries (Sorted by the number of confirmed patients.)");
			else System.out.println("The total number of " + type + " by the selected countries (Sorted by country names in alphabetical order.)");
			
			if(listInput) {
				setSelectedList();
				printList('l');
			}
			else printList('a');
		}


	}
	
	public void setSelectedList() {
		//make a selection list from csv file. 
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new File(listPath));
		}catch(FileNotFoundException e){
			System.out.println("[Error] File not found");
    		System.exit(0);
		}

		while(inputStream.hasNextLine()){
			String oneCountry = inputStream.nextLine();
			oneCountry.trim();
			if(analyzer.CountriesOfAnalyer.containsKey(oneCountry)){
				//System.out.println(oneCountry);
				Analyzer.selectedList.add(oneCountry);
			}else{
				String[] tmp = oneCountry.split(",");
				//System.out.println(tmp[0]);
				Analyzer.selectedList.add(tmp[0]);
			}
			
		}
		inputStream.close();
	}

	/**print the basic information*/
	public void printBasicInfo(){
		
		System.out.println("The total number of countries: " + analyzer.getNumberOfCountries());
		System.out.println("The total number of the accumulated " + type+ " until now: " +analyzer.getNumberOfAllPatients());

	} 


	/**print all('a' option) country list or selected country list('l' option). */
	public void printList(char option){
		
		if(option == 'a'){
			for (Map.Entry<String, Integer> entry : analyzer.CountriesOfAnalyer.entrySet()) {
	            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
			}
			
		}else if(option == 'l'){
			for (Map.Entry<String, Integer> entry : analyzer.CountriesOfAnalyer.entrySet()) {
				if(Analyzer.selectedList.contains(entry.getKey())) {
					System.out.println("- " + entry.getKey() + ": " + entry.getValue());
				}
			}
		}else{
			System.out.println("[Error] Option not found");
		}
		
	}
	
	
	
	


	
}
