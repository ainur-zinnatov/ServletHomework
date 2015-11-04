package ru.kpfu.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.kpfu.repositories.MysqlConnect.getDbCon;
import static ru.kpfu.repositories.MysqlConnect.insert;
import static ru.kpfu.repositories.MysqlConnect.query;

import ru.kpfu.exceptions.DBException;

/**
 * Created by hp on 24.10.2015.
 */
@WebServlet(name = "TestJDBS")
public class TestJDBS extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getDbCon();
        }catch (Exception sqle){
            sqle.getMessage();
        }
        try {
           insert("INSERT INTO users (id, email) VALUE (3,'azat@mail.ru')");

           ResultSet resultSet = query("SELECT * FROM users");
            while (resultSet.next()){
               int id=  resultSet.getInt(1);
               String email = resultSet.getString(2);
                System.out.println(id+":"+email);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
