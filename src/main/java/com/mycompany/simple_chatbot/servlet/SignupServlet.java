/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.Account;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.impl.AccountDatabaseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {

    private final AccountDatabaseService dbService = AccountDatabaseServiceImpl.getInstance();

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
        response.sendRedirect(StringConstants.SIGNUP_PAGE);
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
        String id = request.getParameter(StringConstants.USERNAME_PARAM);
        String password = request.getParameter(StringConstants.PASSWORD_PARAM);
        String surname = request.getParameter(StringConstants.SURNAME_PARAM);
        String middleName = request.getParameter(StringConstants.MIDDLE_NAME_PARAM);
        String firstName = request.getParameter(StringConstants.FIRST_NAME_PARAM);
        String phone = request.getParameter(StringConstants.PHONE_PARAM);
        String email = request.getParameter(StringConstants.EMAIL_PARAM);

        if (dbService.getUser(id) == null) {
        
            Account user = new Account.Builder()
                .firstName(firstName)
                .password(password)
                .email(email)
                .id(id)
                .middleName(middleName)
                .phone(phone)
                .surname(surname)
                .build();
        
            dbService.addUser(user);
            response.sendRedirect(URLUtils.BASE_HOME);
        } else {
            response.sendRedirect(URLUtils.SIGNUP_URL);
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
