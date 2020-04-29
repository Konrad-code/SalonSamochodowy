package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;

@Controller
public class MenuSalonSamochodowyController {

	FabrykaSalonSamochodowy fabrykaWszystkichAut = FabrykaSalonSamochodowy.getInstance();	// FLOTA SALONU
	FabrykaSalonSamochodowy fabrykaSalonSamochodowy = new FabrykaSalonSamochodowy(true);	// DOSTEPNE AUTA
	
//////////////////////////////////////////		DOSTEPNE AUTA   	/////////////////////////////////////////////////////////	
	
	@GetMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("wszystkieSamochody", fabrykaSalonSamochodowy.getListaSamochody());
		return "menu";
	}
	
	@GetMapping("/menu_zalogowanyCustomer")
	public String menu_zalogowanyCustomer(Model model, HttpSession session) {
		model.addAttribute("dostepneSamochody", fabrykaSalonSamochodowy.getListaSamochody());
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		System.out.println("Login of customer entering menu_zalogowanyCustomer: " + customer.getLogin());
		return "menu_zalogowanyCustomer";
	}
	
	@GetMapping("/menu_zalogowanyAdmin")
	public String menu_zalogowanyAdmin(Model model, HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		model.addAttribute("dostepneSamochody", fabrykaSalonSamochodowy.getListaSamochody());
		System.out.println("Login of Admin customer entering menu_zalogowanyAdmin: " + customer.getLogin());
		return "admin/menu_zalogowanyAdmin";
	}
	
//////////////////////////////////////////    FLOTA SALONU   /////////////////////////////////////////////////////////
	
	@GetMapping("/menu_wszystkie")
	public String menu_wszystkie(Model model) {
		model.addAttribute("wszystkieSamochody", fabrykaWszystkichAut.getListaWszystkieSamochody());
		return "menu_wszystkie";
	}
	
	@GetMapping("/menu_zalogowanyCustomerWszystkie")
	public String menu_zalogowanyCustomerWszystkie(Model model, HttpSession session) {
		model.addAttribute("wszystkieSamochody", fabrykaWszystkichAut.getListaWszystkieSamochody());
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		System.out.println("Login of customer entering menu_zalogowanyCustomer: " + customer.getLogin());
		return "menu_zalogowanyCustomerWszystkie";
	}
	
	@GetMapping("/menu_zalogowanyAdminWszystkie")
	public String menu_zalogowanyAdminWszystkie(Model model, HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		model.addAttribute("wszystkieSamochody", fabrykaWszystkichAut.getListaWszystkieSamochody());
		System.out.println("Login of Admin customer entering menu_zalogowanyAdminWszystkie: " + customer.getLogin());
		return "admin/menu_zalogowanyAdminWszystkie";
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
	
	
}
