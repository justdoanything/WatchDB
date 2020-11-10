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

	public MySqlConnector() throws Exception {
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
			logger.info("[Exception] Mysql Connect Fail!");
			throw new Exception(e);
		}
	}
	
	public SqlSession getSession() throws Exception {
		SqlSession session = null;
		try {
			logger.debug("Getting Mysql Session ...");
			session = ssf.openSession();
			session.getConnection();
			logger.debug("Session Open");
		}catch (Exception e) {
			logger.error("FAIL Session Open" + e.getMessage());
			throw new Exception(e);
		}
		return session;
	}
	
	public SqlSession getSession(boolean autoCommit) throws Exception {
		SqlSession session = null;
		try {
			session = ssf.openSession(autoCommit);
			session.getConnection();
			logger.debug("Session Open");
		}catch (Exception e) {
			logger.error("FAIL Session Open" + e.getMessage());
			throw new Exception(e);
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
	
	public Object selectList(String id, Object param) throws Exception {
		SqlSession session = null;
		Object reslut = null;
		try {
			session = getSession();
			reslut = session.selectList(id, param);
		}catch (Exception e) {
			logger.error("FAIL SelectList" + e.getMessage());
			throw new Exception(e);
		}finally {
			close(session);
		}
		return reslut;
	}
	
	public Object selectOne(String id, Object param) throws Exception {
		SqlSession session = null;
		Object result = null;

		try {
			session = getSession();
			result = session.selectOne(id, param);
		}
		catch(Exception e) {
			logger.error("FAIL SelectOne" + e.getMessage());
			throw new Exception(e);
		}
		finally {
			close(session);
		}

		return result;
	}
	
	public Object insert(String id, Object param) throws Exception {
		SqlSession session = null;
		int result = 0;
		
		try {
			session = getSession(false);
			result = session.insert(id, param);
			
			if(result > 0) {
				session.commit();
			}
			else {
				session.rollback();
			}
		}catch (Exception e) {
			logger.error("FAIL Insert" + e.getMessage());
			throw new Exception(e);
		}finally {
			close(session);
		}
		return result;
	}
	
	public int update(String id, Object param) throws Exception {
		SqlSession session = null;
		int result = -1;		
		
		try {			
			session = this.getSession(false);
			result = session.update(id, param);
			
			if(result > 0) {
				session.commit();
			}
			else {
				session.rollback();
			}
		}
		catch(Exception e) {
			logger.error("FAIL Update" + e.getMessage());
			throw new Exception(e);
		}
		finally {
			this.close(session);
		}
		
		return result;
	}
}
