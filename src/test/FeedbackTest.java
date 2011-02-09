package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import util.Tools;
import core.Feedback;
import core.Result;
import core.Result.Status;

public class FeedbackTest {

	@Test public void testFeedback() {

		File file = new File(Tools.getWorkingDir()+File.separator+Feedback.OUTPUT_FILE);
		try{
		
			Feedback f1 = new Feedback();
			f1.results = createTestResults(3);
			f1.serialize(file.getAbsolutePath());
			
			String content = Tools.readFile(file.getAbsolutePath());
			System.out.println(content);
			
			Feedback f2 = new Feedback();
			f2.deserialize(file.getAbsolutePath());
			
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
			if(file.isFile()){
				file.delete();
			}
		}
	}

	private static ArrayList<Result> createTestResults(int count) {
		
		Status[] stati = Status.values();
		ArrayList<Result> results = new ArrayList<Result>();
		for(int i=0; i<count; i++){
			Result result = new Result("Comp-"+i, "message-"+1);
			result.status = stati[count < stati.length ? count : 0];
			for(int j=0; j<i; j++){
				result.properties.put("Prop-"+j, "Value-"+j);
			}
			result.results = createTestResults(count-1);
			results.add(result);
		}
		return results;
	}
}
