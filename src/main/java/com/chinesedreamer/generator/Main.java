package com.chinesedreamer.generator;

import com.chinesedreamer.generator.executor.GeneratorFactory;
import com.chinesedreamer.generator.executor.service.Generator;

public class Main {
	public static void main(String args[]) {
		GeneratorFactory.init("classpath:mygenerator.xml");
		Generator generator = GeneratorFactory.getInstance();
		generator.execute();
		GeneratorFactory.destroy();
	}
}
