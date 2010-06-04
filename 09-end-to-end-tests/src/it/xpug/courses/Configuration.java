package it.xpug.courses;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration extends Properties {

	public Configuration() throws IOException {
		InputStream stream = getClass().getResourceAsStream("/courses.properties");
		if (null == stream) {
			throw new RuntimeException("cant't find courses.properties");
		}
		load(stream);
	}

}
