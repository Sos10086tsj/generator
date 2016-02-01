package com.chinesedreamer.generator.mybatis.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMetaDataHelper {
	public static List<TableColumn> getTableColumns(DatabaseMetaData metaData, String schemaName, String tableName) throws Exception{
		List<TableColumn> columns = new ArrayList<TableColumn>();
		ResultSet rs = metaData.getColumns(null, schemaName, tableName, "%");
		while (rs.next()) {
			columns.add(new TableColumn(rs.getString("COLUMN_NAME")));
		}
		return columns;
	}
}
