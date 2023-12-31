/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.model;

/**
 *
 * @author lesan
 */
public class UserInfo {
    private String id;
    private String userToken;

    public UserInfo(String username, String userToken) {
        this.id = username;
        this.userToken = userToken;
    }
    
    public String getUsername() {
        return id;
    }

    public void setUsername(String username) {
        this.id = username;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    
    
    
}
