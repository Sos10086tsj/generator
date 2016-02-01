package com.chinesedreamer.generator.mybatis.db;

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
}
