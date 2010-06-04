package it.xpug.courses.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmptyServletRequest implements HttpServletRequest {

	public String getAuthType() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getContextPath() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Cookie[] getCookies() {
		throw new RuntimeException("Not yet implemented!");
	}

	public long getDateHeader(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getHeader(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getHeaderNames() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getHeaders(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getIntHeader(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getMethod() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getPathInfo() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getPathTranslated() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getQueryString() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRemoteUser() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRequestURI() {
		throw new RuntimeException("Not yet implemented!");
	}

	public StringBuffer getRequestURL() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRequestedSessionId() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getServletPath() {
		throw new RuntimeException("Not yet implemented!");
	}

	public HttpSession getSession() {
		throw new RuntimeException("Not yet implemented!");
	}

	public HttpSession getSession(boolean create) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Principal getUserPrincipal() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isRequestedSessionIdFromCookie() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isRequestedSessionIdFromURL() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isRequestedSessionIdFromUrl() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isRequestedSessionIdValid() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isUserInRole(String role) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Object getAttribute(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getAttributeNames() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getCharacterEncoding() {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getContentLength() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getContentType() {
		throw new RuntimeException("Not yet implemented!");
	}

	public ServletInputStream getInputStream() throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getLocalAddr() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getLocalName() {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getLocalPort() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Locale getLocale() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getLocales() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getParameter(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Map getParameterMap() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Enumeration getParameterNames() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String[] getParameterValues(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getProtocol() {
		throw new RuntimeException("Not yet implemented!");
	}

	public BufferedReader getReader() throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRealPath(String path) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRemoteAddr() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getRemoteHost() {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getRemotePort() {
		throw new RuntimeException("Not yet implemented!");
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getScheme() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getServerName() {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getServerPort() {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isSecure() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void removeAttribute(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setAttribute(String name, Object o) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		throw new RuntimeException("Not yet implemented!");
	}

}
