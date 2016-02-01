package com.chinesedreamer.generator.mybatis.db;

public class TableColumn {
	private String name;
	
	public TableColumn(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TableColumn [name=" + name + "]";
	}
	
	
}
