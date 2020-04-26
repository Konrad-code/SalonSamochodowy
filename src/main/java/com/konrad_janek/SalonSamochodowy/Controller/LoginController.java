package com.konrad_janek.SalonSamochodowy.Controller;

import javax.swing.JOptionPane;

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
	public String loginPage(Model model) {
		CustomerDAO customer = new CustomerDAO();
		model.addAttribute("customer_model", customer);
		return "login";
	}
	
	@PostMapping("/login")
	public String verifyLoginPage(@ModelAttribute CustomerDAO customer, Model model,
									@RequestParam("Login") String loginProvided,
									@RequestParam("Haslo") String passwordProvided) {
		boolean ifTableExists = CRUD.checkIfTableExistsInDatabase();
		boolean ifLoggedSuccessfully, ifDataCorrect = false;
		System.out.println("Login provided: " + loginProvided + " | Password provided: " 
							+ passwordProvided + " and table `customer` exists? " + ifTableExists);
		if((loginProvided.length() >= 4 && loginProvided.length() <= 30) && (passwordProvided.length() >= 8 && passwordProvided.length() <= 30))
            ifDataCorrect = true;
        else
            System.out.println("Provided data incorrect. Login and password have to consist of between 4 and 30 characters");
		if(ifTableExists && ifDataCorrect){
            ifLoggedSuccessfully = customer.tryLogin(loginProvided, passwordProvided);
            System.out.println("If username managed to log in successfully: " + ifLoggedSuccessfully);
            if(ifLoggedSuccessfully){
                System.out.println("You've been logged successfully!\nWelcome " + customer.getLogin());
                return "menu";	// TODO - wymienic na odpowiednie menu po zalogowaniu
            }
            else
                System.out.println("Incorrect data provided in USERNAME and PASSWORD fields\nFailed to login");
        } else
            System.out.println("User doesn't exist\nDatabase empty");
		
		if(customer.isRoot())
			return "adminPanel";
		return "errorLogin";
	}
	
	@GetMapping("/rejestracja")
	public String registerPage() {
		System.out.println("Switched to Register Form");
		return "rejestracja";
	}
}
