package com.osi.loganalyzer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.osi.loganalyzer.external.service.WrapperService;
import com.osi.loganalyzer.model.InvokeModel;
import com.osi.loganalyzer.util.TimeZoneConvertion;

@Controller
@CrossOrigin(origins = "http://192.168.60.76:4200", allowedHeaders = "*")
public class ExternalInvokeController {

	@Autowired
	WrapperService wrapperService;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView openHello() {
		System.out.println("in getpage");
		ModelAndView  modalAndView = new ModelAndView("welcome");
		modalAndView.addObject("writer", "Bear");
		
		return modalAndView;
	}

	@PostMapping(value = "/invokeservice")
	public String invoke(@RequestBody InvokeModel invokeModel) {
		wrapperService.buildUri(invokeModel);
		StringBuilder response = wrapperService.hitExternalService();
		TimeZoneConvertion currentTime = new TimeZoneConvertion();
		currentTime.getCurrentTime();
		/*Response actualResponse = (Response) response.get("ACTUAL_RESPONSE");
		String output = actualResponse.asString();*/
		return response.toString();
	}

}
