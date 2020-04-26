package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class TooShortOrLongPassword extends Exception{
	public TooShortOrLongPassword(){
		System.out.println("Password is too short or too long. Password has to contain at least 8 characters and not more than 30");
//		JOptionPane.showMessageDialog(null, "Password is too short or too long. Password has to contain at least 8 characters and not more than 30");
	}
}