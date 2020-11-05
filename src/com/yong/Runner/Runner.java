package com.yong.Runner;

import org.apache.log4j.Logger;

import com.yong.Watch.Watcher;

public class Runner {

	private static Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) throws InterruptedException {
		
		try{
			Configuration.initialize(args);
			
			Watcher watcher = new Watcher();
			watcher.execute();
		}catch (Exception e) {
			logger.error("Exception : ", e);
		}
		
		logger.info("[ Watch App End ]");
	}

}
