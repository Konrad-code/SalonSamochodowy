package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.Date;

public class Samochod {
	
	{
		marka = "";
		model = "";
		cena = 0;
		kaucja = 0;
		id_car = 0;
		dataWypozyczenia = null;
		dlugoscWypozyczenia = 0;
	}
	
	private String marka;
	private String model;
	private int cena;
	int kaucja;
	int id_car;
	private Date dataWypozyczenia;
	private int dlugoscWypozyczenia;
	
	public Samochod(String marka, String model, int cena, int kaucja, int id_car) {
		this.marka = marka;
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
		this.id_car = id_car;
	}
	
	public Samochod(int id_car, String model, int cena, int kaucja, Date dataWypozyczenia, int dlugoscWypozyczenia) {
		// FOR TRANSACTION PURPOSE
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
		this.id_car = id_car;
		this.dlugoscWypozyczenia = dlugoscWypozyczenia;
		this.dataWypozyczenia = dataWypozyczenia;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getMarka() {
		return marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getKaucja() {
		return kaucja;
	}

	public void setDataOddania(int dataOddania) {
		this.kaucja = kaucja;
	}

	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	public void setKaucja(int kaucja) {
		this.kaucja = kaucja;
	}

	public Date getDataWypozyczenia() {
		return dataWypozyczenia;
	}

	public void setDataWypozyczenia(Date dataWypozyczenia) {
		this.dataWypozyczenia = dataWypozyczenia;
	}

	public int getDlugoscWypozyczenia() {
		return dlugoscWypozyczenia;
	}

	public void setDlugoscWypozyczenia(int dlugoscWypozyczenia) {
		this.dlugoscWypozyczenia = dlugoscWypozyczenia;
	}
}
