require "rake"
require "result"
require "util/tools"

module Feedback
  
class Feedback

  @@Version = "0.1.0"
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
    
     version = Tools.createTag("version", @@Version, false, level+1)
     
     results = ""
     @results.each do |result|
       results += result.toXml(level+2)
     end
     results = Tools.createTag("results", results, true, level+1)
     
     xml = version+results
     xml = Tools.createTag("Feedback", xml, true, level+0)
     return xml
  end

end

end