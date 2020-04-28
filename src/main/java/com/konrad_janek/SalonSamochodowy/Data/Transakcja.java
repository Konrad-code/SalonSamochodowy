package com.konrad_janek.SalonSamochodowy.Data;

import java.time.LocalDate;
import java.util.Date;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;

public class Transakcja {
	private int id_transakcja;
	private LocalDate dataOddania;
	private boolean zatwierdzona;

//	BY GETTERS FROM private Customer customer;
	private int customer_id;
	private String customer_login;
	private int customer_saldo;
	
//	BY GETTERS FROM private Samochod car;
	private int car_id;
	private String car_model;
	private int car_cena;
	private int car_kaucja;
	private LocalDate car_dataWypozyczenia;
	private int car_dlugoscWypozyczenia;
	
	public Transakcja(TransakcjaDAO transakcja) {
		this.id_transakcja = transakcja.getId_transakcja();
		this.dataOddania = transakcja.getDataOddania();
		this.zatwierdzona = transakcja.isZatwierdzona();
		this.customer_id = transakcja.getCustomer().getId_customer();
		this.customer_login = transakcja.getCustomer().getLogin();		// after implementing nick attribute login should be hidden as sensitive data
		this.customer_saldo = transakcja.getCustomer().getSaldo();
		this.car_id = transakcja.getCar().getId_car();
		this.car_model = transakcja.getCar().getModel();
		this.car_cena = transakcja.getCar().getCena();
		this.car_kaucja = transakcja.getCar().getKaucja();
		this.car_dataWypozyczenia = transakcja.getCar().getDataWypozyczenia();
		this.car_dlugoscWypozyczenia = transakcja.getCar().getDlugoscWypozyczenia();
	}

	public int getId_transakcja() {
		return id_transakcja;
	}

	public LocalDate getDataOddania() {
		return dataOddania;
	}

	public boolean isZatwierdzona() {
		return zatwierdzona;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public String getCustomer_login() {
		return customer_login;
	}

	public int getCustomer_saldo() {
		return customer_saldo;
	}

	public int getCar_id() {
		return car_id;
	}

	public String getCar_model() {
		return car_model;
	}

	public int getCar_cena() {
		return car_cena;
	}

	public int getCar_kaucja() {
		return car_kaucja;
	}

	public LocalDate getCar_dataWypozyczenia() {
		return car_dataWypozyczenia;
	}

	public int getCar_dlugoscWypozyczenia() {
		return car_dlugoscWypozyczenia;
	}
}
