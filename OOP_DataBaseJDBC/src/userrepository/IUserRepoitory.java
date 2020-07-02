package userrepository;

import java.util.List;

import javafx.collections.ObservableList;

public interface IUserRepoitory {

	void setUserContent(ObservableList<UserUnit> listFromDB);
	ObservableList<UserUnit> getUserContent();
	
	Boolean addThisUser(UserUnit usr);
	Boolean updateThisUser(UserUnit usr);
	Boolean deleteThisUser(UserUnit usr);
}
