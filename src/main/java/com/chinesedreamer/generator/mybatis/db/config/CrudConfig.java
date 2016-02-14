package com.chinesedreamer.generator.mybatis.db.config;

public class CrudConfig {
	private String mapperPath;
	private String mapperPackage;
	private String daoPath;
	private String daoPackage;
	private String modelPath;
	private String modelPackage;
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
	public String getMapperPackage() {
		return mapperPackage;
	}
	public String getDaoPackage() {
		return daoPackage;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	
	
}
