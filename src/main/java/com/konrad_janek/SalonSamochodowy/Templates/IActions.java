package com.konrad_janek.SalonSamochodowy.Templates;

public interface IActions {
	public boolean addTransaction(int id_customer, int id_car);
	public boolean checkIfCarFree(int id_car);
	public boolean rentACar(int id_customer, int id_car, int dlugoscWypozyczenia);
	public boolean returnACarFromTransaction(int id_transaction);
	public boolean returnACar(int id_customer, int id_car);
}
