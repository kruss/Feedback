package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import util.Tools;

import core.Feedback;

public class FeedbackTest {

	private static final int PROCESS_OK = 0;

	@Test public void testJava2JavaFeedback() {
		System.out.println("=== Java2JavaFeedback ===");
		
		String path = getWorkingDir()+File.separator+Feedback.OUTPUT_FILE;
		try{
			Feedback feedback1 = new Feedback();
			feedback1.results = Generator.generateResults(2);
			feedback1.serialize(path);
			System.out.println(feedback1.toString()+"===>\n"+Tools.readFile(path));
			
			Feedback feedback2 = new Feedback();
			feedback2.deserialize(path);
			
			assertEquals(feedback1.hashCode(), feedback2.hashCode());
		}catch(Exception e){
			fail(e.getMessage());
		}finally{
			File file = new File(path);
			if(file.isFile()){
				file.delete();
			}
		}
	}
	
	@Test public void testRuby2JavaFeedback() {
		System.out.println("=== Ruby2JavaFeedback ===");
		
		String folder = getWorkingDir()+File.separator+"ruby"+File.separator+"gen";
		String generator = folder+File.separator+"generator.rb";
		String path = folder+File.separator+Feedback.OUTPUT_FILE;
		try{
			Feedback feedback1 = new Feedback();
			feedback1.results = Generator.generateResults(2);
			
			assertEquals(PROCESS_OK, Generator.generateRubyFeedback(generator, 2));
			Feedback feedback2 = new Feedback();
			feedback2.deserialize(path);
			System.out.println(feedback2.toString()+"===>\n"+Tools.readFile(path));
			
			assertEquals(feedback1.hashCode(), feedback2.hashCode());
		}catch(Exception e){
			fail(e.getMessage());
		}finally{
			File file = new File(path);
			if(file.isFile()){
				file.delete();
			}
		}
	}
	
	private String getWorkingDir(){
		return System.getProperty("user.dir");
	}
}
