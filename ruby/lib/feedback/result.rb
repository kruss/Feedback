
class Result

  @@Status = [ :UNDEFINED, :SUCCEED, :WARNING, :ERROR ]
  
  def self.STATUS_UNDEFINED
    return @@Status[0]   
  end 
  
  def self.STATUS_SUCCEED
    return @@Status[1]   
  end 

  def self.STATUS_WARNING 
    return @@Status[2]   
  end 
  
  def self.STATUS_ERROR  
    return @@Status[3]   
  end 
  
  def self.STATUS   
    return @@Status  
  end 
  
  def initialize(name)
    @name = name
    @values = Array.new
    @properties = {}
    @status = :UNDEFINED
    @results = Array.new
  end
  
  attr_accessor :name
  attr_accessor :values
  attr_accessor :properties
  attr_accessor :status
  attr_accessor :results

  def toXml(level)
    name_tag = Tools.createTag("name", @name, false, level+1)
    
    values_tag = ""    
    @values.each do |value|      
      values_tag += Tools.createTag("string", value, false, level+2)    
    end    
    values_tag = Tools.createTag("values", values_tag, true, level+1)
    
    properties_tag = ""
    @properties.each do|key,value|
      key = Tools.createTag("string", key, false, level+3)
      value = Tools.createTag("string", value, false, level+3)
      properties_tag += Tools.createTag("entry", key+value, true, level+2)
    end
    properties_tag = Tools.createTag("properties", properties_tag, true, level+1)
    
    status_tag = Tools.createTag("status", @status.to_s, false, level+1)

    results_tag = ""
    @results.each do |result|
      results_tag += result.toXml(level+2)
    end
    results_tag = Tools.createTag("results", results_tag, true, level+1)
    
    xml = name_tag+values_tag+properties_tag+status_tag+results_tag
    xml = Tools.createTag("Result", xml, true, level+0)
    return xml
  end
end
