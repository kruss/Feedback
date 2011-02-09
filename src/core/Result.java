package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Result {

	public enum Status {
		UNDEFINED, WARNING, ERROR, SUCCEED
	}
	
	public String name;
	public String message;
	public Status status;	
	public HashMap<String, String> properties;
	public ArrayList<Result> results;
	
	public Result(String name, String message){
		
		this.name = name;
		this.message = message;
		
		status = Status.UNDEFINED;
		properties = new HashMap<String, String>();
		results = new ArrayList<Result>();
	}
	
	public int getHashCode(){
		return (name+message+status.toString()).hashCode();
	}
}
