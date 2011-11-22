$:.unshift File.join(File.dirname(__FILE__), "..", "lib")

require "feedback/feedback"

class Generator
  
  def Generator.generateResults(max)
    return generateTestResults(1, max);
  end
  
private

  def Generator.generateTestResults(level, max)
    
    results = Array.new
    for i in 1..level do
      result = Result.new("Result-#{i}");
      for j in 1..level do
        result.values << "Message-#{j}"
      end     
      for j in 1..level do
        result.properties["Key-#{j}"] = "Value-#{j}"
      end
      result.status = Result.STATUS[level % Result.STATUS.length];
      if level < max then
        result.results = Generator.generateTestResults(level+1, max);
      end
      results << result;
    end
    return results;
  end
  
end

if ARGV.length == 1 then
  feedback = Feedback.new()
  feedback.results = Generator.generateResults(ARGV[0].to_i);
  feedback.serialize(Feedback.OUTPUT_FILE)
else
  raise "invalid args"
end