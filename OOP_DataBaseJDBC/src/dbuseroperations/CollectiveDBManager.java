package dbuseroperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import progoperations.DBSources;
import userrepository.UserUnit;

public class CollectiveDBManager implements IDaoMySQL, IDaoSQLite, IDaoMongoDB, IDBAccess, IDBUserProcesses{

	private Connection conSQL = null;
	private MongoClient conMongo = null;
	private MongoDatabase dbMongo = null;
	private MongoCollection<Document> collMongo = null;
	
	private SQLBasedDBcrudsteps sqlCrudExecutor = null;
	private MongoDBcrudsteps mongoExecutor = null;
	private DBSources manageMode = null;
	
	private void executorRevision(DBSources dbsource) {
		if(dbsource != DBSources.MongoDB) {
			if(sqlCrudExecutor == null)
				sqlCrudExecutor = new SQLBasedDBcrudsteps();
		} else {
			if(mongoExecutor == null)
				mongoExecutor = new MongoDBcrudsteps();
		}
	}
	
	@Override
	public List<UserUnit> loadInUsers() {
		
		executorRevision(manageMode);
		String query = "";
		if(manageMode == DBSources.MySQLDB || manageMode == DBSources.SQLiteDB) {
			query = "SELECT * FROM userlogindata;";
			return sqlCrudExecutor.loadInUsers(manageMode, conSQL, query);
		} else {
			return mongoExecutor.loadInUsers(collMongo);
		}
	}

	
	@Override
	public Integer addUser(UserUnit usr) {
		
		executorRevision(manageMode);
		String query = "";
		String queryToId = "";
		if(manageMode == DBSources.MySQLDB || manageMode == DBSources.SQLiteDB) {
			query = "INSERT INTO userlogindata (fname, lname, email, regdate) VALUES (?, ?, ?, ?);";
			queryToId = "SELECT MAX(id) FROM userlogindata;";
			return sqlCrudExecutor.addUser(manageMode, conSQL, usr, query, queryToId);
		}
		else {
			return mongoExecutor.addUser(collMongo, usr);
		}
		
	}

	@Override
	public Boolean updateUser(UserUnit usr) {
		
		executorRevision(manageMode);
		String query = "";
		if(manageMode == DBSources.MySQLDB || manageMode == DBSources.SQLiteDB) {
			query = "UPDATE userlogindata SET fname=?, lname=?, email=?, regdate=? WHERE id=?;";
			return sqlCrudExecutor.updateUser(manageMode, conSQL, usr, query);
		} else {
			return mongoExecutor.updateUser(collMongo, usr);
		}
		
	}

	@Override
	public Boolean deleteUser(UserUnit usr) {
		
		executorRevision(manageMode);
		String query = "";
		if(manageMode == DBSources.MySQLDB || manageMode == DBSources.SQLiteDB) {
			query = "DELETE FROM userlogindata WHERE id = ?;";
			return sqlCrudExecutor.deleteUser(manageMode, conSQL, usr, query);
		} else {
			return mongoExecutor.deleteUser(collMongo, usr);
		}
	}

	@Override
	public Boolean connectDB(DBSources dbsource) {
		try {
			
			manageMode = dbsource;
			
			if(dbsource == DBSources.MySQLDB) {
				conSQL = initiateSQLBasedConnection(url_MySQL, dbms_user_MySQL, dbms_pwd_MySQL);
				return !conSQL.isClosed();
			} else if(dbsource == DBSources.SQLiteDB) {
				conSQL = initiateSQLBasedConnection(url_SQLite, dbms_user_SQLite, dbms_pwd_SQLite);
				return !conSQL.isClosed();
			} else {
				conMongo =  new MongoClient(url_MongoDB, port_MongoDB);
				dbMongo = conMongo.getDatabase(dbname_MongoDB);
				collMongo = dbMongo.getCollection(collname_MongoDB);
				return ( dbMongo != null || collMongo != null ) ? true:false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return false;
	}

	private Connection initiateSQLBasedConnection(String url, String user, String pwd)
			throws ClassNotFoundException, SQLException {
		
		Class.forName(driver_MySQL);
		return DriverManager.getConnection(url, user, pwd);
	}
	
	@Override
	public Boolean disconnectDB() {
			
		if(manageMode == null)
			return false;
		else
			return closeTheProperDB();

	}
	
	private Boolean closeTheProperDB() {
		
		try {
			if(manageMode != DBSources.MongoDB) {
				if(!conSQL.isClosed()) {
					
					conSQL.close();
					return conSQL.isClosed();
				}
				return false;
			} else {
				if(conMongo != null) {
					conMongo.close();
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
