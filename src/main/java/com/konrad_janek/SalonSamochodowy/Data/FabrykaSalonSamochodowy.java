package com.konrad_janek.SalonSamochodowy.Data;

import java.util.ArrayList;

public class FabrykaSalonSamochodowy {

	private ArrayList<Samochod> listaSamochody;

	public FabrykaSalonSamochodowy() {
		super();
		this.listaSamochody = new ArrayList<Samochod>();
		listaSamochody.add(new Samochod("BMW", 20, false));
		listaSamochody.add(new Samochod("OPEL", 30, false));
		listaSamochody.add(new Samochod("Toyota", 150, false));
		listaSamochody.add(new Samochod("Mitsubishi", 32, false));
		listaSamochody.add(new Samochod("Ferrari", 40, false));
		listaSamochody.add(new Samochod("Fiat", 70, false));
		listaSamochody.add(new Samochod("Lamborghini", 60, false));
		
	}

	public ArrayList<Samochod> getListaSamochody() {
		return listaSamochody;
	}
	
}
