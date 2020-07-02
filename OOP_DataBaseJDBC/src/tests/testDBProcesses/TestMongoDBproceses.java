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

public class TestMongoDBproceses {
	private UserUnit user = new UserUnit(-1, "Mr", "Bond", "stg@gmail.com", new Date());
	private DBSources dbsource = DBSources.MongoDB;
	
	@Test
	public void testMongoDB_access() {
		
		CollectiveDBManager sqliteman = new CollectiveDBManager();
		if(!sqliteman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		if(!sqliteman.disconnectDB())
			Assert.fail("No db disconnection");
		
	}

	
	@Test
	public void testMongoDB_read() {
		
		CollectiveDBManager mysqlman = new CollectiveDBManager();
		if(!mysqlman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		List<UserUnit> list = mysqlman.loadInUsers();
		if(list == null) 
			Assert.fail("No loadin process");
		if(list.size() == 0)
			Assert.fail("No testdata arrived");
		
		if(list.get(1).getId() != 2 || !list.get(1).getFname().equals("me"))
			Assert.fail("Not proper data content");
		
		if(!mysqlman.disconnectDB())
			Assert.fail("No db disconnection");
	}
	
	@Test
	public void testMongoDB_CUDdatas() {
		CollectiveDBManager sqliteman = new CollectiveDBManager();
		if(!sqliteman.connectDB(dbsource))
			Assert.fail("No db connection");
		
		//Create
		Integer nextIndex = sqliteman.addUser(user);
		if(nextIndex == null)
			Assert.fail("Creation failed");
		System.out.println("Id of created record is: " + nextIndex);
		user.setId(nextIndex);
		
		
		//Update
		user.setFname("John");
		user.setLname("Doe");
		if(!sqliteman.updateUser(user))
			Assert.fail("Update wasnt successful");
		
		//Delete
		System.out.println("Push a button to start delete");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!sqliteman.deleteUser(user))
			Assert.fail("Deletion wasnt successful");
		
		if(!sqliteman.disconnectDB())
			Assert.fail("No db disconnection");
		
	}
}
