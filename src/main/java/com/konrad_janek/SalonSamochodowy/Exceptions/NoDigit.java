package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class NoDigit extends Exception{
	public NoDigit(){
		System.out.println("Password with no digits. Password has to contain at least 1 digit");
//		JOptionPane.showMessageDialog(null, "Password with no digits. Password has to contain at least 1 digit");
	}
}