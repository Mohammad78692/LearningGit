package com.osi.loganalyzer.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.osi.loganalyzer.model.Command;
import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.model.LogsSpcification;
import com.osi.loganalyzer.util.TimeZoneConvertion;





/**
 * @author moirfan
 *
 */
@Service
public class CommandService3 {

	//private String formate1;

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
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains(logRead.getDebug())) {
					count++;

					output.append(sCurrentLine);
					output.append("\r\n");
					System.out.println(sCurrentLine);

					while (!((sCurrentLine.contains(logs.getDebug()) | sCurrentLine.contains(logs.getFatal())
							| sCurrentLine.contains(logs.getWarn()) | sCurrentLine.contains(logs.getInfo())))) {

						output.append("\r\n");
						if (output.toString().contains(sCurrentLine)) {
						} else {
							output.append(sCurrentLine);
						}
						System.out.println(sCurrentLine);

						sCurrentLine = br.readLine();

					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}

	public StringBuilder getCount1() {
		//int count = 0;
		StringBuilder output = new StringBuilder("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(logs.getLogfilelocation()));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains(TimeZoneConvertion.formate1)) {
					output = getcount1(br, sCurrentLine);
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

	public StringBuilder getcount1(BufferedReader br, String sCurrentLine) {

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder output1 = new StringBuilder("");
		return output1.append(
				"log Info count is " + count[0] + ",\n log Error count is " + count[1] + ",\n log Debug count is "
						+ count[2] + ",\n log Warn count is" + count[3] + ",\n log Fatal count is " + count[4] + " ");

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

	//@SuppressWarnings("unchecked")
	public ModelAndView runcommandAndReadHtml() {
		BufferedReader br;
		String sCurrentLine;
		
		String arr[] = new String[5];
		StringBuilder output = new StringBuilder();
		StringBuilder output1[] = {new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder()};
		
		
		ModelAndView  modalAndView = new ModelAndView("welcome");
		try {
			
		   
			File folder = new File(commandForHtml.getLocation() + "\\target\\Destination");
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

	}
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
