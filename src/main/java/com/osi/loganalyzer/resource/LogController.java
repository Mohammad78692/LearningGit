package com.osi.loganalyzer.resource;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.osi.loganalyzer.model.Command;
import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.service.CommandService;
import com.osi.loganalyzer.service.CommandService3;




/**
 * @author moirfan
 *
 */
@Controller
@CrossOrigin(origins = "http://192.168.60.76:4200", allowedHeaders = "*")
public class LogController {

	@Autowired
	CommandService commandService;
	
	@Autowired
	CommandService3 commandServic3;
	
	@Autowired
	ServletContext servletContext;

	@PostMapping(value = "/getlogbydebug")
	@ResponseBody
	public StringBuilder getLogByDebug(@RequestBody LogRead logRead) {
		/* System.out.println(logRead); */
		StringBuilder logsBydebug = commandServic3.getLogsBydebug1(logRead);
		return logsBydebug;
	}

	@GetMapping(value = "/getlogcount")
	@ResponseBody
	public StringBuilder getLogCount() {
		StringBuilder getcount = commandServic3.getCount1();
		return getcount;
	}

	@PostMapping(value = "/getlogbysearch")
	@ResponseBody
	public StringBuilder getLogByString(@RequestBody LogRead logRead) {
		StringBuilder getcount = commandServic3.getlogBySearch(logRead);
		return getcount;
	}
	
	@PostMapping(value = "/getreport")
	@ResponseBody
	public Map getReport(@RequestBody Command command) {
		Map generateReport = commandServic3.runcommandAndRead(command);
		return generateReport;
	}
	
	@GetMapping(value = "/gethtmlreport")
	@ResponseBody
	public ModelAndView getHtmlReport() {
		ModelAndView runcommandAndReadHtml = commandServic3.runcommandAndReadHtml();
		return runcommandAndReadHtml;
	}
	
	
	

	/*@RequestMapping("/viewhtml")
	//@ResponseBody
	public String welcome(Map<String, Object> model) {
		BufferedReader br;
		String sCurrentLine;
		StringBuilder output = new StringBuilder();
		try {

			//File file = new ClassPathResource("/main/webapp/WEB-INF/jsp/welcome.jsp").getFile();
			File file = new File( servletContext.getRealPath("/WEB-INF/jsp/") );
			br = new BufferedReader(new FileReader(file));
			
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				output.append(sCurrentLine);
				output.append("\r\n");
			}
			
			//System.out.println(file.);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("message", this.message);
		return "welcome";
		//System.out.println(output);
		//return output.toString();
	}*/
	/*@PostMapping(value = "/runcommand")
	public Report getAll(@RequestBody Command command) {
		System.out.println(command);
		Report runApplication = commandServic3.runApplication(command);
		return runApplication;

	}*/
}