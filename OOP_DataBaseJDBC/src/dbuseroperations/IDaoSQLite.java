package dbuseroperations;

public interface IDaoSQLite {

	String url_SQLite = "jdbc:sqlite:userdatabase.db";
	String driver_SQLite = "org.sqlite.JDBC";
	
	String dbms_user_SQLite = "";
	String dbms_pwd_SQLite = "";
}
