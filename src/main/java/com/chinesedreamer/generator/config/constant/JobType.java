package com.chinesedreamer.generator.config.constant;

public enum JobType {
	CRUD;
	
	public static JobType getByType(String jobType) {
		for (JobType jt : JobType.values()) {
			if (jt.toString().equals(jobType)) {
				return jt;
			}
		}
		return null;
	}
}
