package com.example;

import java.io.Serializable;

/**
 *
 * @author Stephane Nicoll
 */
public class Bar implements Serializable {

	private final String id;

	public Bar(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Bar{" + "id='" + id + '\'' +
				'}';
	}
}
