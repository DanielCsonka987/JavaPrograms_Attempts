package dbuseroperations;

import java.sql.Connection;
import java.util.List;

import progoperations.DBSources;
import userrepository.UserUnit;

public interface ISQLCRUDOperations {
	
	List<UserUnit> loadInUsers(DBSources source, Connection con, String query);

	Integer addUser(DBSources source,Connection con, UserUnit usr, String query,  String queryToId);
	
	Boolean updateUser(DBSources source, Connection con, UserUnit usr, String query);
	
	Boolean deleteUser(DBSources source, Connection con, UserUnit usr, String query);

}
