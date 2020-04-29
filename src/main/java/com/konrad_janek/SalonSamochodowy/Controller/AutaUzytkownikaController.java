package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Data.FabrykaTransakcji;

@Controller
public class AutaUzytkownikaController {
	
FabrykaTransakcji fabrykaTransakcji = new FabrykaTransakcji();
	
	@GetMapping("/autaUzytkownika")
	public String listaAutPage(HttpSession session, Model model) {
		System.out.println("Entered @GetMapping `autaUzytkownika` ");
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		int id_customer = customer.getId_customer(customer.getLogin()); 	// when NICK added for user - change to nick verification credentials
		System.out.println("Login customer: " + customer.getLogin());
		System.out.println("ID CUSTOMER: " + id_customer);
		// wywolaj na fabrycetransakcji metode wypelniajaca z parametrem (id_customer)
		FabrykaTransakcji fabrykaTransakcjiUser = new FabrykaTransakcji(id_customer);
		fabrykaTransakcjiUser.wczytajTransakcjeZwrotCustomer(id_customer);
		model.addAttribute("zwroty", fabrykaTransakcjiUser.getListaTransakcjiCustomer());
		if(customer.isRoot())
			return "admin/autaUzytkownikaAdmin";
		return "autaUzytkownika";
	}
	
	@GetMapping("/zwroc")		// TODO
	public String zwroc(@ModelAttribute CustomerDAO customer,
						@ModelAttribute CustomerDAO cleanCustomer,
						@RequestParam("id_car") int id_car,
						Model model) {
		System.out.println("Entered @GetMapping `zwroc` ");
		System.out.println("Customer: " + customer.getLogin() + " | Clean customer: " + cleanCustomer.getLogin());
		
		return "costam";
	}

	@PostMapping("/zwrocPost")		// TODO - okodowac wypozyczenie (przede wszystkim w uzaleznieniu czy auto nie jest wypozyczone. 
									// Zrob tutaj zaciag z bazy id_car dla tych ktore maja customer_id == null, zrob array liste, 
									// wpisz do niej wszystkie te id_car, a potem flage ktora bedzie dawana true jesli id_car 
									// z request parametru dla tego zapytania bedzie sie pokrywal z jakims na (uprzednio zrobionej 
									// i wertowanej teraz) liscie. Potem sprawdzenie flagi i dopiero metoda wypozyczenia dalsza.
									// rowniez nalezy dodac do ustawiania flagi sprawdzenie czy customer.getLogin().length() > 0; - czy zalogowany
	public String zwrocPost(Model model, @ModelAttribute CustomerDAO customer) {
		
		System.out.println("Entered @PostMapping `zwrocPost` ");
//		if(true)
//			return "menu";
//		else
//			return "login";
		return "autaUzytkownika";
	}
}
