package com.osi.log.resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSchException;
import com.osi.log.model.Configuration;
import com.osi.log.model.LogRead;
import com.osi.log.services.LogServiceMillsecLinux2;
import com.osi.log.services.LogServiceMillsecLinux3;
import com.osi.log.util.AwkNearestMatchOfStartAndEndTime;
import com.osi.log.util.LinuxListOfFiles;
import com.osi.log.util.sedCommanGetAllLogs;



@RestController
@CrossOrigin(origins = "http://192.168.60.76:4200", allowedHeaders = "*")
public class LogController {

	/*@Autowired
	LogService logservice;*/
	
	Configuration configuration;
	
	@Autowired
	LogServiceMillsecLinux3 logservice;
	
	@Autowired
	LinuxListOfFiles linuxListOfFiles;
	
	@Autowired
	AwkNearestMatchOfStartAndEndTime awk;
	
	@Autowired
	sedCommanGetAllLogs sed;
	
	Map<String, Object> map=new LinkedHashMap<String, Object>();
	
	@PostMapping(value = "/logs")
	public Map<String, Object> getLogsFromLinux(@RequestBody LogRead logRead) {
		BufferedReader br=null;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		configuration=(Configuration) this.map.get(logRead.getkey());

		try {
			
			
			logRead=logservice.dateFormatter(configuration,logRead);
			
			br = logservice.getLinuxConnection(configuration);
			 
			/* String listOfFiles = linuxListOfFiles.getListOfFiles(configuration);
			 String[] nearestTimes = awk.getNearestTimes(configuration,listOfFiles,logRead);
			 sed.getLogsByTime(configuration,listOfFiles,nearestTimes,logRead);*/
			 //E:/Restassured/VenkatSomala/New folder/RestAssuredFramework/TestCases/src/test/resources/returns.log
			 //F:\\QA\\jboss_order\\serviceability\\logs\\DataNode\\trace.log
		  // br = new BufferedReader(new FileReader("F:\\QA\\jboss_order\\serviceability\\logs\\DataNode\\trace.log"));
		} catch (NullPointerException e) {
			map.put("Exception", "Something went wrong");
			e.printStackTrace();
		}
         catch(JSchException e){
 			map.put("Exception", "something went wrong while making connection to Linux machine" );

		}
		catch (Exception e) {
			map.put("Exception", "Something went wrong");
			e.printStackTrace();
		}
		try{
			 
		if(logRead.getStartTime() != null&logRead.getLogType()==null&logRead.getCookie()==null) {
			// map = logservice.getLogByTimeStamp(br,logRead);
			 String listOfFiles = linuxListOfFiles.getListOfFiles(configuration);
			 System.out.println("this is start time 1 "+Calendar.getInstance().getTime());
			 String[] nearestTimes = awk.getNearestTimes(configuration,listOfFiles,logRead);
			 System.out.println("this is start time 2 "+Calendar.getInstance().getTime());
			 map = sed.getLogsByTime(configuration,listOfFiles,nearestTimes,logRead);
			 System.out.println("this is start time 3 "+Calendar.getInstance().getTime());
			 System.out.println(" from logRead.getStartTime() != null&logRead.getLogType()==null&logRead.getCookie()==null");
		
		}
		else if(logRead.getCookie()!=null&logRead.getLogType()!=null){
			//map = logservice.getLogByTimeStamp(br,logRead);
			 String listOfFiles = linuxListOfFiles.getListOfFiles(configuration);
			 String[] nearestTimes = awk.getNearestTimes(configuration,listOfFiles,logRead);
			 map = sed.getLogsByTime(configuration,listOfFiles,nearestTimes,logRead);
		map=logservice.getLogByCookies(logRead);
		map=logservice.getLogByType(logRead);
		System.out.println("from logRead.getCookie()!=null&logRead.getLogType()!=null");
		}
		else if((logRead.getCookie()!=null)&logRead.getLogType()==null){
			// map = logservice.getLogByTimeStamp(br,logRead);
			 String listOfFiles = linuxListOfFiles.getListOfFiles(configuration);
			 String[] nearestTimes = awk.getNearestTimes(configuration,listOfFiles,logRead);
			 map = sed.getLogsByTime(configuration,listOfFiles,nearestTimes,logRead);
			map=logservice.getLogByCookies(logRead);
			System.out.println("from logRead.getCookie()!=null)&logRead.getLogType()==null");
		}
		else if((logRead.getCookie()==null)&logRead.getLogType()!=null){
			// map = logservice.getLogByTimeStamp(br,logRead);
			 String listOfFiles = linuxListOfFiles.getListOfFiles(configuration);
			 String[] nearestTimes = awk.getNearestTimes(configuration,listOfFiles,logRead);
			 map = sed.getLogsByTime(configuration,listOfFiles,nearestTimes,logRead);
			map=logservice.getLogByType(logRead);
			System.out.println("from logRead.getCookie()==null)&logRead.getLogType()!=null");
		}
		
		else {
			map.put("Exception", "something went wrong while making connection to Linux machine" );
		}
		}catch(Exception e){
			map.put("Exception", "Something went wrong");
			e.printStackTrace();
		}

 

		return map;
	}
	@PostMapping(value = "/confguration")
	public Object getConfiguration(@RequestBody Configuration configuration){
		
		try{
			map.put(configuration.getHostname(), configuration);
		this.configuration=configuration;
		Set<String> keySet = map.keySet();
		return keySet;
		}catch(Exception e){
			e.printStackTrace();
			map.put("Exception", "Something went wrong");
		}
		return map;
	}
	@PostMapping(value="/QueryParam")
	public  Map<String, Object>  getMessageQueryParam(@QueryParam("startTime") String startTime,
	        @QueryParam("endTime") String endTime,
	        @QueryParam("cookie") String cookie,
	        @QueryParam("logType") String logType,
	        @QueryParam("key") String key){
		LogRead logRead=new LogRead();
		logRead.setStartTime(startTime);
		logRead.setEndTime(endTime);
		logRead.setCookie(cookie);
		logRead.setLogType(logType);
		logRead.setkey(key);
		System.out.println(logRead);
		
		Map<String, Object> logsFromLinux = getLogsFromLinux(logRead);
		System.out.println(logsFromLinux);
		return logsFromLinux;
	 //System.out.println(param1);
	    // Store the message
	 
	}
	@ExceptionHandler(value = JSchException.class)
	public Object handelConnection(HttpServletRequest request, Exception ex) {
		System.out.println("in exception");
		return "errorPage " + request.getRequestURL() + "Something went wrong" + ex.getMessage();
	}
	@ExceptionHandler(value = NullPointerException.class)
	public Object handdelNullPointer(HttpServletRequest request, Exception ex) {
		System.out.println("in exception");
		return "errorPage " + request.getRequestURL() + "Something went wrong" + ex.getMessage();
	}
}
