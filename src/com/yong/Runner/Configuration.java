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
			FileInputStream fis = new FileInputStream(args[0]);
			properties.load(fis);
			
			//set Log4j properties
			String LOG4J_CONFIG_FILE_PATH = Configuration.getString(MsgCode.CONF_PATH_LOG4J); 
			PropertyConfigurator.configure(LOG4J_CONFIG_FILE_PATH);
			
			logger.info("[Success] Load App Properties File �� " + args[0]);
			logger.info("[Success] Load Log4j Properties file �� " + LOG4J_CONFIG_FILE_PATH);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("[Exception] App Initialize (Load Proprties File)");
		}
	}
	
	public static String getString(String key) {
		return properties.getProperty(key).trim();
	}
	
	public static boolean getBoolean(String key) {
		if(properties.getProperty(key).trim().equals("1"))
			return true;
		else
			return false;
	}
	
	public static int getInt(String key) {
		try {			
			return Integer.parseInt(properties.getProperty(key).trim());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("[Exception] App Properties - Int Parse error");
		}
		return -1;
	}

}
