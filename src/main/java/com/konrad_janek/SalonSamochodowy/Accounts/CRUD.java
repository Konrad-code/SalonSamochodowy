package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public boolean addCustomer(Customer newCustomer) {
		loadConnection();
		PreparedStatement addCustomerStatement = null;
		boolean ifCustomerAdded = false;
		
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
			ifCustomerAdded = true;
		} catch (SQLException e) {
			System.err.println("Failed to execute query `addCustomer` at database: " + e.getMessage());
		} finally {
			try { addCustomerStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifCustomerAdded;
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
			getSaldoStatement = connection.prepareStatement("SELECT saldo FROM customer WHERE id_customer=?;");
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
	public boolean obciazKonto(int id_customer, int kwota) {
		PreparedStatement updateSaldoStatement = null;
		loadConnection();
		boolean ifBilledAccount = false;
		
		try {
			updateSaldoStatement = connection.prepareStatement("UPDATE customer SET saldo=? WHERE id_customer=?;");
			updateSaldoStatement.setInt(1, kwota);
			updateSaldoStatement.setInt(2, id_customer);
			System.out.println("Executing query `updatesaldo`('" + kwota + "')");
			boolean ifSuccessfulUpdateToDb = updateSaldoStatement.execute();
			if(ifSuccessfulUpdateToDb) {
				System.out.println("Query `updatesaldo` executed successfully");
				ifBilledAccount = true;
			}
		} catch (SQLException e) {
			System.err.println("Failed to execute query `updatesaldo` at database: " + e.getMessage());
		} finally {
			try { updateSaldoStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifBilledAccount;
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
	public boolean checkDowod(String dowod) {
		loadConnection();
		PreparedStatement checkDowodStatement = null;
		ResultSet rs = null;
		boolean dowodFree = false;
		
		try {
			checkDowodStatement = connection.prepareStatement("SELECT login FROM customer WHERE dowod=?;");
			checkDowodStatement.setString(1, dowod);
			System.out.println("Executing query `checkdowod`('" + dowod + "')");
			rs = checkDowodStatement.executeQuery();
			if(rs.next())
				System.out.println("Query `checkdowod` called successfully but it means - dowod is occupied");
			else
				dowodFree = true;
		} catch (SQLException e) {
			System.err.println("Failed to execute query `checkdowod` at database: " + e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* leave action */ }
			try { checkDowodStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return dowodFree;
	}
	
	@Override
	public boolean deleteCustomer(String userToRemove) {
		loadConnection();
		PreparedStatement deleteCustomerStatement = null;
		boolean ifCustomerDeleted = false;
		
		try {
//			String userToRemove = "piotrolot1";
			deleteCustomerStatement = connection.prepareStatement("DELETE FROM customer WHERE login=? AND root IS NOT TRUE;");
			deleteCustomerStatement.setString(1, userToRemove);
			System.out.println("Calling query `deletecustomer`('" + userToRemove + "')");
			boolean ifRowsAffected = deleteCustomerStatement.execute();
			if(ifRowsAffected) {
				ifCustomerDeleted = true;
				System.out.println("Query `deletecustomer` executed successfully");
			} else
				System.out.println("No rows affected - there is no such customer with specified login in database");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `deletecustomer` at database: " + e.getMessage());
		} finally {
			try { deleteCustomerStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifCustomerDeleted;
	}

	@Override
	public boolean deleteCustomers() {
		loadConnection();
		PreparedStatement deleteCustomersStatement = null;
		boolean ifCustomersDeleted = false;
		
		try {
			String table = "customer";
			deleteCustomersStatement = connection.prepareCall("DELETE FROM customer WHERE root IS NOT TRUE;");
			System.out.println("Executing query `deletecustomers`('" + table + "')");
			ifCustomersDeleted = deleteCustomersStatement.execute();
			System.out.println("Query `deletecustomers` executed successfully");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `deletecustomers` at database: " + e.getMessage());
		} finally {
			try { deleteCustomersStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifCustomersDeleted;
	}

	public Customer getCustomerForEdit(String userToEdit) {
		Customer customer = new Customer();
		PreparedStatement searchStatement = null;
		ResultSet results = null;
		loadConnection();
		
		try {
			searchStatement = connection.prepareStatement("SELECT * FROM customer WHERE login=? AND root IS NOT TRUE;");
			searchStatement.setString(1, userToEdit);
			results = searchStatement.executeQuery();
			System.out.println("Executing query `getCustomerForEdit`('" + userToEdit + "')");
			if(results.next()) {
				customer = zmienRekordNaCustomera(results);
				System.out.println("Query `getCustomerForEdit` executed successfully");
			} else
				System.out.println("Query `getCustomerForEdit` executed withut positive result. "
						+ "No customer with login '" + userToEdit +"'");
		} catch (SQLException e) {
			System.err.println("Failed to carry `getCustomerForEdit` method: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { searchStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return customer;
	}
	
	@Override
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException {
		String login = result.getString("login");
		String password = result.getString("password");
		String dowod = result.getString("dowod");
		int id_customer = result.getInt("id_customer");
		int saldo = result.getInt("saldo");
		boolean root = result.getBoolean("root");
		
		Customer dawcaCustomer = new Customer(id_customer, login, password, dowod, saldo, root);
		return dawcaCustomer;	
	}
	
	@Override
	public boolean updateCustomer(Customer loadedCustomerForEdit) {
		boolean ifSuccessfulUpdateToDb = false;
		PreparedStatement updateCustomerStatement = null;
		loadConnection();
		String newLogin = loadedCustomerForEdit.getLogin();
		String newPassword = loadedCustomerForEdit.getPassword();
		String newDowod = loadedCustomerForEdit.getDowod();
		int newSaldo = loadedCustomerForEdit.getSaldo();
		int id_customer = loadedCustomerForEdit.getId_customer();
		
		try {
			updateCustomerStatement = connection.prepareStatement("UPDATE customer SET login=?, "
					+ "password=?, dowod=?, saldo=? WHERE id_customer=?;");
			updateCustomerStatement.setString(1, newLogin);
			updateCustomerStatement.setString(2, newPassword);
			updateCustomerStatement.setString(3, newDowod);
			updateCustomerStatement.setInt(4, newSaldo);
			updateCustomerStatement.setInt(5, id_customer);
			System.out.println("Executing query `updatecustomer`('" + newLogin + "', '" + newPassword 
							+ "', '" + newDowod + "', '" + newSaldo + "') for Customer with id: " + id_customer);
			int numberOfRowsAffected = updateCustomerStatement.executeUpdate();
			if(numberOfRowsAffected > 0) {
				System.out.println("Query `updatecustomer` executed successfully");
				ifSuccessfulUpdateToDb = true;
			}
		} catch (SQLException e) {
			System.err.println("Failed to execute query `updatecustomer` at database: " + e.getMessage());
		} finally {
			try { updateCustomerStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifSuccessfulUpdateToDb;
	}
}
