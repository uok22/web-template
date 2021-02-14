package com.example.webtemplate;

import org.apache.ftpserver.DataConnectionConfiguration;
import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.Listener;
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
		ListenerFactory listenerFactory = new ListenerFactory();

		// set the port of the listener
		listenerFactory.setPort(2221);

		/**************************/

		DataConnectionConfigurationFactory dccF = new DataConnectionConfigurationFactory();

		dccF.setActiveEnabled(false);
		// dccF.setActiveLocalPort(45446);
		// dccF.setActiveLocalAddress("127.0.0.1");

		dccF.setPassivePorts("45445-45455");

		listenerFactory.setDataConnectionConfiguration(dccF.createDataConnectionConfiguration());

		/**************************/

		// replace the default listener
		// Listener listener = listenerFactory.createListener();
		serverFactory.addListener("default", listenerFactory.createListener());




		// DataConnectionConfiguration dcc = listenerFactory.getDataConnectionConfiguration();



		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		// userManagerFactory.setFile(new File("users.properties"));
		userManagerFactory.setFile(new File("/ftp/users.properties"));
		serverFactory.setUserManager(userManagerFactory.createUserManager());

		// start the server
		FtpServer server = serverFactory.createServer();
		server.start();

		System.out.println();
	}
}
