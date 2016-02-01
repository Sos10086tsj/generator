package com.chinesedreamer.generator.config;

public class DataSource {
	private String driverName;//驱动
	private String url;//连接url
	private String username;//用户名
	private String password;//密码
	private String testSql;//测试sql
	public String getDriverName() {
		return driverName;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getTestSql() {
		return testSql;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setTestSql(String testSql) {
		this.testSql = testSql;
	}
	
	
}
