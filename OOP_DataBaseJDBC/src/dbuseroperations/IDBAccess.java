package dbuseroperations;

import progoperations.DBSources;

public interface IDBAccess {

	Boolean connectDB(DBSources dbsource);
	
	Boolean disconnectDB();
}
