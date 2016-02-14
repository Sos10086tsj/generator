package com.chinesedreamer.generator.mybatis.template.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.chinesedreamer.generator.common.constant.EncodingConstant;
import com.chinesedreamer.generator.mybatis.template.entity.Dao;
import com.chinesedreamer.generator.mybatis.template.entity.Mapper;
import com.chinesedreamer.generator.mybatis.template.entity.Model;
import com.chinesedreamer.generator.mybatis.template.service.MyBatisTemplateService;

@Service
public class MyBatisTemplateServiceImpl implements MyBatisTemplateService{
	
	@Autowired
	private VelocityEngine ve;

	@Override
	public String getMapperTemplate(Mapper mapper) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mapper", mapper);
		return VelocityEngineUtils.mergeTemplateIntoString(
				this.ve, 
				"velocity/mybatis/mapper-template.vm", 
				EncodingConstant.ENCODE_UTF_8, model);
	}

	@Override
	public String getDaoTemplate(Dao dao) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dao", dao);
		return VelocityEngineUtils.mergeTemplateIntoString(
				this.ve, 
				"velocity/mybatis/dao-template.vm", 
				EncodingConstant.ENCODE_UTF_8, model);
	}

	@Override
	public String getModelTemplate(Model model) {
		Map<String, Object> vmModel = new HashMap<String, Object>();
		vmModel.put("model", model);
		return VelocityEngineUtils.mergeTemplateIntoString(
				this.ve, 
				"velocity/mybatis/model-template.vm", 
				EncodingConstant.ENCODE_UTF_8, vmModel);
	}

}
