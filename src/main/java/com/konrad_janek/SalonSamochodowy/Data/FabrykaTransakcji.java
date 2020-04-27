package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;
import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.IFabrykaTransakcji;

public class FabrykaTransakcji extends ConnectDatabase implements IFabrykaTransakcji{

	private static ArrayList<TransakcjaDAO> listaTransakcji = new ArrayList<TransakcjaDAO>();
	private static ArrayList<TransakcjaDAO> listaTransakcjiZwrot = new ArrayList<TransakcjaDAO>();
	private ArrayList<Transakcja> listaTransakcjiCustomer;
	
	public FabrykaTransakcji(int id_customer) {
		List<TransakcjaDAO> list = wczytajTransakcjeZwrotCustomer(id_customer);
		ArrayList<Transakcja> listaCustomera = new ArrayList<Transakcja>();
		// TRANSLACJA
		for (TransakcjaDAO transakcja : list) {
			listaCustomera.add(new Transakcja(transakcja));
		}
		// ZAPIS DO listaTransakcjiCustomer
		listaTransakcjiCustomer.addAll(listaCustomera);
	}
	
	public FabrykaTransakcji(boolean listaTransakcjiZwrotFlag) {
		if(listaTransakcjiZwrotFlag) {
			listaTransakcjiZwrot.clear();
			listaTransakcjiZwrot.addAll(wczytajTransakcjeZwrot());
			wypiszTransakcje();
		} else {
			listaTransakcji.clear();
			listaTransakcji.addAll(wczytajTransakcje());
			wypiszTransakcje();
		}
	}
	
	public ArrayList<Transakcja> getListaTransakcjiCustomer() {
		return listaTransakcjiCustomer;
	}
	
	public ArrayList<TransakcjaDAO> getListaTransakcji() {
		return listaTransakcji;
	}
	
	public ArrayList<TransakcjaDAO> getListaTransakcjiZwrot() {
		return listaTransakcjiZwrot;
	}
	
	@Override
	public TransakcjaDAO zmienRekordNaTransakcje(ResultSet result) throws SQLException {
		int id_transakcja = result.getInt("id_transakcja");
		Date dataOddania = result.getDate("dataOddania");
		int customer_id = result.getInt("customer_id");
		int car_id = result.getInt("car_id");
		
		Customer customer = TransakcjaDAO.getInstance().wczytajCustomera(customer_id);
		Samochod car = TransakcjaDAO.getInstance().wczytajAuto(car_id);
		
		TransakcjaDAO dawcaTransakcja = new TransakcjaDAO(id_transakcja, dataOddania, customer, car);
		return dawcaTransakcja;
	}

	@Override
	public List<TransakcjaDAO> wczytajTransakcje() {
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transakcja ORDER BY id_transakcja");
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
	public List<TransakcjaDAO> wczytajTransakcjeZwrot() {
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transakcja WHERE zatwierdzona=FALSE ORDER BY id_transakcja");
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
	public List<TransakcjaDAO> wczytajTransakcjeZwrotCustomer(int id_customer) {
		loadConnection();
		List<TransakcjaDAO> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM transakcja WHERE customer_id=? AND zatwierdzona=FALSE ORDER BY id_transakcja");
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
