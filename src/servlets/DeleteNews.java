package servlets;

import entity.User;
import db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/delete-news")
public class DeleteNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null && user.getRole() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            DBConnection.deleteNews(id);
            DBConnection.deleteComment(id);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }
}
