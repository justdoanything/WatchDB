package com.yong.Watch;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.yong.Common.MsgCode;
import com.yong.Connector.SSHConnector;
import com.yong.Runner.Configuration;

public class WatchService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private SSHConnector ssh = null;
	private WatchDAO dao = null;
	
	public WatchService () throws Exception {
		// Connect SSH
		if(Configuration.getBoolean(MsgCode.CONF_SSH_USE)){
			logger.info("Connecting to Server via SSH ... ");
			ssh = new SSHConnector();
		}
		
		// Connect DB
		logger.info("Connecting to DB ... ");
		dao = new WatchDAO();
	}
	
	public void test() throws Exception {
		ArrayList<LinkedHashMap> result = dao.Test();
		logger.info("Test Result >> \n" + result.toString());
	}
	
	public void finalize() {
		ssh.disconnect();
	}
	
}
