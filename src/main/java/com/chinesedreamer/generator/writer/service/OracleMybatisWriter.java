package com.chinesedreamer.generator.writer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.chinesedreamer.generator.config.Job;
import com.chinesedreamer.generator.db.entity.TableColumn;

public class OracleMybatisWriter implements MyBatisWriter{

	public void writeModel(Job job, List<TableColumn> columns) {
		//1. 读取模板
		//2. 替换内容
		//2.1 替换dao
		//2.2 替换model
		//2.3 替换columns
	}

	public void writeDao(Job job, List<TableColumn> columns) {
		// TODO Auto-generated method stub
		
	}

	public void writeMapper(Job job, List<TableColumn> columns) {
		// TODO Auto-generated method stub
		
	}

	private static String getModelTemplate(Job job, List<TableColumn> columns, String encoding) {
		VelocityEngine ve = new VelocityEngine();
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("userName", "用户");
		model.put("validationUrl", "访问连接");
		return VelocityEngineUtils.mergeTemplateIntoString(ve, "F:\\projects\\GitHub\\generator\\src\\main\\resources\\register-email-velocity-template.vm",encoding, model);
	}
	
	public static void main(String args[]) {
		String text = getModelTemplate(null, null, "utf-8");
		System.out.println(text);
	}
}
