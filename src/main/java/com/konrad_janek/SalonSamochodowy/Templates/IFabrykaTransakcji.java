package com.konrad_janek.SalonSamochodowy.Templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Data.TransakcjaDAO;

public interface IFabrykaTransakcji {
	public TransakcjaDAO zmienRekordNaTransakcje(ResultSet result) throws SQLException;
	public List<TransakcjaDAO> wczytajTransakcje();
	public List<TransakcjaDAO> wczytajTransakcjeZwrot();
	public List<TransakcjaDAO> wczytajTransakcjeZwrotCustomer(int id_customer);
	public void wypiszTransakcje();
}
