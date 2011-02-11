
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
  
end