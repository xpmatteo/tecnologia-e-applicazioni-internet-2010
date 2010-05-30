package it.xpug.courses.util;

import java.util.Map;


public class FakeSession extends EmptyHttpSession {

	private final Map<String, Object> attributes;

	public FakeSession(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}	

}
