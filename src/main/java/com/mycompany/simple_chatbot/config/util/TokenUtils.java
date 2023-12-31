/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config.util;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author lesan
 */
public class TokenUtils {
     public static String generateToken() {
        byte[] tokenBytes = new byte[32];
        new SecureRandom().nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }   
}
