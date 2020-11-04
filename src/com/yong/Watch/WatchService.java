package com.yong.Watch;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.yong.Connector.SSHConnector;

public class WatchService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private SSHConnector ssh;
	private WatchDAO dao;
	
	public WatchService () {
		// Connect SSH
		logger.info("Connecting to Server via SSH ... ");
		ssh = new SSHConnector();
		
		// Connect DB
		logger.info("Connecting to DB ... ");
		dao = new WatchDAO();
	}
	
	public void test() {
		ArrayList<LinkedHashMap> result = dao.Test();
		logger.info("Test Result >> \n" + result.toString());
	}
	
	public void finalize() {
		ssh.disconnect();
	}
	
}
