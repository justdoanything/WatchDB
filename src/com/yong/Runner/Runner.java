package com.yong.Runner;

import org.apache.log4j.Logger;

import com.yong.Common.BatchExecutor;

public class Runner {

	private static Logger logger = Logger.getLogger(Runner.class);
		
	public static void main(String[] args) throws InterruptedException {
		
		try{
			Configuration.initialize(args);
			
			// Run the program at regular intervals
			Runnable runnable = new BatchExecutor() {};
			runnable.run();
			
		}catch (Exception e) {
			logger.error("Exception : ", e);
		}
		
		logger.info("[ Watch App End ]");
	}

}
