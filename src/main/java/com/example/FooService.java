package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stephane Nicoll
 */
@Service
public class FooService {

	private static final Logger logger = LoggerFactory.getLogger(FooService.class);

	@Cacheable("foos")
	public Foo getFoo(String id) {
		logger.info("Loading foo with id: " + id);
		return new Foo(id);
	}

}
