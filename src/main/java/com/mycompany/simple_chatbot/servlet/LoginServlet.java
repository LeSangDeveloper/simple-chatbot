/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.TokenUtils;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.model.ChatMessage;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.RedisService;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.simple_chatbot.service.AccountDatabaseService;

/**
 *
 * @author lesan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private AccountDatabaseService dbService = AccountDatabaseServiceImpl.getInstance();
    private RedisService redisService = RedisServiceImpl.getInstance();
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = request.getParameter("error");

        if (request.getSession().getAttribute(StringConstants.USER_SESSION) == null || redisService.getValueByKey(StringConstants.USER_SESSION) == null) {
            if (error != null && error.contains("incorrect_username_password")) {
                request.setAttribute("loginError", "Invalid username or password");
            }
            request.getRequestDispatcher(StringConstants.LOGIN_PAGE).forward(request, response);
        } else request.getRequestDispatcher(StringConstants.CHAT_PAGE).forward(request, response);
    }

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
        String username = request.getParameter(StringConstants.USERNAME_PARAM);
        String password = request.getParameter(StringConstants.PASSWORD_PARAM);

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            // TODO improve later
            Account account = new Account.Builder()
                    .id(username)
                    .password(password)
                    .build();
            
            if (dbService.validateUser(account)) {
                String token = TokenUtils.generateToken();
                redisService.putValueByKey(token, username);
                UserInfo info = new UserInfo(username, token);
                request.getSession().setAttribute(StringConstants.USER_SESSION, info);
                String url = URLUtils.getFullURL(URLUtils.CHAT_URL);
                response.sendRedirect(url);
            } else {
                // TODO make failed login flow
                String url = URLUtils.getFullURL(URLUtils.LOGIN_URL);
                response.sendRedirect(url + "?error=incorrect_username_password");
            }
        }
        
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
