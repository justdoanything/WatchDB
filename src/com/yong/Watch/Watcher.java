package com.yong.Watch;

import org.apache.log4j.Logger;

public class Watcher {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Watcher() {
		
	}
	
	public void execute() {
		WatchService watchSvc = new WatchService();
		try {
			logger.debug("test Service Start");
			watchSvc.test();
		}catch (Exception e) {
		
		}finally {
			watchSvc.finalize();
		}
	}
}
