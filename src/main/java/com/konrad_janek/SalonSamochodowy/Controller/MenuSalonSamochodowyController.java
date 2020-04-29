package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;
import com.konrad_janek.SalonSamochodowy.Data.Samochod;

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
	
	@GetMapping("/errorRent")
	public String errorRent() {
		return "errors/errorRent";
	}
	
	@GetMapping("/errorRentAdmin")
	public String errorRentAdmin(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(customer.isRoot())
			return "errors/errorRentAdmin";
		else
			return "errors/errorRent";
	}
	
	@GetMapping("/rent()")		// TODO - okodowac wypozyczenie 
	public String wypozycz(HttpSession session, Model model, @RequestParam("days") String days) {
		int dlugoscWypozyczenia = Integer.parseInt(days);
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(dlugoscWypozyczenia < 1 && customer.isRoot())
			return "menu_zalogowanyAdmin";
		else if(dlugoscWypozyczenia < 1)
			return "menu_zalogowanyCustomer";
		int id_car = (int)session.getAttribute("id_car");
		Samochod car = FabrykaSalonSamochodowy.getInstance().wczytajSamochod(id_car);
		int transactionBill = car.getKaucja() + (dlugoscWypozyczenia * car.getCena());
		int id_customer = customer.getId_customer(customer.getLogin()); 	// when NICK added for user - change to nick verification credentials
		int saldo = customer.getSaldo(id_customer);
		int billAfterTransaction = saldo - transactionBill;
		if(saldo > 0 && transactionBill < saldo) {
			boolean ifSuccessfully = customer.checkIfCarFree(id_car);
			if(ifSuccessfully) {
				ifSuccessfully = customer.obciazKonto(id_customer, billAfterTransaction);
				if(ifSuccessfully) {
					ifSuccessfully = customer.rentACar(id_customer, id_car, dlugoscWypozyczenia);
					if(ifSuccessfully) {
						ifSuccessfully = customer.addTransaction(id_customer, id_car);
						if(ifSuccessfully && customer.isRoot())
							return "menu_zalogowanyAdmin";
						else if(ifSuccessfully)
							return "menu_zalogowanyCustomer";
						else
							System.out.println("Failed to store transaction in system. Contact with company service");
					} else
						System.out.println("Failed to rent a car, contact with service to get Your money back");
				} else
					System.out.println("Failed to bill account");
			} else
				System.out.println("Someone managed to rent car before You");
		}
		if(!customer.isRoot())
			return "errorRent";
		else
			return "error";
	}
	
	
}
