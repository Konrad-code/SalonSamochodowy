package com.konrad_janek.SalonSamochodowy.Data;

import java.util.ArrayList;

public class FabrykaSalonSamochodowy {

	private ArrayList<Samochod> listaSamochody = new ArrayList<Samochod>();

	public FabrykaSalonSamochodowy() {
		super();
		listaSamochody.add(new Samochod("Mercedes", "Vito", 25, false));
		listaSamochody.add(new Samochod("Mercedes", "Sprinter", 30, false));
		listaSamochody.add(new Samochod("Toyota", "Yaris", 15, false));
		listaSamochody.add(new Samochod("Toyota", "Auris", 15, false));
		listaSamochody.add(new Samochod("Skoda", "Fabia", 15, false));
		listaSamochody.add(new Samochod("Ford", "Ka", 15, false));
		listaSamochody.add(new Samochod("Renault", "Twingo", 15, false));
		listaSamochody.add(new Samochod("Citroen", "DS3", 15, false));
		listaSamochody.add(new Samochod("Mercedes", "Klassa C", 50, false));
		listaSamochody.add(new Samochod("Bentley", "Continental", 70, false));
		listaSamochody.add(new Samochod("BMW", "7 Series  ", 50, false));
		listaSamochody.add(new Samochod("Aston Martin", "BD9", 70, false));
		listaSamochody.add(new Samochod("Maserati", "Ghibli", 70, false));
		listaSamochody.add(new Samochod("Ford", "Mustang", 50, false));
		listaSamochody.add(new Samochod("Mercedes", "SLK", 50, false));
		listaSamochody.add(new Samochod("Lamborghini", "Huracan", 80, false));
		listaSamochody.add(new Samochod("Chevrolet", "Corvette C7", 60, false));
		listaSamochody.add(new Samochod("SRT", "Viper", 60, false));
		listaSamochody.add(new Samochod("BMW", "M3", 40, false));

		
	}

	public ArrayList<Samochod> getListaSamochody() {
		return listaSamochody;
	}
	
}
