module Feedback
  
class Result

  @@Resolution = [ :UNDEFINED, :WARNING, :SUCCEED, :ERROR ]
  
  def self.RESOLUTION   
    return @@Resolution   
  end 
  
  def initialize(name)
    @name = name
    @resolution = :UNDEFINED
    @messages = Array.new
    @values = {}
    @results = Array.new
  end
  
  attr_accessor :name
  attr_accessor :resolution
  attr_accessor :messages
  attr_accessor :values
  attr_accessor :results

  def toXml(level)
    
    name = Tools.createTag("name", @name, false, level+1)
    resolution = Tools.createTag("resolution", @resolution.to_s, false, level+1)
    
    messages = ""
    @messages.each do |message|
      messages += Tools.createTag("string", message, false, level+2)
    end
    messages = Tools.createTag("messages", messages, true, level+1)
    
    values = ""
    @values.each do|key,value|
      key = Tools.createTag("string", key, false, level+3)
      value = Tools.createTag("string", value, false, level+3)
      values += Tools.createTag("entry", key+value, true, level+2)
    end
    values = Tools.createTag("values", values, true, level+1)
    
    results = ""
    @results.each do |result|
      results += result.toXml(level+2)
    end
    results = Tools.createTag("results", results, true, level+1)
    
    xml = name+resolution+messages+values+results
    xml = Tools.createTag("Result", xml, true, level+0)
    return xml
  end
end

end