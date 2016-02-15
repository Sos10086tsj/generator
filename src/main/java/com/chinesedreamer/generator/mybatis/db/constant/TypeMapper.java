package com.chinesedreamer.generator.mybatis.db.constant;

public enum TypeMapper {
	DEFAULT("VARCHAR","String"),
	CHAR("CHAR","String"),
	VARCHAR("VARCHAR","String"),
	VARCHAR2("VARCHAR","String"),
	NVARCHAR2("VARCHAR","String"),
	NUMBER("NUMERIC","BigDecimal"),
	NUMERIC("NUMERIC","BigDecimal"),
	DECIMAL("DECIMAL","BigDecimal"),
	INTEGER("INTEGER","Integer"),
	INT("INTEGER","Integer"),
	FLOAT("FLOAT", "Float"),
	DATE("DATE","Date"),
	TIMESTAMP("TIMESTAMP","Date");
	
	private final String jdbcType;
	private final String javaType;
	
	private TypeMapper(String jdbcType,String javaType){
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public String getJavaType() {
		return javaType;
	}
	
	public static TypeMapper GetType(String type) {
		for (TypeMapper mapper : TypeMapper.values()) {
			int index = type.indexOf("(");
			if (-1 != index) {
				type = type.substring(0, index);
			}
			if (type.equals(mapper.toString())) {
				return mapper;
			}
		}
		return TypeMapper.DEFAULT;
	}
}
