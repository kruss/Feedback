require "rake"
require "feedback/result"
require "feedback/util/tools"
  
class Feedback

  @@Version = "0.2.0"
  @@OutputFile = "result.xml"
	
  def self.VERSION   
    return @@Version   
  end 
  
  def self.OUTPUT_FILE   
    return @@OutputFile   
  end 
  
	def initialize()
    @results = Array.new
	end
  
  attr_accessor :results
  
  def serialize(path)
    Tools.writeFile(path, toXml(0))
  end
  
  def toXml(level)
     version_tag = Tools.createTag("version", @@Version, false, level+1)
     
     results_tag = ""
     @results.each do |result|
       results_tag += result.toXml(level+2)
     end
     results_tag = Tools.createTag("results", results_tag, true, level+1)
     
     xml = version_tag+results_tag
     xml = Tools.createTag("Feedback", xml, true, level+0)
     return xml
  end

end
