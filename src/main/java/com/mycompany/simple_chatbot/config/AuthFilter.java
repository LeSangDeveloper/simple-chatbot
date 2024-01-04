/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.mycompany.simple_chatbot.config;

import com.mycompany.simple_chatbot.config.util.StringConstants;
import com.mycompany.simple_chatbot.config.util.URLUtils;
import com.mycompany.simple_chatbot.model.UserInfo;
import com.mycompany.simple_chatbot.service.RedisService;
import com.mycompany.simple_chatbot.service.impl.RedisServiceImpl;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lesan
 */
@WebFilter(filterName="AuthFilter", 
        urlPatterns= {"/*"}, 
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AuthFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String EXECUTING_PAGE = "executing_page";
    private final String FILTERING = "filtering"; 
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private RedisService redisService = RedisServiceImpl.getInstance();
    
    public AuthFilter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session=request.getSession();
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(StringConstants.USER_SESSION);
        String tokenValue = redisService.getValueByKey(userInfo == null ? "" : userInfo.getUserToken());
        
        if ((userInfo == null || tokenValue == null) 
                && session.getAttribute(FILTERING) == null) {
            session.setAttribute(AuthFilter.EXECUTING_PAGE, request.getRequestURL().toString());
            session.setAttribute(FILTERING, "true");
            
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.sendRedirect(URLUtils.getFullURL(URLUtils.LOGIN_URL));
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
       
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
