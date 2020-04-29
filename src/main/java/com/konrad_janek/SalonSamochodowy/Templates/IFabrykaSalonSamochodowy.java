package com.konrad_janek.SalonSamochodowy.Templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Data.Samochod;

public interface IFabrykaSalonSamochodowy {
	public Samochod zmienRekordNaSamochod(ResultSet result) throws SQLException;
	public Samochod wczytajSamochod(int id_car);
	public List<Samochod> wczytajSamochody();
	public List<Samochod> wczytajWszystkieSamochody();
	public void wypiszSamochody();
}
