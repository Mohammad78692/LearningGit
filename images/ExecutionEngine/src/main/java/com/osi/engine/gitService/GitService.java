package com.osi.engine.gitService;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

@Service
public class GitService {
	
	Map<String , Object> map=new LinkedHashMap<String , Object>();
	
	public Map<String , Object> gitClone(String REMOTE_URL){
		map.clear();
		String workingDir = System.getProperty("user.dir");
		Random rand = new Random();
		 
        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000);
		File a = new File(workingDir + rand_int1+"test");
        if(!a.delete()) {
        	try {
				FileUtils.deleteDirectory(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				map.put("Exception", e.getMessage());
				e.printStackTrace();
			}
        }
        try (Git result = Git.cloneRepository()
                .setURI(REMOTE_URL)
                .setDirectory(a)
                .call()) {
	        System.out.println("Having repository: " + result.getRepository().getDirectory());
	        map.put("SucessResponse", result.getRepository().getDirectory());
	       
	        List<File> listFeatureFiles = listFeatureFiles(a);
	        map.put("fileLocation", listFeatureFiles);
        }catch(Exception e){
        	map.put("Exception", e.getMessage());
        	e.printStackTrace();
        }
		return map;
	}

	public List<File> listFeatureFiles(File a) throws IOException{
		   String[] extensions = new String[] { "feature"};
		   
		   List<File> files = (List<File>) FileUtils.listFiles(a, extensions, true);
		   System.out.println(files);
		   for (File file : files) {
		    System.out.println("file: " + file.getCanonicalPath());
		    
	}
		return files;
	}
}
