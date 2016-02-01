package com.chinesedreamer.generator.mybatis.db.config;

public class CrudConfig {
	private String mapperPath;
	private String daoPath;
	private String modelPath;
	public String getMapperPath() {
		return mapperPath;
	}
	public String getDaoPath() {
		return daoPath;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setMapperPath(String mapperPath) {
		this.mapperPath = mapperPath;
	}
	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	
	
}
