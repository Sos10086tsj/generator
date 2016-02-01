package com.chinesedreamer.generator.job.service;

import java.util.List;

import com.chinesedreamer.generator.config.Job;
import com.chinesedreamer.generator.db.entity.TableColumn;

/**
 * 根据jobType处理job任务
 * @author Paris
 *
 */
public interface JobTypeCrudHandler extends JobTypeHandler{
	public void handler(Job job, List<TableColumn> columns);
}
