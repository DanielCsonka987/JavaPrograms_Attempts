package dbuseroperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import progoperations.DBSources;
import userrepository.UserUnit;

public class SQLBasedDBcrudsteps implements ISQLCRUDOperations{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Override
	public List<UserUnit> loadInUsers(DBSources source, Connection con, String query) {

		ResultSet res = getTheQueryStatementData(con, query);
		return convertQueryResultToList(source, res);
	}

	private ResultSet getTheQueryStatementData(Connection con, String query) {
		
		try {
			if(con.isClosed())
				return null;
			Statement stm = con.createStatement();
			return stm.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<UserUnit> convertQueryResultToList(DBSources source, ResultSet res) {
		
		if(res == null)
			return null;
		
		List<UserUnit> finalres = new ArrayList<UserUnit>();
		try {
			if(source == DBSources.MySQLDB) {
				while(res.next()) {
					finalres.add( parseUserUnit_MySQL(res) );
				}
			} else {
				while(res.next()) {
					finalres.add( parseUserUnit_SQLiteMungoDB(res) );
				}
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalres;
	}
	
	private UserUnit parseUserUnit_MySQL(ResultSet res) throws SQLException {
		
		 return new UserUnit(	res.getInt("id"),
					res.getString("fname"), res.getString("lname"),
					res.getString("email"), res.getTimestamp("regdate")		);
	}

	private UserUnit parseUserUnit_SQLiteMungoDB(ResultSet res) throws SQLException, ParseException {
		
		return new UserUnit(	res.getInt("id"),
				res.getString("fname"), res.getString("lname"),
				res.getString("email"), sdf.parse(res.getString("regdate"))		);
	}
	
	
	@Override
	public Integer addUser(DBSources source, Connection con, UserUnit usr, String query, String queryToId) {
		
		Boolean insertionRes = false;
		if(executeDBInsertion(source, con, usr, query)){
			
			return getTheIdFromDB(con, queryToId);
		}
		return -1;
	}

	
	private boolean executeDBInsertion(DBSources source, Connection con, UserUnit usr, String query) {
		
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, usr.getFname());
			pstm.setString(2, usr.getLname());
			pstm.setString(3, usr.getEmail());
			if(source == DBSources.MySQLDB)
				pstm.setTimestamp(4, new java.sql.Timestamp(usr.getRegdate().getTime()));
			else
				pstm.setString(4, sdf.format(usr.getRegdate()) );
			pstm.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	private Integer getTheIdFromDB(Connection con,  String queryToId) {
		
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(queryToId);
			res.next();
			return res.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Boolean updateUser(DBSources source, Connection con, UserUnit usr, String query) {
	
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, usr.getFname());
			pstm.setString(2, usr.getLname());
			pstm.setString(3, usr.getEmail());
			if(source == DBSources.MySQLDB)
				pstm.setTimestamp(4, new java.sql.Timestamp(usr.getRegdate().getTime()));
			else
				pstm.setString(4, sdf.format(usr.getRegdate()) );
			pstm.setInt(5, usr.getId());
			pstm.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteUser(DBSources source,Connection con, UserUnit usr, String query) {
		
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, usr.getId());
			pstm.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
