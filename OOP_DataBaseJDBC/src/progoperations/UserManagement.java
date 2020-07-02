package progoperations;

import java.util.ArrayList;
import java.util.List;

import dbuseroperations.CollectiveDBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import userrepository.UserRepoistory;
import userrepository.UserUnit;

public class UserManagement implements IUserManagement{

	private Boolean dbsourceHasChosen;
	private DBSources source;
	private CollectiveDBManager dbman;
	private UserRepoistory repoman;
	
	
	public UserManagement() {
		super();
		
		dbsourceHasChosen = false;
		DBSources source = null;
		dbman = new CollectiveDBManager();
		repoman = new UserRepoistory();
	}

	@Override
	public void setDataBaseSource(DBSources db) {
		
		source = db;
		dbsourceHasChosen = true;
	}

	@Override
	public DBSources getDataBaseActSource() {

		DBSources tempSource;
		if(source != null) {
			tempSource = source;
		} else {
			tempSource = DBSources.NoDBSelected;
		}
		return tempSource;
	}
	
	@Override
	public ObservableList<UserUnit> getDatasFromDB() {
		
		if(dbsourceHasChosen) {
			dbman.connectDB(source);
			List<UserUnit> templist = dbman.loadInUsers();
			dbman.disconnectDB();
			
			if(templist.size() > 0) {	
				repoman.setUserContent(
						FXCollections.observableArrayList(templist)
				);
			} else {
				repoman.setUserContent(  FXCollections.observableArrayList() );
			}
		}
		return repoman.getUserContent();
	}

	@Override
	public Boolean saveNewUser(UserUnit user) {

		Boolean res = false;
		if(dbsourceHasChosen) {
			
			dbman.connectDB(source);
			Integer index = dbman.addUser(user);
			dbman.disconnectDB();
			
			if( index > -1) {
				user.setId(index);
				res = repoman.addThisUser(user);
			} 
		}
		return res;

	}

	@Override
	public Boolean updateNewUser(UserUnit user) {
		
		Boolean res = false;
		if(dbsourceHasChosen) {
			
			dbman.connectDB(source);
			Boolean res1 = dbman.updateUser(user);
			dbman.disconnectDB();
			
			Boolean res2 = repoman.updateThisUser(user);
			res = (res1 && res2);
		}
		return res;
	}

	@Override
	public Boolean deleteNewUser(UserUnit user) {

		Boolean res = false;
		if(dbsourceHasChosen) {
			
			dbman.connectDB(source);
			Boolean res1 = dbman.deleteUser(user);
			dbman.disconnectDB();
			
			Boolean res2 = repoman.deleteThisUser(user);
			res = (res1 && res2);
		}
		return res;
	}


}
