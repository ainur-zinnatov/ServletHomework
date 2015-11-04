package ru.kpfu.servlets;

import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DBException;
import ru.kpfu.exceptions.UserExistException;
import ru.kpfu.repositories.UserRepositories;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.kpfu.repositories.MysqlConnect.getDbCon;


/**
 * Created by hp on 07.10.2015.
 */
@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int sex = request.getParameter("sex").equals("1")?1:0;
        String newsletter = request.getParameter("newsletter");
        UserRepositories adduser = new UserRepositories();
        int subscr =1;
        if(newsletter==null || !newsletter.equals("1")){
            subscr =0;
        }
        User user = new User(name,email,password,sex,subscr);


        try {
            adduser.addUsers(user);
            response.sendRedirect(request.getRequestURI()+"?status=1");

        } catch (UserExistException userExistExeption) {
            request.setAttribute("message", userExistExeption.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/regist.jsp").forward(request, response);




        }  catch (DBException e) {
            request.setAttribute("message", "Data base exeption!");
            getServletContext().getRequestDispatcher("/WEB-INF/regist.jsp").forward(request,response);

        }

    }
    protected String replaceAllN(String text){
        for (int i = 0; i < text.length(); i++) {

        }
        return text;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("status") != null){
            if(request.getParameter("status").equals("1")){
                request.setAttribute("message", "User has been created.");
            }
        }
        HttpSession session = request.getSession();

        if(session.getAttribute("user")!=null){
            response.sendRedirect("/profile");}
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/regist.jsp").forward(request, response);
        }
    }


}
