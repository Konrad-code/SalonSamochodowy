package com.konrad_janek.SalonSamochodowy.Data;

import com.konrad_janek.SalonSamochodowy.Accounts.Account;

public class Samochod {
	private String nazwa;
	private int cena;
	private boolean czyWypozyczony;
	int dataOddania;
	private Account ktoWypozyczyl;
	
	public Samochod(String nazwa, int cena, boolean czyWypozyczony) {
		super();
		this.nazwa = nazwa;
		this.cena = cena;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

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
