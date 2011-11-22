package test;

import java.io.File;
import java.util.ArrayList;

import core.Result;
import core.Result.Status;

public class Generator {

	public static ArrayList<Result> generateResults(int max) {
		return generateTestResults(1, max);
	}
	
	private static ArrayList<Result> generateTestResults(int level, int max) {
		
		ArrayList<Result> results = new ArrayList<Result>();
		for(int i=1; i<=level; i++){
			Result result = new Result("Result-"+i);
			for(int j=1; j<=level; j++){
				result.values.add("Message-"+j);
			}
			for(int j=1; j<=level; j++){
				result.properties.put("Key-"+j, "Value-"+j);
			}
			result.status = Status.values()[level % Status.values().length];
			if(level < max){
				result.results = generateTestResults(level+1, max);
			}
			results.add(result);
		}
		return results;
	}
	
	public static int generateRubyFeedback(String generator, int max) throws Exception {
		
		String command = "ruby "+generator+" "+max;
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C", command);
		builder.directory(new File(generator).getParentFile());
		Process process = builder.start();
		return process.waitFor();
	}
}
