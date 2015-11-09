package ru.kpfu.entities;

/**
 * Created by hp on 08.11.2015.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int id;
    private String text;
    private String datetime;
    private int user_id;
    private String user_name;

    public Post(int id, String text, String datetime, int user_id) {
        this.id = id;
        this.text = text;
        this.datetime = datetime;
        this.user_id = user_id;
    }

    public Post(int user_id, String text) {
        this.user_id = user_id;
        this.text = text;
        this.datetime = currentDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    private static String currentDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
