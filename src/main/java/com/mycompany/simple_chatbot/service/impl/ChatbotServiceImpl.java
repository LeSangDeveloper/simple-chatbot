/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.ConfigManager;
import com.mycompany.simple_chatbot.service.ChatbotService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lesan
 */
public class ChatbotServiceImpl implements ChatbotService {

    private static final ChatbotServiceImpl INSTANCE = new ChatbotServiceImpl(); 
    private final ConfigManager config = ConfigManager.getInstance();
    
    public static ChatbotServiceImpl getInstance() {
        return INSTANCE;
    }
    
    private ChatbotServiceImpl() {
    }
    
    @Override
    public String sendMessage(String userInput) {
        try {
            // Create URL object
            URL chatUrl = new URL(config.getChatServiceUrl());

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) chatUrl.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("user_input", userInput);
            
            // Write JSON payload to the connection output stream
            connection.getOutputStream().write(jsonInput.toString().getBytes("UTF-8"));

            // Get response code
            int responseCode = connection.getResponseCode();
            
            // Read and handle response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Get and print the chatbot's response
                String chatbotResponse = jsonResponse.getString("response");
                return chatbotResponse;
            }
            else {
                return null;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChatbotServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ChatbotServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatbotServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }
    
}
