package com.konrad_janek.SalonSamochodowy.Exceptions;

/**
 * @author Konrad Zolynski | https://github.com/Konrad-code | konrad.zolynski@gmail.com | +48 533 683 168
**/

public class TooShortOrLongDowod extends Exception{
	public TooShortOrLongDowod(){
		System.out.println("Dowod is too short or too long. Dowod has to contain exactly 3 characters followed by 6 capital letters like FBI123456");
//		JOptionPane.showMessageDialog(null, "Dowod is too short or too long. Dowod has to contain exactly 3 characters followed by 6 capital letters like FBI123456");
	}
}