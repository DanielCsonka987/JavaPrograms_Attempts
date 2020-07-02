package dbuseroperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection; 
import userrepository.UserUnit;

import org.bson.BsonValue;
import org.bson.Document; 

public class MongoDBcrudsteps implements IMongoCRUDOperations {

	@Override
	public List<UserUnit> loadInUsers(MongoCollection<Document> collObject) {
		
		FindIterable<Document> iterDoc = collObject.find();
		Iterator it = iterDoc.iterator();
		
		List<UserUnit> list = new ArrayList<UserUnit>();
		while(it.hasNext()) {
			list.add( createTheDocumentToUserUnit(	(Document)it.next() ) );
		}
		return list;
	}

	private UserUnit createTheDocumentToUserUnit(Document tempRecord) {
		
		return new UserUnit(getIntegerValue(tempRecord, "id"), getStringValue(tempRecord, "fname"), 
				getStringValue(tempRecord, "lname"), getStringValue(tempRecord, "email"),
				getDateValue(tempRecord,"regdate") );
	}
	
	private String getStringValue(Document record, String key) {
		
		return record.getString(key);
	}
	
	private Integer getIntegerValue(Document record, String key) {
		
		try {
			Double temp = record.getDouble(key);
			if(temp == null)
				throw new Exception("Document data is null in db : " + temp);
			return  temp.intValue();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Date getDateValue(Document record, String key) {
		
		return record.getDate(key);
	}
	
	@Override
	public Integer addUser(MongoCollection<Document> collObject, UserUnit usr) {

		Integer nextId = findTheMaxIdValue(collObject);
		if(nextId == null)
			return -1;
		//System.out.println(nextId);
		nextId++;
		usr.setId(nextId);
		Document record = createTheDocument(usr);
		collObject.insertOne(record);
		return nextId;
	}


	private Integer findTheMaxIdValue(MongoCollection coll) {
		
		Document resDoc = ((FindIterable<Document>) coll
				.find()
				.limit(1)
				.sort( Sorts.descending("id") )
				).first();
		return resDoc.getDouble("id").intValue() + 0;
	}

	private Document createTheDocument(UserUnit usr) {

		return new Document("id", usr.getId().doubleValue())
				.append("fname", usr.getFname())
				.append("lname", usr.getLname())
				.append("email", usr.getEmail())
				.append("regdate", usr.getRegdate());
	}
	
	@Override
	public Boolean updateUser(MongoCollection<Document> collObject, UserUnit usr) {

		UpdateResult res = collObject.updateMany(
				Filters.eq("id", usr.getId()), 
				Updates.combine(
						Updates.set("fname", usr.getFname()),
						Updates.set("lname", usr.getLname()),
						Updates.set("email", usr.getEmail()),
						Updates.set("regdate", usr.getRegdate())
				)
		);
		
		return (res.getMatchedCount() >= 1) ? true : false;
	}

	@Override
	public Boolean deleteUser(MongoCollection<Document> collObject, UserUnit usr) {
		
		DeleteResult res = collObject.deleteOne(Filters.eq("id", usr.getId()));
		return (res.getDeletedCount() >= 1) ? true : false;
	}



}
