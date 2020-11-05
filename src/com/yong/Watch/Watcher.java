package com.yong.Watch;

import org.apache.log4j.Logger;

import com.yong.Common.MsgCode;
import com.yong.Runner.Configuration;

public class Watcher {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Watcher() {
		
	}
	
	public void execute() throws Exception {
		WatchService watchSvc = null;
		try {
			watchSvc = new WatchService();
			logger.debug("test Service Start");
			watchSvc.test();
			logger.debug("test Service End");
		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			if(Configuration.getBoolean(MsgCode.CONF_SSH_USE))
				if (watchSvc != null) 
					watchSvc.finalize();
		}
	}
}
