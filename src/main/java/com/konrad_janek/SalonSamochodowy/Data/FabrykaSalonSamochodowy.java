package com.konrad_janek.SalonSamochodowy.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.konrad_janek.SalonSamochodowy.Connection.ConnectDatabase;
import com.konrad_janek.SalonSamochodowy.Templates.IFabrykaSalonSamochodowy;

public class FabrykaSalonSamochodowy extends ConnectDatabase implements IFabrykaSalonSamochodowy{

	private static FabrykaSalonSamochodowy single_instance = null;
	private static ArrayList<Samochod> listaSamochody = new ArrayList<Samochod>();

	public static FabrykaSalonSamochodowy getInstance()
    {
        if (single_instance == null)
            single_instance = new FabrykaSalonSamochodowy();
        return single_instance;
    }

	private FabrykaSalonSamochodowy() {
		listaSamochody.addAll(wczytajSamochody());
		
		/* Dane listy samochodow ktore w pozniejszej fazie projektu zostaly zaciagane z bazy danych #depreciated
		listaSamochody.add(new Samochod("Mercedes", "Vito", 25, 5000));
		listaSamochody.add(new Samochod("Mercedes", "Sprinter", 30, 5000));
		listaSamochody.add(new Samochod("Toyota", "Yaris", 15, 2500));
		listaSamochody.add(new Samochod("Toyota", "Auris", 15, 3000));
		listaSamochody.add(new Samochod("Skoda", "Fabia", 15, 3000));
		listaSamochody.add(new Samochod("Ford", "Ka", 15, 2500));
		listaSamochody.add(new Samochod("Renault", "Twingo", 15, 2500));
		listaSamochody.add(new Samochod("Citroen", "DS3", 15, 3000));
		listaSamochody.add(new Samochod("Mercedes", "Klassa C", 50, 7000));
		listaSamochody.add(new Samochod("Bentley", "Continental", 70, 25000));
		listaSamochody.add(new Samochod("BMW", "7 Series  ", 50, 20000));
		listaSamochody.add(new Samochod("Aston Martin", "BD9", 70, 35000));
		listaSamochody.add(new Samochod("Maserati", "Ghibli", 70, 30000));
		listaSamochody.add(new Samochod("Ford", "Mustang", 50, 15000));
		listaSamochody.add(new Samochod("Mercedes", "SLK", 50, 20000));
		listaSamochody.add(new Samochod("Lamborghini", "Huracan", 80, 45000));
		listaSamochody.add(new Samochod("Chevrolet", "Corvette C7", 60, 23000));
		listaSamochody.add(new Samochod("SRT", "Viper", 60, 28000));
		listaSamochody.add(new Samochod("BMW", "M3", 40, 13000));	
		*/
		wypiszSamochody();
	}
	
	@Override
	public void wypiszSamochody() {
		int licznik = 1;
		System.out.println("Lista samochodow dostepnych w bazie wypozyczalni: ");
		for (Samochod samochod : listaSamochody) {
			System.out.println(licznik + ". MARKA: " + samochod.getMarka() + " | MODEL: " + samochod.getModel()
			+ " | CENA ZA DOBE WYPOZYCZENIA: " + samochod.getCena() + " | KAUCJA: " + samochod.getKaucja());
			licznik++;
		}
	}
	
	@Override
	public Samochod zmienRekordNaSamochod(ResultSet result) throws SQLException {
		String marka = result.getString("marka");
		String model = result.getString("model");
		int cena = result.getInt("cena");
		int kaucja = result.getInt("kaucja");
		
		Samochod dawcaSamochod = new Samochod(marka, model, cena, kaucja);
		return dawcaSamochod;	
	}

	@Override
	public List<Samochod> wczytajSamochody() {
		loadConnection();
		List<Samochod> list = new ArrayList<>();
		PreparedStatement wczytajStatement = null;
		ResultSet results = null;
		
		try {
			wczytajStatement = connection.prepareStatement("SELECT * FROM car");
			results = wczytajStatement.executeQuery();
			while(results.next()) {
				Samochod dawcaSamochod = zmienRekordNaSamochod(results);
				list.add(dawcaSamochod);
			}
		} catch (SQLException e) {
			System.err.println("Niepowodzenie przy wykonywaniu komendy `wczytajSamochody`: " + e.getMessage());
		} finally {
			try { results.close(); } catch (Exception e) { /* leave action */ }
			try { wczytajStatement.close(); } catch (Exception e) { /* leave action */ }
			closeConnection();
		}
		return list;
	}

	public ArrayList<Samochod> getListaSamochody() {
		return listaSamochody;
	}
}
