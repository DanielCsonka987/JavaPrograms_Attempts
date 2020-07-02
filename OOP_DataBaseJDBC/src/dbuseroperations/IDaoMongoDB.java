package dbuseroperations;

import com.mongodb.MongoCredential;

public interface IDaoMongoDB {

	String url_MongoDB = "localhost";
	Integer port_MongoDB = 27017;
	String dbname_MongoDB = "userdatabase";
	String collname_MongoDB = "userlogindata";
	MongoCredential dbms_access = 
			MongoCredential.createCredential("admin", "userdatabase" ,"".toCharArray());
	
}
