package test;

import java.io.File;
import java.util.ArrayList;

import core.Result;
import core.Result.Resolution;

public class Generator {

	public static ArrayList<Result> generateTestResults(int level) {
		
		Resolution[] resolutions = Resolution.values();
		ArrayList<Result> results = new ArrayList<Result>();
		for(int i=0; i<level; i++){
			Result result = new Result("Result-"+i);
			for(int j=0; j<i; j++){
				result.messages.add("Message-"+j);
			}
			result.resolution = resolutions[level % resolutions.length];
			for(int j=0; j<i; j++){
				result.values.put("Key-"+j, "Value-"+j);
			}
			result.results = generateTestResults(level-1);
			results.add(result);
		}
		return results;
	}
	
	public static int generateRubyFeedback(String folderPath) throws Exception {
		
		String commandLine = "ruby "+folderPath+File.separator+"generate.rb";
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", commandLine);
		processBuilder.directory(new File(folderPath));
		Process process = processBuilder.start();
		return process.waitFor();
	}
}
