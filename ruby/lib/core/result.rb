
class Result

  @@Resolution = [ :UNDEFINED, :WARNING, :SUCCEED, :ERROR ]
  
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

end