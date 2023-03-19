package sqlPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class user {

    private String ID;
    private String FirstName;
    private String LastName;
    private String MobileNumber;
    private String Email;
    private String Password;
    private String DOB;
    private String Gender;

    public user(String ID, String firstName, String lastName, String mobileNumber, String email,String Password, String DOB, String gender) {

        this.ID = ID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.MobileNumber = mobileNumber;
        this.Password = Password;
        this.Email = email;
        this.DOB = DOB;
        this.Gender = gender;
    }

    public String getID()
    {
        return ID;
    }


    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    public String getMobileNumber()
    {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        MobileNumber = mobileNumber;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }

    public String getDOB()
    {
        return DOB;
    }

    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }

    public String getGender()
    {
        return Gender;
    }

    public void setGender(String gender)
    {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "user{" +
                "ID=" + ID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", MobileNumber='" + MobileNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", DOB=" + DOB +
                ", Gender='" + Gender + '\'' +
                '}';
    }
}
