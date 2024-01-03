/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.DatabaseConnectionManager;
import com.mycompany.simple_chatbot.config.util.DatabaseColumnConstants;
import com.mycompany.simple_chatbot.model.ChatMessage;
import com.mycompany.simple_chatbot.model.Conversation;
import com.mycompany.simple_chatbot.service.MessageDatabaseService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lesan
 */
public class MessageDatabaseServiceImpl implements MessageDatabaseService {

    private static final MessageDatabaseServiceImpl INSTANCE = new MessageDatabaseServiceImpl();
    
    private MessageDatabaseServiceImpl() {}

    public static MessageDatabaseServiceImpl getInstance() { return INSTANCE; }
    
    @Override
    public List<ChatMessage> getListMessagesByConversationId(String conservationId) {
        List<ChatMessage> messages = new ArrayList<>();

        try {
            String selectSql = "SELECT * FROM " + DatabaseColumnConstants.TABLE_MESSAGE 
                    + " WHERE " + DatabaseColumnConstants.COLUMN_CONVERSATION_ID 
                    + " = ? ORDER BY " + DatabaseColumnConstants.COLUMN_CREATED_DATE;

            try (PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setString(1, conservationId);

                // Execute the SQL statement and get the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Create a Conversation object for each row
                        ChatMessage message = new ChatMessage.Builder()
                                .createdDate(resultSet.getTimestamp("created_date"))
                                .username(resultSet.getString("user_id"))
                                .conversationId(resultSet.getString("conversation_id"))
                                .message(resultSet.getString("message"))
                                .response(resultSet.getString("response"))
                                .build();

                        // Add the Conversation object to the list
                        messages.add(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions appropriately
        }

        return messages;
    }

    @Override
    public void insertMessage(ChatMessage message) {
        try (Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO " + DatabaseColumnConstants.TABLE_MESSAGE +
                " (" + DatabaseColumnConstants.COLUMN_USER_ID + ", " +
                       DatabaseColumnConstants.COLUMN_CONVERSATION_ID + ", " +
                       DatabaseColumnConstants.COLUMN_MESSAGE + ", " +
                       DatabaseColumnConstants.COLUMN_RESPONSE + ", " +
                       DatabaseColumnConstants.COLUMN_CREATED_DATE + ") " +
                "VALUES (?, ?, ?, ?, NOW())")) {

            pstmt.setString(1, message.getUsername());
            pstmt.setString(2, message.getConversationId());
            pstmt.setString(3, message.getMessage());
            pstmt.setString(4, message.getResponse());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
