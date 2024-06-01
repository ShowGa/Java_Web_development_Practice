package myCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TryServlet", value = "/TryServlet")
public class TryServlet extends HttpServlet {

    // execute when initialize
    @Override
    public void init() throws ServletException{
        super.init();
        System.out.println("TryServlet init method");
    }

    // everytime refresh
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("TryServlet service method");
    }

    @Override
    public void destroy() {
        System.out.println("TryServlet destroy method");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("doGet");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<p>Hello World! From TryServlet!</p>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}