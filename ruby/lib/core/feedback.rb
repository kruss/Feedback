
require "rake"
require "core/result"
require "util/tools"
  
$VERSION = "0.1.0"
$OUTPUT_FILE = "result.xml"

class Feedback

	def initialize()
    @results = Array.new
	end
	
  attr_accessor :results
  
  def serialize(path)
    Tools.writeFile(path, toXml(0))
  end
  
  def toXml(level)
    
     version = Tools.createTag("version", $VERSION, false, level+1)
     
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