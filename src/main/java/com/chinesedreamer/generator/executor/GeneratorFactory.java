package com.chinesedreamer.generator.executor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinesedreamer.generator.config.Configuration;
import com.chinesedreamer.generator.config.constant.DriverType;
import com.chinesedreamer.generator.executor.service.Generator;
import com.chinesedreamer.generator.executor.service.GeneratorMySqlImpl;
import com.chinesedreamer.generator.executor.service.GeneratorOracleImpl;

/**
 * 生成器工厂类，根据driver类型获取匹配的生成器
 * @author Paris
 *
 */
public class GeneratorFactory {
	
	private static Generator generator = null;
	private static ClassPathXmlApplicationContext applicationContext = null;
	
	/**
	 * 初始化生成器
	 */
	public static void init(String path){
		applicationContext = new ClassPathXmlApplicationContext(path);
		Configuration configuration = (Configuration)applicationContext.getBean("configuration");
		switch (getDriverType(configuration)) {
		case ORACLE:
			generator = new GeneratorOracleImpl(configuration);
			break;
		case MYSQL:
			generator = new GeneratorMySqlImpl(configuration);
			break;
		default:
			break;
		}
	}
	
	public static Generator getInstance() {
		return generator;
	}
	
	public static void destroy() {
		applicationContext.close();
	}
	
	private static DriverType getDriverType(Configuration configuration){
		String driverClassName = configuration.getDataSource().getDriverName();
		if (driverClassName.toLowerCase().startsWith("oracle")) {
			return DriverType.ORACLE;
		}else if (driverClassName.toLowerCase().startsWith("mysql")) {
			return DriverType.MYSQL;
		}else {
			return DriverType.DEFAULT;
		}
	}
}
