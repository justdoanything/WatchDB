package com.yong.Runner;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.yong.Common.MsgCode;

public class Configuration {
	private static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties = new Properties();
	
	public static void initialize(String[] args) {
		try {
			//set application properties(context.properties)
			logger.debug("LOAD Properties File : " + args[0]);
			FileInputStream fis = new FileInputStream(args[0]);
			properties.load(fis);
			
			//set Log4j properties 
			logger.debug("LOAD Log4j Properties file : " + Configuration.getString(MsgCode.CONF_PATH_LOG4J));
			PropertyConfigurator.configure(Configuration.getString(MsgCode.CONF_PATH_LOG4J));
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("Fail App Initialize");
		}
	}
	
	public static String getString(String key) {
		return properties.getProperty(key);
	}
	
	public static boolean getBoolean(String key) {
		if(properties.getProperty(key).equals("1"))
			return true;
		else
			return false;
	}
	
	public static int getInt(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

}
