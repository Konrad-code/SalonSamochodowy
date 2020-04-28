package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaTransakcji;

@Controller
public class AutaUzytkownikaController {
	
FabrykaTransakcji fabrykaTransakcji = new FabrykaTransakcji();
	
	@GetMapping("/autaUzytkownika")
	public String listaAutPage(@ModelAttribute CustomerDAO customer, Model model) {
		int id_customer = customer.getId_customer(customer.getLogin()); 	// when NICK added for user - change to nick verification credentials
		// wywolaj na farbcyetransakcji metode wypelniajaca z parametrem (id_customer)
		fabrykaTransakcji.wczytajTransakcjeZwrotCustomer(id_customer);
		model.addAttribute("zwroty", fabrykaTransakcji.getListaTransakcjiCustomer());
		return "autaUzytkownika";
	}
	
	@GetMapping("/zwroc")		// TODO
	public String onclick(@ModelAttribute CustomerDAO customer,
						  @ModelAttribute CustomerDAO cleanCustomer,
						  @RequestParam("id_car") int id_car) {
		System.out.println("Customer: " + customer.getLogin() + " | Clean customer: " + cleanCustomer.getLogin());
		
		return "costam";
	}

	@PostMapping("/zwroc")		// TODO - okodowac wypozyczenie (przede wszystkim w uzaleznieniu czy auto nie jest wypozyczone. 
									// Zrob tutaj zaciag z bazy id_car dla tych ktore maja customer_id == null, zrob array liste, 
									// wpisz do niej wszystkie te id_car, a potem flage ktora bedzie dawana true jesli id_car 
									// z request parametru dla tego zapytania bedzie sie pokrywal z jakims na (uprzednio zrobionej 
									// i wertowanej teraz) liscie. Potem sprawdzenie flagi i dopiero metoda wypozyczenia dalsza.
									// rowniez nalezy dodac do ustawiania flagi sprawdzenie czy customer.getLogin().length() > 0; - czy zalogowany
	public String zwroc(Model model) {
		if(true)
			return "menu";
		else
			return "login";
	}
}
