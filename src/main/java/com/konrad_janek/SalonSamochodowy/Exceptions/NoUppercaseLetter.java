package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class NoUppercaseLetter extends Exception{
	public NoUppercaseLetter(){
		System.out.println("Password with no uppercase letters. Password has to contain at least 1 uppercase letter");
//		JOptionPane.showMessageDialog(null, "Password with no uppercase letters. Password has to contain at least 1 uppercase letter");
	}
}