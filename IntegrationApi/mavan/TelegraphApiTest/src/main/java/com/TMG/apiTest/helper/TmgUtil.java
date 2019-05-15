package com.TMG.apiTest.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.logging.Logger;

import static com.TMG.apiTest.config.AppConfig.*;

public class TmgUtil {

	private static final Logger LOGGER = Logger.getLogger(TmgUtil.class.getName());

	public static PropertyReader loadPropertyFile(String fileName, String folder) {
		//return new PropertyReader(PROPERTIES_PATH + folder + File.separator + fileName + PROPERTIES_SUFFIX);
		return new PropertyReader(PROPERTIES_PATH + folder + "/" + fileName + PROPERTIES_SUFFIX);
	}
	public static <T> T getTestData(String fileName, Class<T> target) {
		return PropertyReader.loadTelegraphPojo(PROPERTIES_PATH + fileName + PROPERTY_FILE_EXTENSION, target);
	}

	public static String converyObjectToJsonString(Object souce){

		String writeValueAsString = "";
		if (null != souce) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				writeValueAsString = mapper.writeValueAsString(souce);
			} catch (JsonProcessingException e) {
				writeValueAsString = "";
			}
		}
		return writeValueAsString;
	}
}
