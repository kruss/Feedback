package core;

import java.util.ArrayList;

import util.Tools;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import core.Result.Status;

public class Feedback {

	public static final String VERSION = "0.2.0";
	public static final String OUTPUT_FILE = "result.xml";
	
	public String version;
	public ArrayList<Result> results;
	
	private transient XStream xstream;
	
	public Feedback(){
		initialize(
				VERSION, 
				new ArrayList<Result>()
		);
	}
	
	private void initialize(
			String version, 
			ArrayList<Result> results
	){	
		this.version = version;
		this.results = results;
		
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
		initialize(
				feedback.version, 
				feedback.results
		);
	}
	
	public int hashCode(){
		int hash = version.hashCode();
		for(Result result : results){
			hash += result.hashCode();
		}
		return hash;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		for(Result result : results){
			string.append(result2String(result, ""));
		}
		return string.toString();
	}

	private String result2String(Result result, String intent) {
		StringBuilder string = new StringBuilder();
		string.append(intent+"+ "+result.name+(result.status != Status.UNDEFINED ? " ("+result.status+")" : "")+"\n");
		for(String value : result.values){
			string.append(intent+"  |- "+value+"\n");
		}
		for(String key : result.properties.keySet()){
			String value = result.properties.get(key);
			if(value != null){
				string.append(intent+"  |~ "+key+": "+value+"\n");
			}
		}
		for(Result child : result.results){
			string.append(result2String(child, intent+"  "));
		}
		return string.toString();
	}
}
