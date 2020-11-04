package com.yong.Connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.yong.Common.MsgCode;
import com.yong.Runner.Configuration;

public class MySqlConnector {
	Logger logger = Logger.getLogger(this.getClass());
	private SqlSessionFactory ssf;

	public MySqlConnector() {
		try {
			//set mybatis configuration
			String MYBATIS_CONFIG_FILE_PATH = Configuration.getString(MsgCode.CONF_PATH_MYBATIS); 
			File file = new File(MYBATIS_CONFIG_FILE_PATH);
			logger.debug("[Success] Load myBatis Properties file ¡æ " + MYBATIS_CONFIG_FILE_PATH);
			
			InputStream iss = new FileInputStream(file);
			ssf = new SqlSessionFactoryBuilder().build(iss);
			logger.debug("SqlSessionFactory build Success");
			logger.info("[Success] Mysql Connect Success!");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("SqlSessionFactory build Fail" + e.getMessage());
			logger.info("[Exception] Mysql Connect Fail!");
		}
	}
	
	public SqlSession getSession() {
		SqlSession session = null;
		try {
			session = ssf.openSession();
			session.getConnection();
			logger.debug("Session Open");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("FAIL Session Open" + e.getMessage());
		}
		return session;
	}
	
	public SqlSession getSession(boolean autoCommit) {
		SqlSession session = null;
		try {
			session = ssf.openSession(autoCommit);
			session.getConnection();
			logger.debug("Session Open");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("FAIL Session Open" + e.getMessage());
		}
		return session;
	}
	
	public void close(SqlSession session) {
		if(session != null) {
			session.close();
			logger.debug("Session Close");
		}
	}
	
	public void commit(SqlSession session) {
		if(session != null) {
			session.commit();
			logger.debug("Session Commit");
		}
	}
	
	public Object selectList(String id, Object param) {
		SqlSession session = getSession();
		Object reslut = null;
		
		try {
			reslut = session.selectList(id, param);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("FAIL SelectList" + e.getMessage());
		}finally {
			close(session);
		}
		return reslut;
	}
	
	public Object selectOne(String id, Object param) {
		SqlSession session = getSession();
		Object result = null;

		try {
			result = session.selectOne(id, param);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error("FAIL SelectOne" + e.getMessage());
		}
		finally {
			close(session);
		}

		return result;
	}
	
	public Object insert(String id, Object param) {
		SqlSession session = getSession(false);
		int result = 0;
		
		try {
			result = session.insert(id, param);
			
			if(result > 0) {
				session.commit();
			}
			else {
				session.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("FAIL Insert" + e.getMessage());
		}finally {
			close(session);
		}
		return result;
	}
	
	public int update(String id, Object param) {
		SqlSession session = this.getSession(false);
		int result = -1;		
		
		try {			
			result = session.update(id, param);
			
			if(result > 0) {
				session.commit();
			}
			else {
				session.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error("FAIL Update" + e.getMessage());
		}
		finally {
			this.close(session);
		}
		
		return result;
	}
}
