/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

import com.mycompany.simple_chatbot.model.Conversation;
import java.util.List;

/**
 *
 * @author lesan
 */
public interface ConversationDatabaseService {
 
    List<Conversation> getConversationsByUserId(String userId);
    Conversation getConversationById(String id);
    void insertConversation(Conversation con);
    
}
