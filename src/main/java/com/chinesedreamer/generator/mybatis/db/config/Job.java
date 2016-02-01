package com.chinesedreamer.generator.mybatis.db.config;

import com.chinesedreamer.generator.mybatis.db.constant.JobScope;

public class Job {
	private String table;
	private JobScope scope;
	private String javaSourcePath;
	private String xmlSourcePath;
	
	public String getTable() {
		return table;
	}
	public JobScope getScope() {
		return scope;
	}
	public String getJavaSourcePath() {
		return javaSourcePath;
	}
	public String getXmlSourcePath() {
		return xmlSourcePath;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setScope(JobScope scope) {
		this.scope = scope;
	}
	public void setJavaSourcePath(String javaSourcePath) {
		this.javaSourcePath = javaSourcePath;
	}
	public void setXmlSourcePath(String xmlSourcePath) {
		this.xmlSourcePath = xmlSourcePath;
	}
	
	
}
