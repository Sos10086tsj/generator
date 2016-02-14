package com.chinesedreamer.generator.mock;

import com.chinesedreamer.generator.mybatis.db.config.CrudConfig;
import com.chinesedreamer.generator.mybatis.db.config.DataSource;
import com.chinesedreamer.generator.mybatis.db.config.Job;
import com.chinesedreamer.generator.mybatis.db.constant.JobScope;

public class Mock {
	public static DataSource mockDataSource() {
		DataSource ds = new DataSource();
		ds.setDriverName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@192.168.6.224:1521:orcl");
		ds.setUsername("creditcard_test1");
		ds.setPassword("Nbcr123456");
		return ds;
	}
	
	public static Job mockCurdJob() {
		Job job = new Job();
		job.setTable("SYS_USER");
		job.setScope(JobScope.CRUD);
		job.setCrudConfig(Mock.mockCrudConfig());
		return job;
	}
	
	public static CrudConfig mockCrudConfig() {
		CrudConfig crudConfig = new CrudConfig();
		crudConfig.setDaoPath("C:\\Users\\Paris\\Desktop\\test\\");
		crudConfig.setDaoPath("com.chinesedreamer.dao");
		crudConfig.setMapperPath("C:\\Users\\Paris\\Desktop\\test\\");
		crudConfig.setMapperPackage("com.chinesedreamer.dao");
		crudConfig.setModelPath("C:\\Users\\Paris\\Desktop\\test\\");
		crudConfig.setModelPackage("com.chinesedreamer.model");
		return crudConfig;
	}
}
