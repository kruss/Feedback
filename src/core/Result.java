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
	
	public int hashCode(){
		int hash = name.hashCode();
		hash += resolution.toString().hashCode();
		for(String message : messages){
			hash += message.hashCode();
		}
		for(String key : values.keySet()){
			hash += key.hashCode();
			String value = values.get(key);
			if(value != null){
				hash += value.hashCode();
			}
		}
		for(Result result : results){
			hash += result.hashCode();
		}
		return hash;
	}
}
