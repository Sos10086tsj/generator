package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.generator.mybatis.db.DatabaseMetaDataHelper;
import com.chinesedreamer.generator.mybatis.db.TableColumn;
import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorService;

@Service("jobExecutorCrudService")
public class JobExecutorCrudServiceImpl implements JobExecutorService{
	
	private Logger logger = LoggerFactory.getLogger(JobExecutorCrudServiceImpl.class);

	@Override
	public void execute(DataSource dataSource, Connection conn, Job job) {
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			List<TableColumn> columns = DatabaseMetaDataHelper.getTableColumns(metaData,
					dataSource.getUsername().toUpperCase(), job.getTable().toUpperCase());
			
			for (TableColumn tableColumn : columns) {
				System.out.println(tableColumn);
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
	}

}
