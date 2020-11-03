package com.yong.Runner;

import org.apache.log4j.Logger;

import com.yong.Watch.Watcher;

public class Runner {

	private static Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		
		logger.info("[ Watch DB Start ]");
		Configuration.initialize(args);
		
		Watcher watcher = new Watcher();
		watcher.execute();
		
		logger.info("[ Watch DB End ]");
	}

}
