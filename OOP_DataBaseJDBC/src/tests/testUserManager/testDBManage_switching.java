package tests.testUserManager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import javafx.collections.ObservableList;
import progoperations.DBSources;
import progoperations.UserManagement;
import userrepository.UserUnit;

public class testDBManage_switching {

	private UserManagement man = new UserManagement();
	private UserUnit newuser = new UserUnit(null, "Dr", "Who", "nowhere@gmail.com", new Date());
	
	@Test
	public void testDBChoosingAndReadin() {

		ObservableList<UserUnit> list = man.getDatasFromDB();
		if(man.getDataBaseActSource() != DBSources.NoDBSelected)
			fail("No proper revision - no DB is chosen, but not shows that");
		if(list.size() > 0)
			fail("No proper revision - no DB is chosen, but data arrived");
		
		man.setDataBaseSource(DBSources.MongoDB);
		if(man.getDataBaseActSource() != DBSources.MongoDB)
			fail("No rpoper revision - MongoDB shosen, but not shows that");
		list = man.getDatasFromDB();
		if(! (list.size() > 0) )
			fail("No proper function - no testdata from MongoDB");
		
		man.setDataBaseSource(DBSources.SQLiteDB);
		if(man.getDataBaseActSource() != DBSources.SQLiteDB)
			fail("No rpoper revision - SQLiteDB shosen, but not shows that");
		list = man.getDatasFromDB();
		if(! (list.size() > 0) )
			fail("No proper function - no testdata from SQLiteDB");
	}

	
	
	@Test
	public void testDBOperations_mongodb() {
			
		man.setDataBaseSource(DBSources.MongoDB);
		ObservableList<UserUnit> list = man.getDatasFromDB();
		
		if( !(list.size() > 0))
			fail("No data arrived from MongoDB");
		Integer listSize = list.size();
		
		//Creation
		man.saveNewUser(newuser);
		list = man.getDatasFromDB();
		Integer listNextSize = list.size();
		
		if( (listSize+1) != listNextSize)
			fail("Fail addition or replacing happened");
		if(list.get(3).getId() != 4)
			fail("At addition not the next id " + list.get(3).getId());
		if(! list.get(3).getFname().equals("Dr"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Creation sucessed!");
		
		//Updateing
		newuser.setLname("House");
		man.updateNewUser(newuser);
		list = man.getDatasFromDB();
		if(! list.get(3).getLname().equals("House"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Updateing sucessed! Press a button to start deletion");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Deletion
		man.deleteNewUser(newuser);
		list = man.getDatasFromDB();
		if( list.size() != 3)
			fail("No deletion on MongoDB");
		if (list.filtered(item -> item.getLname().equals("House")).size() > 0)
			fail("Not the propter data is deleted");
		
	}
	
	@Test
	public void testDBOperations_sqlitedb() {
			
		man.setDataBaseSource(DBSources.SQLiteDB);
		ObservableList<UserUnit> list = man.getDatasFromDB();
		
		if( !(list.size() > 0))
			fail("No data arrived from SQLiteDB");
		Integer listSize = list.size();
		
		//Creation
		man.saveNewUser(newuser);
		list = man.getDatasFromDB();
		Integer listNextSize = list.size();
		
		if( (listSize+1) != listNextSize)
			fail("Fail addition or replacing happened");
		if(list.get(3).getId() <= 3)
			fail("At addition not the next id " + list.get(3).getId());
		if(! list.get(3).getFname().equals("Dr"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Creation sucessed!");
		
		//Updateing
		newuser.setLname("House");
		man.updateNewUser(newuser);
		list = man.getDatasFromDB();
		if(! list.get(3).getLname().equals("House"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Updateing sucessed! Press a button to start deletion");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Deletion
		man.deleteNewUser(newuser);
		list = man.getDatasFromDB();
		if( list.size() != 3)
			fail("No deletion on SQLiteDB");
		if (list.filtered(item -> item.getLname().equals("House")).size() > 0)
			fail("Not the propter data is deleted");	
	}
	
	@Test
	public void testDBOperations_mysqldb() {
			
		man.setDataBaseSource(DBSources.MySQLDB);
		ObservableList<UserUnit> list = man.getDatasFromDB();
		
		if( !(list.size() > 0))
			fail("No data arrived from MySqlDB");
		Integer listSize = list.size();
		
		//Creation
		man.saveNewUser(newuser);
		list = man.getDatasFromDB();
		Integer listNextSize = list.size();
		
		if( (listSize+1) != listNextSize)
			fail("Fail addition or replacing happened");
		if(list.get(3).getId() <= 3)
			fail("At addition not the next id " + list.get(3).getId());
		if(! list.get(3).getFname().equals("Dr"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Creation sucessed!");
		
		//Updateing
		newuser.setLname("House");
		man.updateNewUser(newuser);
		list = man.getDatasFromDB();
		if(! list.get(3).getLname().equals("House"))
			fail("Not proper name it defined or list has been mixed " + list.get(3).getFname());
		System.out.println("Updateing sucessed! Press a button to start deletion");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Deletion
		man.deleteNewUser(newuser);
		list = man.getDatasFromDB();
		if( list.size() != 3)
			fail("No deletion on MySqlDB");
		if (list.filtered(item -> item.getLname().equals("House")).size() > 0)
			fail("Not the propter data is deleted");	
	}
}
