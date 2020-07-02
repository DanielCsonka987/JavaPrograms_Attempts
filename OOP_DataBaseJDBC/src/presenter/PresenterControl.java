package presenter;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import progoperations.DBSources;
import progoperations.UserManagement;
import userrepository.UserUnit;

public class PresenterControl<T> implements Initializable, IPresenterControl{

	    @FXML private Button btnLoadDB;
	    @FXML private Button btnExitDB;
	    @FXML private Label lblStatusIndicator;
	    
	    @FXML private Button btnSaveUser;
	    @FXML private Button btnUpdateUser;
	    @FXML private Button btnDeleteUser;
	    @FXML private Button btnClearFields;

	    @FXML private TextField txtbxUserfirstn;
	    @FXML private TextField txtbxUserlastn;

	    @FXML private ComboBox<String> cmbbxDBs;

	    @FXML private TextField txtbxUserid;
	    @FXML private TextField txtbxUseremail;

	    @FXML private DatePicker dtpckrUserDate;
	    @FXML private TextField txtbxUserReghour;
	    @FXML private TextField txtbxUserRegmin;
	    @FXML private TextField txtbxUserRegsec;
	    
	    @FXML private TableView<UserUnit> tblvwUsers;
	    
	    @FXML private TableColumn<UserUnit, Number> tblcolId;
	    @FXML private TableColumn<UserUnit, String> tblcolFirstname;
	    @FXML private TableColumn<UserUnit, String> tblcolLastname;
	    @FXML private TableColumn<UserUnit, String> tblcolEmail;
	    @FXML private TableColumn<UserUnit, String> tblcolDate;

	    private UserManagement manage;
	    private List<String> dblist = new ArrayList<String>() {{
	    	add("MySQL"); add("SQLite"); add("MongoDB");
	    }};
	    
	    private SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy-MM-dd");
	    private SimpleDateFormat sdf_Hour = new SimpleDateFormat("hh");
	    private SimpleDateFormat sdf_Min = new SimpleDateFormat("mm");
	    private SimpleDateFormat sdf_Sec = new SimpleDateFormat("ss");
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			manage = new UserManagement();
			cmbbxDBs.getItems().addAll(FXCollections.observableList(dblist));
			swithOFF_ONCRUDControlers(true);
			initializeTableview();
		}
	   
		private void initializeTableview() {
			
			tblcolId = new TableColumn<UserUnit, Number>("User ID");
			tblcolId.setCellValueFactory(cellData -> cellData.getValue().getIdProp());
			tblcolId.setMaxWidth(50);
			
			tblcolFirstname = new TableColumn<UserUnit, String>("User firstname");
			tblcolFirstname.setCellValueFactory(cellData -> cellData.getValue().getFnameProp());
			tblcolFirstname.setMinWidth(150);
			
			tblcolLastname = new TableColumn<UserUnit, String>("User lastname");
			tblcolLastname.setCellValueFactory(cellData -> cellData.getValue().getLnameProp());
			tblcolLastname.setMinWidth(150);
			
			tblcolEmail = new TableColumn<UserUnit, String>("User email");
			tblcolEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProp());
			tblcolEmail.setMinWidth(150);
			
			tblcolDate = new TableColumn<UserUnit, String>("Registration date");
			tblcolDate.setCellValueFactory(cellData -> cellData.getValue().getRegistrProp());
			tblcolDate.setMinWidth(180);
			
			tblvwUsers.getColumns().addAll(  tblcolId, tblcolFirstname, tblcolLastname, tblcolEmail, tblcolDate);
			
		}
		
		@FXML
		@Override
		public void chooseDBSourceProcess(ActionEvent event) {
			
			disconnectDB();
		}
		
	    @FXML
		@Override
		public void loadInDatabaseContenProcesst(ActionEvent event) {
			
	    	connectTheProperDB();
	    	reloadTableView();
	    	swithOFF_ONCRUDControlers(false);
		}
	    
	    private void connectTheProperDB() {
	    	
	    	if(! cmbbxDBs.getSelectionModel().isEmpty()) {
	    		String userchosen = cmbbxDBs.getSelectionModel().getSelectedItem();
	    		
	    		if(userchosen.equals(dblist.get(0)))
	    			manage.setDataBaseSource(DBSources.MySQLDB);
	    		else if(userchosen.equals(dblist.get(1)))
	    				manage.setDataBaseSource(DBSources.SQLiteDB);
	    		else
	    			manage.setDataBaseSource(DBSources.MongoDB);
	    	}
	    }
	    
		private void reloadTableView() {
			
	    	tblvwUsers.getItems().clear();
	    	ObservableList<UserUnit> list = manage.getDatasFromDB();
	    	if(list.size() > 0)
	    		tblvwUsers.getItems().addAll(list);
	    	else
	    		tblvwUsers.setPlaceholder(new Label("No content to display"));
		}
		
		@FXML
		public void exitDB(ActionEvent event) {
			
			disconnectDB();
		}
	    
		private void disconnectDB() {
			
			tblvwUsers.getItems().clear();
			swithOFF_ONCRUDControlers(true);
			manage.setDataBaseSource(DBSources.NoDBSelected);
			lblStatusIndicator.setText("Offline");
		}
		
	    @FXML
		@Override
		public void saveUserProcess(ActionEvent event) {
			
	    	UserUnit usr = getTheUserDetailsFromFields();
	    	if(usr != null) {
	    		//System.out.println(usr);
	    		if(manage.saveNewUser(usr)) {
	    			clearTextfields();
	    			reloadTableView();
	    		}
	    	}
		}
	    @FXML
		@Override
		public void updateUserProcesS(ActionEvent event) {
			
	    	UserUnit usr = getTheUserDetailsFromFields();
	    	if(usr != null) {
		    	if(manage.updateNewUser(usr)) {
		    		clearTextfields();
		    		reloadTableView();
		    	}
	    	}
		}
	    @FXML
		@Override
		public void deleteUserProcess(ActionEvent event) {
			
	    	UserUnit usr = getTheUserDetailsFromFields();
	    	if(usr != null) {
		    	if(manage.deleteNewUser(usr)) {
		    		clearTextfields();
		    		reloadTableView();
		    	}
	    	}
		}

		@FXML
	    void clearFields(ActionEvent event) {

	    	clearTextfields();
	    }
		
	    private UserUnit getTheUserDetailsFromFields() {
			UserUnit usr = new UserUnit();
			usr.setId(reviseAndGetIntegerType(txtbxUserid));
			usr.setFname(reviseAndGetStringType(txtbxUserfirstn));
			usr.setLname(reviseAndGetStringType(txtbxUserlastn));
			usr.setEmail(reviseAndGetEmail());
			usr.setRegDate(reviseAndGetRegDate());
			
			if(usr.areThereAllVariable())
				return usr;
			else {
				System.out.println(usr);
				return null;
			}
		}

		private Integer reviseAndGetIntegerType(TextField item) {
			
			String idtext = item.getText();
			if(! (idtext = idtext.trim()).equals("")) {
				
				try {
					return Integer.parseInt(idtext);
				}catch(Exception e) {
					
				}
			}
			return null;
		}

		private String reviseAndGetStringType(TextField item) {

			String idtext = item.getText();
			if(! (idtext = idtext.trim()).equals("")) {
				return idtext;
			}
			return null;
		}
		
	    private String emailRegexp = "^[a-z0-9]{3,}@[a-z]{3,}.[a-z]{2,4}$";
		private String reviseAndGetEmail() {
			
			String emailtext = txtbxUseremail.getText();
			if(! (emailtext = emailtext.trim()).equals("")) {
				
				if(emailtext.matches(emailRegexp))
					return emailtext;
			}
			return null;
		}

		private Date reviseAndGetRegDate() {
			/*
			Date res = new Date(System.nanoTime());
			if(dtpckrUserDate.focusedProperty().getValue()) {
				LocalDate locdate = (LocalDate)dtpckrUserDate.getUserData();
				res = Date.from(
						locdate.atStartOfDay(ZoneId.systemDefault()).toInstant() );
			} else {
				try {
					res = sdf_Date.parse(dtpckrUserDate.getPromptText());
				} catch (ParseException e) {
					
				}
			}
			long t = reviseAndGetIntegerType(txtbxUserReghour) * 3600L;
			t += reviseAndGetIntegerType(txtbxUserRegmin) * 60L;
			t += reviseAndGetIntegerType(txtbxUserRegsec);
			res.setTime( TimeUnit.SECONDS.toMillis(t) );
			return res;
			*/
			LocalDate locdate = (LocalDate)dtpckrUserDate.getValue();
			Date res = Date.from(locdate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			long d = res.getTime() / 1000L;
			long t = reviseAndGetIntegerType(txtbxUserReghour) * 3600L;
			t += reviseAndGetIntegerType(txtbxUserRegmin) * 60L;
			t += reviseAndGetIntegerType(txtbxUserRegsec);

			res.setTime( TimeUnit.SECONDS.toMillis(t + d) );
			
			
			//System.out.println(sdf_Date.format(res));
			return res;
		}

		private void clearTextfields() {
			
			txtbxUserid.setText("");
			txtbxUserfirstn.setText("");
			txtbxUserlastn.setText("");
			txtbxUseremail.setText("");
			
			dtpckrUserDate.setValue(LocalDate.now());
			dtpckrUserDate.setPromptText("");
			
			txtbxUserReghour.setText("");
			txtbxUserRegmin.setText("");
			txtbxUserRegsec.setText("");
		}

		private void swithOFF_ONCRUDControlers(Boolean state) {
			
			btnSaveUser.disableProperty().setValue(state);
			btnUpdateUser.disableProperty().setValue(state);
			btnDeleteUser.disableProperty().setValue(state);
			btnClearFields.disableProperty().setValue(state);
			
			txtbxUserid.disableProperty().setValue(state);
			txtbxUserfirstn.disableProperty().setValue(state);
			txtbxUserlastn.disableProperty().setValue(state);
			txtbxUseremail.disableProperty().setValue(state);
			
			dtpckrUserDate.disableProperty().setValue(state);
			
			txtbxUserReghour.disableProperty().setValue(state);
			txtbxUserRegmin.disableProperty().setValue(state);
			txtbxUserRegsec.disableProperty().setValue(state);
			
			lblStatusIndicator.setText(state?"Offline":"Online");
		}
		
	    @FXML
	    void dateIsSelected(ActionEvent event) {
			LocalDate tempdate = dtpckrUserDate.getValue();
			System.out.println(tempdate);
			if(dtpckrUserDate.focusedProperty().getValue()) {

				dtpckrUserDate.setPromptText(tempdate.toString());
				dtpckrUserDate.setValue(tempdate);
				dtpckrUserDate.setUserData(tempdate);
				
			}
			
	    }
		@FXML
		public void recordHasSelected(MouseEvent e) {
			
			if(((TableView<UserUnit>)e.getSource()).getSelectionModel().getSelectedIndex() > -1) {
				UserUnit items = ((TableView<UserUnit>)e.getSource()).getSelectionModel().getSelectedItem();
				//System.out.println(items);
				loadTheDatasToTextfields(items);
			}
			
		}

		private void loadTheDatasToTextfields(UserUnit itemFromTable) {
			
			txtbxUserid.setText(itemFromTable.getId().toString());
			txtbxUserfirstn.setText(itemFromTable.getFname());
			txtbxUserlastn.setText(itemFromTable.getLname());
			txtbxUseremail.setText(itemFromTable.getEmail());
			//sdf_Date.format(temporal)(itemFromTable.getRegdate())
			
			dtpckrUserDate.setValue(itemFromTable.getRegdate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			dtpckrUserDate.setPromptText(sdf_Date.format(itemFromTable.getRegdate()));
			
			txtbxUserReghour.setText(sdf_Hour.format(itemFromTable.getRegdate()));
			txtbxUserRegmin.setText(sdf_Min.format(itemFromTable.getRegdate()));
			txtbxUserRegsec.setText(sdf_Sec.format(itemFromTable.getRegdate()));
			
		}
		
}
