package com.chinesedreamer.generator.mybatis.template.entity;

public class ModelProperty {
	private String property;
	private String getterName;
	private String setterName;
	private String type;
	public String getProperty() {
		return property;
	}
	public String getGetterName() {
		return getterName;
	}
	public String getSetterName() {
		return setterName;
	}
	public String getType() {
		return type;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}
	public void setSetterName(String setterName) {
		this.setterName = setterName;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
