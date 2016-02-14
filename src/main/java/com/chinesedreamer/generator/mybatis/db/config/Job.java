package com.chinesedreamer.generator.mybatis.db.config;

import com.chinesedreamer.generator.mybatis.db.constant.JobScope;

public class Job {
	private String table;
	private String modelPrefix;
	private JobScope scope;
	private CrudConfig crudConfig;
	public String getTable() {
		return table;
	}
	public JobScope getScope() {
		return scope;
	}
	public CrudConfig getCrudConfig() {
		return crudConfig;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setScope(JobScope scope) {
		this.scope = scope;
	}
	public void setCrudConfig(CrudConfig crudConfig) {
		this.crudConfig = crudConfig;
	}
	public String getModelPrefix() {
		return modelPrefix;
	}
	public void setModelPrefix(String modelPrefix) {
		this.modelPrefix = modelPrefix;
	}
	
	
}
