/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.DatabaseConnectionManager;
import com.mycompany.simple_chatbot.config.util.DatabaseColumnConstants;
import com.mycompany.simple_chatbot.config.util.PasswordUtils;
import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.service.DatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lesan
 */
public class DatabaseServiceImpl implements DatabaseService {

    private final static DatabaseServiceImpl INSTANCE = new DatabaseServiceImpl();
    
    public static DatabaseServiceImpl getInstance() {
        return INSTANCE;
    }
    
    private DatabaseServiceImpl() {
    }
    
    @Override
    public void updatePassword(String user, String password) {  
        try (Connection conn=DatabaseConnectionManager.getConnection()) {
            Statement stmt=conn.createStatement();

            String salt = PasswordUtils.generateSalt();
            String passwordHashed = PasswordUtils.hashPassword(password, salt);
            
            String sql="update account set "+DatabaseColumnConstants.COLUMN_HASHED_PASSWORD+"='"+passwordHashed+"' where id='"+user+"'";
            stmt.executeUpdate(sql);
            
            sql="update account set "+DatabaseColumnConstants.COLUMN_SALT+"='"+salt+"' where id='"+user+"'";
            stmt.executeUpdate(sql);
            //////////////
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUser(Account user) {
        try (Connection conn=DatabaseConnectionManager.getConnection()) {
            Statement stmt=conn.createStatement();
            String id=user.getId(); //test
            String salt = PasswordUtils.generateSalt();
            String passwordHashed = PasswordUtils.hashPassword(user.getPassword(), salt);
            String sql="insert into account (" + DatabaseColumnConstants.COLUMN_ID
                    + ", " + DatabaseColumnConstants.COLUMN_HASHED_PASSWORD
                    + ", " + DatabaseColumnConstants.COLUMN_SALT
                    + ", " + DatabaseColumnConstants.COLUMN_SURNAME
                    + ", " + DatabaseColumnConstants.COLUMN_MIDDLE_NAME
                    + ", " + DatabaseColumnConstants.COLUMN_FIRST_NAME
                    + ", " + DatabaseColumnConstants.COLUMN_EMAIL
                    + ", " + DatabaseColumnConstants.COLUMN_PHONE
                    + ") values "
                    + "('"+id+"', '"+passwordHashed+"', '"+salt+"', '"+user.getSurname()+"', '"+user.getMiddleName()+"', '"+user.getFirstName()+"', '"+user.getEmail()+"', '"+user.getPhone()+"') ";
            stmt.executeUpdate(sql);
            //////////////
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Account getUser(String username) {
        Account account = null;
        
        try(Connection conn=DatabaseConnectionManager.getConnection()){
            Statement stmt=conn.createStatement();//   test';
            ResultSet rs=stmt.executeQuery("select * from account where id='"+username+"'");

            if(rs.next()){
                
                String surname = rs.getString(DatabaseColumnConstants.COLUMN_SURNAME);
                String middleName = rs.getString(DatabaseColumnConstants.COLUMN_MIDDLE_NAME);
                String firstName = rs.getString(DatabaseColumnConstants.COLUMN_FIRST_NAME);
                String email = rs.getString(DatabaseColumnConstants.COLUMN_EMAIL);
                String phone = rs.getString(DatabaseColumnConstants.COLUMN_PHONE);

                account = new Account.Builder()
                        .surname(surname)
                        .firstName(firstName)
                        .id(username)
                        .middleName(middleName)
                        .email(email)
                        .phone(phone)
                        .build();
                
                conn.close();
                      
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }

    @Override
    public Boolean validateUser(Account user) {
        boolean correct=false;

        try(Connection conn=DatabaseConnectionManager.getConnection()){
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from account where id='"+user.getId()+"'");
            if(rs.next()){
                
                String passwordHashed = rs.getString(DatabaseColumnConstants.COLUMN_HASHED_PASSWORD);
                String salt = rs.getString(DatabaseColumnConstants.COLUMN_SALT);
                
                try {
                    correct = PasswordUtils.validatePassword(user.getPassword(), salt, passwordHashed);
                } catch (Exception ex) {
                    Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
                Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return correct;
    }
    
}
