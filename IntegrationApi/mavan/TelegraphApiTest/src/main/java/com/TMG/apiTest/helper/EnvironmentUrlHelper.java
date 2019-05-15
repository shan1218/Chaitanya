package com.TMG.apiTest.helper;

import com.TMG.apiTest.vo.Environment;
import com.TMG.apiTest.vo.EnvironmentList;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

import static com.TMG.apiTest.config.AppConfig.*;

public class EnvironmentUrlHelper {


	private static final Logger LOGGER = Logger.getLogger(EnvironmentUrlHelper.class.getName());

	public static Environment getEnvironment() {
		String environmentName = getEnvironmentName();
		System.out.println("Environment Name : "+environmentName);
		return getEnvironmentConfig(environmentName);
	}

	private static String getEnvironmentName () {
		String environmentKeyProperty = System.getProperty(ENVIRONMENT_KEY);
		return StringUtils.isNotBlank(environmentKeyProperty) ? environmentKeyProperty : LOCAL;
	}

	private static Environment getEnvironmentConfig (String environmentName) {
		Environment environment = null;

		EnvironmentList environmentList = TmgUtil.getTestData(ENVIRONMENT_FILE_NAME, EnvironmentList.class);
		for (Environment environmentLocal : environmentList.getEnvironmentList()) {
			if (environmentLocal.getName().equalsIgnoreCase(environmentName)) {
				environment = environmentLocal;
				break;
			}
		}
		return environment;
	}

}
