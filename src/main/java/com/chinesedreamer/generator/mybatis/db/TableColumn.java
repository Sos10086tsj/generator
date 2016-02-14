package com.chinesedreamer.generator.mybatis.db;

public class TableColumn {
	private String name;
	private String type;//VARCHAR2(200 BYTE) 删除括号
	
	public TableColumn(String name,String type){
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		int index = type.indexOf("(");
		if (-1 != index) {
			return type.substring(0, index);
		}
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}

}
