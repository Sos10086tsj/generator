package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.generator.mybatis.db.config.Configuration;
import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorFactoryService;
import com.chinesedreamer.generator.mybatis.writer.service.JobRunnerService;

@Service("jobRunnerOracleService")
public class JobRunnerOracleServiceImpl implements JobRunnerService {
	private Logger logger = LoggerFactory.getLogger(JobRunnerOracleServiceImpl.class);
	
	@Resource
	private JobExecutorFactoryService jobExecutorFactoryService;

	@Override
	public void runJob(Configuration configuration){
		Connection conn = null;
		try {
			DataSource dataSource = configuration.getDataSource();
			Class.forName(dataSource.getDriverName());
			conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
			
			for (Job job : configuration.getJobs()) {
				this.jobExecutorFactoryService.getJobExecutorService(job.getScope()).execute(dataSource, conn, job);
			}
			
		} catch (Exception e) {
			this.logger.error("{}",e);
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
