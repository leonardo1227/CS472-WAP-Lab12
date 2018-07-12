package edu.mum.cs.cs472wap.lab12.servlet.servlet;

import edu.mum.cs.cs472wap.lab12.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logon", name = "logonServlet")
public class LogonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Logon</title></head><body>");
        out.print("<form method='get' action='logout' >");

        out.print("<div><label>Welcome, " + user.getUsername() + ":</label></div>");

        out.print("<button type=\"submit\">Logout</button>");
        out.print("</form>");
        out.print("</body></html>");
        out.close();
    }


}
