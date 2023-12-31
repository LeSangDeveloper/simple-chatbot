/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config.util;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 *
 * @author lesan
 */
public class PasswordUtils {
    
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    public static String hashPassword(String password, String salt)throws Exception {
        int iterations = 10000;
        int keyLength = 256;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
    
    public static boolean validatePassword(String enteredPassword, String storedSalt, String storedHashedPassword) throws Exception {
        String enteredPasswordWithSalt = enteredPassword + storedSalt;
        String hashedEnteredPassword = hashPassword(enteredPasswordWithSalt, storedSalt);
        return hashedEnteredPassword.equals(storedHashedPassword);
    }
    
}
