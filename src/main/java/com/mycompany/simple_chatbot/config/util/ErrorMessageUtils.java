/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.config.util;

/**
 *
 * @author lesan
 */
public class ErrorMessageUtils {
    public static String PARAM_ERROR = "error";
    public static String PARAM_SIGNUP_ERROR = "signUpError";
    public static String PARAM_LOGIN_ERROR = "loginError";

    public static String MESSAGE_ACCONT_EXITED = "username existed";
    public static String MESSAGE_INCORRECT_LOGIN_INFO = "Invalid username or password";
    
    public static String ERROR_ACCONT_EXITED = "account_existed";
    public static String ERROR_INVALID_LOGIN_INFO = "incorrect_username_password";
    
    private static final String EQUAL_MARK = "=";
    public static String addParamError(String error) {
        return PARAM_ERROR + EQUAL_MARK + error;
    }
}
