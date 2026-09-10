package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop = new Properties();
    private static String path;
    private static String env;
    
    private ConfigManager(){
    	
    }

    static {
        env = System.getProperty("env", "qa");

        path = switch (env.toLowerCase()) {
            case "dev" -> "Config/Config.dev.properties";
            case "qa" -> "Config/Config.QA.properties";
            default -> "Config/Config.QA.properties";
        };
        

        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new RuntimeException("Property file '" + path + "' not found in classpath");
            }
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load property file '" + path + "'", e);
        }
    }

    public static String getProperty(String key) {
        String value = prop.getProperty(key);
        System.out.println(value);
        return value;
    }
}