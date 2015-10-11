package ru.kpfu.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by hp on 06.10.2015.
 */
@WebServlet(name = "useruard")
public class UserCard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String fileName ="C://Java/text.txt";
        File file = new File(fileName);
        exists(fileName);
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

            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {

                String s;
                String name="";
                String  emale="";
                String password="";
                String pol="";
                String newsletter="";
                while ((s = in.readLine()) != null) {
                    for (int i=0;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {name += s.charAt(i);}
                    }
                    for (int i=name.length()+1;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {emale += s.charAt(i);}
                    }
                    for (int i=emale.length()+name.length()+2;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {password += s.charAt(i);}
                    }
                    for (int i=name.length()+emale.length()+password.length()+3;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {pol += s.charAt(i);}
                    }
                    for (int i=name.length()+emale.length()+password.length()+pol.length()+4;i < s.length() ;i++ ) {


                       newsletter += s.charAt(i);

                    }
                  if(pol.equals("1")){
                  pol="male";
                   }else {pol="female";}
                  if(newsletter.equals("1")){
                    newsletter="yes";
                   }else {newsletter="no";}
                    htmlResponse += "<div class=\"container\">";
                    htmlResponse += "<div class=\"card\">";
                    htmlResponse += "<h2>"+name+"</h2>";
                    htmlResponse += "<p>"+"email:"+emale+"</p>";
                    htmlResponse += "<p>"+pol+"</p>";
                    htmlResponse += "<p>"+"newsletter:"+newsletter+"</p>";
                    htmlResponse +="</div>";
                    htmlResponse += "</div>";


                    name="";
                    emale="";
                    password="";
                    pol="";
                    newsletter="";
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        htmlResponse += "</body>";

        htmlResponse += "</html>";
        writer.println(htmlResponse);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
