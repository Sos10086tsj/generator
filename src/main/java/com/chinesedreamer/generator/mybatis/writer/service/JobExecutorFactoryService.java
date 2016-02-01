package com.chinesedreamer.generator.mybatis.writer.service;

import com.chinesedreamer.generator.mybatis.db.constant.JobScope;

public interface JobExecutorFactoryService {
	/**
	 * 获取对应的执行器
	 * @param jobScope
	 * @return
	 */
	public JobExecutorService getJobExecutorService(JobScope jobScope);
}
