package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.chinesedreamer.generator.common.constant.EncodingConstant;
import com.chinesedreamer.generator.mybatis.db.DatabaseMetaDataHelper;
import com.chinesedreamer.generator.mybatis.db.FormatHelper;
import com.chinesedreamer.generator.mybatis.db.TableColumn;
import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.template.entity.Mapper;
import com.chinesedreamer.generator.mybatis.template.service.MyBatisTemplateService;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorService;
import com.chinesedreamer.generator.util.FileUtil;

@Service("jobExecutorCrudService")
public class JobExecutorCrudServiceImpl implements JobExecutorService{
	
	private Logger logger = LoggerFactory.getLogger(JobExecutorCrudServiceImpl.class);
	
	@Resource
	private MyBatisTemplateService myBatisTemplateService;

	@Override
	public void execute(DataSource dataSource, Connection conn, Job job) {
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			List<TableColumn> columns = DatabaseMetaDataHelper.getTableColumns(metaData,
					dataSource.getUsername().toUpperCase(), job.getTable().toUpperCase());
			List<TableColumn> pks = DatabaseMetaDataHelper.getTablePrimaryKey(metaData, dataSource.getUsername().toUpperCase(), job.getTable().toUpperCase());
			
			String modelName = FormatHelper.getModelName(job.getTable());
			//1. 生成mapper
			FileUtil.write2File(this.myBatisTemplateService.getMapperTemplate(this.generateMapper(job, modelName, pks, columns)), 
					job.getCrudConfig().getMapperPath() + modelName + "Dao.xml", 
					EncodingConstant.ENCODE_UTF_8);
			//2. 生成dao
			//3. 生成model
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
	}
	
	/**
	 * 生成mapper
	 * @param tableName
	 * @param columns
	 * @return
	 */
	private Mapper generateMapper(Job job, String modelName, List<TableColumn> pks, List<TableColumn> columns) {
		Mapper mapper = new Mapper();
		//dao类
		mapper.setDaoClass(FormatHelper.getDaoClass(job.getCrudConfig().getDaoPath(), modelName));
		//refId
		mapper.setRefId(StringUtils.uncapitalize(modelName) + "Columns");
		//columns
		StringBuffer columnsBuffer = new StringBuffer();
//		private String columns;
//		private String resultMapName;
//		private String modelClass;
//		private String resultMap;
//		private String tableName;
//		private String insertValues;
//		private String updateValues;
//		private String pkCondition;
		return mapper;
	}
}
