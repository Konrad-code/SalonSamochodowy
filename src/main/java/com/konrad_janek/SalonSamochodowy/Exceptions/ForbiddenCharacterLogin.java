package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class ForbiddenCharacterLogin extends Exception{
	public ForbiddenCharacterLogin(){
		System.out.println("Login with forbidden sign entered");
//		JOptionPane.showMessageDialog(null, "Login with forbidden sign entered");
	}
}