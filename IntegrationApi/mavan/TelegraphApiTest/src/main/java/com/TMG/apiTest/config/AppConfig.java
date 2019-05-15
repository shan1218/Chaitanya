package com.TMG.apiTest.config;

import java.io.File;

public class AppConfig {

    public static final String PACKAGE_NAME = "com/TMG/apiTest";
    //public static final String PACKAGE_NAME = "com"+ File.separator+"TMG"+ File.separator+"apiTest";

    public static final String PROPERTIES_SUFFIX = ".properties";

    public static final String PROPERTIES_PATH = "/" + PACKAGE_NAME + "/properties/";
    //public static final String PROPERTIES_PATH = File.separator + PACKAGE_NAME + File.separator + "properties"+File.separator;

    public static final String PROPERTY_FILE_EXTENSION = ".yaml";

    public static final String ENVIRONMENT_FILE_NAME = "url";

    public static final String BROWSER_VALUE_DEFAULT = "default";

    public static String ENVIRONMENT_KEY = "environment";

    public static final String LOCAL = "local";
}
