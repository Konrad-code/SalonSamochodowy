package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class AdminPanelController {

	@GetMapping("/adminPanel")
	public String adminPanelPage(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		
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
	public String adminPanelEdycjaDanychUzytkownika(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/adminPanelEdycjaDanychUzytkownika";
	}
	
	@GetMapping("/adminPanelListaAut")
	public String adminPanelListaAut(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/adminPanelListaAut";
	}
	
	@GetMapping("/adminPanelListaKlientow")
	public String adminPanelListaKlientow(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/adminPanelListaKlientow";
	}
	
	@GetMapping("/adminPanelOczekujaceZwroty")
	public String adminPanelOczekujaceZwroty(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/adminPanelOczekujaceZwroty";
	}
	
	@GetMapping("/adminPanelWypozyczenia")
	public String adminPanelWypozyczenia(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/adminPanelWypozyczenia";
	}
	
	
	
	
}
