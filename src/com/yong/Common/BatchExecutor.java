package com.yong.Common;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yong.Runner.Configuration;
import com.yong.Watch.Watcher;

public abstract class BatchExecutor implements Runnable
{
	Logger logger = Logger.getLogger(this.getClass());
	
	/** 
	 * 지정된 시간마다 수행됨
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int fileDelay = 50000;
		try { fileDelay = Configuration.getInt(MsgCode.CONF_EXE_INTERVAL); } catch (Exception ex) {	ex.printStackTrace(); }
		
		logger.info(String.format("Batch start →→→ delay=%dms", fileDelay));
		
		new Timer().schedule(new TimerTask() {	
			
			public void run() {
				try {
					Watcher watcher = new Watcher();
					watcher.execute();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {

				}
			}
		}, 1000, fileDelay);
	}	
}