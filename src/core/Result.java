package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {

	public enum Status {
		UNDEFINED, WARNING, ERROR, SUCCEED
	}
	
	public Status status;	
	public String component;
	public String message;
	
	public HashMap<String, String> properties;
	public ArrayList<Result> results;
	
	public Result(String component, String message){
		
		status = Status.UNDEFINED;
		
		this.component = component;
		this.message = message;
		
		properties = new HashMap<String, String>();
		results = new ArrayList<Result>();
	}
	
	public int getHashCode(){
		return (status.toString()+component+message).hashCode();
	}
}
