package com.projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String connectionUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "0000";
        try (Connection conn = DriverManager.getConnection(connectionUrl , dbUser, dbPassword)) {
            // Insert into database
            String sql = "INSERT INTO Users (displayname, email, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, username);
            pstmt.setString(4, password);

            // Use executeUpdate() for INSERT, not executeQuery()
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<p style='color: green;'>✓ Registration successful!</p>");
                out.println("<p>Welcome, " + username + "!</p>");
            } else {
                out.println("<p style='color: orange;'>⚠ Registration failed - no rows affected</p>");
            }

        } catch (SQLException e) {
            out.println("<p style='color: red;'>✗ Registration failed!</p>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}