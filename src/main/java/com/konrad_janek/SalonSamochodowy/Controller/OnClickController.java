package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class OnClickController {
	
	@GetMapping("/onclick")
	public String onclick(HttpSession session,
						  @RequestParam("id_car") int id_car) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		String login = customer.getLogin();
		if(login.equals(""))
			login = "gosc";
		System.out.println("Customer Login: " + login + " | ID_car: " + id_car);
		session.setAttribute("id_car", id_car);
		
		if(id_car == 1) {
			System.out.println("Show Vito");
			return "auta/Vito";
		} else if(id_car == 2) {
			System.out.println("Show Sprinter");
			return "auta/Sprinter";
		} else if(id_car == 3) {
			System.out.println("Show Yaris");
			return "auta/Yaris";
		} else if(id_car == 4) {
			System.out.println("Show Auris");
			return "auta/Auris";
		} else if(id_car == 5) {
			System.out.println("Show Fabia");
			return "auta/Fabia";
		} else if(id_car == 6) {
			System.out.println("Show Ka");
			return "auta/Ka";
		} else if(id_car == 7) {
			System.out.println("Show Twingo");
			return "auta/Twingo";
		} else if(id_car == 8) {
			System.out.println("Show DS3");
			return "auta/DS3";
		} else if(id_car == 9) {
			System.out.println("Show Klasa C");
			return "auta/KlasaC";
		} else if(id_car == 10) {
			System.out.println("Show Continental");
			return "auta/Continental";
		} else if(id_car == 11) {
			System.out.println("Show BMW 7");
			return "auta/Bmw7";
		} else if(id_car == 12) {
			System.out.println("Show Aston Martin DB9");
			return "auta/DB9";
		} else if(id_car == 13) {
			System.out.println("Show Maserati");
			return "auta/Maserati";
		} else if(id_car == 14) {
			System.out.println("Show Ford Mustang");
			return "auta/FordMustang";
		} else if(id_car == 15) {
			System.out.println("Show Mercedes SLK");
			return "auta/MercedesSLK";
		} else if(id_car == 16) {
			System.out.println("Show Lamborghini Huracan");
			return "auta/LamborghiniHuracan";
		} else if(id_car == 17) {
			System.out.println("Show Chevrolet Corvetta");
			return "auta/ChevroletCorvetta";
		} else if(id_car == 18) {
			System.out.println("Show Dodge Viper");
			return "auta/DodgeViper";
		} else if(id_car == 19) {
			System.out.println("Show BMW M3");
			return "auta/BmwM3";
		}
		
		session.setAttribute("id_car", null);
		if(customer.isRoot())
			return "menu_zalogowanyAdmin";
		else if(customer.getLogin().length() > 0)
			return "menu_zalogowanyCutomer";
		else
			return "menu";
	}
	
	@GetMapping("/onclickWszystkie")
	public String onclickWszystkie(HttpSession session,
						  @RequestParam("id_carWszystkie") int id_carWszystkie) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		String login = customer.getLogin();
		if(login.equals(""))
			login = "gosc";
		System.out.println("Customer Login: " + login + " | ID_car: " + id_carWszystkie);
		session.setAttribute("id_carWszystkie", id_carWszystkie);
		
		if(id_carWszystkie == 1) {
			System.out.println("Show Vito");
			return "autaWszystkie/Vito";
		} else if(id_carWszystkie == 2) {
			System.out.println("Show Sprinter");
			return "autaWszystkie/Sprinter";
		} else if(id_carWszystkie == 3) {
			System.out.println("Show Yaris");
			return "autaWszystkie/Yaris";
		} else if(id_carWszystkie == 4) {
			System.out.println("Show Auris");
			return "autaWszystkie/Auris";
		} else if(id_carWszystkie == 5) {
			System.out.println("Show Fabia");
			return "autaWszystkie/Fabia";
		} else if(id_carWszystkie == 6) {
			System.out.println("Show Ka");
			return "autaWszystkie/Ka";
		} else if(id_carWszystkie == 7) {
			System.out.println("Show Twingo");
			return "autaWszystkie/Twingo";
		} else if(id_carWszystkie == 8) {
			System.out.println("Show DS3");
			return "autaWszystkie/DS3";
		} else if(id_carWszystkie == 9) {
			System.out.println("Show Klasa C");
			return "autaWszystkie/KlasaC";
		} else if(id_carWszystkie == 10) {
			System.out.println("Show Continental");
			return "autaWszystkie/Continental";
		} else if(id_carWszystkie == 11) {
			System.out.println("Show BMW 7");
			return "autaWszystkie/Bmw7";
		} else if(id_carWszystkie == 12) {
			System.out.println("Show Aston Martin DB9");
			return "autaWszystkie/DB9";
		} else if(id_carWszystkie == 13) {
			System.out.println("Show Maserati");
			return "autaWszystkie/Maserati";
		} else if(id_carWszystkie == 14) {
			System.out.println("Show Ford Mustang");
			return "autaWszystkie/FordMustang";
		} else if(id_carWszystkie == 15) {
			System.out.println("Show Mercedes SLK");
			return "autaWszystkie/MercedesSLK";
		} else if(id_carWszystkie == 16) {
			System.out.println("Show Lamborghini Huracan");
			return "autaWszystkie/LamborghiniHuracan";
		} else if(id_carWszystkie == 17) {
			System.out.println("Show Chevrolet Corvetta");
			return "autaWszystkie/ChevroletCorvetta";
		} else if(id_carWszystkie == 18) {
			System.out.println("Show Dodge Viper");
			return "autaWszystkie/DodgeViper";
		} else if(id_carWszystkie == 19) {
			System.out.println("Show BMW M3");
			return "autaWszystkie/BmwM3";
		}
		
		session.setAttribute("id_car", null);
		if(customer.isRoot())
			return "menu_zalogowanyAdmin";
		else if(customer.getLogin().length() > 0)
			return "menu_zalogowanyCutomer";
		else
			return "menu";
	}
}
