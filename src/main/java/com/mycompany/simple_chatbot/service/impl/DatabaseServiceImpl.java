/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.service.DatabaseService;

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
    public void updateUser(Account user) {
    }

    @Override
    public void addUser(Account user) {
    }

    @Override
    public Account getUser(String username) {
        return null;
    }

    @Override
    public Boolean validateUser(Account user) {
        return false;
    }
    
}
