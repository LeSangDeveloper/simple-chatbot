/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

/**
 *
 * @author lesan
 */
public interface RedisService {
    
    String getValueByKey(String key);
    Boolean putValueByKey(String key, String value);
    Boolean putValueByKetWithTimeInSec(String key, String value, Long time);
    
}
