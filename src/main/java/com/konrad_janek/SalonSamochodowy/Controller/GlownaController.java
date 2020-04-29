package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class GlownaController {
	
	@GetMapping("/glowna")
	public String glowna() {
		return "glowna";
	}

	@GetMapping("/glowna_zalogowanyCustomer")
	public String glowna_zalogowanyCustomer() {
		return "glowna_zalogowanyCustomer";
	}
	
	@GetMapping("/glowna_zalogowanyAdmin")
	public String glowna_zalogowanyAdmin(HttpSession session) {
		CustomerDAO customer = (CustomerDAO)session.getAttribute("customer");
		if(!customer.isRoot())
			return "logout";
		return "admin/glowna_zalogowanyAdmin";
	}
}
