/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

import com.mycompany.simple_chatbot.model.User;

/**
 *
 * @author lesan
 */
public interface DatabaseService {
    
    void updateUser(User user);
    void addUser(User user);
    User getUser(String username);
    Boolean validateUser(User user);
    
}
