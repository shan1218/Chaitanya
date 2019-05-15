package com.TMG.apiTest.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
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
