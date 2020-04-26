package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class TooShortOrLongLogin extends Exception{
	public TooShortOrLongLogin(){
		System.out.println("Login is too short or too long. Login has to contain at least 4 characters and not more than 20");
//		JOptionPane.showMessageDialog(null, "Login is too short or too long. Login has to contain at least 4 characters and not more than 20");
	}
}