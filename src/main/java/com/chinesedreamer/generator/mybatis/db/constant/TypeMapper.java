package com.chinesedreamer.generator.mybatis.db.constant;

public enum TypeMapper {
	DEFAULT("JDBC","JAVA"),
	CHAR("CHAR","String"),
	VARCHAR("VARCHAR","JAVA"),
	VARCHAR2("VARCHAR","JAVA"),
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
			if (mapper.toString().equals(type)) {
				return mapper;
			}
		}
		return TypeMapper.DEFAULT;
	}
}
