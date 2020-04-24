package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("konto", new CustomerDAO());
		return "login";
	}
	
	
	@PostMapping("/login")
	public String verifyLoginPage(@ModelAttribute CustomerDAO customer, Model model) {
		model.addAttribute("konto", new CustomerDAO());
		if(customer.isRoot())
			return "adminPanel";
		return "errorLogin";
	}
}
