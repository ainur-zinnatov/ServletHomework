package ru.kpfu.servlets;

import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.kpfu.repositories.UserRepositories2.emailIsValid;
import static ru.kpfu.repositories.UserRepositories2.searchEmail;
import static ru.kpfu.repositories.UserRepositories2.searchName;

/**
 * Created by hp on 14.10.2015.
 */
@WebServlet(name = "login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if ( emailIsValid(email) && password!=null) {
                try {
                    User user = searchEmail(email);
                    if (user != null) {
                        if (password.equals(user.getPassword())) {
                            HttpSession session = request.getSession();
                            session.setAttribute("user", email);
                            response.sendRedirect("/profile");
                        }else {
                            request.setAttribute("message", "Email or password is incorrect!!");
                            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                        }
                    }else {
                        request.setAttribute("message", "User is not found!");
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    }
                } catch (DBException e) {
                    request.setAttribute("message", "Data Base Exception!");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }

            }else {
                request.setAttribute("message", "Email or password is incorrect!");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            response.sendRedirect("/profile");
        }else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }
}
