package com.chinesedreamer.generator.job.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import com.chinesedreamer.generator.config.Configuration;
import com.chinesedreamer.generator.config.Job;
import com.chinesedreamer.generator.config.constant.JobType;
import com.chinesedreamer.generator.db.entity.TableColumn;
import com.chinesedreamer.generator.db.help.DatabaseMetaDataHelper;
import com.chinesedreamer.generator.writer.service.OracleMybatisWriter;

public class OracleJobRunner implements JobRunner{

	public void runJob(Configuration configuration,Connection conn, Job job) throws Exception {
		//1. 获取表所有字段
		
		DatabaseMetaData metaData = conn.getMetaData();
		List<TableColumn> columns = DatabaseMetaDataHelper.getTableColumns(metaData, 
				configuration.getDataSource().getUsername().toUpperCase(), 
				job.getTable().toUpperCase());
		
		//2. 处理job
		switch (JobType.getByType(job.getJobType())) {
		case CRUD:
			this.handleCrudJob(job, columns);
			break;

		default:
			break;
		}
	}
	
	private void handleCrudJob(Job job,List<TableColumn> columns) {
		OracleMybatisWriter mybatisWriter = new OracleMybatisWriter();
		//1. 创建Model文件
		mybatisWriter.writeModel(job, columns);
		//2. 创建Dao文件
		mybatisWriter.writeDao(job, columns);
		//3. 创建Mapper文件
		mybatisWriter.writeMapper(job, columns);
	}
}
