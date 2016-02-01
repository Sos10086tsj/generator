package com.chinesedreamer.generator.mybatis.template.service;

import com.chinesedreamer.generator.mybatis.template.entity.Dao;
import com.chinesedreamer.generator.mybatis.template.entity.Mapper;
import com.chinesedreamer.generator.mybatis.template.entity.Model;

public interface MyBatisTemplateService {
	/**
	 * 获取xml模板
	 * @param mapper
	 * @return
	 */
	public String getMapperTemplate(Mapper mapper);
	/**
	 * 获取dao模板
	 * @param mapper
	 * @return
	 */
	public String getDaoTemplate(Dao dao);
	/**
	 * 获取model模板
	 * @param mapper
	 * @return
	 */
	public String getModelTemplate(Model model);
}
