package com.konrad_janek.SalonSamochodowy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konrad_janek.SalonSamochodowy.Accounts.Customer;
import com.konrad_janek.SalonSamochodowy.Accounts.CustomerDAO;
import com.konrad_janek.SalonSamochodowy.Exceptions.ForbiddenCharacterDowod;
import com.konrad_janek.SalonSamochodowy.Exceptions.ForbiddenCharacterPassword;
import com.konrad_janek.SalonSamochodowy.Exceptions.ForbiddenCharacterLogin;
import com.konrad_janek.SalonSamochodowy.Exceptions.NoDigit;
import com.konrad_janek.SalonSamochodowy.Exceptions.NoLowercaseLetter;
import com.konrad_janek.SalonSamochodowy.Exceptions.NoSpecialCharacter;
import com.konrad_janek.SalonSamochodowy.Exceptions.NoUppercaseLetter;
import com.konrad_janek.SalonSamochodowy.Exceptions.TooShortOrLongDowod;
import com.konrad_janek.SalonSamochodowy.Exceptions.TooShortOrLongPassword;
import com.konrad_janek.SalonSamochodowy.Exceptions.TooShortOrLongLogin;
import com.konrad_janek.SalonSamochodowy.Exceptions.WrongConfirmation;

@Controller
public class RejestracjaController {
	
	@PostMapping("/rejestracja")
	public String verifyRegisterPage(@ModelAttribute CustomerDAO customer,
									@RequestParam("Login") String loginProvided,
									@RequestParam("Haslo") String passwordProvided,
									@RequestParam("repetedHaslo") String passwordConfirmationProvided,
									@RequestParam("dowodOsobisty") String dowodProvided) {
		System.out.println("Register button pushed");
		boolean isFree = false;
        Customer newCustomerToRegister = verify(loginProvided, dowodProvided, passwordProvided, passwordConfirmationProvided);
        if(newCustomerToRegister != null) {
        	String message = new String("Password: " + newCustomerToRegister.getPassword()
            + "\nUsername: " + newCustomerToRegister.getLogin() + "\nNickname: " + newCustomerToRegister.getDowod());
        	System.out.println(message);
        	
        	if(customer.checkDowod(newCustomerToRegister.getDowod())) {
                isFree = true;
                if(!(isFree && customer.checkLogin(newCustomerToRegister.getLogin()))){
                    isFree = false;
                    System.out.println("Login is occupied. Provide other username");
//                  DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
                }
        	} else {
        		System.out.println("Dowod isn't unique. One cannot posess same personal ID as another citizen. Correct Dowod data");
//        		DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
        	}
            if(isFree){
                boolean ifAddedSuccesfully = customer.addCustomer(newCustomerToRegister);
                if(ifAddedSuccesfully) {
	                System.out.println("New customer created successfully!");
//	                DOPISAC JS KOMUNIKAT - KOPIA Z POWYZSZEGO System.out.println
	                return "login";
                }
            }
        }
		return "rejestracja";
	}
                               
    public Customer verify(String loginProvided, String dowodProvided, String passwordProvided, String passwordConfirmationProvided){
		Customer customerToRegister = null;
		
        boolean ifCorrect = verifyData(loginProvided, dowodProvided, passwordProvided, passwordConfirmationProvided);
        if(ifCorrect)
        	customerToRegister = new Customer(loginProvided, passwordProvided, dowodProvided);
        return customerToRegister;
    }
	
	public boolean verifyString(String toTest){
        boolean hasForbiddenCharacter = false;
        int lowercaseCounter = 0, uppercaseCounter = 0, specialCharacterCounter = 0, digitCounter = 0;
        for(int i = 0; i < toTest.length(); i++) {
            if((toTest.charAt(i) >= 'A') && (toTest.charAt(i) <= 'Z'))
                uppercaseCounter++;
            if((toTest.charAt(i) >= 'a') && (toTest.charAt(i) <= 'z'))
                lowercaseCounter++;
            if((toTest.charAt(i) >= '0') && (toTest.charAt(i) <= '9'))
                digitCounter++;
            if((toTest.charAt(i) >= 32) && (toTest.charAt(i) <= 47) || (toTest.charAt(i) >= 58) && (toTest.charAt(i)) <= 64
                    || (toTest.charAt(i) >= 92) && (toTest.charAt(i)) <= 96 || (toTest.charAt(i) >= 123) && (toTest.charAt(i)) <= 126)
                specialCharacterCounter++;
            if((specialCharacterCounter + digitCounter + lowercaseCounter + uppercaseCounter) < i)
                hasForbiddenCharacter = true;
        }
        return hasForbiddenCharacter;
    }
    
    public boolean verifyData(String loginProvided, String dowodProvided, String passwordProvided, String passwordConfirmationProvided){
        int lowercaseCounter = 0, uppercaseCounter = 0, specialCharacterCounter = 0, digitCounter = 0;
        boolean verificationPassed = false;
    	uppercaseCounter = lowercaseCounter = digitCounter = specialCharacterCounter = 0;
        System.out.println("Password provided: " + passwordProvided + "\nPassword confirmation provided: " + passwordConfirmationProvided
                         + "\nLogin provided: " + loginProvided + "\nDowod provided: " + dowodProvided);
        try {
            if(!passwordProvided.equals(passwordConfirmationProvided))
                throw new WrongConfirmation();
            if(passwordProvided.length() < 8 || passwordProvided.length() > 30)
                throw new TooShortOrLongPassword();
            if(loginProvided.length() < 4 || loginProvided.length() > 20)
                throw new TooShortOrLongLogin();
            if(dowodProvided.length() != 10)
                throw new TooShortOrLongDowod();
            if(verifyString(loginProvided))
                throw new ForbiddenCharacterPassword();
            if(verifyString(dowodProvided))
                throw new ForbiddenCharacterDowod();
            if(verifyString(passwordProvided))
                throw new ForbiddenCharacterLogin();
            for(int i = 0; i < passwordProvided.length(); i++) {
                if((passwordProvided.charAt(i) >= 'A') && (passwordProvided.charAt(i) <= 'Z'))
                    uppercaseCounter++;
                if((passwordProvided.charAt(i) >= 'a') && (passwordProvided.charAt(i) <= 'z'))
                    lowercaseCounter++;
                if((passwordProvided.charAt(i) >= '0') && (passwordProvided.charAt(i) <= '9'))
                    digitCounter++;
                if((passwordProvided.charAt(i) >= 32) && (passwordProvided.charAt(i) <= 47)
                        || (passwordProvided.charAt(i) >= 58) && (passwordProvided.charAt(i)) <= 64
                        || (passwordProvided.charAt(i) >= 92) && (passwordProvided.charAt(i)) <= 96
                        || (passwordProvided.charAt(i) >= 123) && (passwordProvided.charAt(i)) <= 126)
                    specialCharacterCounter++;
            }
            if(uppercaseCounter < 1)
            	throw new NoUppercaseLetter();
            if(lowercaseCounter < 1)
                throw new NoLowercaseLetter();
            if(digitCounter < 1)
                throw new NoDigit();
            if(specialCharacterCounter < 1)
                throw new NoSpecialCharacter();   
            verificationPassed = true;
        } catch(TooShortOrLongPassword tsolp) { /* leave action */
        } catch(TooShortOrLongLogin tsolu) { /* leave action */
        } catch(TooShortOrLongDowod tsoln) { /* leave action */
        } catch(NoUppercaseLetter nul) { /* leave action */
        } catch(NoLowercaseLetter nll) { /* leave action */
        } catch(NoDigit nd) { /* leave action */
        } catch(NoSpecialCharacter nsc) { /* leave action */
        } catch(ForbiddenCharacterPassword fcp) { /* leave action */
        } catch(ForbiddenCharacterLogin fcu) { /* leave action */
        } catch(ForbiddenCharacterDowod fcd) { /* leave action */
        } catch(WrongConfirmation wc){ /* leave action */
        }
        return verificationPassed;
    }
}
