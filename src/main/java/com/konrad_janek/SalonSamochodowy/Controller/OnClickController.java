package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class OnClickController {
	
	@GetMapping("/onclick")
	public String onclick(@ModelAttribute CustomerDAO customer,
						  @ModelAttribute CustomerDAO cleanCustomer,
						  @RequestParam("id_car") int id_car) {
		System.out.println("Customer: " + customer.getLogin() + " | Clean customer: " + cleanCustomer.getLogin());
		
		if(id_car == 1) {
			System.out.println("Show Vito");
			return "Vito";
		} else if(id_car == 2) {
			System.out.println("Show Sprinter");
			return "Sprinter";
		} else if(id_car == 3) {
			System.out.println("Show Yaris");
			return "Yaris";
		} else if(id_car == 4) {
			System.out.println("Show Auris");
			return "Auris";
		} else if(id_car == 5) {
			System.out.println("Show Fabia");
			return "Fabia";
		} else if(id_car == 6) {
			System.out.println("Show Ka");
			return "Ka";
		} else if(id_car == 7) {
			System.out.println("Show Twingo");
			return "Twingo";
		} else if(id_car == 8) {
			System.out.println("Show DS3");
			return "DS3";
		} else if(id_car == 9) {
			System.out.println("Show Klasa C");
			return "KlasaC";
		} else if(id_car == 10) {
			System.out.println("Show Continental");
			return "Continental";
		} else if(id_car == 11) {
			System.out.println("Show BMW 7");
			return "Bmw7";
		} else if(id_car == 12) {
			System.out.println("Show Aston Martin DB9");
			return "DB9";
		} else if(id_car == 13) {
			System.out.println("Show Maserati");
			return "Maserati";
		} else if(id_car == 14) {
			System.out.println("Show Ford Mustang");
			return "FordMustang";
		} else if(id_car == 15) {
			System.out.println("Show Mercedes SLK");
			return "MercedesSLK";
		} else if(id_car == 16) {
			System.out.println("Show Lamborghini Huracan");
			return "LamborghiniHuracan";
		} else if(id_car == 17) {
			System.out.println("Show Chevrolet Corvetta");
			return "ChevroletCorvetta";
		} else if(id_car == 18) {
			System.out.println("Show Dodge Viper");
			return "DodgeViper";
		} else if(id_car == 19) {
			System.out.println("Show BMW M3");
			return "BmwM3";
		}
		
		if(customer.isRoot())
			return "menu_zalogowanyAdmin";
		else if(customer.getLogin().length() > 0)
			return "menu_zalogowanyCutomer";
		else
			return "menu";
	}
}
