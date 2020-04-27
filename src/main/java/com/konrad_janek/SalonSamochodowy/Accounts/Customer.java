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
		// ADD CUSTOMER CONSTRUCTOR
		this.login = login;
		this.password = password;
		this.dowod = dowod;
	}
	
	public Customer(int id_customer, String login, int saldo) {
		// FOR TRANSACTION PURPOSE
		this.id_customer = id_customer;
		this.login = login;
		this.saldo = saldo;
	}
	
	public Customer() {	// in case of further SpringBoot functionality development
	}

	public int getId_customer() {
		return id_customer;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getDowod() {
		return dowod;
	}

	public int getSaldo() {
		return saldo;
	}

	public boolean isRoot() {
		return root;
	}

	@Override
	public String toString() {
		return "Customer No: " + id_customer + ". Login: " + login +
				" | legitymujacy sie dowodem osobistym " + dowod + " podsiada stan konta o wartosci = " + saldo;
	}
}