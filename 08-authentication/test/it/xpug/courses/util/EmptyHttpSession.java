package it.xpug.courses.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class EmptyHttpSession implements HttpSession {

	public Object getAttribute(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getAttributeNames() {
		throw new RuntimeException("Not yet implemented!");
	}

	public long getCreationTime() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getId() {
		throw new RuntimeException("Not yet implemented!");
	}

	public long getLastAccessedTime() {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getMaxInactiveInterval() {
		throw new RuntimeException("Not yet implemented!");
	}

	public ServletContext getServletContext() {
		throw new RuntimeException("Not yet implemented!");
	}

	public HttpSessionContext getSessionContext() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Object getValue(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String[] getValueNames() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void invalidate() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isNew() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void putValue(String name, Object value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void removeAttribute(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void removeValue(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setAttribute(String name, Object value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setMaxInactiveInterval(int interval) {
		throw new RuntimeException("Not yet implemented!");
	}

}
