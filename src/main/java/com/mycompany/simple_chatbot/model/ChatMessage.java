/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.model;

import java.util.Date;

/**
 *
 * @author lesan
 */
public class ChatMessage {
    private String username;
    private String conversationId;
    private String message;
    private String response;
    private Date createdDate;

    private ChatMessage() {
    }
    
    public String getConversationId() {
        return conversationId;
    }
    
    public String getResponse() {
        return response;
    }
    
    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    
    public static class Builder {
        public String username;
        public String conversationId;
        public Date createdDate; 
        public String message;
        public String response;
        
        public Builder conversationId(String id) {
            this.conversationId = id;
            return this;
        }
        
        public Builder username(String name) {
            this.username = name;
            return this;
        }
        
        public Builder response(String response) {
            this.response = response;
            return this;
        }
        
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        
        public ChatMessage build() {
            ChatMessage con = new ChatMessage();
            con.createdDate = this.createdDate;
            con.conversationId = this.conversationId;
            con.message = this.message;
            con.response = this.response;
            con.username = this.username;
            return con;
        }
    }
}
