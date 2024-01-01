/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.TokenUtils;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.simple_chatbot.service.DatabaseService;
import com.mycompany.simple_chatbot.service.RedisService;
import com.mycompany.simple_chatbot.service.EmailService;
import com.mycompany.simple_chatbot.service.impl.DatabaseServiceImpl;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import com.mycompany.simple_chatbot.service.impl.EmailServiceImpl;
/**
 *
 * @author lesan
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    private final DatabaseService dbService = DatabaseServiceImpl.getInstance();
    private final RedisService redisService = RedisServiceImpl.getInstance();
    private final EmailService emailService = EmailServiceImpl.getInstance();
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        request.getRequestDispatcher(StringConstants.FORGOT_PASSWORD_PAGE).forward(request, response);
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
        
        Account acc = dbService.getUser(username);
        
        if (acc != null) {
            String email = acc.getEmail();
            String token = TokenUtils.generateToken();
            
            redisService.putValueByKetWithTimeInSec(token, acc.getId(), 180L);
            emailService.sendEmail(email, token);
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
