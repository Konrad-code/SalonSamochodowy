package com.konrad_janek.SalonSamochodowy.Accounts;

public class Customer {
	
	{
		id_customer = 0;
		saldo = 0;
		root = false;
	}
	
	private int id_customer;
	private String login;
	private String password;
	private String dowod;
	private int saldo;
	private boolean root;
	
	public Customer(int id_customer, String login, String password, String dowod, int saldo, boolean root) {
		this.id_customer = id_customer;
		this.login = login;
		this.password = password;
		this.dowod = dowod;
		this.saldo = saldo;
		this.root = root;
	}
	
	public Customer(String login, String password, String dowod) {
		this.login = login;
		this.password = password;
		this.dowod = dowod;
	}
	
	public Customer() {	// in case of further SpringBoot functionality development
	}

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDowod() {
		return dowod;
	}

	public void setDowod(String dowod) {
		this.dowod = dowod;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "Customer No: " + id_customer + ". Login: " + login +
				" | legitymujacy sie dowodem osobistym " + dowod + " podsiada stan konta o wartosci = " + saldo;
	}
}