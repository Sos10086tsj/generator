package com.chinesedreamer.generator.writer.service;

import java.util.List;

import com.chinesedreamer.generator.config.Job;
import com.chinesedreamer.generator.db.entity.TableColumn;

public interface MyBatisWriter {
	public void writeModel(Job job,List<TableColumn> columns);
	public void writeDao(Job job,List<TableColumn> columns);
	public void writeMapper(Job job,List<TableColumn> columns);
}
