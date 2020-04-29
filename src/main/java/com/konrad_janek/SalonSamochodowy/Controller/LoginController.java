package com.konrad_janek.SalonSamochodowy.Controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.CRUD;
import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage(Model model, HttpSession session) {
		CustomerDAO cleanCustomer = new CustomerDAO();
		model.addAttribute("cleanCustomer", cleanCustomer);
		session.setAttribute("customer", cleanCustomer);
		return "login";
	}
	
	@PostMapping("/login")
	public String verifyLoginPage(@ModelAttribute CustomerDAO cleanCustomer, HttpSession session,
									@RequestParam("Login") String loginProvided,
									@RequestParam("Haslo") String passwordProvided) {
		boolean ifTableExists = CRUD.checkIfTableExistsInDatabase();
		boolean ifLoggedSuccessfully, ifDataCorrect = false;
		CustomerDAO customer = new CustomerDAO();
		System.out.println("Login provided: " + loginProvided + " | Password provided: " 
							+ passwordProvided + " and table `customer` exists? " + ifTableExists);
		if((loginProvided.length() >= 4 && loginProvided.length() <= 30) && (passwordProvided.length() >= 8 
				&& passwordProvided.length() <= 30))
            ifDataCorrect = true;
        else
            System.out.println("Provided data incorrect. Login and password have to consist of between 4 and 30 characters");
		if(ifTableExists && ifDataCorrect){
            ifLoggedSuccessfully = cleanCustomer.tryLogin(loginProvided, passwordProvided);
//            customer = new CustomerDAO(cleanCustomer.getLogin(), cleanCustomer.getSaldo(), cleanCustomer.isRoot());
            session.setAttribute("customer", cleanCustomer);
            customer = (CustomerDAO)session.getAttribute("customer");
            System.out.println("If username managed to log in successfully: " + ifLoggedSuccessfully);
            if(customer.isRoot()) {
            	System.out.println("Admin has been logged successfully!\nWelcome " + customer.getLogin());
            	return "admin/glowna_zalogowanyAdmin";
            }
            if(ifLoggedSuccessfully){
                System.out.println("You've been logged successfully!\nWelcome " + customer.getLogin());
                return "glowna_zalogowanyCustomer";
//                DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
            }
            else {
                System.out.println("Incorrect data provided in USERNAME and PASSWORD fields\nFailed to login");
//             	DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
            }
        } else {
            System.out.println("User doesn't exist\nDatabase empty");
//         	DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
        }
		return "login";
	}
	
	@GetMapping("/rejestracja")
	public String registerPage() {
		System.out.println("Switched to Register Form");
		return "rejestracja";
	}
}
