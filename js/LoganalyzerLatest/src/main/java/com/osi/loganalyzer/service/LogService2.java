package com.osi.loganalyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.osi.loganalyzer.model.LogRead;

@Service
public class LogService2 {
	Session session;
	ChannelSftp sftpChannel;
	String workingDir = System.getProperty("user.dir");
	StringBuilder output = new StringBuilder("");

	public BufferedReader getLinuxConnection() {
		String hostname = "192.168.6.245";
		String username = "db2inst1";
		String password = "db2inst1";
		String copyFrom = "/home/db2inst1/logs/returns.log";
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
		System.out.println(br);
		return br;

	}

	@SuppressWarnings("unused")
	public Map<String, Object> getLogByTimeStamp(BufferedReader br) {
		String sCurrentLine = "";
		output.setLength(0);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("2018-04-04 11:40")) {
					while ((sCurrentLine = br.readLine()) != null) {

						output.append(sCurrentLine);
						output.append("\r\n");

						File file = new File(workingDir + "test.log");
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(output.toString());
						fileWriter.flush();
						fileWriter.close();
						if (sCurrentLine.contains("2018-04-04 11:50"))
							break;
					}
					break;
				}
			}
			map.put("WithTimeStampLogs", output);
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
	public Map<String, Object> getLogByCookies() {
		BufferedReader br;
		output.setLength(0);
		String sCurrentLine = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			br = new BufferedReader(new FileReader(workingDir + "test.log"));

			while (sCurrentLine != null) {
				if (sCurrentLine.contains("ProductValidator")) {
					while (sCurrentLine != null) {

						output.append(sCurrentLine);
						output.append("\r\n");
						File file = new File(workingDir + "test1.log");
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(output.toString());
						fileWriter.flush();
						fileWriter.close();
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
							break;
						}

					}
				} else {
					sCurrentLine = br.readLine();
				}

			}
			map.put("LogsByCookies", output);
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

				if (sCurrentLine.contains("INFO")) {
					while (sCurrentLine != null) {

						sb1[0].append(sCurrentLine);
						sb1[0].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
							break;
						}

					}

				} else if (sCurrentLine.contains("DEBUG")) {
					while (sCurrentLine != null) {

						sb1[1].append(sCurrentLine);
						sb1[1].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
							break;
						}

					}
				} else if (sCurrentLine.contains("ERROR")) {
					while (sCurrentLine != null) {

						sb1[2].append(sCurrentLine);
						sb1[2].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
							break;
						}

					}
				} else if (sCurrentLine.contains("WARN")) {
					while (sCurrentLine != null) {

						sb1[3].append(sCurrentLine);
						sb1[3].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
							break;
						}

					}
				} else if (sCurrentLine.contains("FATAL")) {
					while (sCurrentLine != null) {

						sb1[4].append(sCurrentLine);
						sb1[4].append("\r\n");
						sCurrentLine = br.readLine();
						if (sCurrentLine != null && sCurrentLine.contains("2018-04")) {
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
}
