package com.konrad_janek.SalonSamochodowy.Templates;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;
import com.konrad_janek.SalonSamochodowy.Data.Samochod;

public interface ITransakcja {
	public Samochod wczytajAuto(int car_id);
	public Customer wczytajCustomera(int customer_id);
}
