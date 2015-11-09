package ru.kpfu.servlets;

import ru.kpfu.entities.Post;
import ru.kpfu.entities.User;

import ru.kpfu.repositories.PostRepositories;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by hp on 08.11.2015.
 */

public class Posts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String text = request.getParameter("text");
        System.out.println(text);

        Post post = new Post(user.getId(),text);

        post.setUser_name(user.getName());

            PostRepositories.addPost(post);


        getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request,response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect("/login");
        }
        else {

            List<Post> posts = new ArrayList<>();
            try {
                posts = PostRepositories.getAllPosts();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("posts", posts);

            getServletContext().getRequestDispatcher("/WEB-INF/posts.jsp").forward(request,response); }
    }
}
