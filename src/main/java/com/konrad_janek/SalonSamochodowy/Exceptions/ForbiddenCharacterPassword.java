package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class ForbiddenCharacterPassword extends Exception{
	public ForbiddenCharacterPassword(){
		System.out.println("Password with forbidden sign entered");
//		JOptionPane.showMessageDialog(null, "Password with forbidden sign entered");
	}
}