package edu.handong.csee.java.hw5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**Analyzer class for analyzing input data(string list or file path) and making data node list.
 * Provides useful methods for properly analyzing data.
 */
public class Analyzer {
	
	
	private ArrayList<DataNode> datas = new ArrayList<DataNode>();
	
	static public ArrayList<String> selectedList = new ArrayList<String>(); //selected country list from input csv file. 
	Map<String, Integer> CountriesOfAnalyer = new HashMap<String, Integer>(); //list of countries and number of patients of that country
	

	//DataNode myDataNode = new DataNode();
	
	

	//constructor
	//통짜 스트링을 받아서 각 줄에 대해 해당 데이터노드를 만들고... 인스턴트 리스트를 초기화함 
	Analyzer(String[] data){
		
		//System.out.println("We got the String array input!");
		for(int i = 1; i < data.length; i++) {
			DataNode tmp = new DataNode(data[i]);
			datas.add(tmp);
		}
		
		//setting the dates
		DataNode.setDates("1/22/20,1/23/20,1/24/20,1/25/20,1/26/20,1/27/20,1/28/20,1/29/20,1/30/20,1/31/20,2/1/20,2/2/20,2/3/20,2/4/20,2/5/20,2/6/20,2/7/20,2/8/20,2/9/20,2/10/20,2/11/20,2/12/20,2/13/20,2/14/20,2/15/20,2/16/20,2/17/20,2/18/20,2/19/20,2/20/20,2/21/20,2/22/20,2/23/20,2/24/20,2/25/20,2/26/20,2/27/20,2/28/20,2/29/20,3/1/20,3/2/20,3/3/20,3/4/20,3/5/20,3/6/20,3/7/20,3/8/20,3/9/20,3/10/20,3/11/20,3/12/20,3/13/20,3/14/20,3/15/20,3/16/20,3/17/20,3/18/20,3/19/20,3/20/20,3/21/20,3/22/20,3/23/20,3/24/20,3/25/20,3/26/20,3/27/20,3/28/20,3/29/20,3/30/20,3/31/20,4/1/20,4/2/20,4/3/20,4/4/20,4/5/20,4/6/20,4/7/20,4/8/20,4/9/20,4/10/20,4/11/20,4/12/20,4/13/20,4/14/20,4/15/20,4/16/20,4/17/20,4/18/20,4/19/20,4/20/20,4/21/20,4/22/20,4/23/20,4/24/20,4/25/20,4/26/20,4/27/20,4/28/20,4/29/20,4/30/20,5/1/20,5/2/20,5/3/20");

	}
	
	
	//another constructor about for deal with csv data
	Analyzer(File path){
		
	
        Scanner inputStream = null;
        
        try{
            inputStream = new Scanner(path);
            
        }catch(FileNotFoundException e){
        	//TODO 
            System.out.println("[Error] File not found");
            System.exit(0);
        }
        
		boolean flag = false;
		DataNode tmp = null;
        while(inputStream.hasNextLine()){
            String oneCountry = inputStream.nextLine();
            //System.out.println(oneCountry);
            if(flag){
				tmp = new DataNode(oneCountry);
				datas.add(tmp);
			}
            flag = true;
        }
        inputStream.close();
        //*************************

		//setting the dates
		DataNode.setDates("1/22/20,1/23/20,1/24/20,1/25/20,1/26/20,1/27/20,1/28/20,1/29/20,1/30/20,1/31/20,2/1/20,2/2/20,2/3/20,2/4/20,2/5/20,2/6/20,2/7/20,2/8/20,2/9/20,2/10/20,2/11/20,2/12/20,2/13/20,2/14/20,2/15/20,2/16/20,2/17/20,2/18/20,2/19/20,2/20/20,2/21/20,2/22/20,2/23/20,2/24/20,2/25/20,2/26/20,2/27/20,2/28/20,2/29/20,3/1/20,3/2/20,3/3/20,3/4/20,3/5/20,3/6/20,3/7/20,3/8/20,3/9/20,3/10/20,3/11/20,3/12/20,3/13/20,3/14/20,3/15/20,3/16/20,3/17/20,3/18/20,3/19/20,3/20/20,3/21/20,3/22/20,3/23/20,3/24/20,3/25/20,3/26/20,3/27/20,3/28/20,3/29/20,3/30/20,3/31/20,4/1/20,4/2/20,4/3/20,4/4/20,4/5/20,4/6/20,4/7/20,4/8/20,4/9/20,4/10/20,4/11/20,4/12/20,4/13/20,4/14/20,4/15/20,4/16/20,4/17/20,4/18/20,4/19/20,4/20/20,4/21/20,4/22/20,4/23/20,4/24/20,4/25/20,4/26/20,4/27/20,4/28/20,4/29/20,4/30/20,5/1/20,5/2/20,5/3/20");

		

		
	}

		
	
	/**sort the country list as input option.
	 * (1:alphabet order(case sensitive)
	 * (2:number of confirmed patients order)
	 * */
	void sortCountryList(int x){
		switch (x){
			case 1: //alphabet order(case sensitive)
				//Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
				CountriesOfAnalyer = sortMapByKey(CountriesOfAnalyer);
				break;
			case 2: //sorting as number of confirmed patients.
				//sortAsInteger();
				CountriesOfAnalyer = sortMapByKey(CountriesOfAnalyer);
				CountriesOfAnalyer = sortMapByValue(CountriesOfAnalyer);
				break;
			default:
		}
	}
	
	private static LinkedHashMap<String, Integer> sortMapByKey(Map<String, Integer> a) {
		//System.out.println("[Sorted as Key]");
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(a.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

	private static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> a) {
    	//System.out.println("[Sorted as Value]");
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(a.entrySet());
        Collections.sort(entries, (o2, o1) -> o1.getValue().compareTo(o2.getValue()));

        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
	
	/**get the Country name of input index*/
	public String getNames(int i){
		return datas.get(i).getCountryName(i);
	}
	
	
	/**count the number of countries*/
	public int getNumberOfCountries(){
		return CountriesOfAnalyer.size();
	}
	
	/**count the number of all patients*/
	public int getNumberOfAllPatients() {
		int sum = 0;
		for(int i = 0; i < datas.size(); i++) sum += datas.get(i).getNumOfPatients("final");
		return sum;
	}
	
	/**count the number of patients of specific country*/
	public int getNumberOfPatientsOfACountry(String that) {
		int sum = 0;
		for(int i = 0; i < datas.size(); i++) {
			if(datas.get(i).isThatCountry(that)) {
				sum += datas.get(i).getNumOfPatients("final");
			}
		}
		return sum;
	}
	
	/**count the number of patients from a specified date*/
	public int getNumberOfPatientsFromASpecifiedDate(String date) {
		int sum = 0;
		for(int i = 0; i < datas.size(); i++) {
			sum += (datas.get(i).calcIncreasedPatients(datas.get(i).getIndexOfDate(date)-1, datas.get(i).getIndexOfDate("final")));
			//+datas.get(i).calcIncreasedPatients(date, "one")
		}
		return sum;
	}
	
	/**count the number of patients before a specified date*/
	public int getNumberOfPatientsBeforeASpecifiedDate(String date) {
		int sum = 0;
		for(int i = 0; i < datas.size(); i++) {
			sum += datas.get(i).getNumOfPatients(datas.get(i).getIndexOfDate(date)-1);
		}
		return sum;
	}

	/**count the number of patients between two date*/
	public int getNumberOfPatientsBetweenTwoDates(String first, String second) {
		int sum = 0;
		
		for(int i = 1; i < datas.size(); i++) {
			sum += (datas.get(i).calcIncreasedPatients(first, second)+datas.get(i).calcIncreasedPatients(first, "one"));
		}
		return sum;
	}


}