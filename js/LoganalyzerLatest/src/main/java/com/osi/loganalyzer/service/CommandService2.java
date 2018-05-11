package com.osi.loganalyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.loganalyzer.model.Command;
import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.util.TimeZoneConvertion;

@Service
public class CommandService2 {

	/*@Autowired
	TimeZoneConvertion timeZoneConvertion;*/
	
	public void runApplication(Command command) {

		StringBuilder output = new StringBuilder("");
		
		try {
			File file = new File(command.getLocation());
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(command.getCommand(), null, file);
			// Process pr = rt.exec("c:\\helloworld.exe");

			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

			String line = null;

			while ((line = input.readLine()) != null) {
				output.append(line);
				System.out.println(line);
			}

			int exitVal = pr.waitFor();
			System.out.println(output);
			System.out.println("Exited with error code " + exitVal);

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}

	public StringBuilder getLogsBydebug(LogRead logRead) {
		int count = 0;
		StringBuilder output = new StringBuilder("");
		File file = new File(logRead.getLocation());
		Scanner in = null;
		try {
			in = new Scanner(file);
			while (in.hasNext()) {
				
				String line = in.nextLine();
				if(line.contains("22 Feb 17:20")){
					 output = getlogs(in,line, count, logRead, output);
				}
				//line.contains(logRead.getDebug());
				/*if (line.contains(logRead.getDebug())) {
					count++;
					
					output.append(line);
					output.append("\r\n");
					System.out.println(line);
					String ss = in.nextLine();

					while (!((ss.contains("DEBUG") | ss.contains("FATAL") | ss.contains("WARN")
							| ss.contains("INFO")))) {

						output.append("\r\n");
						output.append(ss);
						System.out.println(ss);
						ss = in.nextLine();
					}

				}*/
			}
			System.out.println(count);
			// System.out.println(output);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 return output;

	}

	public StringBuilder getlogs(Scanner in, String line,int count,LogRead logRead,StringBuilder output){
		
		
		line.contains(logRead.getDebug());
		while (in.hasNext()) {
			line = in.nextLine();
		if (line.contains(logRead.getDebug())) {
			count++;
			
			output.append(line);
			output.append("\r\n");
			System.out.println(line);
			String ss = in.nextLine();

			while (!((ss.contains("DEBUG") | ss.contains("FATAL") | ss.contains("WARN")
					| ss.contains("INFO")))) {

				output.append("\r\n");
				output.append(ss);
				System.out.println(ss);
				ss = in.nextLine();
			}

		}
		}
		System.out.println(count);
		return output;
	}
	
	public StringBuilder getcount(LogRead logRead) {
		StringBuilder output = new StringBuilder("");
		File file = new File(logRead.getLocation());
		/*int count0 = 0;
		int count1 = 0;
		int count3 = 0;
		int count4 = 0;
		int count2 = 0;*/
		Scanner in = null;

		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (in.hasNext()) {
			String line = in.nextLine();
			if(line.contains("22 Feb 17:20")){
				output= getcount1(in,line);
			}
            
			/*if (line.contains("**INFO**")) {
				count0++;
			} else if (line.contains("**ERROR**")) {
				count1++;
			} else if (line.contains("**DEBUG**")) {
				count2++;
			} else if (line.contains("**WARN**")) {
				count3++;
			} else if (line.contains("**FATAL**")) {
				count4++;
			}*/
		}
		
		/*StringBuilder output1 = new StringBuilder("");
		output1.append("log Info count is " + count0 + ",\n log Error count is " + count1 + ",\n log Debug count is "
				+ count2 + ",\n log Warn count is" + count3 + ",\n log Fatal count is " + count4 + " ");*/
		return output;
	}
	
	public StringBuilder getcount1(Scanner in,String line){
		int count0 = 0;
		int count1 = 0;
		int count3 = 0;
		int count4 = 0;
		int count2 = 0;
		while (in.hasNext()) {
			line = in.nextLine();

			if (line.contains("**INFO**")) {
				count0++;
			} else if (line.contains("**ERROR**")) {
				count1++;
			} else if (line.contains("**DEBUG**")) {
				count2++;
			} else if (line.contains("**WARN**")) {
				count3++;
			} else if (line.contains("**FATAL**")) {
				count4++;
			}
		}
		StringBuilder output1 = new StringBuilder("");
		return output1.append("log Info count is " + count0 + ",\n log Error count is " + count1 + ",\n log Debug count is "
				+ count2 + ",\n log Warn count is" + count3 + ",\n log Fatal count is " + count4 + " ");
		
	}

	public StringBuilder getlogBySearch(LogRead logRead) {
		
		int count = 0;
		StringBuilder output = new StringBuilder("");
		File file = new File(logRead.getLocation());
		Scanner in = null;
		
			try {
				in = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (in.hasNext()) {
				String search = logRead.getSearch();
				String line = in.nextLine();
				if(line.contains("22 Feb 17:36")){
					searchingbyString(line,search,in,count,output);
				}
			
			}
			
		return output;
	}
	
	public void searchingbyString(String line, String search,Scanner in,int count,StringBuilder output){
		while (in.hasNext()) {
			 line=in.nextLine();
			if (line.contains(search)) {
				count++;
				
				output.append(line);
				output.append("\r\n");
				System.out.println(line);
				
			}
			
	}
		System.out.println(count);
	}
	
	
}
