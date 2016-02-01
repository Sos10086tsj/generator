package com.chinesedreamer.generator.mybatis.db.config;

import java.util.List;

public class Configuration extends AbstractConfiguration{
	private DataSource dataSource;
	private List<Job> jobs;
	public DataSource getDataSource() {
		return dataSource;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
}