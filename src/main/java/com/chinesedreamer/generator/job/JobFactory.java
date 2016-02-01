package com.chinesedreamer.generator.job;

import com.chinesedreamer.generator.job.service.JobRunner;
import com.chinesedreamer.generator.job.service.OracleJobRunner;

public class JobFactory {
	private static JobRunner oracleJobRunner = null;
	
	private static void initOracleJobRunner(){
		if (null == oracleJobRunner) {
			oracleJobRunner = new OracleJobRunner();
		}
	}
	
	public static JobRunner getOracleJobRunner() {
		initOracleJobRunner();
		return oracleJobRunner;
	}
}
