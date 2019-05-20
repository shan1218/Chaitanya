package com.TMG.apiTest.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {

	private final static Logger LOGGER = Logger.getLogger(PropertyReader.class.getName());

	private Properties properties = new Properties();

	public PropertyReader(String filepath) {
		loadProperties(filepath);
	}

	private void loadProperties(String filepath) {
		try {
			System.out.println("VO File Path : "+filepath);
			properties.load(PropertyReader.class.getResourceAsStream(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readProperty(String key) {
		return properties.getProperty(key);
	}

	public void setProperty(String key, String value, String filepath) {
		try {
			properties.setProperty(key, value);
			URL url = this.getClass().getResource(filepath);
			FileOutputStream out = new FileOutputStream(new File(new URI(url.toString())));
			properties.store(out, null);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static <T> T loadTelegraphPojo(String filePath, Class<T> target){

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			System.out.println("File Path : "+filePath);
			return mapper.readValue(PropertyReader.class.getResourceAsStream(filePath), target);
		} catch (Exception e) {
			LOGGER.info("Unable to read property file");
			e.printStackTrace();
		}
		return null;
	}
}
