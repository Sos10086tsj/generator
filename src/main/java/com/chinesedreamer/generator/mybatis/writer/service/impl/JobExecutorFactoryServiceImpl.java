package com.chinesedreamer.generator.mybatis.writer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.generator.mybatis.db.constant.JobScope;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorFactoryService;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorService;

@Service
public class JobExecutorFactoryServiceImpl implements JobExecutorFactoryService{
	
	@Resource(name = "jobExecutorCrudService")
	private JobExecutorService jobExecutorCrudService;

	@Override
	public JobExecutorService getJobExecutorService(JobScope jobScope) {
		JobExecutorService jobExecutorService = null;
		switch (jobScope) {
		case CRUD:
			jobExecutorService = jobExecutorCrudService;
			break;

		default:
			break;
		}
		return jobExecutorService;
	}

}
