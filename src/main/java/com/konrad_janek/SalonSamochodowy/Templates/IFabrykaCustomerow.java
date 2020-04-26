package com.konrad_janek.SalonSamochodowy.Templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;

public interface IFabrykaCustomerow {
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException;
	public List<Customer> wczytajCustomerow();
	public void wypiszCustomerow();
}
