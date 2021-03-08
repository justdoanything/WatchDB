package com.yong.Common;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public abstract class BatchExecutor implements Runnable
{
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * ������ ũ�⸦ �����ϴ� �ֱ�(millisecond)
	 * - context.properties ���� set
	 */
	long fileDelay = 5000;

	/** 
	 * ������ �ð����� �����
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