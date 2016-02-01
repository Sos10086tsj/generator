package com.chinesedreamer.generator.mybatis.writer.service;

import java.sql.Connection;

import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;

public interface JobExecutorService {
	public void execute(DataSource dataSource, Connection conn, Job job);
}
