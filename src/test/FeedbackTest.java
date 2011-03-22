package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import core.Feedback;

public class FeedbackTest {

	private static final int PROCESS_OK = 0;

	@Test public void testJava2JavaFeedback() {

		String resultPath = getWorkingDir()+File.separator+Feedback.OUTPUT_FILE;
		try{
			Feedback feedback1 = new Feedback();
			feedback1.results = Generator.generateTestResults(3);
			feedback1.serialize(resultPath);
			Feedback feedback2 = new Feedback();
			feedback2.deserialize(resultPath);
			assertEquals(feedback1.hashCode(), feedback2.hashCode());
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
	
	@Test public void testRuby2JavaFeedback() {
		
		String generatorFolder = getWorkingDir()+File.separator+"ruby"+File.separator+"lib"+File.separator+"test";
		String resultPath = generatorFolder+File.separator+Feedback.OUTPUT_FILE;
		try{
			assertEquals(PROCESS_OK, Generator.generateRubyFeedback(generatorFolder));
			Feedback feedback1 = new Feedback();
			feedback1.results = Generator.generateTestResults(3);
			Feedback feedback2 = new Feedback();
			feedback2.deserialize(resultPath);
			assertEquals(feedback1.hashCode(), feedback2.hashCode());
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
}
