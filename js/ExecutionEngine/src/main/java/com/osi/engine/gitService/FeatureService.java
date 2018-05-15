package com.osi.engine.gitService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.TokenMatcher;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

@Service
public class FeatureService {
	
	
	public Map<String, Object> featureFileDetails(List<String> files) throws IOException{
		List<Pickle> pickles=new ArrayList<Pickle>();
		List<List<Pickle>> pickles1=new ArrayList<List<Pickle>>();
		Map<String,Object> map=new LinkedHashMap<String,Object>();
		
		for(int i=0;i<files.size();i++){
			StringBuilder pickleData=new StringBuilder("");
		 String contents = new String(Files.readAllBytes(Paths.get(files.get(i)))); 
	        TokenMatcher matcher = new TokenMatcher();
	        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());

	        GherkinDocument gherkinDocument =  parser.parse(contents, matcher);
	        Compiler compiler = new Compiler();
	        pickles = compiler.compile(gherkinDocument);
	        pickles1.add(pickles);
	        for (int i1 = 0; i1 < pickles.size(); i1++) {
	        	if (!pickles.get(i1).getName().isEmpty())
					for (int i2 = 0; i2 < pickles.get(i1).getLocations().size(); i2++) {
						pickleData.append("Scenario name =" + pickles.get(i1).getName() + " ,and line number of scenario "
								+ pickles.get(i1).getLocations().get(i2).getLine());
						pickleData.append("\r\n");
					}

				for (int i2 = 0; i2 < pickles.get(i1).getSteps().size(); i2++) {
					pickleData.append("Test names =" + pickles.get(i1).getSteps().get(i2).getText());
					pickleData.append("\r\n");
				}
	        }
	        map.put(files.get(i)+"\r\n",pickleData);

		}
		
		System.out.println(map);

		return map;
	}
}
