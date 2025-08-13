package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import utility.Log;

public class ConfigReader {
    Properties prop;
    
    public ConfigReader() {
        prop = new Properties();
        try {
           // FileInputStream fis = new FileInputStream("/src/test/resources/Config/config.properties");
            InputStream fis = getClass()
            	    .getClassLoader()
            	    .getResourceAsStream("Config/config.properties");

            	if (fis != null) {
            	    prop.load(fis);
            	} else {
            	    throw new FileNotFoundException("Property file not found in classpath");
            	}

            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties file");
            e.printStackTrace();
        }
    }


    public String getBrowser() {
        String browser = prop.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            Log.warn("[WARNING] 'browser' not found in config. Defaulting to Chrome." + getBrowser());
            
        }
        return prop.getProperty("browser");

    }


    public String getUrl() {
    	String url = prop.getProperty("url");
    	if(url==null|| url.isEmpty()) {
    		System.out.println("Url not found");
            
    	}
        return prop.getProperty("url");
    }
}
