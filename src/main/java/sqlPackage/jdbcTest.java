package sqlPackage;

import java.sql.SQLException;

public class jdbcTest {
    public static void main(String[] args) throws SQLException {

        FacebookUserDAO.createUsersTable();
        user u1= new user("313523649","Shimon","Sason","0508507646","ShimonSason@gmail.com","Ss##1234RB","28/10/1985","male");
        FacebookUserDAO.insertUser(u1);
        user u2= new user("457693649","Yarden","Matityaho","0528881741","YardenMatityaho@gmail.com","Ym*&1234!5","06/08/1974","Female");
        FacebookUserDAO.insertUser(u2);
        user u3= new user("813426749","Shni","Chohen","0544670790","ShniChohen@gmail.com","Sc123434","14/04/1988","Female");
        FacebookUserDAO.insertUser(u3);
        user u4= new user("613569445","Daniel","Naim","0509526932","DanielNaim@gmail.com","Dn1234754","24/07/1954","male");
        FacebookUserDAO.insertUser(u4);
        user u5= new user("223522220","Ron","lev","0525581790","Ronlev@gmail.com","RL1236@4","30/11/1999","male");
        FacebookUserDAO.insertUser(u5);
        user u6= new user("413773641","Yaron","Arbel","0502756384","YaronArbel@gmail.com","Ya123477","08/12/1959","male");
        FacebookUserDAO.insertUser(u6);
        user u7= new user("818572361","Shiran","Ben","0542496778","ShiranBen@gmail.com","Sb1234!!4","05/03/2000","Female");
        FacebookUserDAO.insertUser(u7);
        user u8= new user("771352364","Dorin","Nahum","0504743310","DorinNahum@gmail.com","Dn12@34#RB54","01/07/1984","Female");
        FacebookUserDAO.insertUser(u8);
        user u9= new user("958523666","Eyal","Amar","0546634636","EyalAmar@gmail.com","Ea1234kt","19/06/1970","male");
        FacebookUserDAO.insertUser(u9);
        user u10= new user("234552364","Liel","Moyal","0525468878","LielMoyal@gmail.com","LM12@#3%4RB","26/02/1982","Female");
        FacebookUserDAO.insertUser(u10);

        FacebookUserDAO.selectAll();
        FacebookUserDAO.deleteUser(234552364);
        FacebookUserDAO.updateUserFirstName(613569445,"Daniell");
        FacebookUserDAO.updateUserLastName(413773641,"Arbelus");
        FacebookUserDAO.updateUserEmail(771352364,"DoriniNahum@gmail.com");
        FacebookUserDAO.updateUserDOB(958523666,"18/06/1970");
        FacebookUserDAO.updateUserMobileNumber(813426749,"0509207835");
        FacebookUserDAO.updateUserGender(457693649,"Male");
        FacebookUserDAO.selectAll();

    }
}
