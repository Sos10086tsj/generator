package com.chinesedreamer.generator.mybatis.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMetaDataHelper {
	/**
	 * 获取所有列名字
	 * @param metaData
	 * @param schemaName
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static List<TableColumn> getTableColumns(DatabaseMetaData metaData, String schemaName, String tableName) throws Exception{
		List<TableColumn> columns = new ArrayList<TableColumn>();
		ResultSet rs = metaData.getColumns(null, schemaName, tableName, "%");
		while (rs.next()) {
			String columnName = rs.getString("COLUMN_NAME");
			//int dataType = rs.getInt("DATA_TYPE");
			String dataTypeName = rs.getString("TYPE_NAME");
			columns.add(new TableColumn(columnName, dataTypeName));
		}
		return columns;
	}
	
	/**
	 * 获取表主键
	 * @param metaData
	 * @param schemaName
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static List<TableColumn> getTablePrimaryKey(DatabaseMetaData metaData, String schemaName, String tableName) throws Exception{
		List<TableColumn> pks = new ArrayList<TableColumn>();
		ResultSet rs = metaData.getPrimaryKeys(null, schemaName, tableName);
		while (rs.next()){
			String pkName = rs.getString("COLUMN_NAME");
			pks.add(new TableColumn(pkName, "VARCHAR"));
		}
		return pks;
	}
}
