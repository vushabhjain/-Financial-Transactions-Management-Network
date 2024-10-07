package JAVA_PRO.BankingSystem;

import com.sun.security.jgss.GSSUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection con;
    private Scanner in;

    public User(Connection con, Scanner in) {
        this.con = con;
        this.in = in;
    }

    public void register() {
        in.nextLine();
        System.out.print("Full Name : ");
        String full_name = in.nextLine();
        System.out.print("Email : ");
        String email = in.nextLine();
        System.out.print("Password : ");
        String pass = in.nextLine();
        if(user_exits(email)){
            System.out.println("This email Already Exists in the Database");
            return;
        }
        String Query = "INSERT INTO User(full_name, email, password) VALUES(?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(Query);
            ps.setString(1,full_name);
            ps.setString(2,email);
            ps.setString(3,pass);
           int affectedRow =  ps.executeUpdate();
           if(affectedRow > 0){
               System.out.println("Registration Completed Successfully");
           }else{
               System.out.println("Registration Failed ! ");
           }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    public boolean user_exits(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

public String login(){
        in.nextLine();
    System.out.print("Enter Email : ");
    String email = in.nextLine();
    System.out.print("Enter PassWord : ");
    String  pass = in.nextLine();
    String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
    try{
        PreparedStatement ps = con.prepareStatement(login_query);
        ps.setString(1,email);
        ps.setString(2,pass);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return email;

        }else {
            return null;

        }

    } catch (SQLException e) {
        throw new RuntimeException(e);

    }
}

}
