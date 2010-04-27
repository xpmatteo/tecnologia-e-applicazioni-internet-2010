package it.xpug.courses.util;

import java.util.HashMap;
import java.util.Map;


public class FakeServletRequest extends EmptyServletRequest {

	public String method = "GET";
	public String requestUri;
	private Map<String, String> params = new HashMap<String, String>();

	public void setParameter(String key, String value) {
		params.put(key, value);
	}

	@Override
	public String getParameter(String name) {
		return params.get(name);
	}

	@Override
	public String getRequestURI() {
		return requestUri;
	}

	@Override
	public String getMethod() {
		return method;
	}
	
	
}
