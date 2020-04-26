package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class ForbiddenCharacterDowod extends Exception{
	public ForbiddenCharacterDowod(){
		System.out.println("Nickname with forbidden sign entered");
//		JOptionPane.showMessageDialog(null, "Nickname with forbidden sign entered");
	}
}