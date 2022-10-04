package org.izce.spring_mvc_rest.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	public DataLoader() {
		log.debug("Initializing DataLoader...");
		
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
