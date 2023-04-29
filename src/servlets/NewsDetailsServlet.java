package servlets;

import entity.News;
import entity.Comments;
import db.DBConnection;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            News news = DBConnection.getNewsById(id);
            request.setAttribute("news", news);

            if (news != null) {
                ArrayList<Comments> comments = DBConnection.getComments(news.getId());
                request.setAttribute("comments", comments);
                System.out.println(comments);
            }
            request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }

    }
}
