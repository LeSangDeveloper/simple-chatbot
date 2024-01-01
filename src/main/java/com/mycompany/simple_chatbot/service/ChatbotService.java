/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service;

import com.mycompany.simple_chatbot.model.request.ChatbotRequest;
import com.mycompany.simple_chatbot.model.response.ChatbotResponse;

/**
 *
 * @author lesan
 */
public interface ChatbotService {
    public String sendMessage(String request);
}
