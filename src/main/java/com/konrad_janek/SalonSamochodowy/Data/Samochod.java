package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.Date;
import java.time.LocalDate;

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
	private int kaucja;
	private int id_car;
	private LocalDate dataWypozyczenia;
	private int dlugoscWypozyczenia;
	
	public Samochod() {		// FOR `wczytajSamochod`(int id_car) INICIALIZATION PURPOSE
	}
	
	public Samochod(String marka, String model, int cena, int kaucja, int id_car) {
		this.marka = marka;
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
		this.id_car = id_car;
	}
	
	public Samochod(int id_car, String model, int cena, int kaucja, LocalDate dataWypozyczenia, int dlugoscWypozyczenia) {
		// FOR TRANSACTION PURPOSE
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
		this.id_car = id_car;
		this.dlugoscWypozyczenia = dlugoscWypozyczenia;
		this.dataWypozyczenia = dataWypozyczenia;
	}

	public String getMarka() {
		return marka;
	}

	public String getModel() {
		return model;
	}

	public int getCena() {
		return cena;
	}

	public int getKaucja() {
		return kaucja;
	}

	public int getId_car() {
		return id_car;
	}

	public LocalDate getDataWypozyczenia() {
		return dataWypozyczenia;
	}

	public int getDlugoscWypozyczenia() {
		return dlugoscWypozyczenia;
	}
}
