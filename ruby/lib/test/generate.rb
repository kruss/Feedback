$:.unshift File.join(File.dirname(__FILE__), "..", "..", "lib")

require "core/feedback"

feedback = Feedback.new()

path = $OUTPUT_FILE #Feedback.OutputFile
feedback.serialize(path)