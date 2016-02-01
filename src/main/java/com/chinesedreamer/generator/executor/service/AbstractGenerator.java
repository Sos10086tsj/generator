package com.chinesedreamer.generator.executor.service;

import com.chinesedreamer.generator.config.Configuration;

public class AbstractGenerator {
	protected Configuration configuration;
	public AbstractGenerator(Configuration configuration){
		this.configuration = configuration;
	}

}
