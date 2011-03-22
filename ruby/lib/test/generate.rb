$:.unshift File.join(File.dirname(__FILE__), "..", "..", "lib")

require "core/feedback"

def createTestResults(level)
  
  results = Array.new
  for i in 0..level-1 do

    result = Result.new("Result-"+i.to_s);
    for j in 0..i-1 do
      result.messages << "Message-"+j.to_s;
    end
    result.resolution = Result.Resolution[level % Result.Resolution.length].to_s;
    for j in 0..i-1 do
      result.values["Key-"+j.to_s] = "Value-"+j.to_s
    end
    result.results = createTestResults(level-1);
    results << result;
  end
  return results;
end

feedback = Feedback.new()
feedback.results = createTestResults(3);
feedback.serialize($OUTPUT_FILE)