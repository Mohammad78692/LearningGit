package com.osi.engine.resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.osi.engine.gitService.FeatureService;
import com.osi.engine.model.PicklesList;

import gherkin.pickles.Pickle;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class FeatureFileController {
	
	@Autowired
	FeatureService featureService;
	
	@PostMapping(value="/scenario")
	public Map<String, Object> detailOfFeature(@RequestBody PicklesList picklesList){
		System.out.println(picklesList);
		Map<String, Object> featureFileDetails=null;
		try {
			featureFileDetails = featureService.featureFileDetails(picklesList.getFileLocation());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return featureFileDetails;
		
	}

}
