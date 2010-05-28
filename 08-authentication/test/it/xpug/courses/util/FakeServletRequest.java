package it.xpug.courses.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


public class FakeServletRequest extends EmptyServletRequest {
	public String method = "GET";
	public String requestUri;
	private Map<String, String> params = new HashMap<String, String>();
	private Map<String, Object> session = new HashMap<String, Object>();

	public void setParameter(String key, String value) {
		params.put(key, value);
	}

	public void setSessionAttribute(String name, Object value) {
		session.put(name, value);
	}
	
	@Override
	public HttpSession getSession() {
		return new FakeSession(session);
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
