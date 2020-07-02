package userrepository;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserRepoistory implements IUserRepoitory {

	private ObservableList<UserUnit> userlist = FXCollections.observableArrayList();
	
	@Override
	public void setUserContent(ObservableList<UserUnit> convertedListFromDB) {
		
		userlist = convertedListFromDB;
	}

	@Override
	public ObservableList<UserUnit> getUserContent() {

		return userlist;
	}

	@Override
	public Boolean addThisUser(UserUnit usr) {
		
		try {
			userlist.add(usr);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateThisUser(UserUnit usr) {

		try {
			Integer index = findItemIndex(usr.getId());
			if(index == -1)
				return false;
			
			UserUnit refTochange = userlist.get(index);
			refTochange.setFname(usr.getFname());
			refTochange.setLname(usr.getLname());
			refTochange.setEmail(usr.getEmail());
			refTochange.setRegDate(usr.getRegdate());

			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Integer findItemIndex(Integer seekId) {
		
		for(int i = 0; i < userlist.size(); i++) {
			if(userlist.get(i).getId() == seekId)
				return i;
		}
		return -1;
	}

	@Override
	public Boolean deleteThisUser(UserUnit usr) {
		
		try {
			userlist.removeIf(item -> item.getId() == usr.getId());
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
