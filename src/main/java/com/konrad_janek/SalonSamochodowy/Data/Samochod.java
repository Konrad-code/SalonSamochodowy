package com.konrad_janek.SalonSamochodowy.Data;

public class Samochod {
	private String marka;
	private String model;
	private int cena;
	int kaucja;
	int id_car;
	
	public Samochod(String marka, String model, int cena, int kaucja, int id_car) {
		this.marka = marka;
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
		this.id_car = id_car;
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
	
}
