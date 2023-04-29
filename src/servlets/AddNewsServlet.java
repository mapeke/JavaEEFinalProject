package servlets;

import db.DBConnection;
import entity.News;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/addNews")
public class AddNewsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null && user.getRole()==1) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            DBConnection.addNews(news);
            response.sendRedirect("/");
        }else{
            response.sendRedirect("/login");
        }
    }
}
