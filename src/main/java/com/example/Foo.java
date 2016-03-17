package com.example;

import java.io.Serializable;

/**
 *
 * @author Stephane Nicoll
 */
public class Foo implements Serializable {

	private final String id;

	public Foo(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Foo{" + "id='" + id + '\'' +
				'}';
	}
}
