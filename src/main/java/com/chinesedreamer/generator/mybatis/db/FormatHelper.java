package com.chinesedreamer.generator.mybatis.db;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class FormatHelper {
	
	/**
	 * 获取model的名字
	 * @param tableName
	 * @return
	 */
	public static String getModelName(String tableName) {
		//1. 所有字母小写
		tableName = tableName.toLowerCase();
		//2. 如果有-，-后面的字母大写
		StringBuffer buffer = new StringBuffer();
		String[] words = tableName.split("_");
		if (words.length > 1) {
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				buffer.append(StringUtils.capitalize(word));
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 获取pacakge路径
	 * @param path
	 * @return
	 */
	public static String formatPackageName(String path) {
		return path.replace(File.separator, ".");
	}
	
	/**
	 * 获取dao类
	 * @param path
	 * @param modelName
	 * @return
	 */
	public static String getDaoClass(String path, String modelName) {
		return formatPackageName(path) + modelName + "Dao";
	}
}
