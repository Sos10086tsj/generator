package com.chinesedreamer.generator.job.service;

import java.sql.Connection;

import com.chinesedreamer.generator.config.Configuration;
import com.chinesedreamer.generator.config.Job;

public interface JobRunner {
	public void runJob(Configuration configuration, Connection conn, Job job) throws Exception;
}
