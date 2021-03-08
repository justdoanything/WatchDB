package com.yong.Common;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public abstract class BatchExecutor implements Runnable
{
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 파일의 크기를 감시하는 주기(millisecond)
	 * - context.properties 에서 set
	 */
	long fileDelay = 5000;

	/** 
	 * 지정된 시간마다 수행됨
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
//		logger.info(String.format("start.. DataFileTask(path=%s, name=*%s, delay=%dms)", filePath, fileName, fileDelay, charset));
		
		new Timer().schedule(new TimerTask() {
			public void run() {
				try {		

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