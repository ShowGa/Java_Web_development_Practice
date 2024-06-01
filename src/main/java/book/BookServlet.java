package book;

import Obj.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends HttpServlet {
    private PreparedStatement ps;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initializeDB();
        try {
            ps.setString(1, request.getParameter("bookId"));
            ResultSet rs = ps.executeQuery();
            Book book = null;
            if (rs.next()) {
                book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getInt("price"));
                book.setAuthor(rs.getString("author"));
            }

            request.setAttribute("book", book);
            // render JSP page
            request.getRequestDispatcher("/book.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/showgavideodb", "showga", "991378");
            System.out.println("Database connected");

            ps = conn.prepareStatement("select * from book where bookId = ?");
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}