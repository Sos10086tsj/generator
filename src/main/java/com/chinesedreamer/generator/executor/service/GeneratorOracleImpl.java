package com.chinesedreamer.generator.executor.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chinesedreamer.generator.config.Configuration;
import com.chinesedreamer.generator.config.Job;
import com.chinesedreamer.generator.job.JobFactory;

public class GeneratorOracleImpl extends AbstractGenerator implements Generator{

	public GeneratorOracleImpl(Configuration configuration) {
		super(configuration);
	}

	public void execute() {
		
		Connection conn = null;
		try {
			conn = this.getConnection();
			
			//逐个执行任务
			for (Job job : this.configuration.getJobs()) {
				JobFactory.getOracleJobRunner().runJob(this.configuration, conn, job);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	private Connection getConnection() throws Exception{
		Class.forName(this.configuration.getDataSource().getDriverName());
		return DriverManager.getConnection(
				this.configuration.getDataSource().getUrl(), 
				this.configuration.getDataSource().getUsername(),
				this.configuration.getDataSource().getPassword()
				);
	}
}
