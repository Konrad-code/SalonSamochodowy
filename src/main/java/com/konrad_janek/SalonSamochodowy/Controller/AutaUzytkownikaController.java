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

	@PostMapping("/zwrocPost")		// TODO - okodowac wypozyczenie (przede wszystkim w uzaleznieniu czy auto nie jest wypozyczone. 
									// Zrob tutaj zaciag z bazy id_car dla tych ktore maja customer_id == null, zrob array liste, 
									// wpisz do niej wszystkie te id_car, a potem flage ktora bedzie dawana true jesli id_car 
									// z request parametru dla tego zapytania bedzie sie pokrywal z jakims na (uprzednio zrobionej 
									// i wertowanej teraz) liscie. Potem sprawdzenie flagi i dopiero metoda wypozyczenia dalsza.
									// rowniez nalezy dodac do ustawiania flagi sprawdzenie czy customer.getLogin().length() > 0; - czy zalogowany
	public String zwrocPost(HttpSession session, Model model, @RequestParam("days") String days) {
		
		System.out.println("Entered @PostMapping `zwrocPost` ");
			int dlugoscWypozyczenia = Integer.parseInt(days);
			CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
			if(dlugoscWypozyczenia < 1 && customer.isRoot())
				return "admin/menu_zalogowanyAdmin";
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
								return "admin/menu_zalogowanyAdmin";
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
				return "errors/errorRent";
			else
				return "errors/errorRentAdmin";
//		return "autaUzytkownika";
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
