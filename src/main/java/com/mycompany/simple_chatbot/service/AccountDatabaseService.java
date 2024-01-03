/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

import com.mycompany.simple_chatbot.model.Account;

/**
 *
 * @author lesan
 */
public interface AccountDatabaseService {
    
    void updatePassword(String id, String password);
    void addUser(Account user);
    Account getUser(String username);
    Boolean validateUser(Account user);
    
}
