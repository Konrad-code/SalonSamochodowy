package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class adminPanelListaKlientowController {
/*
	@GetMapping("/adminPanelOczekujaceZwroty")
	public String adminPanelZwrotyPage() {
	
	(@ModelAttribute CustomerDAO customer) {
		if(customer == null)
			return "login";
		if(customer.isRoot())
			return "adminPanelListaKlientow";
		return "login";
	}*/
	
	
	@GetMapping("/adminPanelListaKlientow")
	public String onasPage() {
		return "adminPanelListaKlientow";
	}
}
