package com.yong.Common;

public class MsgCode {
	public static final String CONF_PATH_LOG4J = "CONF.PATH_LOG4J";
	public static final String CONF_PATH_MYBATIS = "CONF.PATH_MYBATIS";
	
	public static final String CONF_SSH_USER = "SSH.USER";
	public static final String CONF_SSH_HOST = "SSH.HOST";
	public static final String CONF_SSH_PORT = "SSH.PORT";
	public static final String CONF_SSH_KEY_PATH = "SSH.PATH_KEY";
	public static final String CONF_SSH_TUNNEL_PORT_START = "SSH.TUNNEL_START_PORT";
	public static final String CONF_SSH_SQL_HOST = "SSH.SQL_HOST";
	public static final String CONF_SSH_SQL_PORT = "SSH.SQL_PORT";
	
}

//[Configuration]
//CONF.PATH_LOG4J = ./conf/log4j.properties
//CONF.PATH_MYBATIS = ./conf/mybatis.xml
//
//[SSH]
//SSH.USER = 
//SSH.HOST = 
//SSH.PORT = 
//SSH.PATH_KEY = ./conf/private.ppk
//SSH.TUNNEL_START_PORT = 3306 
//SSH.SQL_HOST = 127.0.0.1
//SSH.SQL_PORT = 3306