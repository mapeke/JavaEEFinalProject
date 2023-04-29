package servlets;

import db.DBConnection;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            int id = Integer.parseInt(request.getParameter("user_id"));
            String fullName = request.getParameter("user_name");
            String password = request.getParameter("user_password");
            String email = request.getParameter("user_email");

            User u = DBConnection.getUser(email);

            if (u != null) {
                u.setFullName(fullName);
                u.setPassword(password);
                DBConnection.updateUser(u);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", u);
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/");
            }
        }else{
            response.sendRedirect("/login");
        }


    }
}
