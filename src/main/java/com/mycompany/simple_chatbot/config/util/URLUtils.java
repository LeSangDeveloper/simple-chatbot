/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config.util;

/**
 *
 * @author lesan
 */
public class URLUtils {
 
    public static String BASE_URL = "/simple_chatbot";
    public static String BASE_HOME = "/simple_chatbot/";
    public static String LOGIN_URL = "/login";
    public static String CHAT_URL = "/chat";
    public static String SIGNUP_URL = "/signup";
    
    public static String getFullURL(String url) {
        return BASE_URL + url;
    }
    
}
