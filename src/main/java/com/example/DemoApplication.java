package com.example;

import java.util.UUID;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Demonstrates the use of an additional bucket to register the "Bar" cache. A
	 * public bucket with no password should exist on the target couchbase instance.
	 */
	@Configuration
	static class CouchbaseConfiguration {

		private Cluster cluster;

		public CouchbaseConfiguration(Cluster cluster) {
			this.cluster = cluster;
		}

		@Bean
		public Bucket publicBucket() {
			return this.cluster.openBucket("public");
		}

		@Bean
		public CacheManagerCustomizer<CouchbaseCacheManager> cacheManagerCustomizer() {
			return c -> {
				c.prepareCache("bars", CacheBuilder.newInstance(publicBucket())
						.withExpirationInMillis(500));
			};
		}

	}

	@Service
	static class Startup implements CommandLineRunner {

		private static final Logger logger = LoggerFactory.getLogger(Startup.class);

		private final SomeService someService;

		Startup(SomeService someService) {
			this.someService = someService;
		}

		@Override
		public void run(String... strings) throws Exception {
			String id = UUID.randomUUID().toString();
			loadFoo(id);
			loadFoo(id);
			loadFoo(id);
			loadBar(id);
			loadBar(id);
			loadBar(id);
		}

		private void loadFoo(String id) {
			Foo foo = this.someService.getFoo(id);
			logger.info("Loaded " + foo);
		}


		private void loadBar(String id) {
			Bar foo = this.someService.getBar(id);
			logger.info("Loaded " + foo);
		}

	}
}
