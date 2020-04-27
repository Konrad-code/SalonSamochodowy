package com.konrad_janek.SalonSamochodowy;

import java.util.Date;
import java.text.*;
import java.time.Duration;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalonSamochodowyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonSamochodowyApplication.class, args);
		
		// output with java lib - Mon Apr 27 16:05:27 CEST 2020
		LocalDate date = LocalDate.now();
		System.out.println(date);

		Date date2 = new Date();
		
//		System.out.println(date.toString());
		System.out.println(date2.getDay());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf.format(date));
		System.out.println(sdf.format(date2));
		
		int dlugoscWypozyczenia = 4;
		LocalDate dataOddania = LocalDate.now();
		LocalDate dataWypozyczenia = dataOddania.minusDays(4);
		
		System.out.println("Duaration: " + Duration.between(dataWypozyczenia.atStartOfDay(), dataOddania.atStartOfDay()).toDays());
	}
 
}
