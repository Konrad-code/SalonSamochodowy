package com.konrad_janek.SalonSamochodowy.Templates;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;

public interface ICRUD {
	public int getSaldo(int id_customer);
	public boolean checkLogin(String login);
	public void deleteCustomer(String userToRemove);
	public void deleteCustomers();
	public boolean tryLogin(String login, String password);
	public void addCustomer(Customer newCustomer);
	public Customer getCustomerForEdit(String userToEdit);
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException;
}
