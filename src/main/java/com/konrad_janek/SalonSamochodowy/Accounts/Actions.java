package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.konrad_janek.SalonSamochodowy.Templates.IActions;

public abstract class Actions extends CRUD implements IActions {

	public boolean rentACar(int id_customer, int id_car, int dlugoscWypozyczenia) {
		PreparedStatement carRentialStatement = null;
		loadConnection();
		boolean ifReservedCar = false;
		
		try {
			carRentialStatement = connection.prepareStatement("UPDATE car SET customer_id=?, dlugoscWypozyczenia=?, dataWypozyczenia=CURRENT_DATE WHERE id_car=?;");
			carRentialStatement.setInt(1, id_customer);
			carRentialStatement.setInt(2, dlugoscWypozyczenia);
			carRentialStatement.setInt(3, id_car);
			System.out.println("Executing query `rentacar`(" + id_customer + ", " + id_car + ", " + dlugoscWypozyczenia + ")");
			int ifSuccessfulUpdateToDb = carRentialStatement.executeUpdate();
			if(ifSuccessfulUpdateToDb > 0) {
				System.out.println("Query `rentacar` executed successfully");
				ifReservedCar = true;
			} else
				System.out.println("Failed to execute `rentacar` query");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `rentacar` at database: " + e.getMessage());
		} finally {
			try { carRentialStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifReservedCar;
	}
	
	@Override
	public boolean checkIfCarFree(int id_car) {
		loadConnection();
		PreparedStatement checkCarFreeStatement = null;
		ResultSet rs = null;
		boolean carFree = false;
		
		try {
			checkCarFreeStatement = connection.prepareStatement("SELECT * FROM car WHERE (id_car=? AND dlugoscWypozyczenia=0 AND customer_id IS NULL);");
			checkCarFreeStatement.setInt(1, id_car);
			System.out.println("Executing query `checkIfCarFree`('" + id_car + "')");
			rs = checkCarFreeStatement.executeQuery();
			if(rs.next()) {
				System.out.println("Query `checkIfCarFree` called successfully so it means that car is free to rent");
				carFree = true;
			}
		} catch (SQLException e) {
			System.err.println("Failed to execute query `checkIfCarFree` at database: " + e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* leave action */ }
			try { checkCarFreeStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return carFree;
	}

	@Override
	public boolean addTransaction(int id_customer, int id_car) {
		loadConnection();
		PreparedStatement addTransactionStatement = null;
		boolean ifTransactionAdded = false;
		
		try {
			addTransactionStatement = connection.prepareStatement("INSERT INTO transaction (customer_id, car_id) VALUES(?,?)");
			addTransactionStatement.setInt(1, id_customer);
			addTransactionStatement.setInt(2, id_car);
			// MAY COMMENT IN FUTURE
			System.out.println("Calling query `addTransaction`(" + id_customer + ", " + id_car);
			int ifSuccessfully = addTransactionStatement.executeUpdate();
			if(ifSuccessfully > 0) {
				System.out.println("Query `addTransaction` called successfully");
				ifTransactionAdded = true;
			} else
				System.out.println("0 transactions inserted. `addTransaction` operation failed");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `addTransaction` at database: " + e.getMessage());
		} finally {
			try { addTransactionStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifTransactionAdded;
	}
	
	@Override
	public boolean returnACarFromTransaction(int id_transaction) {
		PreparedStatement carReturnStatement = null;
		loadConnection();
		boolean ifReturned = false;
		
		try {
			carReturnStatement = connection.prepareStatement("UPDATE transaction SET dataOddania=CURRENT_DATE WHERE id_transaction=?;");
			carReturnStatement.setInt(1, id_transaction);
			System.out.println("Executing query `returnACarFromTransaction`(" + id_transaction + ")");
			int ifSuccessfulUpdateToDb = carReturnStatement.executeUpdate();
			if(ifSuccessfulUpdateToDb > 0) {
				System.out.println("Query `returnACarFromTransaction` executed successfully");
				ifReturned = true;
			} else
				System.out.println("Failed to execute `returnACarFromTransaction` query");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `returnACarFromTransaction` at database: " + e.getMessage());
		} finally {
			try { carReturnStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifReturned;
	}
	
	@Override
	public boolean returnACar(int id_customer, int id_car) {
		PreparedStatement carReturnStatement = null;
		loadConnection();
		boolean ifReturned = false;
		
		try {
			carReturnStatement = connection.prepareStatement("UPDATE transaction SET dataOddania=CURRENT_DATE WHERE (customer_id=? AND car_id=? AND zatwierdzona=FALSE);");
			carReturnStatement.setInt(1, id_customer);
			carReturnStatement.setInt(2, id_car);
			System.out.println("Executing query `returnACar`(" + id_customer + ", " + id_car + ")");
			int ifSuccessfulUpdateToDb = carReturnStatement.executeUpdate();
			if(ifSuccessfulUpdateToDb > 0) {
				System.out.println("Query `returnACar` executed successfully");
				ifReturned = true;
			} else
				System.out.println("Failed to execute `returnACar` query");
		} catch (SQLException e) {
			System.err.println("Failed to execute query `returnACar` at database: " + e.getMessage());
		} finally {
			try { carReturnStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifReturned;
	}
}
