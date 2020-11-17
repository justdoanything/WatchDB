package com.yong.Runner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.yong.Common.MsgCode;

public class Configuration {
	private static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties = new Properties();
	private static String propPath = "";

	public static void initialize(String[] args) throws Exception {
		try {
			// set application properties(context.properties)
			FileInputStream fis = new FileInputStream(args[0]);
			properties.load(fis);
			propPath = args[0];

			// set Log4j properties
			String LOG4J_CONFIG_FILE_PATH = Configuration.getString(MsgCode.CONF_PATH_LOG4J);
			PropertyConfigurator.configure(LOG4J_CONFIG_FILE_PATH);

			logger.info("[Success] Load App Properties File ¡æ " + args[0]);
			logger.info("[Success] Load Log4j Properties file ¡æ " + LOG4J_CONFIG_FILE_PATH);
		} catch (Exception e) {
			logger.info("[Exception] App Initialize (Load Proprties File)");
			throw new Exception(e);
		}
	}

	public static String getString(String key) {
		return properties.getProperty(key).trim();
	}

	public static boolean getBoolean(String key) {
		if (properties.getProperty(key).trim().equals("1"))
			return true;
		else
			return false;
	}

	public static int getInt(String key) throws Exception {
		try {
			return Integer.parseInt(properties.getProperty(key).trim());
		} catch (Exception e) {
			logger.info("[Exception] App Properties - Int Parse error");
			logger.error("Exception : ", e);
			throw new Exception(e);
		}
	}

	public static void updateValue(String key, String value, String comment) throws Exception {
		try {
			properties.setProperty(key, value);
			logger.info("Update properties : " + key + " : " + getString(key) + " ¡æ " + value);
			properties.store(new FileOutputStream(propPath), comment);
		} catch (Exception e) {
			logger.info("[Exception] App Properties - update Properties");
			logger.error("Exception : ", e);
			throw new Exception(e);
		}
	}

	public static void updateValue(String key, int value, String comment) throws Exception {
		try {
			properties.setProperty(key, "" + value);
			logger.info("Update properties : " + key + " : " + getString(key) + " ¡æ " + value);
			
			properties.store(new FileOutputStream(propPath), comment);
		} catch (Exception e) {
			logger.info("[Exception] App Properties - update Properties");
			logger.error("Exception : ", e);
			throw new Exception(e);
		}
	}
}
