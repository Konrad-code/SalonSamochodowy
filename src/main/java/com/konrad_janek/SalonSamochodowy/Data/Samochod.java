package com.konrad_janek.SalonSamochodowy.Data;

public class Samochod {
	private String marka;
	private String model;
	private int cena;
	int kaucja;
	
	public Samochod(String marka, String model, int cena, int kaucja) {
		this.marka = marka;
		this.model = model;
		this.cena = cena;
		this.kaucja = kaucja;
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
}
