package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public String getLogin() {
		return Login;
	}
	
	public void setLogin(String login) {
		Login = login;
	}
	
	public String getDowod() {
		return Dowod;
	}
	
	public void setDowod(String dowod) {
		Dowod = dowod;
	}
	
	public int getSaldo() {
		return Saldo;
	}
	
	public void setSaldo(int saldo) {
		Saldo = saldo;
	}
	
	public boolean isRoot() {
		return Root;
	}
	
	public void setRoot(boolean root) {
		Root = root;
	}

	public static int getLicznikInstancji() {
		return licznikInstancji;
	}

	public static void setLicznikInstancji(int licznikInstancji) {
		CustomerDAO.licznikInstancji = licznikInstancji;
	}
	
	@Override
	public boolean tryLogin(String login, String password) {
		boolean ifSuccessfullyLogged = false;
		PreparedStatement tryLoginStatement = null;
		ResultSet loggedData = null;
		loadConnection();
		
		try {
			tryLoginStatement = connection.prepareStatement("SELECT dowod, saldo, root FROM customer WHERE (login=? AND password=?);");
			
			tryLoginStatement.setString(1, login);
			tryLoginStatement.setString(2, password);
			System.out.println("Calling query for `trylogin`('" + login + "', '" + password + "')");
			loggedData = tryLoginStatement.executeQuery();
			
			if(loggedData.next()) {
				System.out.println("Query `trylogin` executed successfully");
				Dowod = loggedData.getString("dowod");
				Saldo = loggedData.getInt("saldo");
				Root = loggedData.getBoolean("root");
				System.out.println("Player dowod: " + Dowod + " | Saldo: " + Saldo + " | Root: " + Root);
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
}
