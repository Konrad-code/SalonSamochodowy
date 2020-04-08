package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.konrad_janek.SalonSamochodowy.Accounts.Account;

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("konto", new Account());
		return "login";
	}
	
	
	@PostMapping("/login")
	public String verifyLoginPage(@ModelAttribute Account account, Model model) {
		model.addAttribute("konto", new Account());
		if(account.getLogin().equals("ABCD") && account.getHaslo().equals("qwertyuiop"))
			return "adminPanel";
		return "errorLogin";
	}
}
