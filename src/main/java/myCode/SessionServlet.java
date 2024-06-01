package myCode;

import Obj.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {
    private PreparedStatement ps;

    @Override
    public void init() throws ServletException{
        initializeDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String bookId = request.getParameter("bookId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");


        // Create Cookie object
        Cookie cookieBookId = new Cookie("bookId", bookId);
        Cookie cookieName = new Cookie("name", name);
        Cookie cookiePrice = new Cookie("price", price);
        Cookie cookieAuthor = new Cookie("author", author);

        response.addCookie(cookieBookId);
        response.addCookie(cookieName);
        response.addCookie(cookiePrice);
        response.addCookie(cookieAuthor);

        Book book = new Book();
        book.setBookId(Integer.parseInt(bookId));
        book.setName(name);
        book.setPrice(Integer.parseInt(price));
        book.setAuthor(author);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("book", book);


        PrintWriter out = response.getWriter();

        out.println("You enter the following data :");
        out.println("<p>" + bookId + "</p>");
        out.println("<br />");
        out.println("<p>" + name + "</p>");
        out.println("<br />");
        out.println("<p>" + price + "</p>");
        out.println("<br />");
        out.println("<p>" + author + "</p>");

        out.println("<form method=\"post\" action=\"/cookie\">");
        out.println("<input type=\"submit\" value=\"Confirm\">");
        out.println("</form>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Book book = (Book) httpSession.getAttribute("book");

        try{
            saveBook(book.getBookId(), book.getName(), book.getPrice(), book.getAuthor());
            PrintWriter out = response.getWriter();
            out.println("Book" + book.getName() + " has been store in the database");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/showgavideodb", "showga", "991378");
            System.out.println("Database connected");

            ps = conn.prepareStatement("insert into book (bookId, name, price, author) values (?,?,?,?)");
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveBook(int id, String name, int price, String author) {
        try {
            // parameterIndex refer to the sequence of the insert into book "(1, 2, 3, 4)"
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setString(4, author);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}