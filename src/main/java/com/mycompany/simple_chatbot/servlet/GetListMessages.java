/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.google.gson.Gson;
import com.mycompany.simple_chatbot.model.ChatMessage;
import com.mycompany.simple_chatbot.service.MessageDatabaseService;
import com.mycompany.simple_chatbot.service.impl.MessageDatabaseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lesan
 */
@WebServlet(name = "GetListMessages", urlPatterns = {"/messages"})
public class GetListMessages extends HttpServlet {

    private final MessageDatabaseService messageService = MessageDatabaseServiceImpl.getInstance();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get conversation ID from the request parameters
        String conversationId = request.getParameter("conversationId");

        // Retrieve messages based on the conversation ID (you need to implement this logic)
        List<ChatMessage> messages = messageService.getListMessagesByConversationId(conversationId);

        // Convert the list of messages to JSON
        String json = convertMessagesToJson(messages);

        // Set content type to JSON
        response.setContentType("application/json");

        // Send the JSON response
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
    }

    private String convertMessagesToJson(List<ChatMessage> messages) {
        // Use Gson to convert the list of messages to JSON
        Gson gson = new Gson();
        return gson.toJson(messages);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
