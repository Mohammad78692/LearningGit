package com.osi.log.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.osi.log.model.Configuration;
import com.osi.log.model.LogRead;

@Service
public class AwkNearestMatchOfStartAndEndTime {

	/**
	 * JSch Example Tutorial
	 * Java SSH Connection Program
	 * @param listOfFiles 
	 * @param configuration 
	 * @param logRead 
	 */
	public  String[] getNearestTimes(Configuration configuration, String listOfFiles, LogRead logRead) {
	    String host=configuration.getHostname();
	    String user=configuration.getUsername();
	    String password=configuration.getPassword();
	  
	    	//String allfiles="/home/db2inst1/logs/returns.log  /home/db2inst1/logs/tracestart.log  /home/db2inst1/logs/traceend.log";
	    String startTime=logRead.getStartTime();
		String endTime=logRead.getEndTime();
		String command1="awk '$0>=from&&$0<=to' from=\""+startTime+"\" to=\""+endTime+"\" "+listOfFiles;
	//	System.out.println(command1);
		 String nearestTimes[]=new String[2];

	    try{
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
			
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String currentLine;
	        	   
	      
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
	        System.out.println("DONE");
	       
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return nearestTimes;

	}

}