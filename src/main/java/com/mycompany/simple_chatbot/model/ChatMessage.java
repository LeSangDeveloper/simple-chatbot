/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.model;

/**
 *
 * @author lesan
 */
public class ChatMessage {
    private String username;
    private String message;
    private String response;

    public ChatMessage(String username, String message, String response) {
        this.username = username;
        this.message = message;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    
    
    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
