package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {

	public enum Resolution {
		UNDEFINED, WARNING, SUCCEED, ERROR
	}
	
	public String name;
	public Resolution resolution;	
	public ArrayList<String> messages;
	public HashMap<String, String> values;
	public ArrayList<Result> results;
	
	public Result(String name){
		
		this.name = name;
		resolution = Resolution.UNDEFINED;
		messages = new ArrayList<String>();
		values = new HashMap<String, String>();
		results = new ArrayList<Result>();
	}
}
