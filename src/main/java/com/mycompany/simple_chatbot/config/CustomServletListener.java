/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config;

import com.mycompany.simple_chatbot.service.impl.ChatbotServiceImpl;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.ConversationDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.MessageDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author lesan
 */
@WebListener
public class CustomServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(ConfigManager.getInstance().getDbDriver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RedisServiceImpl.getInstance();
        AccountDatabaseServiceImpl.getInstance();
        ConversationDatabaseServiceImpl.getInstance();
        MessageDatabaseServiceImpl.getInstance();
        ChatbotServiceImpl.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
