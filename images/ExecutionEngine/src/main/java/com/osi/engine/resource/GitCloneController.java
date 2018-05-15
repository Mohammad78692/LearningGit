package com.osi.engine.resource;

import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osi.engine.gitService.GitService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class GitCloneController {

	@Autowired
	GitService gitService;

	@RequestMapping(value = "/gitclone", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> gitClone(@QueryParam("Url") String Url) {
		System.out.println(Url);
		Map<String, Object> output = gitService.gitClone(Url);
		return output;

	}

	
	
}
