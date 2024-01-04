/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.TokenUtils;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.AccountDatabaseService;
import com.mycompany.simple_chatbot.service.RedisService;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lesan
 */
@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/update-password"})
public class UpdatePasswordServlet extends HttpServlet {

    private final AccountDatabaseService accDbService = AccountDatabaseServiceImpl.getInstance();
    private final RedisService redisService = RedisServiceImpl.getInstance();
    
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
        request.getRequestDispatcher(StringConstants.UPDATE_PASSWORD_PAGE).forward(request, response);
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
        String oldPassword = request.getParameter(StringConstants.OLD_PASSWORD);
        String newPassword = request.getParameter(StringConstants.NEW_PASSWORD);
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(StringConstants.USER_SESSION);

        
        if (oldPassword != null && newPassword != null && !oldPassword.isEmpty() && !newPassword.isEmpty()) { 
            Account account = new Account.Builder()
                    .id(userInfo.getUsername())
                    .password(oldPassword)
                    .build();
            
            // Validate old password
            if (accDbService.validateUser(account)) {
                accDbService.updatePassword(userInfo.getUsername(), newPassword);
                String url = URLUtils.getFullURL(URLUtils.LOGOUT_URL);
                response.sendRedirect(url);
            } else {
                String url = URLUtils.getFullURL(URLUtils.UPDATE_PASSWORD_URL);
                response.sendRedirect(url);
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
