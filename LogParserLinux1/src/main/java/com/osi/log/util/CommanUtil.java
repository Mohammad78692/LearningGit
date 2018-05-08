package com.osi.log.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
public class CommanUtil {
	
	
	@Autowired
	LogServiceMillsecLinux3 logservice;
	
	
	String listOfFiles="";
	String nearestTimes[]=new String[2];
	Session session;
	Channel channel;
	
	Configuration configuration;
	
	public void getFileOfDir(Configuration configuration) throws Exception{
		 
		 String dire=configuration.getLocation();
 		String command1="ls -t "+dire;
		 ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String currentLine;
	      
	        while((currentLine=br.readLine())!=null){
	        	//System.out.println(currentLine);
	        	listOfFiles=dire+currentLine+"  "+listOfFiles;
	        }
	        channel.disconnect();
	        session.disconnect();
		System.out.println(listOfFiles);
	}
	
	public String[] getTimes(LogRead logRead) throws Exception{
		connectToLinux(configuration);
		String command1="awk '$0>=from&&$0<=to' from=\""+logRead.getStartTime()+"\" to=\""+logRead.getEndTime()+"\" "+listOfFiles;
		 ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String currentLine;
	        	   
	      System.out.println(command1);
	      int i=0;
	      String startTimeNearestLine="";
	      String endTimeNearest="";
	        while((currentLine=br.readLine())!=null){
	        	i++;
	        	if(i==1){
	        		startTimeNearestLine=currentLine;
	        		continue;
	        	}
	        		
	        	//System.out.println(currentLine);
	        	endTimeNearest=currentLine;
				/*allFiles.append(dire+currentLine+"  ");*/
				//System.out.println(lineNumberReader.getLineNumber());
				//break;
			
	        }
	        System.out.println("starting point = " +startTimeNearestLine+" and ending point = " +endTimeNearest);
	       // System.out.println(listOfFiles);
	         nearestTimes[0]=startTimeNearestLine;
	         nearestTimes[1]=endTimeNearest;
	         channel.disconnect();
		        session.disconnect();
		return null;
		
	}
	
	public Map<String, Object> getLogsByTime() throws Exception{
		connectToLinux(configuration);
		String workingDir = System.getProperty("user.dir");
    	File file = new File(workingDir + "test.log");
		FileWriter fileWriter = new FileWriter(file);
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
	        }
	       
	       
	        fileWriter.flush();
			fileWriter.close();
	        System.out.println("DONE");
	        channel.disconnect();
	        session.disconnect();
	        String contents = new String(Files.readAllBytes(Paths.get(workingDir + "test.log"))); 

			map.put("WithTimeStampLogs", contents);
			 if (map == null)
					map.put("Exception", "Something went wrong while getting logs by given timestamp");

				return map;
	}
	
	public Channel connectToLinux(Configuration configuration){
		this.configuration=configuration;
		    String host=configuration.getHostname();
		    String user=configuration.getUsername();
		    String password=configuration.getPassword();
		  

		    try{
		    	
		    	java.util.Properties config = new java.util.Properties(); 
		    	config.put("StrictHostKeyChecking", "no");
		    	JSch jsch = new JSch();
		        session=jsch.getSession(user, host, 22);
		    	session.setPassword(password);
		    	session.setConfig(config);
		    	session.connect();
		    	System.out.println("Connected");
				
		    	channel=session.openChannel("exec");
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
			return channel;
}
}
