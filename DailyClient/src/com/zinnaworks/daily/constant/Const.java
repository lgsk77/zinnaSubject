package com.zinnaworks.daily.constant;

public interface Const {
	
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	static final String DB_ID = "system";
	static final String DB_PW = "741963";

	static final String DB_TYPE_MEM = "db.type.mem";
	static final String DB_TYPE_NETWORK = "db.type.network";
	static final String DB_TYPE_ORACLE="db.type.oracle";

	static final String DB_COMMAND_INSERT="insert";
	static final String DB_COMMAND_DELETE="delete";
	static final String DB_COMMAND_UPDATE="update";
	static final String DB_COMMAND_LIST="list";
	static final String DB_COMMAND_SEARCH="search";
	static final String DB_COMMAND_UNKNOWN="unknown";
}
