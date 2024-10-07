package JAVA_PRO.BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection con ;
    private Scanner in ;

    public AccountManager(Connection con, Scanner in) {
        this.con = con;
        this.in = in;
    }

    public void credit_money(long account_number) throws SQLException {
        in.nextLine();
        System.out.println("Enter Amount : ");
        double amount = in.nextDouble();
        in.nextLine();
        System.out.println("Enter Security_Pin : ");
        String secrity_pin = in.nextLine();
        String sql = "SELECT * FROM accounts WHERE Account_number = ? and Security_pin = ? ";


        try{
            con.setAutoCommit(false);
            if(account_number != 0) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, account_number);
                ps.setString(2, secrity_pin);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    String credit_query = "UPDATE Accounts SET balance = balance + ? WHERE account_number = ?";
                    PreparedStatement ps1  = con.prepareStatement(credit_query);
                    ps1.setDouble(1,amount);
                    ps1.setLong(2,account_number);
                    int rowafacted = ps1.executeUpdate();
                    if(rowafacted > 0){
                        System.out.println("Rs "+amount+" credited Successful ! ");
                        con.commit();
                        con.setAutoCommit(true);
                    }else {
                        System.out.println("Transaction Failed ! ");
                        con.rollback();
                        con.setAutoCommit(true);
                    }

                }else {
                    System.out.println("Invalid Security Pin!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        con.setAutoCommit(true);
    }


    public void debit_money(long Account_number) throws SQLException {
        in.nextLine();
        System.out.print("Enter Amount: ");
        double amount = in.nextDouble();
        in.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = in.nextLine();

        try{
            con.setAutoCommit(false);
            if(Account_number != 0) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE Account_number = ? and Security_pin = ? ");
                ps.setDouble(1, Account_number);
                ps.setString(2, security_pin);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    double current_balance = rs.getDouble("balance");
                    if(amount<=current_balance){
                        String debit_query = "UPDATE Accounts SET balance = balance - ? WHERE account_number = ?";
                        PreparedStatement preparedStatement1 = con.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setLong(2, Account_number);
                        int rowsAffected = preparedStatement1.executeUpdate();
                        if(rowsAffected > 0){
                            System.out.println("Rs."+amount+" debited Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction Failed!");
                            con.rollback();
                            con.setAutoCommit(true);
                        }

                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Pin!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        con.setAutoCommit(true);
    }

    public void transfer_money(long sender_account_number) throws SQLException {
        in.nextLine();
        System.out.print("Enter Receiver Account Number: ");
        long receiver_account_number = in.nextLong();
        System.out.print("Enter Amount: ");
        double amount = in.nextDouble();
        in.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = in.nextLine();

        try{
            con.setAutoCommit(false);
            if(sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? AND security_pin = ? ");
                preparedStatement.setLong(1, sender_account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){

                        // Write debit and credit queries
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE Account_number = ?";
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE Account_number = ?";

                        // Debit and Credit prepared Statements
                        PreparedStatement creditPreparedStatement = con.prepareStatement(credit_query);
                        PreparedStatement debitPreparedStatement = con.prepareStatement(debit_query);

                        // Set Values for debit and credit prepared statements
                        creditPreparedStatement.setDouble(1, amount);
                        creditPreparedStatement.setLong(2, receiver_account_number);
                        debitPreparedStatement.setDouble(1, amount);
                        debitPreparedStatement.setLong(2, sender_account_number);
                        int rowsAffected1 = debitPreparedStatement.executeUpdate();
                        int rowsAffected2 = creditPreparedStatement.executeUpdate();
                        if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                            System.out.println("Transaction Successful!");
                            System.out.println("Rs."+amount+" Transferred Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction Failed");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Security Pin!");
                }
            }else{
                System.out.println("Invalid account number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        con.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        in.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = in.nextLine();
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT balance FROM accounts WHERE Account_number = ? AND Security_pin = ?");
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance: "+balance);
            }else{
                System.out.println("Invalid Pin!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
