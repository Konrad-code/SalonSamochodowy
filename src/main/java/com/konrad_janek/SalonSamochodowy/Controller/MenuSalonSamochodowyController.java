package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;

@Controller
public class MenuSalonSamochodowyController {

	FabrykaSalonSamochodowy fabrykaSalonSamochodowy = FabrykaSalonSamochodowy.getInstance();
	
	@GetMapping("/menu")
	public String listaAutPage(Model model) {
		model.addAttribute("samochody", fabrykaSalonSamochodowy.getListaSamochody());
		return "menu";
	}
	
	@PostMapping("/wypozycz")		// TODO - okodowac wypozyczenie (przede wszystkim w uzaleznieniu czy auto nie jest wypozyczone. 
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
