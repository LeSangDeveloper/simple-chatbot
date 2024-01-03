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
public class Conversation {
    private String username;
    private String id;
    private String name;
    private Date createdDate; 

    private Conversation() {}
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public static class Builder {
        private String username;
        private String id;
        private String name;
        private Date createdDate; 
        
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public Builder username(String name) {
            this.username = name;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        
        public Conversation build() {
            Conversation con = new Conversation();
            con.createdDate = this.createdDate;
            con.id = this.id;
            con.name = this.name;
            con.username = this.username;
            return con;
        }
    }
    
}
