package core;

import java.util.ArrayList;

import util.Tools;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Feedback {

	public static final String VERSION = "0.1.0";
	public static final String OUTPUT_FILE = "result.xml";
	
	public String version;
	public ArrayList<Result> results;
	
	private transient XStream xstream;
	
	public Feedback(){
		
		version = VERSION;
		results = new ArrayList<Result>();
		
		xstream = new XStream(new DomDriver());
		xstream.alias(Feedback.class.getSimpleName(), Feedback.class);
		xstream.alias(Result.class.getSimpleName(), Result.class);
	}
	
	public void serialize(String path) throws Exception {

		String xml = xstream.toXML(this);
		Tools.writeFile(path, xml);
	}
	
	public void deserialize(String path) throws Exception {

		String xml = Tools.readFile(path);
		Feedback feedback = (Feedback)xstream.fromXML(xml);
		version = feedback.version;
		results = feedback.results;
	}
}
