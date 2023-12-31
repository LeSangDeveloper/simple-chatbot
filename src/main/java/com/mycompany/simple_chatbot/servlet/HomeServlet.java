/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.simple_chatbot.servlet;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.RedisService;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends HttpServlet {

    private RedisService redisService = RedisServiceImpl.getInstance();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(StringConstants.USER_SESSION);
        if (userInfo == null || redisService.getValueByKey(userInfo.getUserToken()) == null) {
            response.sendRedirect(URLUtils.getFullURL(URLUtils.LOGIN_URL));
        } else response.sendRedirect(URLUtils.getFullURL(URLUtils.CHAT_URL));
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
