package com.example;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Service
	static class Startup implements CommandLineRunner {

		private static final Logger logger = LoggerFactory.getLogger(Startup.class);

		private final FooService fooService;

		Startup(FooService fooService) {
			this.fooService = fooService;
		}

		@Override
		public void run(String... strings) throws Exception {
			String id = UUID.randomUUID().toString();
			loadFoo(id);
			loadFoo(id);
			loadFoo(id);
		}

		private void loadFoo(String id) {
			Foo foo = this.fooService.getFoo(id);
			logger.info("Loaded " + foo);
		}

	}
}
