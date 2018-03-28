package com.osi.loganalyzer.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osi.loganalyzer.external.service.WrapperService;
import com.osi.loganalyzer.model.InvokeModel;
import com.osi.loganalyzer.util.TimeZoneConvertion;

import io.restassured.response.Response;

@RestController
public class ExternalInvokeController {

	@Autowired
	WrapperService wrapperService;

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
