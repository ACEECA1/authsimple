package com.projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String connectionUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "0000";
        try (Connection conn = DriverManager.getConnection(connectionUrl , dbUser, dbPassword)) {
            // Insert into database
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                out.println("<p style='color: green;'>✓ Login successful!</p>");
                out.println("<p>Welcome back, " + username + "!</p>");
            } else {
                out.println("<p style='color: red;'>✗ Login failed - invalid email or password</p>");
            }
        } catch (SQLException e) {
            out.println("<p style='color: red;'>✗ Registration failed!</p>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}