
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
    
    xml = "test..."
    Tools.writeFile(path, xml)
  end

end