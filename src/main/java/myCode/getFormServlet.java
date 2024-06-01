package myCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getFormServlet", value = "/getFormServlet")
public class getFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Parameters from Request => name, price, author
        String bookname = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String author = request.getParameter("author");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet GetFormServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3> Name: " + bookname + "</h3>");
        out.println("<h3> Price: " + price + "</h3>");
        out.println("<h3> Author: " + author + "</h3>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}