package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Tools {
	
	public static String getWorkingDir(){
		return System.getProperty("user.dir");
	}
	
	public static void writeFile(String path, String string) throws Exception { 

		int i=0; int c=0;
		FileWriter fileWriter = new FileWriter(path, false);
		while(i<string.length()){ c=string.charAt(i++); fileWriter.write(c); }
		fileWriter.close();
	}
	
	public static String readFile(String path) throws Exception {

		File file = new File(path);
		StringBuffer sb = new StringBuffer((int)file.length());
		String line;
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		while((line = bufferedReader.readLine()) != null){ sb.append(line+"\n"); }
		bufferedReader.close();
		
		return sb.toString();
	}
}
