package com.vgmsistemas.vgmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VgmWebApplication  extends SpringBootServletInitializer{
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(VgmWebApplication.class);
	    }
	public static void main(String[] args) {
		SpringApplication.run(VgmWebApplication.class, args);
	}

}
