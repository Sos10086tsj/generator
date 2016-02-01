package com.chinesedreamer.generator.mybatis.writer.service;

import com.chinesedreamer.generator.mybatis.db.constant.Driver;

public interface JobFactoryService {
	/**
	 * 获取对应的生成器
	 * @param driver
	 * @return
	 */
	public JobRunnerService getJobRunner(Driver driver);
}
