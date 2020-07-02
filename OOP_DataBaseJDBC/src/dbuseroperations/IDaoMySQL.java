package dbuseroperations;

public interface IDaoMySQL {

	//String driver_MySQL = "com.mysql.jdbc.Driver";
	String driver_MySQL = "com.mysql.cj.jdbc.Driver";
	
	String url_MySQL = "jdbc:mysql://localhost:3306/userdatabase?useTimezone=true&serverTimezone=UTC";
	String dbms_user_MySQL = "usercollector";
	String dbms_pwd_MySQL = "pwdtoaccess";
}
