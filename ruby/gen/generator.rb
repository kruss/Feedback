$:.unshift File.join(File.dirname(__FILE__), "..", "lib")

require "feedback"

module Feedback

class Generator
  def Generator.createTestResults(level)
    
    results = Array.new
    for i in 0..level-1 do
  
      result = Result.new("Result-"+i.to_s);
      for j in 0..i-1 do
        result.messages << "Message-"+j.to_s;
      end
      result.resolution = Result.RESOLUTION[level % Result.RESOLUTION.length];
      for j in 0..i-1 do
        result.values["Key-"+j.to_s] = "Value-"+j.to_s
      end
      result.results = createTestResults(level-1);
      results << result;
    end
    return results;
  end
end

end

feedback = Feedback::Feedback.new()
feedback.results = Feedback::Generator.createTestResults(3);
feedback.serialize(Feedback::Feedback.OUTPUT_FILE)
