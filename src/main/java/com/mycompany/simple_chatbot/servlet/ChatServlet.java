/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.ChatMessage;
import com.mycompany.simple_chatbot.model.Conversation;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.ChatbotService;
import com.mycompany.simple_chatbot.service.ConversationDatabaseService;
import com.mycompany.simple_chatbot.service.MessageDatabaseService;
import com.mycompany.simple_chatbot.service.RedisService;
import com.mycompany.simple_chatbot.service.impl.ChatbotServiceImpl;
import com.mycompany.simple_chatbot.service.impl.ConversationDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.MessageDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import java.io.IOException;
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
    
    private final ChatbotService chatbot = ChatbotServiceImpl.getInstance();
    private final ConversationDatabaseService conversationService = ConversationDatabaseServiceImpl.getInstance(); 
    private final MessageDatabaseService messageService =  MessageDatabaseServiceImpl.getInstance();
    private final RedisService redisService = RedisServiceImpl.getInstance();
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
        String conversationToken = request.getParameter(StringConstants.CONVERSATION_TOKEN_PARAM);
        
        String chatbotResponse = "";
        
        if (username != null && message != null && !username.isEmpty() && !message.isEmpty()) {
            chatbotResponse = chatbot.sendMessage(message);
            chatbotResponse = chatbotResponse.replaceAll(message, "");
            chatbotResponse = chatbotResponse.replaceAll("\n", "");
            
            ChatMessage chatMessage = new ChatMessage.Builder()
                    .conversationId(conversationToken)
                    .username(username)
                    .response(chatbotResponse)
                    .message(message)
                    .build();
            
            chatMessages.add(chatMessage);
            
            if (conversationService.getConversationById(conversationToken) == null) {
                Conversation conver = new Conversation.Builder()
                        .id(conversationToken)
                        .username(username)
                        .name(message)
                        .build();
                conversationService.insertConversation(conver);
            }
            
            messageService.insertMessage(chatMessage);
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
        
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(StringConstants.USER_SESSION);
        if (userInfo == null || redisService.getValueByKey(userInfo.getUserToken()) == null) {
            response.sendRedirect(URLUtils.getFullURL(URLUtils.LOGIN_URL));
        } else request.setAttribute("username", redisService.getValueByKey(userInfo.getUserToken()));
        
        request.getRequestDispatcher(StringConstants.CHAT_PAGE).forward(request, response);
    }
 
}
