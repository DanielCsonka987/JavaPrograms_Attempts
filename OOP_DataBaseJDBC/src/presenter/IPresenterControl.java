package presenter;

import javafx.event.ActionEvent;

public interface IPresenterControl {
	
	void chooseDBSourceProcess(ActionEvent event);
	
	void loadInDatabaseContenProcesst(ActionEvent event);
	
	void saveUserProcess(ActionEvent event);
	void updateUserProcesS(ActionEvent event);
	void deleteUserProcess(ActionEvent event);
}
