/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lesan
 */
public class DatabaseConnectionManager {
    private static final ConfigManager config = ConfigManager.getInstance();
    
    public static Connection getConnection() throws SQLException {
        String username = config.getDbUsername();
        String password = config.getDbPassword();
        return DriverManager.getConnection(config.getDbConnectionString(), username, password);
    }
}
