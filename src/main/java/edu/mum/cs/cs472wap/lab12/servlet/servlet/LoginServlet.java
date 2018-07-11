package edu.mum.cs.cs472wap.lab12.servlet.servlet;

import edu.mum.cs.cs472wap.lab12.servlet.database.UserDB;
import edu.mum.cs.cs472wap.lab12.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/", name = "loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = "";
        String checkBoxHtmlLine = "<div><label>Remember me:</label><input type=\"checkbox\" name=\"rememberme\"";

        for(Cookie cookie:req.getCookies()){
            if(cookie.getName().equals("username")){
                username = cookie.getValue();
            }
            if(cookie.getName().equals("remembermeCheckbox") && cookie.getValue().equals("on")){
                checkBoxHtmlLine+=" checked";
            }
        }

        checkBoxHtmlLine +="></input></div>";


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Login</title></head><body>");
        out.print("<form method='post'>");

        out.print("<div><label>Username:</label><input type=\"text\" name=\"username\" value=\""+username+"\"></input></div>");
        out.print("<div><label>Password:</label><input type=\"password\" name=\"password\"></input></div>");
        out.print(checkBoxHtmlLine);

        out.print("<button type=\"submit\">Log in</button>");
        out.print("</form>");
        out.print("</body></html>");
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDB userDB = new UserDB();
        User user = userDB.getUsers().get(req.getParameter("username"));
        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);


            if(req.getParameter("rememberme")!=null){
                Cookie rememberCookie = new Cookie("remembermeCheckbox","on");
                Cookie usernameCookie = new Cookie("username",user.getUsername());
                rememberCookie.setMaxAge(600);
                usernameCookie.setMaxAge(600);
                resp.addCookie(rememberCookie);
                resp.addCookie(usernameCookie);
            }else{
                for(Cookie cookie:req.getCookies()){
                    if(cookie.getName().equals("username")){
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                    if(cookie.getName().equals("remembermeCheckbox")){
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                }
            }


            resp.sendRedirect(req.getContextPath() + "/logon");
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
