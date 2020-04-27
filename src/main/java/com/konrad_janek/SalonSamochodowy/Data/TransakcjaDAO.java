package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;
import com.konrad_janek.SalonSamochodowy.Accounts.FabrykaCustomerow;
import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.ITransakcja;

public class TransakcjaDAO extends ConnectDatabase implements ITransakcja {
	private static TransakcjaDAO single_instance = null;	// FOR FABRYKATRANSAKCJI CLASS USAGE

	public static TransakcjaDAO getInstance()  // FOR FABRYKATRANSAKCJI CLASS USAGE
    {
        if (single_instance == null)
            single_instance = new TransakcjaDAO(true);
        return single_instance;
    }
	
	public TransakcjaDAO(boolean flag) {
		// BRIDGE CONSTRUCTOR FOR SINGLE INSTANCE FOR OBTAINING ACCESS 
		// TO METHOD LOADING CUSTOMER AND CAR FROM DAO FORM FOR FABRYKATRANSAKCJI CLASS USAGE
	}
	
	private int id_transakcja;
	private Date dataOddania;
	private Customer customer;
	private Samochod car;
	private boolean zatwierdzona = false;
	
	public TransakcjaDAO(int id_transakcja, Date dataOddania, Customer customer, Samochod car) {
		this.id_transakcja = id_transakcja;
		this.dataOddania = dataOddania;
		this.customer = customer;
		this.car = car;
	}
	
	public static TransakcjaDAO getSingle_instance() {
		return single_instance;
	}

	public int getId_transakcja() {
		return id_transakcja;
	}

	public Date getDataOddania() {
		return dataOddania;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Samochod getCar() {
		return car;
	}

	public boolean isZatwierdzona() {
		return zatwierdzona;
	}

	@Override
	public Samochod wczytajAuto(int car_id) {
		loadConnection();
		Samochod dawcaSamochod = null;
		PreparedStatement wczytajStatement = null;
		ResultSet result = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM car WHERE id_car=?");
			wczytajStatement.setInt(1, car_id);
			result = wczytajStatement.executeQuery();
			if(result.next())
				dawcaSamochod = FabrykaSalonSamochodowy.getInstance().zmienRekordNaSamochod(result);
			else
				System.out.println("Car no " + car_id + " not found");
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajAuto`: " + e.getMessage());
		} finally {
			try { result.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return dawcaSamochod;
	}
	
	@Override
	public Customer wczytajCustomera(int customer_id) {
		loadConnection();
		Customer dawcaCustomer = null;
		PreparedStatement wczytajStatement = null;
		ResultSet result = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM customer WHERE id_customer=?");
			wczytajStatement.setInt(1, customer_id);
			result = wczytajStatement.executeQuery();
			if(result.next())
				dawcaCustomer = FabrykaCustomerow.getInstance().zmienRekordNaCustomera(result);
			else
				System.out.println("Customer no " + customer_id + " not found");
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajCustomera`: " + e.getMessage());
		} finally {
			try { result.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return dawcaCustomer;
	}
	
}
