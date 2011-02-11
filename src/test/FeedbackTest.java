package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import util.Tools;
import core.Feedback;
import core.Result;
import core.Result.Resolution;

public class FeedbackTest {

	@Test public void testJava2JavaFeedback() {

		print("=== JAVA ===");
		String resultPath = getWorkingDir()+File.separator+Feedback.OUTPUT_FILE;
		try{
			Feedback f1 = new Feedback();
			f1.results = createTestResults(3);
			f1.serialize(resultPath);
			String content1 = Tools.readFile(resultPath);
			print(content1);
			
			Feedback f2 = new Feedback();
			f2.deserialize(resultPath);
			f2.serialize(resultPath);
			String content2 = Tools.readFile(resultPath);
			assertEquals(content1.hashCode(), content2.hashCode());
			
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
			File file = new File(resultPath);
			if(file.isFile()){
				file.delete();
			}
		}
	}

	private static ArrayList<Result> createTestResults(int level) {
		
		Resolution[] stati = Resolution.values();
		ArrayList<Result> results = new ArrayList<Result>();
		for(int i=0; i<level; i++){
			Result result = new Result("Result-"+i);
			for(int j=0; j<i; j++){
				result.messages.add("Message-"+j);
			}
			result.resolution = stati[level % stati.length];
			for(int j=0; j<i; j++){
				result.values.put("Key-"+j, "Value-"+j);
			}
			result.results = createTestResults(level-1);
			results.add(result);
		}
		return results;
	}
	
	@Test public void testRuby2JavaFeedback() {
		
		print("=== RUBY ===");
		String resultFolder = getWorkingDir()+File.separator+"ruby"+File.separator+"lib"+File.separator+"test";
		String resultPath = resultFolder+File.separator+Feedback.OUTPUT_FILE;
		try{

			// generate test-results
			String commandLine = "ruby "+resultFolder+File.separator+"generate.rb";
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", commandLine);
			processBuilder.directory(new File(resultFolder));
			Process process = processBuilder.start();
			process.waitFor();
			String content1 = Tools.readFile(resultPath);
			
			Feedback f = new Feedback();
			f.deserialize(resultPath);
			f.serialize(resultPath);
			String content2 = Tools.readFile(resultPath);
			assertEquals(content1.hashCode(), content2.hashCode());
			
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
			File file = new File(resultPath);
			if(file.isFile()){
				file.delete();
			}
		}
	}
	
	private String getWorkingDir(){
		return System.getProperty("user.dir");
	}
	
	private void print(String text) {
		System.out.println(text);
	}
}
