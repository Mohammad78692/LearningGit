package com.osi.loganalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class LoganalyzerLatestApplication extends SpringBootServletInitializer{
	//public static String ARGS="-Djava.awt.headless=false";
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoganalyzerLatestApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LoganalyzerLatestApplication.class, args);
		/*SpringApplicationBuilder builder = new SpringApplicationBuilder(LoganalyzerLatestApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run("-Djava.awt.headless=false");*/
		 System.setProperty("java.awt.headless", "false");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	}
}
