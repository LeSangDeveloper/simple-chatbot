/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.DatabaseConnectionManager;
import com.mycompany.simple_chatbot.config.util.DatabaseColumnConstants;
import com.mycompany.simple_chatbot.model.Conversation;
import com.mycompany.simple_chatbot.service.ConversationDatabaseService;
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
public class ConversationDatabaseServiceImpl implements ConversationDatabaseService {

    private static final ConversationDatabaseServiceImpl INSTANCE = new ConversationDatabaseServiceImpl();
    
    private ConversationDatabaseServiceImpl() {}
    
    public static ConversationDatabaseServiceImpl getInstance() { return INSTANCE; }
    
    @Override
    public Conversation getConversationById(String conversationId) {
        Conversation conversation = null;

        try {
            String selectSql = "SELECT * FROM Conversation WHERE conversation_id = ?";

            try (PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setString(1, conversationId);

                // Execute the SQL statement and get the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Create a Conversation object for the result
                        conversation = new Conversation.Builder()
                                .createdDate(resultSet.getTimestamp("created_date"))
                                .id(resultSet.getString("id"))
                                .name(resultSet.getString("name"))
                                .username(resultSet.getString("user_id"))
                                .build();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions appropriately
        }

        return conversation;
    }
    
    @Override
    public List<Conversation> getConversationsByUserId(String userId) {
        List<Conversation> conversations = new ArrayList<>();

        try {
            String selectSql = "SELECT * FROM " + DatabaseColumnConstants.TABLE_CONVERSTION 
                    + " WHERE " + DatabaseColumnConstants.COLUMN_USER_ID
                    + " = ? ORDER BY " + DatabaseColumnConstants.COLUMN_CREATED_DATE + " DESC";

            try (PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setString(1, userId);

                // Execute the SQL statement and get the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Create a Conversation object for each row
                        Conversation conversation = new Conversation.Builder()
                                .createdDate(resultSet.getTimestamp(DatabaseColumnConstants.COLUMN_CREATED_DATE))
                                .id(resultSet.getString(DatabaseColumnConstants.COLUMN_ID))
                                .name(resultSet.getString(DatabaseColumnConstants.COLUMN_NAME))
                                .username(resultSet.getString(DatabaseColumnConstants.COLUMN_USER_ID))
                                .build();

                        // Add the Conversation object to the list
                        conversations.add(conversation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions appropriately
        }

        return conversations;
    }

    @Override
    public void insertConversation(Conversation con) {
        try (Connection conn=DatabaseConnectionManager.getConnection()) { 
            Statement stmt=conn.createStatement();
            String sql = "Insert into " + DatabaseColumnConstants.TABLE_CONVERSTION + " ("
                    + DatabaseColumnConstants.COLUMN_USER_ID + ", "
                    + DatabaseColumnConstants.COLUMN_ID + ", "
                    + DatabaseColumnConstants.COLUMN_NAME + ", "
                    + DatabaseColumnConstants.COLUMN_CREATED_DATE
                    + ") values ('" 
                    + con.getUsername() + "', '" 
                    + con.getId() + "', '" 
                    + con.getName() 
                    + "', NOW())";
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
