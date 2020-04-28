

package com.konrad_janek.SalonSamochodowy.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;

@Controller
public class LogoutController {
@GetMapping("/logout")
	public String logout(HttpSession session,
				@ModelAttribute CustomerDAO cleanCustomer) {
		CustomerDAO customerToLogout = (CustomerDAO)session.getAttribute("customer");	
		System.out.println("Before logout login is: " + customerToLogout.getLogin());
		
		session.setAttribute("customer", cleanCustomer);
		
		CustomerDAO customerCheck = (CustomerDAO)session.getAttribute("customer");
		System.out.println("After logout login is: " + customerCheck.getLogin());
		return "login";
	}
}