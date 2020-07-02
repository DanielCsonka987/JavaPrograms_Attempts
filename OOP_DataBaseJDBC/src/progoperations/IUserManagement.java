package progoperations;

import java.util.List;

import javafx.collections.ObservableList;
import userrepository.UserUnit;

public interface IUserManagement {

	void setDataBaseSource(DBSources db);
	DBSources getDataBaseActSource();
	
	ObservableList<UserUnit> getDatasFromDB();
	
	Boolean saveNewUser(UserUnit user);
	Boolean updateNewUser(UserUnit user);
	Boolean deleteNewUser(UserUnit user);

	
}
