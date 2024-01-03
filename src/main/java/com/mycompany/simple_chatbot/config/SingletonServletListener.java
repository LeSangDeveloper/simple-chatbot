/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config;

import com.mycompany.simple_chatbot.service.impl.ChatbotServiceImpl;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author lesan
 */
@WebListener
public class SingletonServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigManager.getInstance();
        RedisServiceImpl.getInstance();
        AccountDatabaseServiceImpl.getInstance();
        ChatbotServiceImpl.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
