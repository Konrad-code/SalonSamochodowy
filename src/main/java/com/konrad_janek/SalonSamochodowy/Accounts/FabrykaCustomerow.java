package com.konrad_janek.SalonSamochodowy.Accounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.IFabrykaCustomerow;

public class FabrykaCustomerow extends ConnectDatabase implements IFabrykaCustomerow{
	private static ArrayList<Customer> listCustomers = new ArrayList<Customer>();
	
	public FabrykaCustomerow() {
		listCustomers.clear();
		listCustomers.addAll(wczytajCustomerow());
		wypiszCustomerow();
	}
	
	@Override
	public void wypiszCustomerow() {
		int licznik = 1;
		System.out.println("Lista customerow zarejestrowanych w bazie wypozyczalni: ");
		for (Customer customer : listCustomers) {
			System.out.println(licznik + ". LOGIN: " + customer.getLogin() + " | PASSWORD: " + customer.getPassword()
			+ " | SALDO: " + customer.getSaldo() + " | ID_CUSTOMER: " + customer.getId_customer() + " | ROOT: " + customer.isRoot());
			licznik++;
		}
	}
	
	@Override
	public Customer zmienRekordNaCustomera(ResultSet result) throws SQLException {
		String login = result.getString("login");
		String password = result.getString("password");
		String dowod = result.getString("dowod");
		
		Customer dawcaCustomer = new Customer(login, password, dowod);
		return dawcaCustomer;	
	}

	@Override
	public List<Customer> wczytajCustomerow() {
		loadConnection();
		List<Customer> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM customer ORDER BY id_customer");
			results = wczytajStatement.executeQuery();
			while(results.next()) {
				Customer dawcaCustomer = zmienRekordNaCustomera(results);
				list.add(dawcaCustomer);
			}
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajCustomerow`: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return list;
	}

	public ArrayList<Customer> getListaCustomerow() {
		return listCustomers;
	}
}
