package myCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "PostFormServlet", value = "/PostFormServlet")
public class PostFormServlet extends HttpServlet {
    private PreparedStatement ps;
    PrintWriter out;

    @Override
    public void init() throws ServletException{
        initializeDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
//        System.out.println("Id:" + bookId + " Name:"+ name + " Price:" + price + " Author:" + author);
        saveBook(Integer.parseInt(bookId), name, Integer.parseInt(price), author);
        out = response.getWriter();
        out.println(name + " has been save in database !");
        out.close();
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