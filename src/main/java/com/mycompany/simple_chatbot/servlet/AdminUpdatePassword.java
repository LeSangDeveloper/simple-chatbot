/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.service.AccountDatabaseService;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
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
@WebServlet(name = "AdminUpdatePassword", urlPatterns = {"/admin/update-password"})
public class AdminUpdatePassword extends HttpServlet {

    private final AccountDatabaseService accDbService = AccountDatabaseServiceImpl.getInstance();

    
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
        String username = request.getParameter(StringConstants.USERNAME_PARAM);
        request.setAttribute(StringConstants.USERNAME_PARAM, username);
        request.getRequestDispatcher(StringConstants.ADMIN_UPDATE_PASSWORD_PAGE).forward(request, response);
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
        String newPassword = request.getParameter(StringConstants.NEW_PASSWORD_PARAM);
        
        if (username != null && newPassword != null && !username.isEmpty() && !newPassword.isEmpty()) { 
            accDbService.updatePassword(username, newPassword);
            String url = URLUtils.getFullURL(URLUtils.ADMIN_URL);
            response.sendRedirect(url);
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
