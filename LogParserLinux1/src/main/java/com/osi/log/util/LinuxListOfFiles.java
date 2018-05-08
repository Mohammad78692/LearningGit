package com.osi.log.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.osi.log.model.Configuration;


@Service
public class LinuxListOfFiles {
	/**
	 * JSch Example Tutorial
	 * Java SSH Connection Program
	 * @param configuration 
	 */
	public String  getListOfFiles(Configuration configuration) {
	    String host=configuration.getHostname();
	    String user=configuration.getUsername();
	    String password=configuration.getPassword();
	    String dire=configuration.getLocation();
	    String reverse="";
	    	
	    		String command1="ls -t "+dire;

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
	      
	        while((currentLine=br.readLine())!=null){
	        	//System.out.println(currentLine);
	        	reverse=dire+currentLine+"  "+reverse;
	        }
	        //System.out.println(reverse);
	        channel.disconnect();
	        session.disconnect();
	        System.out.println("DONE");
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return reverse;

	}

}