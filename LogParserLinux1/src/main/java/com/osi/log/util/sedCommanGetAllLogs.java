package com.osi.log.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.osi.log.model.Configuration;
import com.osi.log.model.LogRead;
import com.osi.log.services.LogServiceMillsecLinux3;


@Service
public class sedCommanGetAllLogs {

	/**
	 * JSch Example Tutorial
	 * Java SSH Connection Program
	 * @param nearestTimes 
	 * @param listOfFiles 
	 * @param configuration 
	 * @param logRead 
	 * @return 
	 */
	@Autowired
	LogServiceMillsecLinux3 logservice;
	public   Map<String, Object> getLogsByTime(Configuration configuration, String listOfFiles, String[] nearestTimes, LogRead logRead) throws Exception {
	    String host=configuration.getHostname();
	    String user=configuration.getUsername();
	    String password=configuration.getPassword();
	    String string[]=new String[2];
	    Map<String, Object> map = new LinkedHashMap<String, Object>();
	    for(int i=0;i<nearestTimes.length;i++){
			Matcher matcher = Pattern.compile(logservice.regexOFTime).matcher(nearestTimes[i]);
			
			if (matcher.find())
			 {
			    
				nearestTimes[i] = matcher.group(0);
			   
				System.out.println(nearestTimes[i]);
			 }
		}
	    String startTime=nearestTimes[0];
		String endTime=nearestTimes[1];
		String command1="sed -n '/"+startTime+"/,/"+endTime+"/p' "+listOfFiles;
		System.out.println(command1);


	    try{
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
	    	String workingDir = System.getProperty("user.dir");
	    	File file = new File(workingDir + "test.log");
			FileWriter fileWriter = new FileWriter(file);
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String currentLine;
	        	   
	        
	   
	        while((currentLine=br.readLine())!=null){
	        	
	        	fileWriter.write(currentLine);
				fileWriter.write("\r\n");
	        //	System.out.println(currentLine);
	        	
				/*allFiles.append(dire+currentLine+"  ");*/
				//System.out.println(lineNumberReader.getLineNumber());
				//break;
			
	        }
	       
	        channel.disconnect();
	        session.disconnect();
	        fileWriter.flush();
			fileWriter.close();
	        System.out.println("DONE");
	        String contents = new String(Files.readAllBytes(Paths.get(workingDir + "test.log"))); 

			map.put("WithTimeStampLogs", contents);
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    if (map == null)
			map.put("Exception", "Something went wrong while getting logs by given timestamp");

		return map;
	}

}
