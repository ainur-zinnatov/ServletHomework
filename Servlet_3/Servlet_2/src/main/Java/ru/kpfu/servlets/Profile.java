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
@WebServlet(name = "profile")
public class Profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/login");
        }
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        htmlResponse +="<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "    <title>Users</title>\n" +
                "    <style>\n" +
                ".container {width: 900px; /* Ширина слоя */\n" +
                "    margin: 0 auto; /* Выравнивание по центру */\n" +
                "    background: #f0f0f0; /* Цвет фона левой колонки */}\n"+
                "    .card  {\n" +
                "    width: 250px;\n"+
                "    float: left;\n"+
                "    border: 2px solid #55c5e9; /* Рамка вокруг фотографии */\n" +
                "    padding: 15px; /* Расстояние от картинки до рамки */\n" +
                "    background: #666; /* Цвет фона */\n" +
                "    margin-right: 10px; /* Отступ справа */\n" +
                "    margin-bottom: 10px; /* Отступ снизу */\n" +
                "   }\n" +
                "   </style>\n" +
                "   </head>";
        htmlResponse += "<body>";
        try {
            String email = (String) session.getAttribute("user");
            if (searchEmail(email) != null) {
                User user = searchEmail(email);
                String s = user.getSex().equals("1")?"male":"female";
                String n = user.getNewsletter().equals("1")?"yes":"no";
                htmlResponse += "<div class=\"container\">";
                htmlResponse += "<div class=\"card\">";
                htmlResponse += "<h2>"+user.getName()+"</h2>";
                htmlResponse += "<p>"+"email:"+user.getEmail()+"</p>";
                htmlResponse += "<p>"+s+"</p>";
                htmlResponse += "<p>"+"newsletter:"+n+"</p>";
                htmlResponse += "<p>"+"Comment:<br>"+user.getComment()+"</p>";
                htmlResponse +="</div>";
                htmlResponse += "</div>";
                htmlResponse += " <form name=\"loginForm\" method=\"get\" action=\"/logout\">\n" +
                        "            <p><input type=\"submit\" value=\"exit\" /></p>\n" +
                        "        </form>";


            }
        } catch (DBException e) {
            htmlResponse += "<h1>Error!!!</h1>";
        }
        htmlResponse += "</body>";

        htmlResponse += "</html>";
        writer.println(htmlResponse);
        out.close();
    }
}
