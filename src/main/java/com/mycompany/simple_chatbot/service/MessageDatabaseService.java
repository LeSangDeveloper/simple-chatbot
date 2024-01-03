/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

import com.mycompany.simple_chatbot.model.ChatMessage;
import java.util.List;

/**
 *
 * @author lesan
 */
public interface MessageDatabaseService {
    
    List<ChatMessage> getListMessagesByConversationId(String conservationId);
    void insertMessage(ChatMessage message);
    
}
