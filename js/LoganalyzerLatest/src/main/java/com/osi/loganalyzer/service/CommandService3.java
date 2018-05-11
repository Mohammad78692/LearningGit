package com.osi.loganalyzer.service;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.osi.loganalyzer.model.Command;
import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.model.LogsSpcification;
import com.osi.loganalyzer.util.Failure;
import com.osi.loganalyzer.util.MyPojo;
import com.osi.loganalyzer.util.Testcase;
import com.osi.loganalyzer.util.TimeZoneConvertion;







/**
 * @author moirfan
 *
 */
@Service
public class CommandService3 {
	static Map map=new LinkedHashMap();
	//private String formate1;
	Desktop desktop;
	@Autowired
	LogsSpcification logs;
	
	Command commandForHtml;
	public StringBuilder getLogsBydebug1(LogRead logRead) {
		int count = 0;
		StringBuilder output = new StringBuilder("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(logs.getLogfilelocation()));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains(TimeZoneConvertion.formate1)) {
					output = getlogs(br, sCurrentLine, count, output, logRead);
				}

			}

		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			// e.printStackTrace();
		} catch (IOException e) {
			output.append("requested data not available " + e.getMessage());
			// e.printStackTrace();
		}
		if (output.toString().isEmpty()) {
			output.append("Requested log is not available");
		}
		return output;
	}

	public StringBuilder getlogs(BufferedReader br, String sCurrentLine, int count, StringBuilder output,
			LogRead logRead) {

		try {
			if(logRead.getDebug().equals(logs.getInfo())){
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains(logRead.getDebug())|!sCurrentLine.contains(TimeZoneConvertion.formate2)) {
					output.append(sCurrentLine);
					output.append("\r\n");
					}
				}


			}
			else{
				while ((sCurrentLine = br.readLine()) != null) {

					if (sCurrentLine.contains(logRead.getDebug())) {
						output.append(sCurrentLine);
						output.append("\r\n");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}

	public Map getCount1() {
		//int count = 0;
		StringBuilder output = new StringBuilder("");
		Map logcounts = new LinkedHashMap();
		try {
			BufferedReader br = new BufferedReader(new FileReader(logs.getLogfilelocation()));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains(TimeZoneConvertion.formate1)) {
					logcounts = getcount1(br, sCurrentLine);
				}

			}

		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			// e.printStackTrace();
		} catch (IOException e) {
			output.append("requested data not available " + e.getMessage());
			// e.printStackTrace();
		}
		if (logcounts.isEmpty()) {
			output.append("Requested log is not available");
		}
		logcounts.put("output", output);
		return logcounts;
	}

	public Map getcount1(BufferedReader br, String sCurrentLine) {
		Map map = new LinkedHashMap();
		int count[] = { 0, 0, 0, 0, 0 };
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				// line = in.nextLine();

				if (sCurrentLine.contains(logs.getInfo())) {
					count[0]++;
					
				} else if (sCurrentLine.contains(logs.getError())) {
					count[1]++;
				} else if (sCurrentLine.contains(logs.getDebug())) {
					count[2]++;
				} else if (sCurrentLine.contains(logs.getWarn())) {
					count[3]++;
				} else if (sCurrentLine.contains(logs.getFatal())) {
					count[4]++;
				}
			}
			map.put(logs.getInfo(), count[0]++);
			map.put(logs.getError(), count[1]++);
			map.put(logs.getDebug(), count[2]++);
			map.put(logs.getWarn(), count[3]++);
			map.put(logs.getFatal(), count[4]++);



		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		StringBuilder output1 = new StringBuilder("");
		System.out.println(map);
		/*return output1.append(
				"log Info count is " + count[0] + ",\n log Error count is " + count[1] + ",\n log Debug count is "
						+ count[2] + ",\n log Warn count is" + count[3] + ",\n log Fatal count is " + count[4] + " ");*/
return map;
	}

	public StringBuilder getlogBySearch(LogRead logRead) {

		int count = 0;
		String search = logRead.getSearch();
		StringBuilder output = new StringBuilder("");
		//File file = new File(logs.getLogfilelocation());
		//Scanner in = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(logs.getLogfilelocation()));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains(TimeZoneConvertion.formate1)) {
					searchingbyString(br, sCurrentLine, search, count, output);
				}

			}

		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (output.toString().isEmpty()) {
			output.append("Requested string is not available, please check case also");
		}
		return output;
	}

	public void searchingbyString(BufferedReader br, String sCurrentLine, String search, int count,
			StringBuilder output) {
		try {
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains(search)) {
					count++;
					
					output.append(sCurrentLine);
					output.append("\r\n");
					System.out.println(sCurrentLine);

				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println(count);
		
	}

	public Map runcommandAndRead(Command command) {
		BufferedReader br;
		String sCurrentLine;
		commandForHtml=command;
		StringBuilder output = new StringBuilder();

		Map map = new LinkedHashMap();
		File file = new File(command.getLocation());
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(logs.getMvnCommand(), null, file);
			pr.waitFor();
			br = new BufferedReader(new FileReader(command.getLocation() + "\\log1.txt"));

			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				output.append(sCurrentLine);
				// output.append("\r\n");

				if (sCurrentLine.contains("TestCaseRunner Summary")) {
					br.readLine();
					while (i < 7) {
						sCurrentLine = br.readLine();
						map.put(sCurrentLine.split(":")[0].trim().replaceAll("\\s", ""), sCurrentLine.split(":")[1]);
						i++;
						output.append(sCurrentLine);
						// output.append("\r\n");

					}
				}
				if (sCurrentLine.contains("Tests run: ")) {
					String[] s = sCurrentLine.split(",");
					for (i = 0; i < s.length; i++)
						map.put(s[i].split(":")[0].trim().replaceAll("\\s", ""), s[i].split(":")[1]);

					while ((sCurrentLine = br.readLine()) != null) {
						System.out.println(sCurrentLine);
						output.append(sCurrentLine);
						// output.append("\r\n");

					}
				}

			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("testcases", output.toString());
		return map;
	}

	
	
	public Map erroWithDetails() throws Exception {

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		String xmll = FileUtils.readFileToString(new File(logs.getHtmlLocation()));
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir + "test1.json");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(org.json.XML.toJSONObject(xmll).toString());
		fileWriter.flush();
		fileWriter.close();
		ObjectMapper mapper = new ObjectMapper();

		MyPojo obj = mapper.readValue(new File(workingDir + "test1.json"), MyPojo.class);

		Testcase[] testcase = obj.getTestsuite().getTestcase();

		for (int i = 0; i < testcase.length; i++) {
			StringBuilder sb = new StringBuilder();
			if ((testcase[i].getError() != null) && (testcase[i].getFailure() == null)) {
				com.osi.loganalyzer.util.Error error = testcase[i].getError();
				sb.append("\r\n");
				sb.append(" CLASSNAME :" + testcase[i].getClassname());
				sb.append("\r\n");
				sb.append(" NAME :" + testcase[i].getName());
				sb.append("\r\n");
				sb.append(" TIME :" + testcase[i].getTime());
				sb.append("\r\n");
				sb.append(" ERROR :" + error);

				sb1.append(" DETAILS OF ERROR " + sb);

				sb1.append("\r\n");
			}

			else if ((testcase[i].getError() == null) && (testcase[i].getFailure() != null)) {
				Failure failure = testcase[i].getFailure();
				sb.append("\r\n");
				sb.append(" CLASSNAME :" + testcase[i].getClassname());
				sb.append("\r\n");
				sb.append(" NAME :" + testcase[i].getName());
				sb.append("\r\n");
				sb.append(" TIME :" + testcase[i].getTime());
				sb.append("\r\n");
				sb.append(" FAILURE :" + failure);

				sb2.append(" DETAILS OF FAILURE " + sb);
				sb2.append("\r\n");
			} else if ((testcase[i].getError() == null) && (testcase[i].getFailure() == null)) {
				sb.append("\r\n");
				sb.append(" CLASSNAME :" + testcase[i].getClassname());
				sb.append("\r\n");
				sb.append(" NAME :" + testcase[i].getName());
				sb.append("\r\n");
				sb.append(" TIME :" + testcase[i].getTime());

				sb3.append(" DETAILS OF SKIPPED " + sb);
				sb3.append("\r\n");
			}
			sb = null;

		}
		map.put("Exceptions", sb1);
		map.put("Assertion", sb2);
		map.put("Skipped", sb3);

		return map;

	}

	public String getLogsfromLinux() throws Exception {
		String hostname = logs.getHostname();
        String username = logs.getUsername();
        String password = logs.getPassword();
        String copyFrom = logs.getLinuxLog();
       
        JSch jsch = new JSch();
        Session session = null;
        StringBuilder sb1 = new StringBuilder();
        System.out.println("Trying to connect.....");
        try {
            session = jsch.getSession(username, hostname, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect(); 
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel; 
            InputStream inputStream = sftpChannel.get(copyFrom);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("2018-04-04 11:40")){
					while ((sCurrentLine = br.readLine()) != null) {
						
						
				sb1.append(sCurrentLine);
				sb1.append("\r\n");
				System.out.println(sCurrentLine);
				File file = new File("F:/QA/jboss_order/standalone/log/server.log");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(sb1.toString());
				fileWriter.flush();
				fileWriter.close();
				if(sCurrentLine.contains("2018-04-04 11:50"))
					break;
					}
					break;
			}

			}
           
            
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        System.out.println("Done !!");
		return sb1.toString();
	}
	
	
	/*public ModelAndView runcommandAndReadHtml() {
		BufferedReader br;
		String sCurrentLine;
		
		String arr[] = new String[5];
		StringBuilder output = new StringBuilder();
		//List output1=new ArrayList<StringBuilder>();
		StringBuilder output1[] = {new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder()};
		
		
		ModelAndView  modalAndView = new ModelAndView("welcome");
		try {
			
		   
			File folder = new File("E:\\Restassured\\VenkatSomala\\New folder\\RestAssuredFramework\\TestCases\\target\\surefire-reports\\");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				arr[i] = listOfFiles[i].getName();
				if (arr[i].endsWith(".html")) {
					System.out.println(arr[i]);
					br = new BufferedReader(new FileReader(folder + "\\" + arr[i]));
					
					while ((sCurrentLine = br.readLine()) != null) {
						if(!(sCurrentLine.contains("<link")||sCurrentLine.contains("<script"))){
						output.append(sCurrentLine);
						output.append("\r\n");
					}
					}
				}
				else if (arr[i].endsWith(".css")) {
					br = new BufferedReader(new FileReader(folder + "\\" + arr[i]));

					while ((sCurrentLine = br.readLine()) != null) {
						output1[i].append(sCurrentLine);
						output1[i].append("\r\n");
					}
					output.append("<style>"+output1[i]+"</style>");
				}
				
				else if (!(arr[i].endsWith(".html"))) {
					br = new BufferedReader(new FileReader(folder + "\\" + arr[i]));
					while ((sCurrentLine = br.readLine()) != null) {
						output1[i].append(sCurrentLine);
						output1[i].append("\r\n");
					}
					output.append("<script>"+output1[i]+"</script>");
				}
			}
			
			modalAndView.addObject("writer",output);
			
		} 
		catch(NullPointerException e){
			e.printStackTrace();
		}
		catch (IOException  ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(output.toString());
		return modalAndView;

	}*/
	
	/*public Desktop runHtml(){
		//String url = "E:\\Restassured\\VenkatSomala\\New folder\\RestAssuredFramework\\TestCases\\target\\surefire-reports\\index.html";
		File htmlFile = new File(logs.getHtmlLocation());
		//Desktop.getDesktop().browse(htmlFile.toURI());
		//if(Desktop.isDesktopSupported()){
			 desktop = Desktop.getDesktop();
			try {
				desktop.open(htmlFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return desktop;
	}*/
}



/*
 * public Report runApplication(Command command) { Report report=null;
 * StringBuilder output = new StringBuilder("");
 * 
 * try { File file = new File(command.getLocation()); Runtime rt =
 * Runtime.getRuntime(); //Process pr = rt.exec("mvn.cmd clean install", null,
 * file); Process pr = rt.exec("mvn.cmd surefire-report:report", null, file); //
 * Process pr = rt.exec("c:\\helloworld.exe");
 * 
 * BufferedReader input = new BufferedReader(new
 * InputStreamReader(pr.getInputStream()));
 * 
 * String line = null;
 * 
 * while ((line = input.readLine()) != null) { output.append(line);
 * System.out.println(line); }
 * 
 * int exitVal = pr.waitFor(); System.out.println(output); System.out.println(
 * "Exited with error code " + exitVal); report = report(command);
 * 
 * } catch (Exception e) { System.out.println(e.toString());
 * e.printStackTrace(); }
 * 
 * return report; }
 * 
 * public Report report(Command command) { Report r=new Report(); Map map=new
 * LinkedHashMap(); try { Thread.sleep(5000); } catch (InterruptedException e1)
 * { e1.printStackTrace(); } StringBuilder output = new StringBuilder(""); try {
 * BufferedReader br = new BufferedReader(new FileReader(command.getLocation()+
 * "\\target\\surefire-reports\\com.macys.marketing.loyalty.LtyIntegrationTest.runner.TestRunnerTest.txt"
 * ));
 * 
 * String sCurrentLine;
 * 
 * while ((sCurrentLine = br.readLine()) != null) {
 * //System.out.println(sCurrentLine);
 * 
 * output.append(sCurrentLine); if(sCurrentLine.contains("Tests run")){ String[]
 * s=sCurrentLine.split(","); for(int i=0;i<s.length;i++){ String
 * ss[]=s[i].split(":");
 * 
 * System.out.println(Arrays.toString(ss));
 * 
 * if(ss[0].equalsIgnoreCase(" Failures")) r.setFailures(ss[1]);
 * 
 * else if(ss[0].equalsIgnoreCase(" Errors")) r.setErrors(ss[1]);
 * 
 * else if(ss[0].equalsIgnoreCase(" Skipped")) r.setSkipped(ss[1]); else
 * if(ss[0].equalsIgnoreCase("Tests run")) r.setTests_run(ss[1]);
 * 
 * else if(ss[0].equalsIgnoreCase(" Time elapsed"))
 * r.setTime_elapsed(ss[1].split("-")[0]);
 * 
 * }
 * 
 * } }
 * 
 * } catch (IOException e) { output.append("requested data not available " +
 * e.getMessage()); // e.printStackTrace(); }
 * 
 * return r; }
 * 
 */
