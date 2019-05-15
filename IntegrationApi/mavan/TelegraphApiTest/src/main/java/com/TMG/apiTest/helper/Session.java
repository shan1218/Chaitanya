package com.TMG.apiTest.helper;

import com.TMG.apiTest.vo.Environment;

import java.util.logging.Logger;

public class Session {

	private static final Logger LOGGER = Logger.getLogger(Session.class.getName());
	private static Session session;
	private Application application;
	private Environment environment;

	private Session() {
		environment = EnvironmentUrlHelper.getEnvironment();
		System.out.println("Environment Detail : "+environment.getEndpoints());
		application = new Application(environment);
		application.initialize();
	}

	public static synchronized Session getBrowserInstance() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}

	public Application getApplication() {
		return application;
	}

	public Environment getEnvironment() {
		return environment;
	}

}
