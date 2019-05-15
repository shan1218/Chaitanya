package com.TMG.apiTest.helper;

import com.TMG.apiTest.vo.Environment;

public class StepDefinition {

    private Session session;

    private Environment environment;

    public StepDefinition() {
        session = Session.getBrowserInstance();
        environment = session.getEnvironment();
    }

    public Application getApplication() {
        return session.getApplication();
    }

    public Session getSession() {
        return session;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
