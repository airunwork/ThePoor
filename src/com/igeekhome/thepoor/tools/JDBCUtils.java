package com.igeekhome.thepoor.tools;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
	
	public static BasicDataSource datasource = new BasicDataSource();
	
	static{
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/thepoor");
		datasource.setUsername("root");
		datasource.setPassword("zhao123++");
	}
	public static DataSource getDataSource(){
		return datasource;
	}
}
