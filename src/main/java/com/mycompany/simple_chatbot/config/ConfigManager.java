/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config;

import com.mycompany.simple_chatbot.service.impl.ChatbotServiceImpl;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author lesan
 */
public class ConfigManager {
    private static final String CONFIG_FILE = "C:\\Users\\lesan\\Documents\\projects\\simple_chatbot\\src\\main\\webapp\\WEB-INF\\application.properties";
    private static Properties properties;

    private static final ConfigManager INSTANCE = new ConfigManager(); 

    
    private ConfigManager() {
        loadConfig();
    }
    
    private void loadConfig() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }
    }

    public static ConfigManager getInstance() {
        return INSTANCE;
    }
    
    public String getDbUsername() {
        return properties.getProperty("db.username");
    }
    
    public String getDbDriver() {
        return properties.getProperty("db.driver");
    }

    public String getDbConnectionString() {
        return properties.getProperty("db.connectionString");
    }
    
    public String getDbPassword() {
        return properties.getProperty("db.password");
    }
    
    public String getRedisHost() {
        return properties.getProperty("redis.host");
    }
    
    public Integer getRedisPort() {
        return Integer.valueOf(properties.getProperty("redis.port"));
    }
}
