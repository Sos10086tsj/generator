package com.chinesedreamer.generator.mybatis.writer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.generator.mybatis.db.constant.Driver;
import com.chinesedreamer.generator.mybatis.writer.service.JobFactoryService;
import com.chinesedreamer.generator.mybatis.writer.service.JobRunnerService;

@Service
public class JobFactoryServiceImpl implements JobFactoryService{

	@Resource(name = "jobRunnerOracleService")
	private JobRunnerService oracleJobRunnerService;
	
	@Override
	public JobRunnerService getJobRunner(Driver driver) {
		JobRunnerService jobRunnerService = null;
		switch (driver) {
		case ORACLE:
			jobRunnerService = oracleJobRunnerService;
			break;
		default:
			break;
		}
		return jobRunnerService;
	}

}
