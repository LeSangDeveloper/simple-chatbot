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
        String error = request.getParameter(StringConstants.PARAM_ERROR);
        if (error != null && error.contains("account_existed")) {            
            String id = (String)request.getSession().getAttribute(StringConstants.SIGNUP_ID);
            String password = (String)request.getSession().getAttribute(StringConstants.SIGNUP_PASSWORD);
            String surname = (String)request.getSession().getAttribute(StringConstants.SIGNUP_SURNAME);
            String middleName = (String)request.getSession().getAttribute(StringConstants.SIGNUP_MIDDLE_NAME);
            String firstName = (String)request.getSession().getAttribute(StringConstants.SIGNUP_FIRST_NAME);
            String phone = (String)request.getSession().getAttribute(StringConstants.SIGNUP_PHONE);
            String email = (String)request.getSession().getAttribute(StringConstants.SIGNUP_EMAIL);
            
            request.getSession().removeAttribute(StringConstants.SIGNUP_ID);
            request.getSession().removeAttribute(StringConstants.SIGNUP_PASSWORD);
            request.getSession().removeAttribute(StringConstants.SIGNUP_SURNAME);
            request.getSession().removeAttribute(StringConstants.SIGNUP_MIDDLE_NAME);
            request.getSession().removeAttribute(StringConstants.SIGNUP_FIRST_NAME);
            request.getSession().removeAttribute(StringConstants.SIGNUP_PHONE);
            request.getSession().removeAttribute(StringConstants.SIGNUP_EMAIL);
            
            request.setAttribute("signUpError", "username existed");
            
            request.setAttribute(StringConstants.SIGNUP_ID, id);
            request.setAttribute(StringConstants.SIGNUP_PASSWORD, password);
            request.setAttribute(StringConstants.SIGNUP_SURNAME, surname);
            request.setAttribute(StringConstants.SIGNUP_MIDDLE_NAME, middleName);
            request.setAttribute(StringConstants.SIGNUP_FIRST_NAME, firstName);
            request.setAttribute(StringConstants.SIGNUP_PHONE, phone);
            request.setAttribute(StringConstants.SIGNUP_EMAIL, email);
        }
        request.getRequestDispatcher(StringConstants.SIGNUP_PAGE).forward(request, response);
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
            request.getSession().setAttribute(StringConstants.SIGNUP_ID, id);
            request.getSession().setAttribute(StringConstants.SIGNUP_PASSWORD, password);
            request.getSession().setAttribute(StringConstants.SIGNUP_SURNAME, surname);
            request.getSession().setAttribute(StringConstants.SIGNUP_MIDDLE_NAME, middleName);
            request.getSession().setAttribute(StringConstants.SIGNUP_FIRST_NAME, firstName);
            request.getSession().setAttribute(StringConstants.SIGNUP_PHONE, phone);
            request.getSession().setAttribute(StringConstants.SIGNUP_EMAIL, email);
            
            String url = URLUtils.getFullURL(URLUtils.SIGNUP_URL);
            response.sendRedirect(url + "?" + StringConstants.PARAM_ERROR + "=account_existed");
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
