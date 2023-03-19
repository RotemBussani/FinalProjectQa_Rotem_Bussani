import org.junit.jupiter.api.Test;
import sqlPackage.validateInputs;

import static org.junit.Assert.assertEquals;

public class SqlTest {

    @Test
    void testID()
    {
        //this method tests if a given id is valid (contains only number and exactly 9 characters).
        validateInputs validateID= new validateInputs();
        //String tst= "3648854!7"; //example for invalid ID
        String tst= "364885947";
        assertEquals(tst,validateID.IDValidation(tst));
    }

    @Test
    void testFirstName()
    {
        //this method tests if a given First Name is valid (contains only Characters and starts with capital letter
        // and the rest are lower case letters).
        validateInputs validateFirstName= new validateInputs();
        //String tst= "S-imon";//example for invalid First Name
        String tst= "Shimon";
        assertEquals(tst,validateFirstName.FirstNameValidation(tst));
    }

    @Test
    void testLastName()
    {
        //this method tests if a given Last Name is valid (contains only Characters and starts with Uppercase letter
        // and the rest are lowercase letters).
        validateInputs validateLastName= new validateInputs();
        //String tst= "Sa89son";//example for invalid Last Name
        String tst= "Sason";
        assertEquals(tst,validateLastName.LastNameValidation(tst));
    }


    @Test
    void testMobileNumber()
    {
        //this method tests if a given Phone Number is valid (Starts only with 050||052||054
        // and contains exactly 10 numbers as characters)
        validateInputs validateMobileNumber= new validateInputs();
        //String tst= "0779994848";//example for invalid Mobile Number
        String tst= "0549994848";
        assertEquals(tst,validateMobileNumber.MobileNumberValidation(tst));
    }


    @Test
    void testEmail()
    {
        //this method tests if a given Email is valid (contains '@' and ends with '.com').
        validateInputs validateEmail= new validateInputs();
        //String tst= "test@test";//example for invalid Email
        String tst= "test@test.com";
        assertEquals(tst,validateEmail.EmailValidation(tst));
    }

    /*
    the following 2 tests and 2 hushed tests are testing if a given password is valid
    A valid password is a password which has at least 6 characters and which is defines as
    either a Medium or a Strong password.
    Strong Password: Contains at least 1 Uppercase letter,at least 1 Lowercase letter, and at least 1 Number
    Medium Password: At least 2 of the conditions listed above
    easy password: Only 1 condition.
    */
    @Test
    void testPasswordStrong()
    {
        validateInputs validatePassword= new validateInputs();
        String tst= "Ss@!669936";
        assertEquals(tst,validatePassword.PasswordValidation(tst));
    }

    @Test
    void testPasswordMedium()
    {
        validateInputs validatePassword= new validateInputs();
        String tst= "S669936";
        assertEquals(tst,validatePassword.PasswordValidation(tst));
    }
/*
    @Test
    void testPasswordWeak()
    {
    //Weak because it contains only numbers
        validateInputs validatePassword= new validateInputs();
        String tst= "669936";
        assertEquals(tst,validatePassword.PasswordValidation(tst));
    }

 */
/*
    @Test
    void testPasswordFalse()
    {
    //False because its too short and contains only numbers
        validateInputs validatePassword= new validateInputs();
        String tst= "49936";
        assertEquals(tst,validatePassword.PasswordValidation(tst));
    }

 */

    @Test
    void testDOB()
    {
        //this method tests if a given Date of Birth is valid (an existing Date, and it is got to be in the past).
        validateInputs validateDOB= new validateInputs();
        //String tst= "31/02/1985";//example for invalid Date of Birth
        String tst= "28/10/1985";
        assertEquals(tst,validateDOB.DOBValidation(tst));
    }

}
