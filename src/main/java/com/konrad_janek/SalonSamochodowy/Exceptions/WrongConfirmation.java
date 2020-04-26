package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class WrongConfirmation extends Exception{
	public WrongConfirmation(){
		System.out.println("Password doesn't match confirm password");
//		JOptionPane.showMessageDialog(null, "Password doesn't match confirm password");
	}
}