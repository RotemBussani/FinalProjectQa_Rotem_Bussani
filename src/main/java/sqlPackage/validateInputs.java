package sqlPackage;

import bsh.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class validateInputs {

    public boolean validateDateOfBirth(String dobString) {
        //This Function is checking if a given test is an existing Date, and if it is in the past
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date dob = dateFormat.parse(dobString);
            // check if date is in the past
            return dob.before(new Date());
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String IDValidation(String ID)
    {
        //this method is validating a given id (contains only number and exactly 9 characters).
        if (ID.matches("[0-9]+") && ID.length() == 9) return ID;
        return "The ID You've Entered is Invalid";
    }
    public String FirstNameValidation(String fName)
    {
        //this method is validating a given First Name (contains only Characters and starts with capital letter
        // and the rest are lower case letters).
        if (fName.matches("[A-Z][a-z]+")) return fName;
        return "The First Name You've Entered is Invalid";
    }
    public String LastNameValidation(String lName)
    {
        //this method is validating a given Last Name (contains only Characters and starts with capital letter
        // and the rest are lower case letters).
        if (lName.matches("[A-Z][a-z]+")) return lName;
        return "The Last Name You've Entered is Invalid";
    }
    public String MobileNumberValidation(String phoneNumber)
    {
        //this method is validating a given Phone Number (Starts only with 050||052||054
        // and contains exactly 10 numbers as characters
        if (phoneNumber.matches("(050|052|054)\\d{7}")) return phoneNumber;
        return "The Phone Number You've Entered is Invalid";
    }
    public String EmailValidation(String email)
    {
        //this method is validating a given Email (contains '@' and ends with '.com').
        if (email.contains("@") && email.endsWith(".com")) return email;
        return "The Email You've Entered is Invalid";
    }
    public String PasswordValidation(String password)
    {
        /*
        this method is validating a given password.
        A valid password is a password which has at least 6 characters and which is defines as
        either a Medium or a Strong password.
        Strong Password: Contains at least 1 Uppercase letter,at least 1 Lowercase letter, and at least 1 Number
        Medium Password: At least 2 of the conditions listed above
        easy password: Only 1 condition.
        */
        if (password.length() >= 6) {
            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
                System.out.println("Password is Strong");
                return password;
            } else if (password.matches("^(?=.*[a-z])(?=.*[A-Z])|(?=.*[a-z])(?=.*\\d)|(?=.*[A-Z])(?=.*\\d).+$")) {
                System.out.println("Password is Medium");
                return password;
            } else {
                System.out.println("Password is Weak");
                return "Password is Weak";
            }
        } else {
            System.out.println("Password is Weak");
            return "Password is too short";
        }
    }
    public String DOBValidation(String DOB)
    {
        //this method is validating a given Date of Birth (an existing Date, and it is got to be in the past).
        if (validateDateOfBirth(DOB)) {
            return DOB;
        } else {
            return "The Date Of Birth You've Entered is Invalid";
        }
    }
}
