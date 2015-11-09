package ru.kpfu.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.FileWriter;
import org.json.JSONObject;
import ru.kpfu.exceptions.SQLException;

import java.io.StringWriter;

/**
 * Created by hp on 04.11.2015.
 */
@WebServlet(name = "AjaxTestServlet")
public class AjaxTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textForPost = request.getParameter("text");
            String data = getJSON("post", textForPost);
            writeJSON("src/main/webapp/example.json",data);
            response.getWriter().write(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static void writeJSON(String file,String data){
        if (data == null){
            return;
        }
        try(FileWriter fw = new FileWriter(file)){
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String getJSON(String key, String value){
        try(StringWriter sWriter  = new StringWriter()) {
            JSONObject obj = new JSONObject();
            obj.put(key,value);
            obj.write(sWriter);
            return sWriter.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
