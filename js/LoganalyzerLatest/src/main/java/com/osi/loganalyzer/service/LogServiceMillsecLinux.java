package com.osi.loganalyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.joestelmach.natty.Parser;
import com.osi.loganalyzer.model.Configuration;
import com.osi.loganalyzer.model.LogRead;

@Service
public class LogServiceMillsecLinux {
	
	Configuration configuration;
	String string;
	Session session;
	ChannelSftp sftpChannel;
	
	 long startTime;
	 long endTime;
	
	String workingDir = System.getProperty("user.dir");
	StringBuilder output = new StringBuilder("");

	public BufferedReader getLinuxConnection(Configuration configuration) {
		
		this.configuration=configuration;
		
		String hostname = this.configuration.getHostname();
		String username = this.configuration.getUsername();
		String password = this.configuration.getPassword();
		String copyFrom = this.configuration.getLocation();
		JSch jsch = new JSch();
		BufferedReader br = null;

		try {
			session = jsch.getSession(username, hostname, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			InputStream inputStream = sftpChannel.get(copyFrom);
			br = new BufferedReader(new InputStreamReader(inputStream));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return br;

	}

	@SuppressWarnings("unused")
	public Map<String, Object> getLogByTimeStamp(BufferedReader br, LogRead logRead) {
		String sCurrentLine = "";
		StringBuilder sb=new StringBuilder();
		output.setLength(0);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		ArrayList<String> fileContents = new ArrayList<>();
		try {
			File file = new File(workingDir + "test.log");
			FileWriter fileWriter = new FileWriter(file);
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (Pattern.matches(logRead.getStartTime(),sCurrentLine)) {
					while ((sCurrentLine = br.readLine()) != null) {

						fileWriter.write(sCurrentLine);
						fileWriter.write("\r\n");
						if (Pattern.matches(logRead.getEndTime(), sCurrentLine))
							break;
					}
					break; 
				}
				
			}
			fileWriter.flush();
			fileWriter.close();
			String contents = new String(Files.readAllBytes(Paths.get(workingDir + "test.log"))); 

			map.put("WithTimeStampLogs", contents);
			
			sftpChannel.exit();
			session.disconnect();

		} catch (Exception e) {
			map.put("Exception", "Something went wrong while getting logs by given timestamp");
			e.printStackTrace();
		}
		if (map == null)
			map.put("Exception", "Something went wrong while getting logs by given timestamp");

		return map;
	}

	@SuppressWarnings("unused")
	public Map<String, Object> getLogByCookies(LogRead logRead) {
		BufferedReader br;
		output.setLength(0);
		String sCurrentLine = "";
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			File file = new File(workingDir + "test1.log");
			FileWriter fileWriter = new FileWriter(file);
			br = new BufferedReader(new FileReader(workingDir + "test.log"));

			while (sCurrentLine != null) {
				if (sCurrentLine.contains(logRead.getCookie())) {
					while (sCurrentLine != null) {

					
						fileWriter.write(sCurrentLine);
						fileWriter.write("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}
				} else {
					sCurrentLine = br.readLine();
				}

			}
			fileWriter.flush();
			fileWriter.close();
			String contents = new String(Files.readAllBytes(Paths.get(workingDir + "test1.log"))); 

			map.put("LogsByCookies", contents);
		} catch (Exception e) {
			map.put("Exception", "Something went wrong while getting logs by cookies");
			e.printStackTrace();
		}
		if (map == null)
			map.put("Exception", "Something went wrong while getting logs by given timestamp");

		return map;

	}

	public Map<String, Object> getLogByType(LogRead logRead) {
		BufferedReader br;
		output.setLength(0);
		String sCurrentLine = "";
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		StringBuilder sb1[] = { new StringBuilder(), new StringBuilder(), new StringBuilder(), new StringBuilder(),
				new StringBuilder() };
		
		try {
			if(logRead.getCookie()==null&logRead.getLogType()!=null){
				br = new BufferedReader(new FileReader(workingDir + "test.log"));}
			else{
			br = new BufferedReader(new FileReader(workingDir + "test1.log"));
			}
			while ((sCurrentLine) != null) {

				if (sCurrentLine.contains(this.configuration.getFormatOfInfo())) {
					while (sCurrentLine != null) {

						sb1[0].append(sCurrentLine);
						sb1[0].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}

				} else if (sCurrentLine.contains(this.configuration.getFormatOfDebug())) {
					while (sCurrentLine != null) {

						sb1[1].append(sCurrentLine);
						sb1[1].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}
				} else if (sCurrentLine.contains(this.configuration.getFormatOfError())) {
					while (sCurrentLine != null) {

						sb1[2].append(sCurrentLine);
						sb1[2].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}
				} else if (sCurrentLine.contains(this.configuration.getFormatOfWarn())) {
					while (sCurrentLine != null) {

						sb1[3].append(sCurrentLine);
						sb1[3].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}
				} else if (sCurrentLine.contains(this.configuration.getFormatOfFatal())) {
					while (sCurrentLine != null) {

						sb1[4].append(sCurrentLine);
						sb1[4].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && (Pattern.matches(logRead.getTimeFormatOfRegex(), sCurrentLine))) {
							break;
						}

					}
				} else {
					sCurrentLine = br.readLine();
				}

			}
			map.put("INFO", sb1[0]);
			map.put("DEBUG", sb1[1]);
			map.put("ERROR", sb1[2]);
			map.put("WARN", sb1[3]);
			map.put("FATAL", sb1[4]);
		} catch (Exception e) {
			e.printStackTrace();
		}
return map;
	}

	
	public LogRead dateFormatter(Configuration configuration2, LogRead logRead) throws Exception {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat(configuration2.getFormatOfTime());
		String arr[]={logRead.getStartTime(),logRead.getEndTime()};
		for(String s1:arr){
		Date date = format1.parse(s1);
		//"startTime":	".*2018-04-04 11:40:\\d\\d.*",
		//  "endTime":".*2018-04-04 11:50:\\d\\d.*",
		format2.setTimeZone(TimeZone.getTimeZone(configuration2.getTimeZoneOfLinux()));
		
		if(arr[0].equals(s1)){
			String s2=configuration2.getFormatOfTime();
			
			//System.out.println(s2.indexOf("ss"));
			String startTime = format2.format(date);
			System.out.println(startTime);
			//System.out.println(startTime);
			StringBuilder sb1= new StringBuilder(startTime);
			startTime=sb1.replace(s2.indexOf("ss"), s2.indexOf("ss")+2, "\\d\\d").toString();
			startTime=".*"+startTime+".*";
			
			//logRead.setStartTime(startTime);
			}
		else{
          String s2=configuration2.getFormatOfTime();
			
			//System.out.println(s2.indexOf("ss"));
			String startTime = format2.format(date);
			//System.out.println(startTime);
			StringBuilder sb1= new StringBuilder(startTime);
			 string = sb1.toString();
			startTime=sb1.replace(s2.indexOf("ss"), s2.indexOf("ss")+2, "\\d\\d").toString();
			startTime=".*"+startTime+".*";
			//logRead.setEndTime(startTime);
		}
		}
		
		String s1= string;
        //char c = 'a';
for(int i=0;i<s1.length();i++){
	
        if( (s1.charAt(i) >= 'a' && s1.charAt(i) <= 'z')|| (s1.charAt(i)>='0' && s1.charAt(i)<='9') || (s1.charAt(i) >= 'A' && s1.charAt(i) <= 'Z')){
            //System.out.println(s1.charAt(i) + " is an alphabet.");
        s1=s1.replace(s1.charAt(i), '.');
        }
        
        
}
s1=".*"+s1+".*";
		logRead.setTimeFormatOfRegex(s1);
		//System.out.println(logRead.getTimeFormatOfRegex());

		return logRead;
		}
	
	public void convertTOMilli(Configuration configuration2, LogRead logRead) throws Exception{
		System.out.println("check first convertTOMilli  "+configuration2+" "+ logRead);
        String startTime1[] = {logRead.getStartTime(),logRead.getEndTime()};//1521624180000
		//String endTime = "Wed 21 Mar 14:57:00 IST 2018";////1521624420000
        int i=0;
		for(String time : startTime1){
			i++;
		SimpleDateFormat sdf = new SimpleDateFormat(configuration2.getFormatOfTime());
		Date date = sdf.parse(time);
		long millis = date.getTime();
		//System.out.println(millis);
		if(i++==1)
		startTime=millis;
		else
			endTime=millis;
		}
}
	public Map<String, Object> getLogsByTime(BufferedReader br, LogRead logRead) throws Exception, Exception{
		//BufferedReader br=null;
		// br = new BufferedReader(new FileReader("F:\\QA\\jboss_order\\serviceability\\logs\\DataNode\\trace.log"));
		String currentline;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "test.log");
		FileWriter fileWriter = new FileWriter(file);
		List<Date> dates=null;
		//System.out.println("in getLogsByTime " +logRead);
		 while((currentline=br.readLine())!=null){
			 if(Pattern.matches(logRead.getTimeFormatOfRegex(),currentline)){
				dates =new Parser().parse(currentline).get(0).getDates();
				String string = dates.get(0).toString();
				SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				Date date1 = sdf1.parse(string);
				long millis1 = date1.getTime();
				if(millis1>=startTime){
					while(currentline!=null){
						fileWriter.write(currentline);
						fileWriter.write("\r\n");
					System.out.println(currentline);
					if(Pattern.matches(logRead.getTimeFormatOfRegex(),currentline)){
					dates =new Parser().parse(currentline).get(0).getDates();
					String string2 = dates.get(0).toString();
					SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
					Date date2 = sdf2.parse(string2);
					long millis2 = date2.getTime();
					if(millis2>=endTime){
						break;
					}
					else{
						currentline=br.readLine();
						continue;
					}
					}
					else{
						currentline=br.readLine();
						continue;
					}
				}
					break;
				}
			 }
			 }
			 
		 fileWriter.flush();
			fileWriter.close();
		//System.out.println(millis1);
			String contents = new String(Files.readAllBytes(Paths.get(workingDir + "test.log"))); 

			map.put("WithTimeStampLogs", contents);
			sftpChannel.exit();
			session.disconnect();

		
		if (map == null)
			map.put("Exception", "Something went wrong while getting logs by given timestamp");

		return map;
		
	}
		
	}

