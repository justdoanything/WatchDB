package com.yong.Connector;

import java.net.Socket;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.yong.Common.MsgCode;
import com.yong.Runner.Configuration;

public class SSHConnector {

	private Logger logger = Logger.getLogger(this.getClass());
	private Session session;
	
	public SSHConnector() throws Exception {
		JSch jsch = new JSch();

		try {
			int SSH_TUNNEL_PORT = Configuration.getInt(MsgCode.CONF_SSH_TUNNEL_PORT_START);
			logger.debug("Tunnel port starting to find : " + SSH_TUNNEL_PORT);
			while (this.checkPort(SSH_TUNNEL_PORT)) {
				SSH_TUNNEL_PORT++;
			}

			int SSH_SQL_PORT = Configuration.getInt(MsgCode.CONF_SSH_SQL_PORT);

			session = jsch.getSession(Configuration.getString(MsgCode.CONF_SSH_USER),
									  Configuration.getString(MsgCode.CONF_SSH_HOST), 
									  Configuration.getInt(MsgCode.CONF_SSH_PORT));

			jsch.addIdentity(Configuration.getString(MsgCode.CONF_SSH_KEY_PATH));
			
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("ConnectionAttempts", "3");
			session.setConfig(config);
			session.connect();

			logger.info("[Success] SSH Session Connect !");
			session.setPortForwardingL(SSH_TUNNEL_PORT, 
										Configuration.getString(MsgCode.CONF_SSH_SQL_HOST), 
										SSH_SQL_PORT);
			Configuration.updateValue("db.port", SSH_TUNNEL_PORT, "Update : db.port");
			logger.info("Port Forward : " + SSH_TUNNEL_PORT + " ¡æ " + SSH_SQL_PORT);
		} catch (Exception e) {
			logger.info("[Exception] SSH Session Connect");
			this.disconnect();
			throw new Exception(e);
		}
	}
	
	private boolean checkPort(int port) {
		logger.debug("Check if Port is available. : " + port);
		Socket socket = null;
		try {
			socket = new Socket("localhost", port);
			logger.debug(port + " is not available.");
			return true;
		}catch (Exception e) {
			logger.debug(port + " is available.");
			return false;
		}finally {
			try { if(socket != null) socket.close(); } catch (Exception e) { logger.error("Socket Close Error"); logger.error("Exception : ", e);}
		}	
	}
	
	public void disconnect() {
		try {
			if(session != null) {
				session.disconnect();
				logger.info("SSH Close Success!");
			}
		}catch (Exception e) {
			logger.info("SSH Close Fail!");
			logger.error("Exception : ", e);
		}
	}
}
