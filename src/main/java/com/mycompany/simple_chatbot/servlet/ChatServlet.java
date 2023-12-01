/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.model.ChatMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ChatServlet", urlPatterns = {"/chat"})
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private List<ChatMessage> chatMessages;

    @Override
    public void init() throws ServletException {
        super.init();
        chatMessages = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String message = request.getParameter("message");

        if (username != null && message != null && !username.isEmpty() && !message.isEmpty()) {
            ChatMessage chatMessage = new ChatMessage(username, message);
            chatMessages.add(chatMessage);
        }

        request.setAttribute("chatMessages", chatMessages);
        request.getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/chat.jsp").forward(request, response);
    }
 
}
