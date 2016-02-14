package com.chinesedreamer.generator.mybatis.db;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.chinesedreamer.generator.mybatis.template.entity.ModelProperty;

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
	 * 获取Mapper文件Result Map一行
	 * @param column
	 * @return
	 */
	public static String getMapperResultMapRow(TableColumn column) {
		StringBuffer buffer = new StringBuffer("<result column=\"");
		buffer.append(column.getName())
		.append("\" property=\"")
		.append(getHumpStr(column.getName(), "_"))
		.append("\" jdbcType=\"")
		.append(column.getType())
		.append("\" />");
		return buffer.toString();
	}
	
	/**
	 * 获取Maaper文件Insert部分一行
	 * @param column
	 * @return
	 */
	public static String getMapperInsertRow(TableColumn column) {
		StringBuffer buffer = new StringBuffer("#{");
		buffer.append(getHumpStr(column.getName(), "_"))
		.append(",jdbcType=")
		.append(column.getType())
		.append("},");
		return buffer.toString();
	}
	
	/**
	 * 获取Maaper文件Update部分一行
	 * @param column
	 * @return
	 */
	public static String getMapperUpdateRow(TableColumn column) {
		String propertyName = getHumpStr(column.getName(), "_");
		StringBuffer buffer = new StringBuffer("<if test=\"");
		buffer.append(propertyName)
		.append(" != null\">")
		.append(column.getName())
		.append(" = #{")
		.append(propertyName)
		.append(",jdbcType=")
		.append(column.getType())
		.append("},</if>");
		return buffer.toString();
	}
	
	/**
	 * 获取Maaper文件主键代码
	 * @param pks
	 * @return
	 */
	public static String getMapperPkCondition(List<TableColumn> pks) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < pks.size(); i++) {
			TableColumn column = pks.get(i);
			if (i != 0) {
				buffer.append(" AND ");
			}
			buffer.append(column.getName())
			.append(" = #{")
			.append(getHumpStr(column.getName(), "_"))
			.append(",jdbcType=")
			.append(column.getType())
			.append("} ");
		}
		return buffer.toString();
	}
	
	/**
	 * 获取驼峰格式字符串
	 * @param str
	 * @param sperator 分隔符
	 * @return
	 */
	public static String getHumpStr(String str, String seperator){
		if (StringUtils.isEmpty(seperator)) {
			return StringUtils.uncapitalize(str);
		}else {
			String[] strWords = str.split(seperator);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < strWords.length; i++) {
				String word = strWords[i];
				if (i == 0) {
					buffer.append(word.toLowerCase());
				}else {
					if (!StringUtils.isEmpty(word)) {
						buffer.append(StringUtils.capitalize(word.toLowerCase()));
					}
				}
			}
			return buffer.toString();
		}
	}
	
	/**
	 * 逐行增加model属性
	 * @param column
	 * @param packages
	 * @param properties
	 */
	public static void addProperty(TableColumn column,Set<String> packages,List<ModelProperty> properties) {
		String humpProperty = getHumpStr(column.getName(), "_");
		ModelProperty modelProperty = new ModelProperty();
		modelProperty.setProperty(humpProperty);
		String methodSuffix = StringUtils.capitalize(humpProperty);
		modelProperty.setGetterName("get" + methodSuffix);
		modelProperty.setSetterName("set" + methodSuffix);
		if (column.getType().startsWith("DATE") || column.getType().startsWith("TIMESTAMP")) {//date
			modelProperty.setType("Date");
			packages.add("java.util.Date");
		}else if (column.getType().startsWith("INT")) {//int
			modelProperty.setType("Integer");
		}else {
			modelProperty.setType("String");
		}
		properties.add(modelProperty);
	}
}
