package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RejestracjaController {
	
	@GetMapping("/rejestracja")
	public String onasPage() {
		return "rejestracja";
	}
}
