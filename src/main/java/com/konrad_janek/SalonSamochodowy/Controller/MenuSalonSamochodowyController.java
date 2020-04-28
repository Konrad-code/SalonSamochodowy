package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;

@Controller
public class MenuSalonSamochodowyController {

	FabrykaSalonSamochodowy fabrykaWszystkichAut = FabrykaSalonSamochodowy.getInstance();
	FabrykaSalonSamochodowy fabrykaSalonSamochodowy = new FabrykaSalonSamochodowy(true);
	
	@GetMapping("/menu")
	public String listaAutPage(Model model) {
		model.addAttribute("samochody", fabrykaSalonSamochodowy.getListaSamochody());
		return "menu";
	}
	
	@GetMapping("/rent()")		// TODO - okodowac wypozyczenie (przede wszystkim w uzaleznieniu czy auto nie jest wypozyczone. 
									// Zrob tutaj zaciag z bazy id_car dla tych ktore maja customer_id == null, zrob array liste, 
									// wpisz do niej wszystkie te id_car, a potem flage ktora bedzie dawana true jesli id_car 
									// z request parametru dla tego zapytania bedzie sie pokrywal z jakims na (uprzednio zrobionej 
									// i wertowanej teraz) liscie. Potem sprawdzenie flagi i dopiero metoda wypozyczenia dalsza.
									// rowniez nalezy dodac do ustawiania flagi sprawdzenie czy customer.getLogin().length() > 0; - czy zalogowany
	public String wypozycz(Model model) {
		if(true)
			return "menu";
		else
			return "login";
	}
	
	@GetMapping("/menu_zalogowanyAdmin")
	public String menu_zalogowanyAdmin(Model model, HttpSession session) {
		model.addAttribute("samochody", fabrykaSalonSamochodowy.getListaSamochody());
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		System.out.println("Login of Admin customer entering menu_zalogowanyAdmin: " + customer.getLogin());
		return "menu_zalogowanyCustomer";
	}
	
	@GetMapping("/menu_zalogowanyCustomer")
	public String menu_zalogowanyCustomer(Model model, HttpSession session) {
		model.addAttribute("samochody", fabrykaSalonSamochodowy.getListaSamochody());
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		System.out.println("Login of customer entering menu_zalogowanyCustomer: " + customer.getLogin());
		return "menu_zalogowanyCustomer";
	}
}
