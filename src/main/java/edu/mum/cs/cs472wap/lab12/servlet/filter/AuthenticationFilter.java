package edu.mum.cs.cs472wap.lab12.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter",
        urlPatterns = {"/logon","/logout"}//,
        //servletNames = {"logonServlet"}
)
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if(session!=null && session.getAttribute("user")!=null){
            filterChain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath()+"/");
        }
    }

    @Override
    public void destroy() {

    }
}
