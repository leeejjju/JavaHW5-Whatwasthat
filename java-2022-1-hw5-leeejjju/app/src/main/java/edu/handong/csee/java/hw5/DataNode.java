package edu.handong.csee.java.hw5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**Data Node class for deal with the data of each country
 * Contains basic methods for interacting and information about a country.
 * */
public class DataNode {
	
	private String Province; 
	private String Country; 
	private double Lat; 
	private double Long; 
	private ArrayList<Integer> patients = new ArrayList<Integer>(); //number of patients between 1/22/20~5/3/20
	
	
	//static 
	ArrayList<String> countries = new ArrayList<String>(); //list of countries
	
	//Map<String, Integer> Countries = new HashMap<String, Integer>(); //list of countries and number of patients of that country
	
	static String[] dates; //list of dates (1/22/20~5/3/20)

	
	
	DataNode(){
		System.out.println("#new DataNode instance generated.");
	}

	


	//constructor
	DataNode(String line){
		
		String[] array = line.split(",");
		
		int count = 0;
		Province = array[count++];
		Country = array[count++];
		
		try {
			Lat = Double.parseDouble(array[count++]);
			Long = Double.parseDouble(array[count++]);
		}
		catch(NumberFormatException e) {
			
			if(Province.length() == 0 || String.valueOf(array[2].charAt(0)).equals("\"")) {
				Province = array[0];
				Country = (array[1] + "," + array[2]).replace("\"", "");
			}else {
				Province = (array[0] + "," + array[1]).replace("\"", "");
				Country = array[2];
			} 
			//count++;
			//System.out.println("* exception input occurred : " + Province +", " + Country);
			Lat = Double.parseDouble(array[count++]);
			Long = Double.parseDouble(array[count++]);
		}
		
		try {for(int i = count; i < array.length; i++) patients.add(Integer.parseInt(array[i]));}
		catch(NumberFormatException e) {}
		
		
		//if the country doesn't exist in countries array, add to array.
		if(!countries.contains(Country)){
			countries.add(Country);
			//numOfPatientsByCountry.add(patients.get(patients.size()-1));
		}//else ;numOfPatientsByCountry.set(countries.indexOf(Country), numOfPatientsByCountry.get(countries.indexOf(Country)) + patients.get(patients.size()-1));
		 
		
		//using hash map (key: country name, value: number of patients)
		if(!Countries.containsKey(Country)){
			Countries.put(Country, patients.get(patients.size()-1));
		}else{
			Countries.put(Country, Countries.get(Country)+patients.get(patients.size()-1));
		}

		
	}
	
	
	
	
	/**setting dates array*/
	static void setDates(String dateLine) {
		dates = dateLine.split(",");
	}

	/**get the country name of input index*/
	public String getCountryName(int i){
		return countries.get(i);
	}
	
	/**print information about this data node*/
	public void printInfo() {
		System.out.println("Province/State: "+ Province);
		System.out.println("Country/Region: "+ Country);
		System.out.println("Lat and Long: "+ Lat +", " + Long);
		System.out.println("Num of patients(1/22/20~5/3/20): ");
		
		if(patients.size() == 0) {
			for(int i = 0; i < (dates.length); i++) System.out.print(dates[i]+", ");
			System.out.print(dates[dates.length-1]+"\n");
		}else {
			for(int i = 0; i < (patients.size()-1); i++) System.out.print(patients.get(i)+", ");
			System.out.print(patients.size()-1+"\n");
		}
		
	}
	
	
	/**search the date on dates array and return the index*/
	public int getIndexOfDate(String date) { //"start" means first date, "final" means last date.
		int i;
		if(date.equals("final")) return patients.size()-1;
		else if(date.equals("start")) return 0;
		else {
			for(i = 0; !(dates[i].equals(date)); i++) {}
			if(i <= dates.length) return i;
			else return -1; //exception, cannot find the date in data
		}
	}
	
	
	/**get the number of patients on input date*/
	public int getNumOfPatients(String date) {
		if(patients.size() > 0) return patients.get(getIndexOfDate(date));
		else return 0;
	}
	
	/**@Overloading, can be work as input index integer*/
	public int getNumOfPatients(int dateIndex) {
		try {
			if(patients.size() > 0) return patients.get(dateIndex);
			else return 0;
		}catch(IndexOutOfBoundsException e) {return 0;}
		
	}

	/**calculate the number of increased patients between two date. start date and final date. 
	 * put the "one" as second parameter to get the  number of just one day.*/
	public int calcIncreasedPatients(String startDate, String finalDate){ 
		if(finalDate.equals("one")) {
			return (getNumOfPatients(startDate) - getNumOfPatients(getIndexOfDate(startDate)-1));
		}
		return (getNumOfPatients(finalDate) - getNumOfPatients(startDate));
	}
	
	/**@Overloading. can be work as input two index integer.
	 * put the negative integer as second parameter to get the  number of just one day.*/
	public int calcIncreasedPatients(int startDateIndex, int finalDateIndex){ 
		if(finalDateIndex < 0) {
			return (getNumOfPatients(startDateIndex) - getNumOfPatients(startDateIndex-1));
		}
		return (getNumOfPatients(finalDateIndex) - getNumOfPatients(startDateIndex));
	}
	
	
	/**return if this data is about given country*/
	public boolean isThatCountry(String that) {
		
		if(that.equals(Country)) return true;
		else return false;
	}
	

}

