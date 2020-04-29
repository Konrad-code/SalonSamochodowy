package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.annotation.RequestScope;


public class CustomerDAO extends CRUD {
	
	{
		Login = "";
		Dowod = "";
		Saldo = 0;
		Root = false;
	}
	
	private String Login;
	private String Dowod;
	private int Saldo;
	private boolean Root;
	public static int licznikInstancji = 0;
	
	public CustomerDAO() {
		licznikInstancji++;
		System.out.println("Konstruktor CustomerDAO numer: " + licznikInstancji);
	}
	
//	public CustomerDAO(String login, int saldo, boolean root) {
//		licznikInstancji++;
//		System.out.println("Konstruktor CustomerDAO numer: " + licznikInstancji);
//		this.Login = login;
//		this.Saldo = saldo;
//		this.Root = root;
//	}
	
	public String getLogin() {
		return Login;
	}
	
	public String getDowod() {
		return Dowod;
	}
	
	public int getSaldo() {
		return Saldo;
	}
	
	public boolean isRoot() {
		return Root;
	}

	public static int getLicznikInstancji() {
		return licznikInstancji;
	}
	
	@Override
	public boolean tryLogin(String login, String password) {
		boolean ifSuccessfullyLogged = false;
		PreparedStatement tryLoginStatement = null;
		ResultSet loggedData = null;
		loadConnection();
		
		try {
			tryLoginStatement = connection.prepareStatement("SELECT saldo, root FROM customer WHERE (login=? AND password=?);");
			
			tryLoginStatement.setString(1, login);
			tryLoginStatement.setString(2, password);
			System.out.println("Calling query for `trylogin`('" + login + "', '" + password + "')");
			loggedData = tryLoginStatement.executeQuery();
			
			if(loggedData.next()) {
				System.out.println("Query `trylogin` executed successfully");
//				Nick = loggedData.getString("dowod"); // update customer with nickname functionality to log on unique nick not on login - login will be preserved from stealing data in app
				Saldo = loggedData.getInt("saldo");
				Root = loggedData.getBoolean("root");
				Login = login;
				System.out.println("Customer login: " + Login + " | saldo: " + Saldo + " | Root: " + Root);
				ifSuccessfullyLogged = true;
			}
		} catch (SQLException e) {
			System.err.println("Failed to execute query `trylogin` on database: " + e.getMessage());
		} finally {
			try { loggedData.close(); } catch (Exception e) { /* leave action */ }
			try { tryLoginStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifSuccessfullyLogged;
	}	

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
			boolean ifSuccessfulUpdateToDb = carRentialStatement.execute();
			if(ifSuccessfulUpdateToDb) {
				System.out.println("Query `rentacar` executed successfully");
				ifReservedCar = true;
			}
		} catch (SQLException e) {
			System.err.println("Failed to execute query `rentacar` at database: " + e.getMessage());
		} finally {
			try { carRentialStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return ifReservedCar;
	}







}
