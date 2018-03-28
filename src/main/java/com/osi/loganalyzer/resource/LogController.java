package com.osi.loganalyzer.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osi.loganalyzer.model.Command;
import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.model.Report;
import com.osi.loganalyzer.service.CommandService;
import com.osi.loganalyzer.service.CommandService3;

/**
 * @author moirfan
 *
 */
@RestController
// @CrossOrigin(origins = "http://192.168.60.76:4200", allowedHeaders = "*")
public class LogController {

	@Autowired
	CommandService commandService;
	
	@Autowired
	CommandService3 commandServic3;

	@PostMapping(value = "/getlogbydebug")
	public StringBuilder getLogByDebug(@RequestBody LogRead logRead) {
		/* System.out.println(logRead); */
		StringBuilder logsBydebug = commandServic3.getLogsBydebug1(logRead);
		return logsBydebug;
	}

	@GetMapping(value = "/getlogcount")
	public StringBuilder getLogCount() {
		StringBuilder getcount = commandServic3.getCount1();
		return getcount;
	}

	@PostMapping(value = "/getlogbysearch")
	public StringBuilder getLogByString(@RequestBody LogRead logRead) {
		StringBuilder getcount = commandServic3.getlogBySearch(logRead);
		return getcount;
	}
	
	@PostMapping(value = "/getreport")
	public Map getReport(@RequestBody Command command) {
		Map generateReport = commandServic3.runcommandAndRead(command);
		return generateReport;
	}
	
	@PostMapping(value = "/gethtmlreport")
	public String getHtmlReport(@RequestBody Command command) {
		String htmlReport = commandServic3.runcommandAndReadHtml(command);
		return htmlReport;
	}
	
	/*@PostMapping(value = "/runcommand")
	public Report getAll(@RequestBody Command command) {
		System.out.println(command);
		Report runApplication = commandServic3.runApplication(command);
		return runApplication;

	}*/
}