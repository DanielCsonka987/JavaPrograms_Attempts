package dbuseroperations;
import java.util.List;

import progoperations.DBSources;
import userrepository.UserUnit;

public interface IDBUserProcesses {
	
	List<UserUnit> loadInUsers();

	Integer addUser(UserUnit usr);
	
	Boolean updateUser(UserUnit usr);
	
	Boolean deleteUser(UserUnit usr);
}
