package it.xpug.test.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class EmptyServletResponse implements HttpServletResponse {

	public void addCookie(Cookie cookie) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void addDateHeader(String name, long date) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void addHeader(String name, String value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void addIntHeader(String name, int value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean containsHeader(String name) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String encodeRedirectURL(String url) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String encodeRedirectUrl(String url) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String encodeURL(String url) {
		throw new RuntimeException("Not yet implemented!");
	}

	public String encodeUrl(String url) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void sendError(int sc) throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public void sendError(int sc, String msg) throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public void sendRedirect(String location) throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setDateHeader(String name, long date) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setHeader(String name, String value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setIntHeader(String name, int value) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setStatus(int sc) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setStatus(int sc, String sm) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void flushBuffer() throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public int getBufferSize() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getCharacterEncoding() {
		throw new RuntimeException("Not yet implemented!");
	}

	public String getContentType() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Locale getLocale() {
		throw new RuntimeException("Not yet implemented!");
	}

	public ServletOutputStream getOutputStream() throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public PrintWriter getWriter() throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

	public boolean isCommitted() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void reset() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void resetBuffer() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setBufferSize(int size) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setCharacterEncoding(String charset) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setContentLength(int len) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setContentType(String type) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void setLocale(Locale loc) {
		throw new RuntimeException("Not yet implemented!");
	}

}
