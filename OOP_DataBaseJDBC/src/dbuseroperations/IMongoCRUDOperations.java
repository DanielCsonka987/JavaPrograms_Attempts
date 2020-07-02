package dbuseroperations;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import userrepository.UserUnit;

public interface IMongoCRUDOperations {

	List<UserUnit> loadInUsers(MongoCollection<Document> collObject);

	Integer addUser(MongoCollection<Document> collObject, UserUnit usr);
	
	Boolean updateUser(MongoCollection<Document> collObject, UserUnit usr);
	
	Boolean deleteUser(MongoCollection<Document> collObject, UserUnit usr);
}
