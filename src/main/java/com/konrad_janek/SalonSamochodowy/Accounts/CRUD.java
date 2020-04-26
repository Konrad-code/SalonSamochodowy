package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.ICRUD;

public abstract class CRUD extends ConnectDatabase implements ICRUD {
	
	public static boolean checkIfTableExistsInDatabase() {
		ConnectDatabase dbConn = new ConnectDatabase();
		dbConn.loadConnection();
		
		String trueOutput = "true";
		String hasTable = "";
		Statement statement = null;
		ResultSet resultCheck = null;
		String sqlQuery = "SELECT EXISTS("
							+ "SELECT FROM information_schema.tables "
							+ "WHERE table_schema = 'public' "
							+ "AND table_name = 'customer'"
							+ ")::text AS if_has_table;";
		try {
			statement = dbConn.connection.createStatement();
			resultCheck = statement.executeQuery(sqlQuery);
			if(resultCheck.next())
				hasTable = resultCheck.getString("if_has_table");
		} catch (SQLException e) {
			System.err.println("Failed to finalize method `checkIfTableExistsInDatabase` on database: " + e.getMessage());
		}finally {
			try { statement.close(); } catch (Exception e) { /* leave action */ }
			try { resultCheck.close(); } catch (Exception e) { /* leave action */ }
			dbConn.closeConnection();
		}
		if(hasTable.equalsIgnoreCase(trueOutput))
			return true;
		else
			return false;
	}
	
	@Override
	public void addCustomer(Customer newCustomer) {
		loadConnection();
		PreparedStatement addCustomerStatement = null;
		
		try {
			addCustomerStatement = connection.prepareStatement("INSERT INTO customer (login, password, dowod) VALUES(?,?,?)");
			addCustomerStatement.setString(1, newCustomer.getLogin());
			addCustomerStatement.setString(2, newCustomer.getPassword());
			addCustomerStatement.setString(3, newCustomer.getDowod());
			// MAY COMMENT IN FUTURE
			System.out.println("Calling query `addCustomer`('" + newCustomer.getLogin() + "', '" 
								+ newCustomer.getPassword() + "', '" + newCustomer.getDowod() + "')");
			addCustomerStatement.executeUpdate();
			System.out.println("Query `addCustomer` called successfully");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `addCustomer` at database: " + e.getMessage());
		} finally {
			try { addCustomerStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
	}
	
	@Override
	public boolean tryLogin(String login, String password) {
		return false;}
	
	@Override
	public int getSaldo(int id_customer) {
		loadConnection();
		ResultSet rs = null;
		PreparedStatement getSaldoStatement = null;
		int saldo = 0;
		
		try {
			getSaldoStatement = connection.prepareStatement("SELECT saldo FROM players WHERE id_customer=?;");
			getSaldoStatement.setInt(1, id_customer);
			System.out.println("Executing query `getsaldo`('" + id_customer + "')");
			rs = getSaldoStatement.executeQuery();
			if(rs.next())
				saldo = rs.getInt("saldo");
			else
				System.out.println("No user with specified id_customer");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `getsaldo` on database: " + e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* leave action */ }
			try { getSaldoStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return saldo;
	}
	
	@Override
	public boolean checkLogin(String login) {
		loadConnection();
		PreparedStatement checkLoginStatement = null;
		ResultSet rs = null;
		boolean loginFree = false;
		try {
			checkLoginStatement = connection.prepareStatement("SELECT login FROM customer WHERE login=?;");
			checkLoginStatement.setString(1, login);
			System.out.println("Executing query `checklogin`('" + login + "')");
			rs = checkLoginStatement.executeQuery();
			if(rs.next())
				System.out.println("Query `checklogin` called successfully but it means - login is occupied");
			else
				loginFree = true;
		} catch (SQLException e) {
			System.err.println("Failed to execute query `checklogin` at database: " + e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* leave action */ }
			try { checkLoginStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return loginFree;
	}
	
	@Override
	public void deleteCustomer(String userToRemove) {
		loadConnection();
		PreparedStatement deleteCustomerStatement = null;
		
		try {
//			String userToRemove = "piotrolot1";
			deleteCustomerStatement = connection.prepareStatement("DELETE FROM customer WHERE login=? AND root IS NOT TRUE;");
			deleteCustomerStatement.setString(1, userToRemove);
			System.out.println("Calling query `deletecustomer`('" + userToRemove + "')");
			boolean ifRowsAffected = deleteCustomerStatement.execute();
			if(ifRowsAffected)
				System.out.println("Query `deletecustomer` executed successfully");
			else
				System.out.println("No rows affected - there is no such customer with specified login in database");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `deletecustomer` at database: " + e.getMessage());
		} finally {
			try { deleteCustomerStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
	}

	@Override
	public void deleteCustomers() {
		loadConnection();
		PreparedStatement deleteCustomersStatement = null;
		
		try {
			String table = "customer";
			deleteCustomersStatement = connection.prepareCall("DELETE FROM customer WEHRE root IS NOT TRUE;");
			System.out.println("Calling stored procedure `deletecustomers`('" + table + "')");
			deleteCustomersStatement.execute();
			System.out.println("Stored procedure `deletecustomers` called successfully");
		} catch (SQLException e) {
			System.err.println("Failed to call Procedure `deletecustomers` from database: " + e.getMessage());
		} finally {
			try { deleteCustomersStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
	}

	public Customer getCustomerForEdit(String userToEdit) {
		Customer cutomer = new Customer();
		PreparedStatement searchStatement = null;
		ResultSet results = null;
		loadConnection();
		
//		try {
//			nickname = nickname.concat("%");
//			searchStatement = connection.prepareStatement("SELECT * FROM players WHERE login=? AND root IS NOT TRUE;");
//			searchStatement.setString(1, nickname);
//			results = searchStatement.executeQuery();
//			while(results.next()) {
//				Player donorPlayer = changeRowToPlayer(results);
//				list.add(donorPlayer);
//			}
//		} catch (SQLException e) {
//			System.err.println("Failed to carry `searchPlayers` method: " + e.getMessage());
//		} finally {
//			try { results.close(); } catch (Exception e) { /* leave action */ }
//			try { searchStatement.close(); } catch (Exception e) { /* leave action */ }
//			closeConnection();
//		}
		return null;
	}
	
	@Override
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException {
		String login = result.getString("login");
		String password = result.getString("password");
		String dowod = result.getString("dowod");
		int id_customer = result.getInt("id_customer");
		int saldo = result.getInt("saldo");
		boolean root = result.getBoolean("root");
		
		Customer dawcaCustomer = new Customer(login, password, dowod);
		return dawcaCustomer;	
	}
	
}
