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
import com.konrad_janek.SalonSamochodowy.Data.FabrykaTransakcji;
import com.konrad_janek.SalonSamochodowy.Data.Samochod;

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
	
	@GetMapping("/zwroc")
	public String zwroc(@RequestParam("id_transaction") int id_transaction,
						HttpSession session,
						Model model) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		System.out.println("Entered @GetMapping `zwroc` ");
		System.out.println("Customer: " + customer.getLogin() + " | ID of transaction: " + id_transaction);
		int id_customer = customer.getId_customer(customer.getLogin());
		boolean ifSuccessfully = customer.returnACarFromTransaction(id_transaction);
		if(ifSuccessfully) {
			FabrykaTransakcji fabrykaTransakcjiUser = new FabrykaTransakcji(id_customer);
			fabrykaTransakcjiUser.wczytajTransakcjeZwrotCustomer(id_customer);
			model.addAttribute("zwroty", fabrykaTransakcjiUser.getListaTransakcjiCustomer());
			if(customer.isRoot())
				return "admin/autaUzytkownikaAdmin";
			return "autaUzytkownika";
		} else
			System.out.println("Failed to return car from this transaction No: " + id_transaction);
		if(!customer.isRoot())
			return "errors/errorReturn";
		else
			return "errors/errorReturnAdmin";
	}

	@PostMapping("/zwrocPost")
	public String zwrocPost(@RequestParam("noCar") String carNumber,
							HttpSession session,
							Model model) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		int id_car = Integer.parseInt(carNumber);
		System.out.println("Entered @PostMapping `zwrocPost` ");
		System.out.println("Customer: " + customer.getLogin() + " | ID of car to return: " + id_car);
		int id_customer = customer.getId_customer(customer.getLogin());
		boolean ifSuccessfully = customer.returnACar(id_customer, id_car);
		if(ifSuccessfully) {
			FabrykaTransakcji fabrykaTransakcjiUser = new FabrykaTransakcji(id_customer);
			fabrykaTransakcjiUser.wczytajTransakcjeZwrotCustomer(id_customer);
			model.addAttribute("zwroty", fabrykaTransakcjiUser.getListaTransakcjiCustomer());
			if(customer.isRoot())
				return "admin/autaUzytkownikaAdmin";
			return "autaUzytkownika";
		} else
			System.out.println("Failed to return car number " + id_car + " from its transaction");
		if(!customer.isRoot())
			return "errors/errorReturn";
		else
			return "errors/errorReturnAdmin";
	}
	
	@GetMapping("/errorReturn")
	public String errorReturn() {
		return "errors/errorReturn";
	}
	
	@GetMapping("/errorReturnAdmin")
	public String errorReturnAdmin(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(customer.isRoot())
			return "errors/errorReturnAdmin";
		else
			return "errors/errorReturn";
	}
	
}
