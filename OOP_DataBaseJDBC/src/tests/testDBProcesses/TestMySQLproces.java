package tests.testDBProcesses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dbuseroperations.CollectiveDBManager;
import progoperations.DBSources;
import userrepository.UserUnit;

public class TestMySQLproces {

	private UserUnit user = new UserUnit(-1, "data1", "data2", "data@gmail.com", new Date());
	private DBSources dbsource = DBSources.MySQLDB;
	
	@Test
	public void testMySQLdatabase_access() {

		CollectiveDBManager mysqlman = new CollectiveDBManager();
		if(!mysqlman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		if(!mysqlman.disconnectDB())
			Assert.fail("No db disconnection");
			
	}
	
	@Test
	public void testMySQLdatabase_read() {
		
		CollectiveDBManager mysqlman = new CollectiveDBManager();
		if(!mysqlman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		List<UserUnit> list = mysqlman.loadInUsers();
		if(list == null) 
			Assert.fail("No loadin process");
		if(list.size() == 0)
			Assert.fail("No testdata arrived");
		
		if(list.get(0).getId() != 1 || !list.get(0).getEmail().equals("this@gmail.com"))
			Assert.fail("Not proper data content");
		
		if(!mysqlman.disconnectDB())
			Assert.fail("No db disconnection");
	}
	
	@Test
	public void testMySQLdatabase_CUDData() throws IOException {
		 
		CollectiveDBManager mysqlman = new CollectiveDBManager();
		if(!mysqlman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		//Create
		Integer nextIncrement = mysqlman.addUser(user);

		if(nextIncrement == -1)
			Assert.fail("No proper nextIdValue " + nextIncrement);
		
		System.out.println("Id of created record: "+nextIncrement);
		user.setId(nextIncrement);
		
		//Update
		user.setFname("John");
		user.setLname("Doe");
		if(!mysqlman.updateUser(user))
			Assert.fail("No update occured");
		
		//Delete
		System.out.println("Press a button to start Delete");
		System.in.read();
		if(!mysqlman.deleteUser(user))
			Assert.fail("No data removing");
		
		if(!mysqlman.disconnectDB())
			Assert.fail("No db disconnection");
	}
	
}
 