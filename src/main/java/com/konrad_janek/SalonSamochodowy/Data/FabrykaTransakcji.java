package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;
import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.IFabrykaTransakcji;

public class FabrykaTransakcji extends ConnectDatabase implements IFabrykaTransakcji{

	private static ArrayList<TransakcjaDAO> listaTransakcji = new ArrayList<TransakcjaDAO>();		// ADMIN ALL
	private static ArrayList<TransakcjaDAO> listaTransakcjiZwrot = new ArrayList<TransakcjaDAO>();	// ADMIN TO ACCEPT
	private ArrayList<Transakcja> listaTransakcjiCustomer = new ArrayList<Transakcja>();											// CUSTOMER
	
	public FabrykaTransakcji() {	// SPECIAL FOR AutaUzytkownikaController
	}
	
	public FabrykaTransakcji(int id_customer) {							// CUSTOMER
		List<TransakcjaDAO> list = new ArrayList<TransakcjaDAO>();
		list.addAll(wczytajTransakcjeZwrotCustomer(id_customer));
		System.out.println(list.size());
		ArrayList<Transakcja> listaCustomera = new ArrayList<Transakcja>();
		for (TransakcjaDAO transakcja : list)					// TRANSLACJA
			listaCustomera.add(new Transakcja(transakcja));
		listaTransakcjiCustomer.addAll(listaCustomera);			// ZAPIS DO listaTransakcjiCustomer
	}
	
	public FabrykaTransakcji(boolean listaTransakcjiZwrotFlag) {
		if(listaTransakcjiZwrotFlag) {									// ADMIN TO ACCEPT
			listaTransakcjiZwrot.clear();
			listaTransakcjiZwrot.addAll(wczytajTransakcjeZwrot());
			wypiszTransakcje();
		} else {														// ADMIN ALL
			listaTransakcji.clear();
			listaTransakcji.addAll(wczytajTransakcje());
			wypiszTransakcje();
		}
	}
	
	public ArrayList<Transakcja> getListaTransakcjiCustomer() {			// CUSTOMER
		return listaTransakcjiCustomer;
	}
	
	public ArrayList<TransakcjaDAO> getListaTransakcji() {				// ADMIN ALL
		return listaTransakcji;
	}
	
	public ArrayList<TransakcjaDAO> getListaTransakcjiZwrot() {			// ADMIN TO ACCEPT
		return listaTransakcjiZwrot;
	}
	
	@Override
	public TransakcjaDAO zmienRekordNaTransakcje(ResultSet result) throws SQLException {
		LocalDate dataOddania = null;
		int id_transakcja = result.getInt("id_transaction");
		if(result.getDate("dataOddania") != null)
			dataOddania = result.getDate("dataOddania").toLocalDate();
		int customer_id = result.getInt("customer_id");
		int car_id = result.getInt("car_id");
		
		Customer customer = TransakcjaDAO.getInstance().wczytajCustomera(customer_id);
		Samochod car = TransakcjaDAO.getInstance().wczytajAuto(car_id);
		
		TransakcjaDAO dawcaTransakcja = new TransakcjaDAO(id_transakcja, dataOddania, customer, car);
		return dawcaTransakcja;
	}

	@Override
	public List<TransakcjaDAO> wczytajTransakcje() {					// ADMIN ALL
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transaction ORDER BY id_transakcja");
			results = wczytajStatement.executeQuery();
			while(results.next()) {
				TransakcjaDAO dawcaTransakcja = zmienRekordNaTransakcje(results);
				list.add(dawcaTransakcja);
			}
			System.out.println("Komenda `wczytajTransakcje` zakonczona powodzeniem");
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajTransakcje`: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return list;
	}
	
	@Override
	public List<TransakcjaDAO> wczytajTransakcjeZwrot() {				// ADMIN TO ACCEPT
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transaction WHERE zatwierdzona=FALSE ORDER BY id_transakcja");
			results = wczytajStatement.executeQuery();
			while(results.next()) {
				TransakcjaDAO dawcaTransakcja = zmienRekordNaTransakcje(results);
				list.add(dawcaTransakcja);
			}
			System.out.println("Komenda `wczytajTransakcjeZwrot` zakonczona powodzeniem");
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajTransakcjeZwrot`: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return list;
	}
	
	@Override
	public List<TransakcjaDAO> wczytajTransakcjeZwrotCustomer(int id_customer) {	// CUSTOMER
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transaction WHERE customer_id=? "
					+ "AND zatwierdzona=FALSE ORDER BY id_transaction");	// previously "ORDER BY (DATEDIFF(day, dataWypozyczenia, CURRENT_DATE) - dlugoscWypozyczenia) DESC"
			wczytajStatement.setInt(1, id_customer);
			results = wczytajStatement.executeQuery();
			while(results.next()) {
				TransakcjaDAO dawcaTransakcja = zmienRekordNaTransakcje(results);
				list.add(dawcaTransakcja);
			}
			System.out.println("Komenda `wczytajTransakcjeZwrotCustomer` zakonczona powodzeniem");
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajTransakcjeZwrotCustomer`: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return list;
	}

	@Override
	public void wypiszTransakcje() {
		int licznik = 1;
		System.out.println("Lista transakcji: ");
		for (TransakcjaDAO transakcja : listaTransakcji) {
			System.out.println(licznik + ". LOGIN: " + transakcja.getCustomer().getLogin() 
									   + " | ID_CUSTOMER: " + transakcja.getCustomer().getId_customer()
									   + " | SALDO: " + transakcja.getCustomer().getSaldo() 
									   + " | ID_CAR: " + transakcja.getCar().getId_car() 
									   + " | MODEL: " + transakcja.getCar().getModel()
									   + " | CENA: " + transakcja.getCar().getCena()
									   + " | KAUCJA: " + transakcja.getCar().getKaucja()
									   + " | DLUGOSC WYPOZYCZENIA: " + transakcja.getCar().getDataWypozyczenia()
									   + " | DATA WYPOZYCZENIA: " + transakcja.getCar().getDataWypozyczenia()
									   );
			licznik++;
		}
	}
}
