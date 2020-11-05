package com.yong.Watch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.yong.Connector.MySqlConnector;

@SuppressWarnings({"rawtypes","unchecked"})
public class WatchDAO {
	Logger logger = Logger.getLogger(this.getClass());
	
	private MySqlConnector mysql;
	private String namespace = "com.yong.WatchMapper.";
	private HashMap<String, String> param;
	
	public WatchDAO() throws Exception {
		// Build SqlSession
		mysql = new MySqlConnector();
		logger.debug("Sql Namespace : " + this.namespace);
		
		param = new HashMap<String, String>();
	}
	
	public String getParam(String key) {
		logger.debug("Get Param : " + key + ", " + this.param.get(key));
		return this.param.get(key);
	}
	
	public void setParam(String key, String value) {
		logger.debug("Add Param : " + key + ", " + value);
		this.param.put(key, value);
	}
	
	public ArrayList<LinkedHashMap> Test() throws Exception {
		ArrayList<LinkedHashMap> result = 
				(ArrayList<LinkedHashMap>) mysql.selectList(namespace + "TEST", param);
		return result;
	}  
}
