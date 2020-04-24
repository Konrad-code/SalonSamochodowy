package com.konrad_janek.SalonSamochodowy;

import java.util.Date;
import java.text.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalonSamochodowyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonSamochodowyApplication.class, args);
		
		
		Date date = new Date();
		
		System.out.println(date.toString());
		System.out.println(date.getDay());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
	}
 
}
