package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;

@Controller
public class MenuSalonSamochodowyController {

	FabrykaSalonSamochodowy fabrykaSalonSamochodowy = new FabrykaSalonSamochodowy();
	
	@GetMapping("/menu")
	public String menuSushiPage(Model model) {
		model.addAttribute("Lista aut w wypozyczalni", fabrykaSalonSamochodowy.getListaSamochody());
		return "menu";
	}
}
