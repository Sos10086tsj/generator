package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.generator.common.constant.EncodingConstant;
import com.chinesedreamer.generator.mybatis.db.DatabaseMetaDataHelper;
import com.chinesedreamer.generator.mybatis.db.FormatHelper;
import com.chinesedreamer.generator.mybatis.db.TableColumn;
import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.template.entity.Dao;
import com.chinesedreamer.generator.mybatis.template.entity.Mapper;
import com.chinesedreamer.generator.mybatis.template.entity.Model;
import com.chinesedreamer.generator.mybatis.template.entity.ModelProperty;
import com.chinesedreamer.generator.mybatis.template.service.MyBatisTemplateService;
import com.chinesedreamer.generator.mybatis.writer.service.JobExecutorService;
import com.chinesedreamer.generator.util.FileUtil;
import com.chinesedreamer.generator.util.StringUtil;

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
			//根据columns获取pks列类型
			for (TableColumn pk : pks) {
				for (TableColumn column : columns) {
					if (column.getName().equals(pk.getName())) {
						pk.setType(column.getType());
					}
				}
			}
			
			String modelName = FormatHelper.getModelName(job.getTable());
			//1. 生成mapper
			FileUtil.write2File(this.myBatisTemplateService.getMapperTemplate(this.generateMapper(job, modelName, pks, columns)), 
					job.getCrudConfig().getMapperPath() + modelName + "Dao.xml", 
					EncodingConstant.ENCODE_UTF_8);
			//2. 生成dao
			FileUtil.write2File(this.myBatisTemplateService.getDaoTemplate(this.generateDao(job, modelName, pks, columns)), 
					job.getCrudConfig().getDaoPath() + modelName + "Dao.java", 
					EncodingConstant.ENCODE_UTF_8);
			//3. 生成model
			FileUtil.write2File(this.myBatisTemplateService.getModelTemplate(this.generateModel(job, modelName, pks, columns)), 
					job.getCrudConfig().getModelPath() + modelName + ".java", 
					EncodingConstant.ENCODE_UTF_8);
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
		mapper.setDaoClass(job.getCrudConfig().getDaoPackage() + "." + modelName + "Dao");
		//refId
		String uncapitalizeModelName = StringUtils.uncapitalize(modelName);
		mapper.setRefId(uncapitalizeModelName + "Columns");
		//columns
		StringBuffer columnsBuffer = new StringBuffer();
		StringBuffer resultMapBuffer = new StringBuffer();
		StringBuffer insertValuesBuffer = new StringBuffer();
		StringBuffer updateValuesBuffer = new StringBuffer();
		for (TableColumn tableColumn : columns) {
			columnsBuffer.append(tableColumn.getName()).append(",");
			resultMapBuffer.append(FormatHelper.getMapperResultMapRow(tableColumn));
			insertValuesBuffer.append(FormatHelper.getMapperInsertRow(tableColumn));
			updateValuesBuffer.append(FormatHelper.getMapperUpdateRow(tableColumn));
		}
		mapper.setColumns(StringUtil.removeLastCharacter(columnsBuffer.toString(), ","));
		mapper.setResultMapName(uncapitalizeModelName + "ResultMap");
		mapper.setModelClass(job.getCrudConfig().getModelPackage() + "." + modelName);
		//resultMap
		mapper.setResultMap(resultMapBuffer.toString());
		//tableName
		mapper.setTableName(job.getTable());
		//insertValues
		mapper.setInsertValues(StringUtil.removeLastCharacter(insertValuesBuffer.toString(), ","));
		//updateValues
		mapper.setUpdateValues(updateValuesBuffer.toString());
		//pkCondition
		mapper.setPkCondition(FormatHelper.getMapperPkCondition(pks) );
		return mapper;
	}
	
	/**
	 * 生成dao配置
	 * @param job
	 * @param modelName
	 * @param pks
	 * @param columns
	 * @return
	 */
	private Dao generateDao(Job job, String modelName, List<TableColumn> pks, List<TableColumn> columns) {
		Dao dao = new Dao();
		dao.setDaoName(modelName + "Dao");
		dao.setDaoPackage(job.getCrudConfig().getDaoPackage());
		dao.setModelName(modelName);
		dao.setModelPackage(job.getCrudConfig().getModelPackage() + "." + modelName);
		return dao;
	}
	
	/**
	 * 生成model配置
	 * @param job
	 * @param modelName
	 * @param pks
	 * @param columns
	 * @return
	 */
	private Model generateModel(Job job, String modelName, List<TableColumn> pks, List<TableColumn> columns) {
		Model model = new Model();
		model.setModelName(modelName);
		model.setModelPackage(job.getCrudConfig().getModelPackage());
		Set<String> packages = new HashSet<String>();
		List<ModelProperty> properties = new ArrayList<ModelProperty>();
		for (TableColumn column : columns) {
			FormatHelper.addProperty(column, packages, properties);
		}
		model.setPackages(packages);
		model.setProperties(properties);
		return model;
	}
}
