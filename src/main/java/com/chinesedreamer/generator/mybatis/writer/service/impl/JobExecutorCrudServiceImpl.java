package com.chinesedreamer.generator.mybatis.writer.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

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
import com.chinesedreamer.generator.mybatis.template.entity.Mapper;
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
}
