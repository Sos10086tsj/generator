package com.chinesedreamer.generator.mybatis.template.entity;

import java.util.List;
import java.util.Set;

public class Model {
	private String modelName;
	private String modelPackage;
	private Set<String> packages;
	private List<ModelProperty> properties;
	public String getModelName() {
		return modelName;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public Set<String> getPackages() {
		return packages;
	}
	public List<ModelProperty> getProperties() {
		return properties;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	public void setPackages(Set<String> packages) {
		this.packages = packages;
	}
	public void setProperties(List<ModelProperty> properties) {
		this.properties = properties;
	}
	
}
