package edu.handong.csee.java.hw5;

import java.io.File;

public class ThreadForAnalyze implements Runnable{

	MainRunner myMain = new MainRunner();
	String t;
	String path;
	
	static boolean numsort = false;

	public ThreadForAnalyze() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		System.out.println("Strat analyzing....................");
		myMain.type = t;
		myMain.analyzer = new Analyzer(new File(path));
	
		if(numsort) myMain.analyzer.sortCountryList(2);
		else myMain.analyzer.sortCountryList(1);
			
		System.out.println("......................finished analyzing!!");
		
		
		
		
	}
	
	public void printInfo() {
		myMain.runrun();
		
	}
	
	//Constructor
	public void setData(String t, String path) {
		this.t = t;
		this.path= path;
	}



}
