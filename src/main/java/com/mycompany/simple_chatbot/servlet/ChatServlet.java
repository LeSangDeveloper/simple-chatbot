/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.ChatMessage;
import com.mycompany.simple_chatbot.service.ChatbotService;
import com.mycompany.simple_chatbot.service.impl.ChatbotServiceImpl;
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
    
    private ChatbotService chatbot = ChatbotServiceImpl.getInstance();
    
    @Override
    public void init() throws ServletException {
        super.init();
        chatMessages = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter(StringConstants.USERNAME_PARAM);
        String message = request.getParameter(StringConstants.MESSAGE_PARAM);
        String chatbotResponse = "";
        
        if (username != null && message != null && !username.isEmpty() && !message.isEmpty()) {
            chatbotResponse = chatbot.sendMessage(message);
            ChatMessage chatMessage = new ChatMessage(username, message, chatbotResponse);
            chatMessages.add(chatMessage);
        }

//        request.setAttribute(StringConstants.CHAT_MESSAGES_ATTRIBUTE, chatMessages);
//        request.getRequestDispatcher(StringConstants.CHAT_PAGE).forward(request, response);
        response.getWriter().write(chatbotResponse.replaceAll(message, ""));
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
        response.sendRedirect(StringConstants.CHAT_PAGE);
    }
 
}
