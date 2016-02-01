package com.chinesedreamer.generator.config;

public class Job {
	private String table;
	private String scope;
	private String javaSourcePath;
	private String xmlSourcePath;
	
	private String jobType;

	public String getTable() {
		return table;
	}

	public String getScope() {
		return scope;
	}

	public String getJavaSourcePath() {
		return javaSourcePath;
	}

	public String getXmlSourcePath() {
		return xmlSourcePath;
	}

	public String getJobType() {
		return jobType;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setJavaSourcePath(String javaSourcePath) {
		this.javaSourcePath = javaSourcePath;
	}

	public void setXmlSourcePath(String xmlSourcePath) {
		this.xmlSourcePath = xmlSourcePath;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	
}
