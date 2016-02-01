package com.chinesedreamer.generator.config;

/**
 * 配置类
 * @author Paris
 *
 */
public abstract class AbstractConfiguration {
	private String id;
	private String path;//配置文件路径
	public String getId() {
		return id;
	}
	public String getPath() {
		return path;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
}
