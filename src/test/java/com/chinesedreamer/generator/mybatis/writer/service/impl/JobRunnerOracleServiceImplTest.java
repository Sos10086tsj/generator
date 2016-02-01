package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.generator.base.SpringBaseTest;
import com.chinesedreamer.generator.mock.Mock;
import com.chinesedreamer.generator.mybatis.db.config.Configuration;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.db.constant.Driver;
import com.chinesedreamer.generator.mybatis.writer.service.JobFactoryService;

public class JobRunnerOracleServiceImplTest extends SpringBaseTest{
	@Resource 
	private JobFactoryService jobFactoryService; 
	@Test
	public void testRunJob() {
		Configuration configuration = new Configuration();
		configuration.setDataSource(Mock.mockDataSource());
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(Mock.mockCurdJob());
		configuration.setJobs(jobs);
		
		this.jobFactoryService.getJobRunner(Driver.ORACLE).runJob(configuration);
	}

}
