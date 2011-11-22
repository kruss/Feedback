package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {

	public enum Status {
		UNDEFINED, SUCCEED, WARNING, ERROR
	}
	
	public String name;
	public ArrayList<String> values;
	public HashMap<String, String> properties;
	public Status status;	
	public ArrayList<Result> results;
	
	public Result(String name){
		
		this.name = name;
		values = new ArrayList<String>();
		properties = new HashMap<String, String>();
		status = Status.UNDEFINED;
		results = new ArrayList<Result>();
	}
	
	public int hashCode(){
		int hash = name.hashCode();
		for(String value : values){
			hash += value.hashCode();
		}
		for(String key : properties.keySet()){
			hash += key.hashCode();
			String value = properties.get(key);
			if(value != null){
				hash += value.hashCode();
			}
		}
		hash += status.toString().hashCode();
		for(Result result : results){
			hash += result.hashCode();
		}
		return hash;
	}
}
