package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.konrad_janek.SalonSamochodowy.Data.FabrykaSalonSamochodowy;

@Controller
public class MenuSalonSamochodowyController {

	FabrykaSalonSamochodowy fabrykaSalonSamochodowy = FabrykaSalonSamochodowy.getInstance();
	
	@GetMapping("/menu")
	public String listaAutPage(Model model) {
		model.addAttribute("samochody", fabrykaSalonSamochodowy.getListaSamochody());
		return "menu";
	}
}
