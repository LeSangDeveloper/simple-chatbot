/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.model.request;

/**
 *
 * @author lesan
 */
public class ChatbotRequest {
    public String user_input;
    
    public ChatbotRequest(String  user_input) {
        this.user_input = user_input;
    }
    
    public String getUserInput() {
        return user_input;
    }
}
