package ru.kpfu.repositories;

import ru.kpfu.entities.Post;
import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static ru.kpfu.repositories.MysqlConnect.getDbCon;
import static ru.kpfu.repositories.MysqlConnect.insert;
import static ru.kpfu.repositories.MysqlConnect.query;

/**
 * Created by hp on 08.11.2015.
 */
public class PostRepositories {

    public static void addPost(Post post) {
        try {
            getDbCon();
        }catch (Exception sqle){
            sqle.getMessage();
        }
        try {

            insert("INSERT INTO posts(text,date,user_id) VALUE('"+post.getText()+"','"+post.getDatetime()+"',"+post.getUser_id()+")");

        }  catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    public static List<Post> getAllPosts() throws SQLException {
        getDbCon();
        ResultSet resultSet = null;
        try {
            resultSet = query("SELECT * FROM posts");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Post> posts = new LinkedList<>();
        while (resultSet.next()){


             Post post = new Post(resultSet.getInt("id"),resultSet.getString("text"),resultSet.getDate("date").toString()+"  " + resultSet.getTime("date").toString(), resultSet.getInt("user_id"));
            User user = UserRepositories.getUserById(resultSet.getInt("user_id"));
            if (user != null){
                post.setUser_name(user.getName());

                posts.add(post);
            }
        }
        return posts;
    }



}
