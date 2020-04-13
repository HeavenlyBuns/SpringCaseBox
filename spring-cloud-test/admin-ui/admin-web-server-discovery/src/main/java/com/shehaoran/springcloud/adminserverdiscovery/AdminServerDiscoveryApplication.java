package com.shehaoran.springcloud.adminserverdiscovery;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminServerDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerDiscoveryApplication.class, args);
	}

}
