module Feedback

class Tools
  
  def Tools.writeFile(filePath, content)
    file = File.new(filePath, "w")
    if file
      file.syswrite(content)
      file.close
    else
       raise "unable to write: " + filePath
    end
  end
  
  def Tools.createTag(name, content, newline, level)
    intend = ""
    for i in 0..level-1 do
      intend += "  "
    end
    if content.length == 0 then
      return intend+"<#{name}/>\n"
    else
      if newline then
        if !(content =~ /\n$/) then
          content += "\n"
        end
        return intend+"<#{name}>\n#{content}"+intend+"</#{name}>\n"
      else
        return intend+"<#{name}>#{content}</#{name}>\n"
      end
    end
  end
  
end

end