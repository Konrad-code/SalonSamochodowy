package com.konrad_janek.SalonSamochodowy.Data;

import com.konrad_janek.SalonSamochodowy.Accounts.Account;

public class Samochod {
	private String marka;
	private String model;
	private int cena;
	private boolean czyWypozyczony;
	int dataOddania;
	private Account ktoWypozyczyl;

	
	public Samochod(String marka, String model, int cena, boolean czyWypozyczony) {
		super();
		this.marka = marka;
		this.model = model;
		this.cena = cena;
	}

	public void setMarka(String marka) {this.marka = marka;}

	public String getMarka() {return marka;}

	public String getModel() {return model;}

	public void setModel(String model) {this.model = model;}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isCzyWypozyczony() {
		return czyWypozyczony;
	}

	public void setCzyWypozyczony(boolean czyWypozyczony) {
		this.czyWypozyczony = czyWypozyczony;
	}

	public int getDataOddania() {
		return dataOddania;
	}

	public void setDataOddania(int dataOddania) {
		this.dataOddania = dataOddania;
	}

	public Account getKtoWypozyczyl() {
		return ktoWypozyczyl;
	}

	public void setKtoWypozyczyl(Account ktoWypozyczyl) {
		this.ktoWypozyczyl = ktoWypozyczyl;
	}
		
}
