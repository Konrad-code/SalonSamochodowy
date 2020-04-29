package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class AdminPanelController {

	@GetMapping("/adminPanel")
	public String adminPanelPage() {
	/*
	(@ModelAttribute CustomerDAO customer) {
		if(customer == null)
			return "login";
		if(customer.isRoot())
			return "adminPanel";
		return "login";*/
		
		return "admin/adminPanel";
	}
	
	@GetMapping("/adminPanelEdycjaDanychUzytkownika")
	public String adminPanelEdycjaDanychUzytkownika() {
		return "admin/adminPanelEdycjaDanychUzytkownika";
	}
	
	@GetMapping("/adminPanelListaAut")
	public String adminPanelListaAut() {
		return "admin/adminPanelListaAut";
	}
	
	@GetMapping("/adminPanelListaKlientow")
	public String adminPanelListaKlientow() {
		return "admin/adminPanelListaKlientow";
	}
	
	@GetMapping("/adminPanelOczekujaceZwroty")
	public String adminPanelOczekujaceZwroty() {
		return "admin/adminPanelOczekujaceZwroty";
	}
	
	@GetMapping("/adminPanelWypozyczenia")
	public String adminPanelWypozyczenia() {
		return "admin/adminPanelWypozyczenia";
	}
	
	
	
	
}
