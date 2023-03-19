package sqlPackage;

import java.sql.*;

public class FacebookUserDAO {

    public static final String url = "jdbc:sqlite:C:\\Users\\rotem\\Desktop\\Rotem studies\\presentations\\FinalProject\\sqlDBFinalProject\\my_db.db";
    public static final Connection con;



    static {
        try
        {
            con = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void createUsersTable() {
        try {
            if (FacebookUserDAO.con != null) {
                String sql = "create table if not exists " +
                        "users (id TEXT PRIMARY KEY,FirstName TEXT NOT NULL, " +
                        "LastName TEXT NOT NULL, MobileNumber TEXT, Email TEXT,Password TEXT, DOB TEXT, Gender TEXT);";
                Statement stm = con.createStatement();
                stm.executeUpdate(sql);
                System.out.println("create table =");
                System.out.println("the table  is created ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertUser(user u) throws SQLException {
        String query = "insert into users (id , FirstName , " +
                "LastName , MobileNumber , Email ,Password , DOB , Gender) " +
                "values ("+u.getID()+
                ",'"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getMobileNumber()+
                "','"+u.getEmail()+"','"+u.getPassword()+"','"+u.getDOB()+"','"+u.getGender()+"');";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }

    public static void deleteUser(int idToDelete) throws SQLException {
        String query = "Delete From users Where id= '"+idToDelete+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }

    public static void updateUserFirstName(int user_id,String newFirstName) throws SQLException {
        String query = "UPDATE users SET FirstName = '"+newFirstName+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserLastName(int user_id,String newLastName) throws SQLException {
        String query = "UPDATE users SET LastName = '"+newLastName+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserMobileNumber(int user_id,String newMobileNumber) throws SQLException {
        String query = "UPDATE users SET MobileNumber = '"+newMobileNumber+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserEmail(int user_id,String newEmail) throws SQLException {
        String query = "UPDATE users SET Email = '"+newEmail+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserPassword(int user_id,String newPassword) throws SQLException {
        String query = "UPDATE users SET Password = '"+newPassword+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserDOB(int user_id,String newDOB) throws SQLException {
        String query = "UPDATE users SET DOB = '"+newDOB+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }
    public static void updateUserGender(int user_id,String newGender) throws SQLException {
        String query = "UPDATE users SET Gender = '"+newGender+"' WHERE id ='"+ user_id+"';";
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
    }

    public static void selectAll() throws SQLException {
        String sql = "Select * From users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("id") + "\t" + rs.getString("FirstName") + "\t" + rs.getString("LastName") + "\t"
            + rs.getString("MobileNumber") + "\t" + rs.getString("Email") + "\t"+ rs.getString("Password") + "\t"
                    + rs.getString("DOB") + "\t"+ rs.getString("Gender") + "\t");
        }
        System.out.println("Done reading table.");
    }

}
