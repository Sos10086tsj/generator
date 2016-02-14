package com.chinesedreamer.generator.mybatis.template.entity;

public class Dao {
	public String daoName;
	public String daoPackage;
	public String modelName;
	public String modelPackage;
	
	public String getDaoName() {
		return daoName;
	}
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	public String getDaoPackage() {
		return daoPackage;
	}
	public String getModelName() {
		return modelName;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	
	
}
