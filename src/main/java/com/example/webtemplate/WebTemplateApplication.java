package com.example.webtemplate;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@SpringBootApplication
public class WebTemplateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();

		// set the port of the listener
		factory.setPort(2221);

		// replace the default listener
		serverFactory.addListener("default", factory.createListener());

		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("myusers.properties"));
		serverFactory.setUserManager(userManagerFactory.createUserManager());

		// start the server
		FtpServer server = serverFactory.createServer();
		server.start();
	}


	@PostMapping("/sendfile")
	public ResponseEntity sendFile(@RequestBody String file) {

		return new ResponseEntity(HttpStatus.OK);
	}
}
