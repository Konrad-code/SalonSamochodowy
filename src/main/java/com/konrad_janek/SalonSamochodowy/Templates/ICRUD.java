package com.konrad_janek.SalonSamochodowy.Templates;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;

public interface ICRUD {
	public int getSaldo(int id_customer);
	public boolean checkLogin(String login);
	public boolean deleteCustomer(String userToRemove);
	public boolean deleteCustomers();
	public boolean addCustomer(Customer newCustomer);
	public Customer getCustomerForEdit(String userToEdit);
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException;
	public boolean updateCustomer(Customer loadedCustomerForEdit);
	public boolean obciazKonto(int id_customer, int transactionBill);
	public boolean checkDowod(String dowod);
	public int getId_customer(String login);
	public boolean tryLogin(String login, String password);
}
