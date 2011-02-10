package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {

	public enum Resolution {
		UNDEFINED, WARNING, ERROR, SUCCEED
	}
	
	public String name;
	public String message;
	public Resolution resolution;	
	public HashMap<String, String> values;
	public ArrayList<Result> results;
	
	public Result(String name, String message){
		
		this.name = name;
		this.message = message;
		
		resolution = Resolution.UNDEFINED;
		values = new HashMap<String, String>();
		results = new ArrayList<Result>();
	}
}
