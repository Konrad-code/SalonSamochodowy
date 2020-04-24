package com.konrad_janek.SalonSamochodowy.Accounts;

public class CustomerDAO {
	
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
	
	public CustomerDAO() {
		
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
}
