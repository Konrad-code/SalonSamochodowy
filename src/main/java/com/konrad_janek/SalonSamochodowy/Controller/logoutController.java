

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
public class logoutController {
@GetMapping("/logout")
	public String logout(@ModelAttribute CustomerDAO customer,
				@ModelAttribute CustomerDAO cleanCustomer,
				Model model) {
		CustomerDAO newCleanCustomer = new CustomerDAO();
		CustomerDAO newCustomer = new CustomerDAO();
		cleanCustomer = newCleanCustomer;
		customer = newCustomer;
		model.addAttribute("cleanCustomer", cleanCustomer);
		model.addAttribute("customer", customer);
		return "login";
	}
}